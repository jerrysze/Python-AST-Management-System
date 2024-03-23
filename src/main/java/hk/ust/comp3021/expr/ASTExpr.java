package hk.ust.comp3021.expr;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;

import java.util.ArrayList;

public abstract class ASTExpr extends ASTElement {
    enum ExprType {
        BoolOp, BinOp, UnaryOp, Compare, Call, Constant, Attribute,
        Subscript, Name, List, Tuple
    }

    protected ExprType exprType;

    public ASTExpr(XMLNode node) {
        super(node);
    }

    @Override
    public String getNodeType() {
        return this.exprType.name();
    }

    /**
     * Create ASTExpr from the XNL Node based on the tag name
     *
     * @param node: the XML Node from which to generate ASTExpr
     * @return: created ASTExpr
     *
     * You may need to remove the `return null` from the skeleton.
     */
    public static ASTExpr createASTExpr(XMLNode node) {
        switch (node.getTagName()) {
            case "Attribute":
                return new AttributeExpr(node);
            case "BinOp":
                return new BinOpExpr(node);
            case "BoolOp":
                return new BoolOpExpr(node);
            case "Call":
                return new CallExpr(node);
            case "Compare":
                return new CompareExpr(node);
            case "Constant":
                return new ConstantExpr(node);
            case "List":
                return new ListExpr(node);
            case "Name":
                return new NameExpr(node);
            case "Subscript":
                return new SubscriptExpr(node);
            case "Tuple":
                return new TupleExpr(node);
            case "UnaryOp":
                return new UnaryOpExpr(node);
            default:
                throw new UnsupportedOperationException("" + node.getTagName());
        }
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
