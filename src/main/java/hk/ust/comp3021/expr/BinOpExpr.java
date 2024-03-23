package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;
import java.util.function.Consumer;

public class BinOpExpr extends ASTExpr {
    // BinOp(expr left, operator op, expr right)
    private ASTExpr left;
    private ASTEnumOp op;
    private ASTExpr right;

    public void visitOp(Consumer<ASTEnumOp> func) {
        func.accept(op);
    }


    public BinOpExpr(XMLNode node) {
        super(node);
        left = ASTExpr.createASTExpr(node.getChildByIdx(0));
        op = new ASTEnumOp(node.getChildByIdx(1));
        right = ASTExpr.createASTExpr(node.getChildByIdx(2));
        getSelfChildren().add(left);
        getSelfChildren().add(right);
        exprType = ExprType.BinOp;
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
     * Attention: You may need to define more methods to update or access the field of the class `User`
     * Feel free to define more method but remember not
     * (1) removing the fields or methods in our skeleton.
     * (2) changing the type signature of `public` methods
     * (3) changing the modifiers of the fields and methods, e.g., changing a modifier from "private" to "public"
     */
    public void yourMethod() {

    }

}
