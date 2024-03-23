package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.ArrayList;

public class WhileStmt extends ASTStmt {
    // While(expr test, stmt* body, stmt* orelse)
    private ASTExpr test;
    private ArrayList<ASTStmt> body = new ArrayList<>();
    private ArrayList<ASTStmt> orelse = new ArrayList<>();

    public WhileStmt(XMLNode node) {
        super(node);
        test = ASTExpr.createASTExpr(node.getChildByIdx(0));
        getSelfChildren().add(test);
        for (XMLNode n : node.getChildByIdx(1).getChildren()) {
            ASTStmt st = ASTStmt.createASTStmt(n);
            body.add(st);
            getSelfChildren().add(st);
        }
        for (XMLNode n : node.getChildByIdx(2).getChildren()) {
            ASTStmt st = ASTStmt.createASTStmt(n);
            orelse.add(st);
            getSelfChildren().add(st);
        }
        stmtType = StmtType.While;
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
