package Backend;

import IR.BasicBlock;
import IR.Constant.*;
import IR.IRVisitor;
import IR.Instruction.*;
import IR.Module;
import IR.Value;
import Symbol.ClassType;
import Symbol.PointerType;
import Symbol.Type;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class IRPrinter implements IRVisitor {
    private List<String> IRList = new ArrayList<>();

    public String getIR(boolean addBuiltin) {
        StringBuilder resBuilder = new StringBuilder();
        try {
            File filename = new File(addBuiltin ? "src/Utils/builtin.ll" : "src/Utils/header.ll");
            InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
            BufferedReader br = new BufferedReader(reader);
            String line;
            line = br.readLine();
            while (line != null) {
                resBuilder.append(line).append("\n");
                line = br.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (String str : IRList) {
            resBuilder.append(str).append("\n");
        }
        return resBuilder.toString();
    }

    @Override
    public void visit(Value node) {}

    @Override
    public void visit(Module node) {
        node.getClassList().forEach(x -> x.accept(this));

        for (Value i : node.getGlobalVariableList()) {
            String str = "";
            Type type = ((PointerType) i.getType()).getMember();
            if (i instanceof StringConst) {
                str += i.getIdentifier() + " = constant " + type.IRName() + " ";
                str += "c\"" + ((StringConst) i).getString() + "\\00\", align 1";
            } else {
                str += i.getIdentifier() + " = global ";
                if ((type.isInt() || type.isBoolean())) {
                    str += type.IRName() + " 0, ";
                } else {
                    str += (type.isPointer() || type.isString() ? type.IRName() : i.getType().IRName()) + " null, ";
                }
                str += "align 8";
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
            Type type = v.getType();
            paramsBuilder.append(type.IRName())
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
        StringBuilder builder = new StringBuilder();
        builder.append(node.IRName()).append(" = type { ");
        List<Type> types = node.getTypeList();
        for (int i = 0; i < types.size(); ++i) {
            Type type = types.get(i);
            builder.append(type.IRName())
                    .append(i == types.size() - 1 ? " " : ", ");
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
        str += type.IRName() + ", align " + type.getByteNum();
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
            sb.append(node.getOperands().get(i).getType().IRName())
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
        IRList.add(str);
    }

    @Override
    public void visit(StoreInst node) {
        //store i32 0, i32* %ret
        List<Value> list = node.getOperands();
        String str = "store " + list.get(0).getType().IRName() + " " + list.get(0).getIdentifier() + ", " +
                list.get(1).getType().IRName() + " " + list.get(1).getIdentifier();
        IRList.add(str);
    }

    @Override
    public void visit(PhiInst node) {
        //%i = phi i32 [0, %for_after_1], [%T.67, %for_step_2]
        StringBuilder sb = new StringBuilder();
        sb.append(node.getIdentifier())
                .append(" = phi ")
                .append(node.getType().IRName());

        List<Value> list = node.getOperands();
        for (int i = 0; i < list.size(); i += 2) {
            sb.append(" [")
                    .append(list.get(i) == null ? "undef" : list.get(i).getIdentifier())
                    .append(", %")
                    .append(list.get(i + 1).getIdentifier())
                    .append("]")
                    .append(i == list.size() - 2 ? "" : ",");
        }
        IRList.add(sb.toString());
    }

    @Override
    public void visit(MoveInst node) {
        //move %rs to %rd
        String str = "move " + (node.getOperand(0) == null ? "undef" : node.getOperand(0).getIdentifier()) + " to "
                + node.getOperand(1).getIdentifier();
        IRList.add(str);
    }
}
