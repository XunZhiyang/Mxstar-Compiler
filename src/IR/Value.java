package IR;

import Symbol.Type;

import java.util.Map;

public class Value {
    private static Map<String, Integer> freq;

    private String identifier;
    private Type type;

    public Value(String origName, Type type) {
        this.identifier = rename(origName);
        this.type = type;
    }

    String rename(String origName) {
        Integer cnt = 0;
        Integer times = freq.get(origName);
        if (times != null) {
            cnt = times + 1;
        }
        freq.put(origName, cnt);
        return origName + "_" + cnt;
    }

    public String getIdentifier() {
        return identifier;
    }

    public Type getType() {
        return type;
    }
}