package IR.Instruction;

import IR.User;
import Symbol.Type;

public abstract class Instruction extends User {
    Instruction(String name, Type type) {
        super(name, type);
    }
}
