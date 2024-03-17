package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.ArrayList;

public class ConstantExpr extends ASTExpr {
    // Constant(constant value, string? kind)
    private String value;
    private String kind;

    public ConstantExpr(XMLNode node) {
        // TODO: complete the definition of the constructor. Define the class as the subclass of ASTExpr.
        super(node);
        this.kind = node.getAttribute("kind");
        this.value = node.getAttribute("value");
        this.exprType = ExprType.Constant;
    }

    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        return 1;
        // no child
    }
    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        return new ArrayList<>();
        // not child
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
