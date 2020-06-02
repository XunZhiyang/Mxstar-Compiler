package Backend;

import OperandRV.BlockRV;
import OperandRV.FunctionRV;
import OperandRV.InstRV.*;
import OperandRV.ModuleRV;
import OperandRV.Register;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.*;

public class RVPrinter {
    private Set<BlockRV> blocks = new LinkedHashSet<>();
    private List<String> list = new ArrayList<>();
    int clk = 0;
    private FunctionRV curFunction;

    public RVPrinter(ModuleRV module) {
        write(".text");
        write(".file\t\"code.mx\"");
        write("");
        module.getFunctions().forEach(this::print);
        module.getGlobalVariables().forEach(this::print);
            module.getStringConst().forEach(this::print);
    }

    private void write(String string) {
        list.add("\t" + string);
    }

    private void write(String string, boolean noIndent) {
        list.add(string);
    }

    public String getRV() {
        StringBuilder resBuilder = new StringBuilder();
        for (String str : list) {
            resBuilder.append(str).append("\n");
        }
        return resBuilder.toString();
    }

    private boolean dfs(BlockRV block, boolean first) {
        if (blocks.contains(block)) return false;
        blocks.add(block);
        if (first) {
            block.setIdentifier(curFunction.getIdentifier());
        } else {
            block.setIdentifier("." + block.getIdentifier());
        }
        InstRV lastInst = block.getInstructions().get(block.getInstructions().size() - 1);
        if (lastInst instanceof JumpInst) {
            if (dfs(((JumpInst) lastInst).getDest(), false)) {
                block.getInstructions().remove(lastInst);
            }
        }
        block.getSuccessors().forEach(b -> dfs(b, false));
        return true;
    }

    private void print(FunctionRV function) {
        curFunction = function;
//        System.err.println(function.getIdentifier());
        write(".globl\t" + function.getIdentifier(), true);
        write(".p2align\t1", true);
        write(".type\t" + function.getIdentifier() + ",@function", true);
        clk = 0;
        blocks.clear();
        dfs(function.entryBlock(), true);
        blocks.forEach(this::print);
        write("");
    }

    public void print(BlockRV block) {
        write(block.getIdentifier() + ":");
//        block.getInstructions().forEach(System.err::println);
        block.getInstructions().forEach(inst -> inst.print(this));
        write("");
    }

    public void print(InstRV inst) {
        throw new RuntimeException();
    }

    public void print(BranchInst inst) {
        write(String.format("%s %s, %s, %s", inst.getOp(), inst.getRs1(), inst.getRs2(), inst.getDest()));
    }

    public void print(CallInst inst) {
        write("call " + inst.getFunction());
    }

    public void print(CmpZeroInst inst) {
        write(String.format("%s %s, %s", inst.getOp(), inst.getRdStr(), inst.getRs1()));
    }

    public void print(ITypeInst inst) {
        write(String.format("%s %s, %s, %s", inst.getOp(), inst.getRdStr(), inst.getRs1(), inst.getImmediate()));
    }

    public void print(JumpInst inst) {
        write("j " + inst.getDest().getIdentifier());
    }

    public void print(LaInst inst) {
        write(String.format("la %s, %s", inst.getRdStr(), inst.getSymbol()));
    }

    public void print(LoadImmInst inst) {
        write(String.format("li %s, %s", inst.getRdStr(), inst.getValue()));
    }

    public void print(LoadInst inst) {
        write(String.format("%s %s, %s", inst.getOp(), inst.getRdStr(), inst.getSrc()));
    }

    public void print(LuiInst inst) {
        write(String.format("lui %s, %s", inst.getRdStr(), inst.getImmediate()));
    }

    public void print(MoveInst inst) {
        write(String.format("mv %s, %s", inst.getRdStr(), inst.getRs1()));
    }

    public void print(ReturnInst inst) {
        write("ret");
    }

    public void print(RTypeInst inst) {
        write(String.format("%s %s, %s, %s", inst.getOp(), inst.getRdStr(), inst.getRs1(), inst.getRs2()));
    }

    public void print(StoreInst inst) {
        write(String.format("%s %s, %s", inst.getOp(), inst.getRs2(), inst.getSrc()));
    }

    private void print(Register register) {
        write(".type\t" + register.getIdentifier() + ",@object", true);
        write(".section\t.bss", true);
        write(".globl\t" + register.getIdentifier(), true);
        write(".p2align\t2", true);
        write(register.getIdentifier() + ":", true);
        write(".L" + register.getIdentifier() + "$local:");
        write(".word\t0");
        write(".size\t" + register.getIdentifier() + ", 4\n");
    }

    private void print(Register register, String string) {
        write(".type\t" + register.getIdentifier() + ",@object", true);
        write(".section\t.rodata", true);
        write(register.getIdentifier() + ":", true);
        write(".asciz\t\"" + convertString(string) + "\"");
    }

    private String convertString(String str) {
        String ret = str.replace("\\5C", "\\\\");
        ret = ret.replace("\\0A", "\\n");
        ret = ret.replace("\\22", "\\\"");
        return ret;
    }
}
