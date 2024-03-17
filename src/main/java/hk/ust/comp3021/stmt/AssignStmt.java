package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class AssignStmt extends ASTStmt {
    // Assign(expr* targets, expr value, ...)
    private ArrayList<ASTExpr> targets = new ArrayList<>();
    private ASTExpr value;

    public AssignStmt(XMLNode node) {
        // TODO: complete the definition of the constructor. Define the class as the subclass of ASTStmt.
        super(node);
        this.stmtType = StmtType.Assign;
        // Extract the targets
        XMLNode targetsNode = node.getChildByIdx(0);
        if (targetsNode != null) {
            List<XMLNode> targetNodes = targetsNode.getChildren();
            for (XMLNode targetNode : targetNodes) {
                ASTExpr target = ASTExpr.createASTExpr(targetNode);
                targets.add(target);
            }
        }
        // Extract the value
        XMLNode valueNode = node.getChildByIdx(1);
        if (valueNode != null) {
            value = ASTExpr.createASTExpr(valueNode);
        }
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        ArrayList<ASTElement> children = new ArrayList<>(targets);
        children.add(value);
        return children;
    }

    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        int numChild = 0;
        for (ASTExpr target : targets) {
            numChild += target.countChildren();
        }
        if (value != null) {
            numChild += value.countChildren();
        }
        return numChild + targets.size() + 1;
    }
    @Override
    public void printByPos(StringBuilder str) {
        // TODO: (Bonus) complete the definition of the method `printByPos`
        if (!targets.isEmpty()) {
            for (ASTExpr target : targets) {
                target.printByPos(str);
                str.append(", ");
            }
            str.setLength(str.length() - 2); // Remove the extra comma and space
            str.append(" = ");
        }
        if (value != null) {
            value.printByPos(str);
        }
    }

    /**
     * Attention: You may need to define more methods to update or access the field
     * of the class ASTStmt, i.e., getters or setters Feel free to define more
     * method but remember not
     * (1) removing the fields or methods in our skeleton.
     * (2) changing the type signature of `public` methods
     * (3) changing the modifiers of the fields and methods, e.g., changing a modifier from "private"
     * to "public"
     */
    @Override
    public void yourMethod() {
    }

}
