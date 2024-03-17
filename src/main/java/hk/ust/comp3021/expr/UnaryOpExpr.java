package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class UnaryOpExpr extends ASTExpr {
    // UnaryOp(unaryop op, expr operand)
    private ASTEnumOp op;
    private ASTExpr operand;

    public UnaryOpExpr(XMLNode node) {
        // TODO: complete the definition of the constructor. Define the class as the subclass of ASTExpr.
        super(node);
        this.exprType = ExprType.UnaryOp;
        // Get the op node
        XMLNode opNode = node.getChildByIdx(0);
        if (opNode != null) {
            this.op = new ASTEnumOp(opNode);
        }

        // Get the operand node
        XMLNode operandNode = node.getChildByIdx(1);
        if (operandNode != null) {
            this.operand = ASTExpr.createASTExpr(operandNode);
        }
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(op);
        children.add(operand);
        return children;
    }

    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        return 1 + operand.countChildren();
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
