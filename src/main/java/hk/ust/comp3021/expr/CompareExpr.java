package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;


public class CompareExpr extends ASTExpr {
    // Compare(expr left, cmpop* ops, expr* comparators)
    private ASTExpr left;
    private ArrayList<ASTEnumOp> ops = new ArrayList<>();
    private ArrayList<ASTExpr> comparators = new ArrayList<>();

    public CompareExpr(XMLNode node) {
        // TODO: complete the definition of the constructor. Define the class as the subclass of ASTExpr.
        super(node);
        this.exprType = ExprType.Compare;

        XMLNode leftNode = node.getChildByIdx(0);
        if (leftNode != null) {
            this.left = ASTExpr.createASTExpr(leftNode);
        }

        XMLNode opsNode = node.getChildByIdx(1);
        if (opsNode != null) {
            for (XMLNode child : opsNode.getChildren()) {
                this.ops.add(new ASTEnumOp(child));
            }
        }

        XMLNode comparatorsNode = node.getChildByIdx(2);
        if (comparatorsNode != null) {
            for (XMLNode child : comparatorsNode.getChildren()) {
                this.comparators.add(ASTExpr.createASTExpr(child));
            }
        }
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        ArrayList<ASTElement> children = new ArrayList<>();
        children.add(left);
        children.addAll(comparators);
        return children;
    }

    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        int numChild = 1 + comparators.size();
        numChild += left.countChildren();
        for(ASTExpr child : comparators){
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
