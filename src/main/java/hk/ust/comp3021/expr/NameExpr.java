package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;
import java.util.function.Consumer;

public class NameExpr extends ASTExpr {
    // Name(identifier id, expr_context ctx)
    private String id;
    private ASTEnumOp ctx;

    public NameExpr(XMLNode node)  {
        super(node);
        id = node.getAttribute("id");
        XMLNode sub = node.getChildByIdx(0);
        ctx = new ASTEnumOp(sub);
        exprType = ExprType.Name;
    }

    public void visitOp(Consumer<ASTEnumOp> func) {
        func.accept(ctx);
    }

    public String getId() {
        return id;
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
