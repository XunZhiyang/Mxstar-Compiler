package IR;

import Symbol.Type;

import java.util.Map;

public class Value {
    private static Map<String, Integer> freq;

    private String name;
    private Type type;

    String rename(String origName) {
        Integer cnt = 0;
        Integer times = freq.get(origName);
        if (times != null) {
            cnt = times + 1;
        }
        freq.put(origName, cnt);
        return origName + "_" + cnt;
    }

    Value(String origName, Type type) {
        this.name = rename(origName);
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }
}
