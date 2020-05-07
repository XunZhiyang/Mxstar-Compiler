package Backend;

import IR.BasicBlock;
import IR.Constant.*;
import IR.IRVisitor;
import IR.Instruction.*;
import IR.Module;
import IR.Value;
import Symbol.ClassType;
import Symbol.PointerType;
import Symbol.Symbol;
import Symbol.Type;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class IRPrinter implements IRVisitor {
    private List<String> IRList = new ArrayList<>();

    public String getIR() {
        StringBuilder resBuilder = new StringBuilder();
        for (String str : IRList) {
            resBuilder.append(str)
                      .append("\n");
        }
        return resBuilder.toString();
    }

    @Override
    public void visit(Value node) {

    }

    @Override
    public void visit(Module node) {
        for (Value i : node.getGlobalVariableList()) {
            String str = "";
            Type type = ((PointerType) i.getType()).getMember();
            str += i.getIdentifier() + " = global " + type.IRName() + " ";
            str += (type.isInt() || type.isBoolean()) ? "0, " : "null, ";
            str += "align " + type.getAlignment();
            IRList.add(str);
        }

        node.getClassList().forEach(x -> x.accept(this));
        node.getFunctionList().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(Function node) {
        String str = "define " + node.getType().IRName() + " " + node.getIdentifier() + "(";
        List<Value> params = node.getOperands();
        StringBuilder paramsBuilder = new StringBuilder();
        for (int i = 0; i < params.size(); i++) {
            Value v = params.get(i);
            paramsBuilder.append(v.getType().IRName())
                         .append(" ")
                         .append(v.getIdentifier())
                         .append(i == params.size() - 1 ? ", " : "");
        }
        paramsBuilder.append(") {");
        IRList.add(str + paramsBuilder.toString());

        node.getBasicBlockList().forEach(x -> x.accept(this));

        IRList.add("}");
        IRList.add("");
    }

    @Override
    public void visit(ClassType node) {
        String str = "";
        StringBuilder builder = new StringBuilder();
        builder.append(node.IRName()).append(" = type { ");
        for (Type t : node.getTypeList()) {
            builder.append(t.IRName()).append(" ");
        }
        builder.append("}");
        IRList.add(builder.toString());
    }

    @Override
    public void visit(GlobalVariable node) {
//        res += node.getIdentifier();
    }

    @Override
    public void visit(BasicBlock node) {
        IRList.add(node.getIdentifier() + ":");
        node.getInstructionList().forEach(x -> x.accept(this));
    }

    //Instruction
    @Override
    public void visit(AllocaInst node) {
        String str = node.getIdentifier() + " = alloca ";
        Type type = ((PointerType) node.getType()).getMember();
        str += type.IRName() + ", align" + type.getAlignment();
        IRList.add(str);
    }

    @Override
    public void visit(BinaryOpInst node) {

    }

    @Override
    public void visit(BranchInst node) {

    }

    @Override
    public void visit(CallInst node) {

    }

    @Override
    public void visit(GEPInst node) {

    }

    @Override
    public void visit(JumpInst node) {

    }

    @Override
    public void visit(LoadInst node) {

    }

    @Override
    public void visit(ReturnInst node) {

    }

    @Override
    public void visit(SextInst node) {

    }

    @Override
    public void visit(StoreInst node) {

    }

    //Constant
    @Override
    public void visit(BoolConst node) {

    }

    @Override
    public void visit(IntConst node) {

    }

    @Override
    public void visit(NullConst node) {

    }

    @Override
    public void visit(StringConst node) {

    }

}
