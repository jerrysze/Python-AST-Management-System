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
            // TODO: complete the definition of the constructor. Define the class as the subclass of ASTElement.
            super(node);
            this.arg = node.getAttribute("arg");
            this.annotation = ASTExpr.createASTExpr(node.getChildByIdx(0));
        }

        @Override
        public ArrayList<ASTElement> getChildren() {
            // TODO: complete the definition of the method `getChildren`
            ArrayList<ASTElement> children = new ArrayList<>();
            children.add(annotation);
            return children;
        }

        @Override
        public int countChildren() {
            // TODO: complete the definition of the method `countChildren`
            return 1 + this.annotation.countChildren();
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
        // TODO: complete the definition of the constructor. Define the class as the subclass of ASTElement.
        super(node);
        for(XMLNode child : node.getChildByIdx(0).getChildren())
        {
            args.add(new ASTArg(child));
        }
        for(XMLNode child : node.getChildByIdx(1).getChildren())
        {
            defaults.add(ASTExpr.createASTExpr(child));
        }
    }


    /*
    * Return the number of ASTArg child nodes
    */
    public int getParamNum() {
        // TODO: complete the definition of the method `getParamNum`
        int paraNum = args.size();
        for (ASTArg child : this.args) {
            paraNum += child.countChildren();
        }
        return paraNum;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        ArrayList<ASTElement> children= new ArrayList<>();
        children.addAll(args);
        children.addAll(defaults);
        return children;
    }

    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        int count = defaults.size() + args.size();
        for (ASTExpr child : defaults) {
            count += child.countChildren();
        }
        for (ASTArg child : args) {
            count += child.countChildren();
        }
        return count;
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
    public ArrayList<ASTArg> getArgs() {
        return args;
    }

    public ArrayList<ASTExpr> getDefaults() {
        return defaults;
    }

}
