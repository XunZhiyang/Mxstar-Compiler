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
    public void visit(Value node) {    }

    @Override
    public void visit(Module node) {
        node.getClassList().forEach(x -> x.accept(this));

        for (Value i : node.getGlobalVariableList()) {
            String str = "";
            if (i instanceof StringConst) {
                Type type = ((PointerType) i.getType()).getMember();
                str += i.getIdentifier() + " = constant " + type.IRName() + " ";
                str += "c\"" + ((StringConst) i).getString() + "\\00\", align 1";
            } else {
                Type type = ((PointerType) i.getType()).getMember();
                str += i.getIdentifier() + " = global " + type.IRName() + " ";
                str += (type.isInt() || type.isBoolean()) ? "0, " : "null, ";
                str += "align " + type.getAlignment();
            }
            IRList.add(str);
        }
        IRList.add("");

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
                         .append(i == params.size() - 1 ? "" : ", ");
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
        IRList.add("");
    }

    @Override
    public void visit(GlobalVariable node) {
//        res += node.getIdentifier();
    }

    @Override
    public void visit(BasicBlock node) {
        IRList.add(node.getIdentifier() + ":");
        node.getInstructionList().forEach(x -> x.accept(this));
        IRList.add("");
    }

    //Instruction
    @Override
    public void visit(AllocaInst node) {
        String str = node.getIdentifier() + " = alloca ";
        Type type = ((PointerType) node.getType()).getMember();
        str += type.IRName() + ", align " + type.getAlignment();
        IRList.add(str);
    }

    @Override
    public void visit(BinaryOpInst node) {
        String str = node.getIdentifier() + " = " + node.getOp() + " " + node.getType().IRName() + " " +
                node.getOperand(0).getIdentifier() + ", " + node.getOperand(1).getIdentifier();
        IRList.add(str);
    }

    @Override
    public void visit(BitCastInst node) {
        //%M.4 = bitcast i8* %call_malloc.1 to i32*
        String str = node.getIdentifier() + " = bitcast " + node.getOperand(0).getType().IRName() + " " +
                node.getOperand(0).getIdentifier() + " to " + node.getType().IRName();
        IRList.add(str);
    }

    @Override
    public void visit(BranchInst node) {
        String str = "br i1 " + node.getOperand(0).getIdentifier() + ", label %" +
                node.getOperand(1).getIdentifier() + ", label %" + node.getOperand(2).getIdentifier();
        IRList.add(str);
    }

    @Override
    public void visit(CallInst node) {
        // call void @f(i32 %a)
        // %call_f = call i32 @f(i32 %load_a.1, i32 2)
        StringBuilder sb = new StringBuilder();
        if (!node.getType().isVoid())
             sb.append(node.getIdentifier()).append(" = ");
        sb.append("call ").append(node.getType().IRName()).append(" ").append(node.getFunctionIdentifier()).append("(");
        for (int i = 0; i < node.getOperands().size(); i++) {
            sb.append(node.getOperands().get(i).getType())
                    .append(" ")
                    .append(node.getOperands().get(i).getIdentifier())
                    .append(i == node.getOperands().size() - 1 ? "" : ", ");
        }
        sb.append(")");
        IRList.add(sb.toString());
    }

    @Override
    public void visit(CmpInst node) {
//        %T.9 = icmp slt i32 3, 5
        String str = node.getIdentifier() + " = icmp " + node.getOp() + " " + node.getOperand(0).getType().IRName() + " " +
                node.getOperand(0).getIdentifier() + ", " + node.getOperand(1).getIdentifier();
        IRList.add(str);
    }

    @Override
    public void visit(GEPInst node) {
        //%T.11 = getelementptr i32, i32* %load_T.1, i32 2
        StringBuilder sb = new StringBuilder();
        sb.append(node.getIdentifier())
                .append(" = getelementptr ")
                .append(((PointerType) node.getOperand(0).getType()).getMember().IRName());
        for (Value v : node.getOperands()) {
            sb.append(", ")
                    .append(v.getType().IRName())
                    .append(" ")
                    .append(v.getIdentifier());
        }
        IRList.add(sb.toString());
    }

    @Override
    public void visit(JumpInst node) {
        IRList.add("br label %" + node.getOperand(0).getIdentifier());
    }

    @Override
    public void visit(LoadInst node) {
//        %load_a = load i32***, i32**** %a
        String str = node.getIdentifier() + " = load ";
        PointerType type = (PointerType) node.getOperand(0).getType();
        str += type.getMember().IRName() + ", " + type.IRName() + " ";
        str += node.getOperand(0).getIdentifier();
        IRList.add(str);
    }

    @Override
    public void visit(ReturnInst node) {
        //ret i32 aa
        if (node.getOperands().size() == 0) {
            IRList.add("ret void");
        }
        else {
            Value v = node.getOperand(0);
            IRList.add("ret " + v.getType().IRName() + " " + v.getIdentifier());
        }
    }

    @Override
    public void visit(SextInst node) {
        //%M.3 = sext i32 %T.6 to i64
        String str = node.getIdentifier() + "= sext " + node.getOperand(0).getType().IRName() + " " +
                node.getOperand(0).getIdentifier() + " to " + node.getType().IRName();
    }

    @Override
    public void visit(StoreInst node) {
        //store i32 0, i32* %ret
        List<Value> list = node.getOperands();
        String str = "store " + list.get(0).getType().IRName() + " " + list.get(0).getIdentifier() + ", " +
                list.get(1).getType().IRName() + " " + list.get(1).getIdentifier();
        IRList.add(str);
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
