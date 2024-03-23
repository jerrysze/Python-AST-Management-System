package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.XMLNode;
import java.util.*;

public class ReturnStmt extends ASTStmt {
    // Return(expr? value)
    private ASTExpr value = null;
    public ReturnStmt(XMLNode node) {
        super(node);
        this.stmtType = ASTStmt.StmtType.Return;
        if (!node.hasAttribute("value")) {
            value = ASTExpr.createASTExpr(node.getChildByIdx(0));
            getSelfChildren().add(value);
        }

    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        return super.getChildren();
    }

    @Override
    public int countChildren() {
        return super.countChildren();
    }
    @Override
    public void printByPos(StringBuilder str) {
        this.fillStartBlanks(str);
        str.append("return");
        if (value != null) {
            value.printByPos(str);
        }
        this.fillEndBlanks(str);
    }
}
