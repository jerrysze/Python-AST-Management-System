package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

import java.awt.image.Kernel;
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
        super(node);

        name = node.getAttribute("name");
        for (XMLNode n : node.getChildByIdx(0).getChildren()) {
            ASTExpr e = ASTExpr.createASTExpr(n);
            getSelfChildren().add(e);
            bases.add(e);
        }

        for (XMLNode n : node.getChildByIdx(1).getChildren()) {
            ASTKeyWord kw = new ASTKeyWord(n);
            keywords.add(kw);
            getSelfChildren().add(kw);
        }

        for (XMLNode n : node.getChildByIdx(2).getChildren()) {
            ASTStmt st = ASTStmt.createASTStmt(n);
            body.add(st);
            getSelfChildren().add(st);
        }

        for (XMLNode n : node.getChildByIdx(3).getChildren()) {
            ASTExpr e = ASTExpr.createASTExpr(n);
            getSelfChildren().add(e);
            decoratorList.add(e);
        }
        stmtType = StmtType.ClassDef;
    }



    @Override
    public ArrayList<ASTElement> getChildren() {
        return super.getChildren();
    }

    @Override
    public int countChildren() {
        return super.countChildren();
    }

    @Override
    public void printByPos(StringBuilder str) {
        // TODO: (Bonus) complete the definition of the method `printByPos`
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
    @Override
    public void yourMethod() {
    }
}
