package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class ListExpr extends ASTExpr {
    // List(expr* elts, expr_context ctx)
    private ArrayList<ASTExpr> elts = new ArrayList<>();
    private ASTEnumOp ctx;
    public ListExpr(XMLNode node) {
        // TODO: complete the definition of the constructor. Define the class as the subclass of ASTExpr.
        super(node);
        this.exprType = ExprType.List;

        // Get the elts nodes
        XMLNode eltsNode = node.getChildByIdx(0);
        if (eltsNode != null) {
            for (XMLNode child : eltsNode.getChildren()) {
                this.elts.add(ASTExpr.createASTExpr(child));
            }
        }

        // Get the ctx node
        XMLNode ctxNode = node.getChildByIdx(1);
        if (ctxNode != null) {
            this.ctx = new ASTEnumOp(ctxNode);
        }
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        ArrayList<ASTElement> children = new ArrayList<>(elts);
        children.add(ctx);
        return children;
    }

    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        return this.elts.size()+1;
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
