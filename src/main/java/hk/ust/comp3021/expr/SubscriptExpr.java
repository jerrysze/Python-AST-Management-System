package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class SubscriptExpr extends ASTExpr {
    // Subscript(expr value, expr slice, expr_context ctx)
    private ASTExpr value;
    private ASTExpr slice;
    private ASTEnumOp ctx;

    public SubscriptExpr(XMLNode node) {
        // TODO: complete the definition of the constructor. Define the class as the subclass of ASTExpr.
        super(node);
        this.exprType = ExprType.Subscript;
        // Get the value node
        XMLNode valueNode = node.getChildByIdx(0);
        if (valueNode != null) {
            this.value = ASTExpr.createASTExpr(valueNode);
        }

        // Get the slice node
        XMLNode sliceNode = node.getChildByIdx(1);
        if (sliceNode != null) {
            this.slice = ASTExpr.createASTExpr(sliceNode);
        }

        // Get the ctx node
        XMLNode ctxNode = node.getChildByIdx(2);
        if (ctxNode != null) {
            this.ctx = new ASTEnumOp(ctxNode);
        }
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(value);
        children.add(slice);
        children.add(ctx);
        return children;
    }
    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        return 2 + this.slice.countChildren() + this.value.countChildren();
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
