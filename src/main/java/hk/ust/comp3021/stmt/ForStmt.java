package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.ArrayList;


public class ForStmt extends ASTStmt {
    //  For(expr target, expr iter, stmt* body, stmt* orelse, ...)
    private ASTExpr target;
    private ASTExpr iter;
    private ArrayList<ASTStmt> body = new ArrayList<>();
    private ArrayList<ASTStmt> orelse = new ArrayList<>();
    public ForStmt(XMLNode node) {
        super(node);


        target = ASTExpr.createASTExpr(node.getChildByIdx(0));
        getSelfChildren().add(target);
        iter = ASTExpr.createASTExpr(node.getChildByIdx(1));
        getSelfChildren().add(iter);
        for (XMLNode n : node.getChildByIdx(2).getChildren()) {
            ASTStmt st = ASTStmt.createASTStmt(n);
            body.add(st);
            getSelfChildren().add(st);
        }
        for (XMLNode n : node.getChildByIdx(3).getChildren()) {
            ASTStmt st = ASTStmt.createASTStmt(n);
            orelse.add(st);
            getSelfChildren().add(st);
        }
        stmtType = StmtType.For;
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
