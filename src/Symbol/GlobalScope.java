package Symbol;

import AST.TypeNode;
import Utils.Position;
import Utils.RedefError;
import Utils.SemanticError;

import java.util.*;

public class GlobalScope extends BaseScope{
    Map<String, Type> typeMap = new LinkedHashMap<>();
    public static PrimitiveType intType = new PrimitiveType("int");
    public static PrimitiveType boolType = new PrimitiveType("bool");
    public static PrimitiveType voidType = new PrimitiveType("void");
    public static NullType nullType = new NullType();
    private ClassType stringType = new ClassType("string", null, this);
    private FunctionSymbol size = new FunctionSymbol(intType, "size", null, this);

    public GlobalScope() {
        typeMap.put("int", intType);
        typeMap.put("bool", boolType);
        typeMap.put("void", voidType);
        defineClass(stringType);

        stringType.defineSymbol(new FunctionSymbol(intType, "length", null, stringType));
        stringType.defineSymbol(new FunctionSymbol(stringType, "substring", null, stringType){{
            defineSymbol(new VarSymbol(intType, "left", null));
            defineSymbol(new VarSymbol(intType, "right", null));
        }});
        stringType.defineSymbol(new FunctionSymbol(intType, "parseInt", null, stringType));
        stringType.defineSymbol(new FunctionSymbol(intType, "ord", null, stringType){{
            defineSymbol(new VarSymbol(intType, "pos", null));
        }});

        this.defineSymbol(new FunctionSymbol(voidType, "print", null, this){{
            defineSymbol(new VarSymbol(stringType, "str", null));
        }});
        this.defineSymbol(new FunctionSymbol(voidType, "println", null, this){{
            defineSymbol(new VarSymbol(stringType, "str", null));
        }});
        this.defineSymbol(new FunctionSymbol(voidType, "printInt", null, this){{
            defineSymbol(new VarSymbol(intType, "n", null));
        }});
        this.defineSymbol(new FunctionSymbol(voidType, "printlnInt", null, this){{
            defineSymbol(new VarSymbol(intType, "n", null));
        }});
        this.defineSymbol(new FunctionSymbol(stringType, "getString", null, this));
        this.defineSymbol(new FunctionSymbol(intType, "getInt", null, this));
        this.defineSymbol(new FunctionSymbol(stringType, "toString", null, this){{
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
        if (typeMap.containsKey(classType.getIdentifier())) {
            throw new RedefError(classType.getIdentifier(), classType.getPos());
        }
        typeMap.put(classType.getIdentifier(), classType);
    }

    public Type getType(TypeNode node) {
        Type type = typeMap.get(node.getIdentifier());
        if (type == null) {
            System.out.println(node.getIdentifier());
            throw new SemanticError("'" + node.getIdentifier() + "' does not name a type.", node.getPosition());
        } else {
            int d = node.getDim();
            if (d > 0) return new ArrayType(type, d);
            else return type;
        }
    }

    public FunctionSymbol getMain() {
        FunctionSymbol mainFunction = (FunctionSymbol) symbolMap.get("main");
        if (mainFunction == null)
            throw new SemanticError("Main function not defined.", new Position(0, 0));
        return mainFunction;
    }

    public FunctionSymbol getSize() {
        return size;
    }

    public PrimitiveType getIntType() {
        return intType;
    }
    public PrimitiveType getBoolType() {
        return boolType;
    }
    public PrimitiveType getVoidType() {
        return voidType;
    }
    public ClassType getStringType() {
        return stringType;
    }
    public NullType getNullType() {
        return nullType;
    }

}
