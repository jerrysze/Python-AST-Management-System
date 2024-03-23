package hk.ust.comp3021.stmt;

import hk.ust.comp3021.expr.*;
import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.utils.*;
import java.util.*;

public class FunctionDefStmt extends ASTStmt {
    /*
     * FunctionDef(identifier name, arguments args, stmt* body, expr*
     * decorator_list, expr? returns, ..)
     */
    private String name;
    private ASTArguments args;
    private ArrayList<ASTStmt> body = new ArrayList<>();
    private ArrayList<ASTExpr> decoratorList = new ArrayList<>();
    private ASTExpr returns = null;

    private int lineNo = 0;

    @Override
    public int getLineNo() {
        return lineNo;
    }

    public void setLineNo(int lineNo) {
        this.lineNo = lineNo;
    }

    public String getMangle(String astID) {
        return astID + "_" + getName() + "_" + lineNo;
    }


    public FunctionDefStmt(XMLNode node) {
        super(node);
        name = node.getAttribute("name");
        args = new ASTArguments(node.getChildByIdx(0));
        getSelfChildren().add(args);
        setLineNo(Integer.parseInt(node.getAttribute("lineno")));
        for (XMLNode n : node.getChildByIdx(1).getChildren()) {
            ASTStmt st = ASTStmt.createASTStmt(n);
            body.add(st);
            getSelfChildren().add(st);
        }
        for (XMLNode n : node.getChildByIdx(2).getChildren()) {
            ASTExpr e = ASTExpr.createASTExpr(n);
            getSelfChildren().add(e);
            decoratorList.add(e);
        }
        if (node.getChildren().size() > 3) {
            ASTExpr e = ASTExpr.createASTExpr(node.getChildByIdx(3));
            getSelfChildren().add(e);
            returns = e;
        }

        stmtType = StmtType.FunctionDef;
    }

    /*
     * Find all AST node whose class type is `CallExpr` shown in the AST
     * Hints: you need to traverse all the nodes in AST and check its class type.
     * We have prepared the method `getChildren` for you to ease the traversal.
     * You may need to remove the `return null` in the skeleton.
     * */
    public ArrayList<CallExpr> getAllCalledFunc() {
         return getSelectedChildren(CallExpr.class);
    }

    public int getParamNum() {
        return args.getParamNum();
    }

    public String getName() {
        return name;
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
