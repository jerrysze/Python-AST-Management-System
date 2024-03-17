package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class ClassDefStmt extends ASTStmt {
    /*
     * ClassDef(identifier name,
     *         expr* bases,
     *         keyword* keywords,
     *         stmt* body,
     *         expr* decorator_list,...)
     */
    private String name;
    private ArrayList<ASTExpr> bases = new ArrayList<>();
    private ArrayList<ASTKeyWord> keywords = new ArrayList<>();
    private ArrayList<ASTStmt> body = new ArrayList<>();
    private ArrayList<ASTExpr> decoratorList = new ArrayList<>();

    public ClassDefStmt(XMLNode node) {
        // TODO: complete the definition of the constructor. Define the class as the subclass of ASTExpr.
        super(node);
        this.stmtType = StmtType.ClassDef;
        this.name = node.getAttribute("name");
        XMLNode basesNode = node.getChildByIdx(0);
        if (basesNode != null) {
            for (XMLNode child : basesNode.getChildren()) {
                this.bases.add(ASTExpr.createASTExpr(child));
            }
        }

        XMLNode keywordsNode = node.getChildByIdx(1);
        if (keywordsNode != null) {
            for (XMLNode child : keywordsNode.getChildren()) {
                this.keywords.add(new ASTKeyWord(child));
            }
        }

        XMLNode bodyNode = node.getChildByIdx(2);
        if (bodyNode != null) {
            for (XMLNode child : bodyNode.getChildren()) {
                this.body.add(ASTStmt.createASTStmt(child));
            }
        }

        XMLNode decoratorListNode = node.getChildByIdx(3);
        if (decoratorListNode != null) {
            this.decoratorList.add(ASTExpr.createASTExpr(decoratorListNode));
            for (XMLNode child : decoratorListNode.getChildren()) {
                this.decoratorList.add(ASTExpr.createASTExpr(child));
            }
        }
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        ArrayList<ASTElement> children = new ArrayList<>(bases);
        children.addAll(keywords);
        children.addAll(body);
        children.addAll(decoratorList);
        return children;
    }

    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        return bases.size() + keywords.size() + body.size() + decoratorList.size();
    }

    @Override
    public void printByPos(StringBuilder str) {
        // TODO: (Bonus) complete the definition of the method `printByPos`
        str.append("class ");
        str.append(name);
        if (!bases.isEmpty()) {
            str.append("(");
            for (int i = 0; i < bases.size(); i++) {
                if (i > 0) {
                    str.append(", ");
                }
                bases.get(i).printByPos(str);
            }
            str.append(")");
        }
        str.append(":\n");
        for (ASTStmt stmt : body) {
            stmt.printByPos(str);
        }
    }

    /**
     * Attention: You may need to define more methods to update or access the field
     * of the class ASTStmt, i.e., getters or setters Feel free to define more
     * method but remember not
     * (1) removing the fields or methods in our skeleton.
     * (2) changing the type signature of `public` methods
     * (3) changing the modifiers of the fields and methods, e.g., changing a modifier from "private"
     * to "public"
     */

}
