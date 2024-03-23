package hk.ust.comp3021.utils;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

public class ASTParser {
    private final String xmlFileID;
    private boolean isErr;
    private XMLNode rootXMLNode;
    private ASTModule rootASTModule;

    public ASTParser(String xmlFileID) {
        this.xmlFileID = xmlFileID;

        this.isErr = false;
        this.rootXMLNode = null;
        this.rootASTModule = null;
    }

    public boolean isErr() {
        return isErr;
    }

    public ASTModule getASTModule() {
        return rootASTModule;
    }

    public void parse() {
        // parse the XML Tree into rootXMLNode
        parse2XMLNode();
        // obtain the module node as the first child of ast node
        rootXMLNode = rootXMLNode.getChildByIdx(0);
        // create AST Tree and return the root node ASTModule
        rootASTModule = new ASTModule(rootXMLNode, xmlFileID);
    }

    /**
     * 1. Parse the XML Tree inside given XML File whose path is `xmlFilePath`
     * 2. Initialize the rootXMLNode as the root node of XML Tree, the tag Name of rootXMLNode should be ast
     * 3. If any exception throws, please set the field `isErr` to true. Otherwise, `isErr` is false.
     *
     * Hints:
     * For the following XML snippet:
     * <Assign type_comment="None" lineno="4" col_offset="8" end_lineno="4" end_col_offset="19">
     *     <targets>
     *         <Name id="tail" lineno="4" col_offset="8" end_lineno="4" end_col_offset="12">
     *             <Store/>
     *         </Name>
     *     </targets>
     *     <Constant value="None" kind="None" lineno="4" col_offset="15" end_lineno="4" end_col_offset="19"/>
     * </Assign>
     *
     * There are five XML nodes in total. Each XMLNode has two fields, i.e., attributes and children.
     * Attributes are key-value pairs. For instance, for xml node whose tag is Assign, the key-value pairs contains
     * `id: tail`. Children are a list of XMLNode, for instance, for Assign node, it has two children (targets, Constant).
     *
     * Noticed that in each line, there could be a self-closing tag, e.g., <Store/>, or a closing tag, e.g., </Name>, or
     * an opening tag <Name>. Please carefully organize the parent-children relation and initialize the attributes.
     *
     */
    public void parse2XMLNode() {
        String filename = "resources/pythonxml/python_" + xmlFileID + ".xml";
        Parser parser = null;
        try {
            BufferedInputStream bis = new BufferedInputStream(new FileInputStream(filename));
            parser = new Parser(bis);
            rootXMLNode = parser.parseNode();
            isErr = false;
        } catch (IOException | RuntimeException e) {
            isErr = true;
            // e.printStackTrace();
            // throw new RuntimeException("Error parsing XML file: " + e.getMessage() + " " + parser.getLine() + ", " + parser.getCol());
        }
    }

    /**
     * Attention: You may need to define more methods to parse XML file
     * Feel free to define more method but remember not
     * (1) removing the fields or methods in our skeleton.
     * (2) changing the type signature of `public` methods
     * (3) changing the modifiers of the fields and methods, e.g., changing a modifier from "private" to "public"
     */
    public void yourMethod() {

    }

    public XMLNode getRootXMLNode() {
        return rootXMLNode;
    }

    static class Parser {
        private int currentChar;
        private BufferedInputStream bis;
        private int line = 1;
        private int col = 1;

        Parser(BufferedInputStream bis) {
            this.bis = bis;
        }

        public int getLine() {
            return line;
        }

        public void setLine(int line) {
            this.line = line;
        }

        public int getCol() {
            return col;
        }

        public void setCol(int col) {
            this.col = col;
        }

        public int nextChar() throws IOException {
            currentChar = bis.read();
            if (currentChar == '\n') {
                line++;
                col = 1;
            } else {
                col++;
            }
            return currentChar;
        }

        public int peekChar() throws IOException {
            bis.mark(1);
            int c = bis.read();
            bis.reset();
            return c;
        }

        public void skip() throws IOException {
            while (Character.isWhitespace(peekChar())) {
                nextChar();
            }
        }

        public void expectLAngle() throws IOException {
            skip();
            if (nextChar() != '<') {
                throw new RuntimeException("Expected '<'");
            }
        }

        public void expectRAngle() throws IOException {
            skip();
            if (nextChar() != '>') {
                throw new RuntimeException("Expected '>'");
            }
        }

        public void expectCRAngle() throws IOException {
            skip();
            if (nextChar() != '/') {
                throw new RuntimeException("Expected '/'");
            }
            expectRAngle();
        }

        public String expectIdentifier() throws IOException {
            skip();
            StringBuilder sb = new StringBuilder();
            while (Character.isLetterOrDigit(peekChar()) || peekChar() == '_') {
                try {
                    sb.append((char) nextChar());
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
            return sb.toString();
        }

        public void expectEq() throws IOException {
            skip();
            if (nextChar() != '=') {
                throw new RuntimeException("Expected '='");
            }
        }

        public String expectString() throws IOException {
            skip();
            StringBuilder sb = new StringBuilder();
            if (nextChar() != '\"') {
                throw new RuntimeException("Expected '\"'");
            }
            while (peekChar() != '\"') {
                sb.append((char) nextChar());
            }
            nextChar(); // consume the closing quote
            return sb.toString();
        }

        public void parseAttributeList(XMLNode target) throws IOException {
            skip();
            while (peekChar() != '>' && peekChar() != '/') {
                String key = expectIdentifier();
                expectEq();
                String value = expectString();
                target.getAttributes().put(key, value);
                skip();
            }
        }

        public XMLNode parseNode1() throws IOException {
            int lineNo = line;
            String tagName = expectIdentifier();
            XMLNode node = new XMLNode(tagName);
            node.setLineNo(line);
            skip();
            parseAttributeList(node);
            if (peekChar() == '/') {
                expectCRAngle();
            } else {
                expectRAngle();
                skip();
                while (peekChar() == '<') {
                    nextChar();
                    skip();
                    if (peekChar() == '/') {
                        break;
                    }
                    XMLNode n = parseNode1();
                    node.addChild(n);
                    skip();
                }
                expectCRAngle1(tagName);
            }
            return node;
        }

        private void expectCRAngle1(String tag) throws IOException {
            if (nextChar() != '/') {
                throw new RuntimeException("Expected '/' '" + tag + ">'");
            }
            String endTag = expectIdentifier();
            if (!endTag.equals(tag)) {
                throw new RuntimeException("Mismatched end tag. Expected '</" + tag + ">', but got '</" + endTag + ">'");
            }
            expectRAngle();
        }

        public XMLNode parseNode() throws IOException {
            expectLAngle();
            return parseNode1();
        }

        public void expectCRAngle(String tag) throws IOException {
            expectLAngle();
            if (nextChar() != '/') {
                throw new RuntimeException("Expected '/'");
            }
            String endTag = expectIdentifier();
            if (!endTag.equals(tag)) {
                throw new RuntimeException("Mismatched end tag. Expected '</" + tag + ">', but got '</" + endTag + ">'");
            }
            expectRAngle();
        }
    }
}
