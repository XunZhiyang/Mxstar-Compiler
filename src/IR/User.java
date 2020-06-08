package IR;

import Symbol.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class User extends Value {
    protected List<Value> operands = new ArrayList<>();

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

    public void setOperand(int index, Value value) {
        operands.get(index).deleteUse(this);
        operands.set(index, value);
        if (value != null)
           value.addUse(this);
    }

    public Value getOperand(int index) {
        return operands.get(index);
    }

    public void replaceOperand(Value orig, Value replacement) {
        var iterator = operands.listIterator();
        while(iterator.hasNext()) {
            Value current = iterator.next();
            if (current == orig) {
                current.deleteUse(this);
                iterator.set(replacement);
                replacement.addUse(this);
            }
        }
    }

    public void reverseOperands() {
        Collections.reverse(operands);
    }
}
