package IR;

import Symbol.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User extends Value {
    private List<Value> operands = new ArrayList<>();

    public User(String origName, Type type) {
        super(origName, type);
    }

    public void addOperand(Value... values) {
        for (Value value : values) {
            if (value != null)
                value.addUse(this);
        }
        operands.addAll(Arrays.asList(values));
    }

    public List<Value> getOperands() {
        return operands;
    }

    public Value getOperand(int index) {
        return operands.get(index);
    }
}
