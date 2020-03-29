package Symbol;

import Utils.Position;
import Utils.RedefError;
import Utils.SemanticError;
import org.antlr.v4.runtime.misc.Triple;

import java.util.*;

public class GlobalScope extends BaseScope{
    Map<String, Type> typeMap = new LinkedHashMap<>();

    public GlobalScope() {
        PrimitiveType intType = new PrimitiveType("int");
        PrimitiveType boolType = new PrimitiveType("bool");
        PrimitiveType voidType = new PrimitiveType("void");
        typeMap.put("int", intType);
        typeMap.put("bool", boolType);
        typeMap.put("void", voidType);

        ClassType string = new ClassType("String", null);
        defineClass(string);

        string.defineSymbol(new FunctionSymbol(intType, "length", null));
        string.defineSymbol(new FunctionSymbol(string, "substring", null));
        string.defineSymbol(new FunctionSymbol(intType, "parseInt", null));
        string.defineSymbol(new FunctionSymbol(intType, "ord", null));
        string.defineSymbol(new FunctionSymbol(intType, "length", null));

    }

    @Override
    public Symbol getSymbol(String identifier, Position position) {
        Symbol symbol = symbolMap.get(identifier);
        if (symbol == null) {
            throw new SemanticError("'" + identifier + "' was not declared in this scope.", position);
        }
        return symbol;
    }

    public void defineClass(ClassType classType) {
        if (typeMap.containsKey(classType.getName())) {
            throw new RedefError(classType.getName(), classType.getPos());
        }
        typeMap.put(classType.getName(), classType);
    }

    public Type getType(String identifier, Position position) {
        Type type = typeMap.get(identifier);
        if (type == null)
            throw new SemanticError("'" + identifier + "' does not name a type.", position);
        else
            return symbol;
    }


}
