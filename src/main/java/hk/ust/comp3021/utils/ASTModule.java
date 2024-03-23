package hk.ust.comp3021.utils;

//import hk.ust.comp3021.expr.CallExpr;
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
            ASTStmt stmt = ASTStmt.createASTStmt(bodyNode);
            this.body.add(stmt);
            getSelfChildren().add(stmt);
        }
    }

    /*
    * Find all AST node whose class type is `FunctionDefStmt` shown in the AST
    * Hints: you need to traverse all the nodes in AST and check its class type.
    * We have prepared the method `getChildren` for you to ease the traversal.
    * You may need to remove the `return null` in the skeleton.
    * */
    public ArrayList<FunctionDefStmt> getAllFunctions() {
        return getSelectedChildren(FunctionDefStmt.class);
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
        ArrayList<ASTEnumOp> ret = new ArrayList<>();
        getChildren().stream().forEach(x -> x.visitOp(y -> {
            if (y.getOp() == ASTEnumOp.ASTOperator.Ctx_Load ||
                    y.getOp() == ASTEnumOp.ASTOperator.Ctx_Store ||
                    y.getOp() == ASTEnumOp.ASTOperator.Ctx_Del) {
                return;
            }
            ret.add(y);
        }));
        return ret;
    }


    /*
     * Find all AST node shown in the AST
     * Hints: you need to traverse all the nodes in AST.
     * You may need to remove the `return null` in the skeleton.
     * */
    public ArrayList<ASTElement> getAllNodes() {
        ArrayList<ASTElement> x = getChildren();
        x.add(0, this);
        return x;
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
    public void yourMethod() {

    }
}
