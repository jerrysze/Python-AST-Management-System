package hk.ust.comp3021.utils;

import hk.ust.comp3021.misc.*;
import hk.ust.comp3021.stmt.*;
import java.util.*;


public class ASTModule extends ASTElement {
    // Module(stmt* body, ...)
    private ArrayList<ASTStmt> body;
    private String astID;

    public ASTModule(XMLNode node, String astID) {
        this.astID = astID;

        this.body = new ArrayList<>();
        for (XMLNode bodyNode : node.getChildByIdx(0).getChildren()) {
            this.body.add(ASTStmt.createASTStmt(bodyNode));
        }
    }

    /*
    * Find all AST node whose class type is `FunctionDefStmt` shown in the AST
    * Hints: you need to traverse all the nodes in AST and check its class type.
    * We have prepared the method `getChildren` for you to ease the traversal.
    * You may need to remove the `return null` in the skeleton.
    * */
    public ArrayList<FunctionDefStmt> getAllFunctions() {
        // TODO: complete the definition of the method `getAllFunctions`
        ArrayList<FunctionDefStmt> functions = new ArrayList<>();
        getAllFunctionsRecursive(body, functions);
        return functions;
    }

    /*
     * Find all operators whose class type is `ASTEnumOp` shown in the AST.
     * Hints: We have prepared the method `getChildren` for you to ease the traversal.
     * But ASTEnumOp is not regarded as children node in AST Tree.
     * To find all operators, you need to first find the nodes whose types are BinOpExpr, BoolOpExpr, etc.
     * Then, you obtain their operators by accessing field `op`.
     * Further, Ctx_Store, Ctx_Load and Ctx_Del are not operators as well.
     * You may need to remove the `return null` in the skeleton.
     * */
    public ArrayList<ASTEnumOp> getAllOperators() {
        // TODO: complete the definition of the method `getAllOperators`
        ArrayList<ASTEnumOp> operatorList = new ArrayList<>();
        for (ASTStmt stmt : body) {
            collectOperators(stmt, operatorList);
        }
        return operatorList;

    }

    /*
     * Find all AST node shown in the AST
     * Hints: you need to traverse all the nodes in AST.
     * You may need to remove the `return null` in the skeleton.
     * */
    public ArrayList<ASTElement> getAllNodes() {
        // TODO: complete the definition of the method `getAllNodes`
        ArrayList<ASTElement> nodes = new ArrayList<>();
        for (ASTStmt stmt : body) {
            nodes.add(stmt);
            ArrayList<ASTElement> children = stmt.getChildren();
            nodes.addAll(children);
        }
        return nodes;
    }

    @Override
    public ArrayList<ASTElement> getChildren() {
        // TODO: complete the definition of the method `getChildren`
        return new ArrayList<>(body);
    }

    @Override
    public int countChildren() {
        // TODO: complete the definition of the method `countChildren`
        return body.size();
    }

    @Override
    public void printByPos(StringBuilder str) {
        // TODO: (Bonus) complete the definition of the method `printByPos`
    }

    public String getASTID() {
        return astID;
    }

    @Override
    public String getNodeType() {
        return "Module";
    }

    /**
     * Attention: You may need to define more methods to update or access the field of the class `User`
     * Feel free to define more method but remember not
     * (1) removing the fields or methods in our skeleton.
     * (2) changing the type signature of `public` methods
     * (3) changing the modifiers of the fields and methods, e.g., changing a modifier from "private" to "public"
     */
    private void collectOperators(ASTElement element, ArrayList<ASTEnumOp> operatorList) {
        if (element instanceof ASTEnumOp) {
            operatorList.add((ASTEnumOp) element);
        } else {
            for (ASTElement child : element.getChildren()) {
                collectOperators(child, operatorList);
            }
        }
    }
    private void collectNodes(ASTElement element, ArrayList<ASTElement> nodeList) {
        nodeList.add(element);
        for (ASTElement child : element.getChildren()) {
            collectNodes(child, nodeList);
        }
    }
    private void getAllFunctionsRecursive(ArrayList<ASTStmt> stmts, ArrayList<FunctionDefStmt> functions) {
        for (ASTStmt stmt : stmts) {
            if (stmt instanceof FunctionDefStmt) {
                functions.add((FunctionDefStmt) stmt);
            }
            ArrayList<ASTElement> children = stmt.getChildren();

            getAllFunctionsRecursive(children, functions);
        }
    }
}
