package OperandRV;

import java.util.*;

public class RV32 {
    public static List<String> callerSave = new ArrayList<>() {
        {
            for (int i = 0; i < 7; ++i) add("t" + i);
            for (int i = 0; i < 8; ++i) add("a" + i);
            add("ra");
        }
    };
    public static List<String> calleeSave = new ArrayList<>() {
        {
            for (int i = 0; i < 12; ++i) add("s" + i);
        }
    };
    static List<String> normal = new ArrayList<>(callerSave) {{addAll(calleeSave);}};
    static List<String> special = Arrays.asList("zero", "sp", "gp", "tp");
//    public static Register zero = new Register();
//    public static Register sp = new Register();
//    public static Register gp = new Register();
//    public static Register tp = new Register();
    private static Map<String, Register> registers = new HashMap<>() {
        {
            normal.forEach(reg -> registers.put(reg, new Register()));
            special.forEach(reg -> registers.put(reg, new Register()));
        }
    };

    public static Register get(String str) {
        return registers.get(str);
    }

}
