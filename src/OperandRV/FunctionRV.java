package OperandRV;

import IR.BasicBlock;
import IR.Constant.Function;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.max;
import static java.lang.Integer.min;

public class FunctionRV extends OperandRV {
    private List<BlockRV> blocks = new ArrayList<>();
    private List<Register> params = new ArrayList<>();
    private int regNum, maxParamBytes;

    public FunctionRV(Function function) {
        this.identifier = function.getIdentifier();
        function.setCorRV(this);
    }

    public void addBlock(BlockRV block) {
        blocks.add(block);
    }

    public List<BlockRV> getBlocks() {
        return blocks;
    }

    public List<Register> getParams() {
        return params;
    }

    public BlockRV entryBlock() {
        return blocks.get(0);
    }

    public BlockRV exitBlock() {
        return blocks.get(min(1, blocks.size() - 1));
    }

    public void setRegNum(int regNum) {
        this.regNum = regNum;
    }

    public int getRegNum() {
        return regNum;
    }

    public void updateParamBytes(int value) {
        maxParamBytes = max(maxParamBytes, value);
    }
}