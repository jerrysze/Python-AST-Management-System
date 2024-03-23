package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;
import java.util.function.Consumer;

public class BoolOpExpr extends ASTExpr {
    // BoolOp(boolop op, expr* values)
    private ASTEnumOp op;
    private ArrayList<ASTExpr> values = new ArrayList<>();

    public void visitOp(Consumer<ASTEnumOp> func) {
        func.accept(op);
    }

    public BoolOpExpr(XMLNode node) {

        super(node);
        op = new ASTEnumOp(node.getChildByIdx(0));
        for (XMLNode n : node.getChildByIdx(1).getChildren()) {
            ASTExpr e = ASTExpr.createASTExpr(n);
            values.add(e);
            getSelfChildren().add(e);
        }
        exprType = ExprType.BoolOp;
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
