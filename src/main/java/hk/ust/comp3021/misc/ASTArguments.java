package hk.ust.comp3021.misc;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class ASTArguments extends ASTElement {
    public class ASTArg extends ASTElement {
        /*
         * arg = (identifier arg, expr? annotation, ...)
         *       attributes (int lineno, int colOffset, int? endLineno, int? endColOffset)
         */
        private String arg;
        private ASTExpr annotation;

        public ASTArg(XMLNode node) {
            super(node);
            arg = node.getAttribute("arg");
            if (node.getChildren().size() > 0) {
                annotation = ASTExpr.createASTExpr(node.getChildByIdx(0));
                getSelfChildren().add(annotation);
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
            // TODO: (Bonus) complete the definition of the method `printByPos`
        }

        @Override
        public String getNodeType() {
            return "arg";
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
    /*
     * arguments = (.., arg* args, ..., expr* defaults)
     */

    private ArrayList<ASTArg> args = new ArrayList<>();
    private ArrayList<ASTExpr> defaults = new ArrayList<>();

    public ASTArguments(XMLNode node) {
        super(node);


        for (XMLNode n: node.getChildByIdx(1).getChildren()) {
            ASTArg arg = new ASTArg(n);
            getSelfChildren().add(arg);
            args.add(arg);
        }
        for (XMLNode n: node.getChildByIdx(4).getChildren()) {
            ASTExpr arg = ASTExpr.createASTExpr(n);
            getSelfChildren().add(arg);
            defaults.add(arg);
        }
    }


    /*
    * Return the number of ASTArg child nodes
    */
    public int getParamNum() {
        return args.size();
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
        // TODO: (Bonus) complete the definition of the method `printByPos`
    }

    @Override
    public String getNodeType() {
        return "arguments";
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
