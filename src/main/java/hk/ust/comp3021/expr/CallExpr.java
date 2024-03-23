package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class CallExpr extends ASTExpr {
    // Call(expr func, expr* args, keyword* keywords)
    private ASTExpr func;
    private ArrayList<ASTExpr> args = new ArrayList<>();
    private ArrayList<ASTKeyWord> keywords = new ArrayList<>();
    private int lineNo;

    @Override
    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }
    public String getMangle(String astID) {
        return astID + "_" + getCalledFuncName() + "_" + lineNo;
    }


    public CallExpr(XMLNode node) {
        super(node);
        setLineNo(Integer.parseInt(node.getAttribute("lineno")));
        func = ASTExpr.createASTExpr(node.getChildByIdx(0));
        getSelfChildren().add(func);
        for (XMLNode n : node.getChildByIdx(1).getChildren()) {
            ASTExpr e = ASTExpr.createASTExpr(n);
            args.add(e);
            getSelfChildren().add(e);
        }
        for (XMLNode n : node.getChildByIdx(2).getChildren()) {
            ASTKeyWord e = new ASTKeyWord(n);
            keywords.add(e);
            getSelfChildren().add(e);
        }
        exprType = ExprType.Call;
    }

    /*
     * Find all paths from func node to node with class type Name, which contain several cases
     * (1) if the path is func -> Attribute (attr: b) -> Name (id: self), then the name is self.b
     * (2) if the path is func -> Attribute (attr: b) -> Attribute (attr: a) -> Name (id: self), then the name is self.a.b
     * (3) if the path is func -> Name (id: bubbleSort), then the name is bubbleSort
     * @return: name of called function
     */
    public String getCalledFuncName() {
        String ret = "";
        ASTExpr ae = func;
        while (ae instanceof AttributeExpr) {
            AttributeExpr attributeExpr = (AttributeExpr) ae;
            ret = ret + "." + attributeExpr.getAttr();
            ae = attributeExpr.getValue();
        }
        if (ae instanceof NameExpr) {
            ret = ((NameExpr) ae).getId() + ret;
        }
        return ret;
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
