package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;
//import java.util.function.Consumer;

public class ListExpr extends ASTExpr {
    // List(expr* elts, expr_context ctx)
    private ArrayList<ASTExpr> elts = new ArrayList<>();
    private ASTEnumOp ctx;
    public ListExpr(XMLNode node) {
        super(node);
        for (XMLNode n : node.getChildByIdx(0).getChildren()) {
            ASTExpr e = ASTExpr.createASTExpr(n);
            getSelfChildren().add(e);
            elts.add(e);
        }
        ctx = new ASTEnumOp(node.getChildByIdx(1));
        exprType = ExprType.List;
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
