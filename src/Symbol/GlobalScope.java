package Symbol;

import AST.TypeNode;
import Utils.Position;
import Utils.RedefError;
import Utils.SemanticError;

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

        ClassType string = new ClassType("String", null, this);
        defineClass(string);

        string.defineSymbol(new FunctionSymbol(intType, "length", null, string));
        string.defineSymbol(new FunctionSymbol(string, "substring", null, string){{
            defineSymbol(new VarSymbol(intType, "left", null));
            defineSymbol(new VarSymbol(intType, "right", null));
        }});
        string.defineSymbol(new FunctionSymbol(intType, "parseInt", null, string));
        string.defineSymbol(new FunctionSymbol(intType, "ord", null, string){{
            defineSymbol(new VarSymbol(intType, "pos", null));
        }});
        string.defineSymbol(new FunctionSymbol(intType, "length", null, string));

        this.defineSymbol(new FunctionSymbol(voidType, "print", null, this){{
            defineSymbol(new VarSymbol(string, "str", null));
        }});
        this.defineSymbol(new FunctionSymbol(voidType, "println", null, this){{
            defineSymbol(new VarSymbol(string, "str", null));
        }});
        this.defineSymbol(new FunctionSymbol(voidType, "printInt", null, this){{
            defineSymbol(new VarSymbol(intType, "n", null));
        }});
        this.defineSymbol(new FunctionSymbol(voidType, "printlnInt", null, this){{
            defineSymbol(new VarSymbol(intType, "n", null));
        }});
        this.defineSymbol(new FunctionSymbol(string, "getString", null, this));
        this.defineSymbol(new FunctionSymbol(intType, "getInt", null, this));
        this.defineSymbol(new FunctionSymbol(string, "toString", null, this){{
            defineSymbol(new VarSymbol(intType, "i", null));
        }});
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

    public Type getType(TypeNode node) {
        Type type = typeMap.get(node.getIdentifier());
        if (type == null)
            throw new SemanticError("'" + node.getIdentifier() + "' does not name a type.", node.getPosition());
        else {
            int d = node.getDim();
            if (d > 0) return new ArrayType(type, d);
            else return type;
        }
    }


}
