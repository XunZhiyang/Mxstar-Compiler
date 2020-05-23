package IR;

import OperandRV.OperandRV;
import OperandRV.Register;
import Symbol.Type;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Value {
    private static Map<String, Integer> freq = new HashMap<>();

    protected String identifier;
    private Type type;

    private List<User> uses = new ArrayList<>();

    private OperandRV corRV;

    public Value(String origName, Type type) {
        this.identifier = rename(origName);
        this.type = type;
    }

    String rename(String origName) {
        int cnt = 0;
        Integer times = freq.get(origName);
        if (times != null) {
            cnt = times + 1;
        }
        freq.put(origName, cnt);
        return cnt == 0 ? origName : origName + "_" + cnt;
    }

    public String getIdentifier() {
        return "%" + identifier;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public List<User> getUses() {
        return uses;
    }

    public void addUse(User user) {
        uses.add(user);
    }

    public void replaceAllUsesWith(Value value) {
        for (User user : uses) {
            List<Value> list = user.getOperands();
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == this) {
                    list.set(i, value);
                }
            }
            value.addUse(user);
        }
        uses.clear();
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }

    public void setCorRV(OperandRV corRV) {
        this.corRV = corRV;
    }

    public OperandRV getCorRV() {
        return corRV;
    }
}
