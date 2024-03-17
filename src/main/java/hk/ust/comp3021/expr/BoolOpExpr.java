package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class BoolOpExpr extends ASTExpr {
    // BoolOp(boolop op, expr* values)
    private ASTEnumOp op;
    private ArrayList<ASTExpr> values = new ArrayList<>();

    public BoolOpExpr(XMLNode node) {
        // TODO: complete the definition of the constructor. Define the class as the subclass of ASTExpr.
        super(node);
        this.exprType = ExprType.BoolOp;
        op = new ASTEnumOp(node.getChildByIdx(0));
        // value
        XMLNode valuesNode = node.getChildByIdx(1);
        if (valuesNode != null) {
            for (XMLNode child : valuesNode.getChildren()) {
                values.add(ASTExpr.createASTExpr(child));
            }
        }
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        return new ArrayList<ASTElement>(this.values);
    }
    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        int numChild = this.values.size();
        for(ASTExpr child : this.values){
            numChild += child.countChildren();
        }
        return numChild;
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
