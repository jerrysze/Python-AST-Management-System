package hk.ust.comp3021.misc;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class ASTKeyWord extends ASTElement {
    /*
     * keyword = (identifier? arg, expr value)
     * attributes (int lineno, int colOffset, int? endLineno, int? endColOffset)
     */
    private String arg;
    private ASTExpr value;
    private ArrayList<ASTElement> children = new ArrayList<>();


    public ASTKeyWord(XMLNode node) {
        super(node);
        arg = node.getAttribute("arg");
        value = ASTExpr.createASTExpr(node.getChildByIdx(0));
        getSelfChildren().add(value);
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
    public String getNodeType() {
        return "keyword";
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
