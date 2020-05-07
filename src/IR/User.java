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
        operands.addAll(Arrays.asList(values));
    }

    public List<Value> getOperands() {
        return operands;
    }
}
