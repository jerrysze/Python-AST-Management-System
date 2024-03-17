package hk.ust.comp3021.utils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

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
        // TODO: complete the definition of the method `parse2XMLNode`
        try {
            List<String> lines = readXMLFile(xmlFileID);
            rootXMLNode = parseXMLNode(lines, 0, lines.size() - 1, null);
        } catch (IOException e) {
            isErr = true;
            e.printStackTrace();
        }
    }

    /**
     * Attention: You may need to define more methods to parse XML file
     * Feel free to define more method but remember not
     * (1) removing the fields or methods in our skeleton.
     * (2) changing the type signature of `public` methods
     * (3) changing the modifiers of the fields and methods, e.g., changing a modifier from "private" to "public"
     */
    private List<String> readXMLFile(String filePath) throws IOException {
        List<String> lines = new ArrayList<>();
        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        String line;
        while ((line = reader.readLine()) != null) {
            lines.add(line.trim());
        }
        reader.close();
        return lines;
    }
    private XMLNode parseXMLNode(List<String> lines, int startLine, int endLine, XMLNode parent) {
        if (startLine > endLine) {
            return null;
        }

        String startTag = lines.get(startLine);
        String endTag = lines.get(endLine);

        String tagName = startTag.replaceAll("<|>|/|\\s.*", "");
        XMLNode xmlNode = new XMLNode(tagName);

        if (startTag.contains("=")) {
            String[] attributes = startTag.split("\\s+");
            for (int i = 1; i < attributes.length; i++) {
                String attribute = attributes[i].trim();
                if (attribute.contains("=")) {
                    String[] keyValue = attribute.split("=");
                    String key = keyValue[0];
                    String value = keyValue[1].replaceAll("\"", "");
                    xmlNode.getAttributes().put(key, value);
                }
            }
        }

        if (startTag.endsWith("/>")) {
            return xmlNode;
        }

        int childStartLine = startLine + 1;
        int childEndLine = endLine - 1;
        while (!lines.get(childEndLine).equals(endTag)) {
            childEndLine--;
        }

        int childStartIndex = childStartLine;
        while (childStartIndex <= childEndLine) {
            String childTag = lines.get(childStartIndex);
            if (childTag.startsWith("<") && !childTag.startsWith("</")) {
                int childEndIndex = findClosingTagIndex(lines, childStartIndex, childEndLine);
                XMLNode childNode = parseXMLNode(lines, childStartIndex, childEndIndex, xmlNode);
                xmlNode.getChildren().add(childNode);
                childStartIndex = childEndIndex + 1;
            } else {
                childStartIndex++;
            }
        }

        xmlNode.setParent(parent);
        return xmlNode;
    }

    private int findClosingTagIndex(List<String> lines, int startIndex, int endIndex) {
        String startTag = lines.get(startIndex);
        String tagName = startTag.replaceAll("<|>|/|\\s.*", "");
        String endTag = "</" + tagName + ">";

        int depth = 1;
        for (int i = startIndex + 1; i <= endIndex; i++) {
            String line = lines.get(i);
            if (line.equals(startTag)) {
                depth++;
            } else if (line.equals(endTag)) {
                depth--;
                if (depth == 0) {
                    return i;
                }
            }
        }

        return endIndex;
    }
    public XMLNode getRootXMLNode(){
        return rootXMLNode;
    }

}
