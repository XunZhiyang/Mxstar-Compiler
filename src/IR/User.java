package IR;

import Symbol.Type;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User extends Value {
    List<Value> operands = new ArrayList<>();

    User(String origName, Type type) {
        super(origName, type);
    }

    void addOperand(Value... values) {
        operands.addAll(Arrays.asList(values));
    }
}
