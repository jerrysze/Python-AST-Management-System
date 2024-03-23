package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;
import java.util.function.Consumer;

public class SubscriptExpr extends ASTExpr {
    // Subscript(expr value, expr slice, expr_context ctx)
    private ASTExpr value;
    private ASTExpr slice;
    private ASTEnumOp ctx;

    public void visitOp(Consumer<ASTEnumOp> func) {
        func.accept(ctx);
    }
    public SubscriptExpr(XMLNode node) {
        super(node);
        value = ASTExpr.createASTExpr(node.getChildByIdx(0));
        slice = ASTExpr.createASTExpr(node.getChildByIdx(1));
        ctx = new ASTEnumOp(node.getChildByIdx(2));
        getSelfChildren().add(value);
        getSelfChildren().add(slice);
        exprType = ExprType.Subscript;
    }

    @Override
    public void printByPos(StringBuilder str) {

    }



    @Override
    public int countChildren() {
        return super.countChildren();
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
