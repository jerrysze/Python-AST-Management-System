package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;
import java.util.function.Consumer;

public class AugAssignStmt extends ASTStmt {
    // AugAssign(expr target, operator op, expr value)
    private ASTExpr target;
    private ASTEnumOp op;
    private ASTExpr value;


    @Override
    public void visitOp(Consumer<ASTEnumOp> func) {
        func.accept(op);
    }

    public AugAssignStmt(XMLNode node) {

        super(node);
        target = ASTExpr.createASTExpr(node.getChildByIdx(0));
        op = new ASTEnumOp(node.getChildByIdx(1));
        value = ASTExpr.createASTExpr(node.getChildByIdx(2));
        stmtType = StmtType.AugAssign;
        getSelfChildren().add(target);
        getSelfChildren().add(value);
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
