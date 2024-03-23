package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.XMLNode;
import java.util.*;
import java.util.function.Consumer;

public class AttributeExpr extends ASTExpr {
    // Attribute(expr value, identifier attr, expr_context ctx)
    private ASTExpr value;
    private String attr;
    private ASTEnumOp ctx;

    @Override
    public void visitOp(Consumer<ASTEnumOp> func) {
        func.accept(ctx);
    }

    public AttributeExpr(XMLNode node) {
        super(node);
        value = ASTExpr.createASTExpr(node.getChildByIdx(0));
        attr = node.getAttribute("attr");
        ctx = new ASTEnumOp(node.getChildByIdx(1));
        getSelfChildren().add(value);
        exprType = ExprType.Attribute;
    }

    public ASTExpr getValue() {
        return value;
    }

    public String getAttr() {
        return attr;
    }

    public ASTEnumOp getCtx() {
        return ctx;
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
