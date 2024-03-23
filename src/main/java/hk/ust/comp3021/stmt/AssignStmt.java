package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class AssignStmt extends ASTStmt {
    // Assign(expr* targets, expr value, ...)
    private ArrayList<ASTExpr> targets = new ArrayList<>();
    private ASTExpr value;

    public AssignStmt(XMLNode node) {
        super(node);
        for (XMLNode n : node.getChildByIdx(0).getChildren()) {
            ASTExpr e = ASTExpr.createASTExpr(n);
            getSelfChildren().add(e);
            targets.add(e);
        }
        value = ASTExpr.createASTExpr(node.getChildByIdx(1));
        getSelfChildren().add(value);
        stmtType = StmtType.Assign;
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
