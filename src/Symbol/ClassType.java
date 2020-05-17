package Symbol;

import AST.ClassDeclNode;
import IR.IRVisitor;
import Utils.Position;
import Utils.RedefError;
import Utils.SemanticError;

import java.util.*;

public class ClassType extends Type implements Scope {
    private Map<String, Symbol> symbolMap = new LinkedHashMap<>();
    private Scope fatherScope;
    private ClassDeclNode define;
    private FunctionSymbol constructor;


    //for IR
    int fieldNum = 0;
    private Map<String, Integer> fieldIndex = new LinkedHashMap<>();

    public ClassType(String typeName, ClassDeclNode define, Scope fatherScope) {
        super(typeName);
        this.define = define;
        this.fatherScope = fatherScope;
        this.constructor = null;
    }

    @Override
    public void defineSymbol(Symbol symbol) {
        if (constructor != null && symbol.ifConstructor())
            throw new SemanticError("Duplicate constructors.", symbol.getPosition());
        if(symbol.ifConstructor()) {
            this.constructor = (FunctionSymbol) symbol;
        }
        if (symbolMap.containsKey(symbol.getIdentifier())) {
            throw new RedefError(symbol.getIdentifier(), symbol.getPos());
        }
        symbolMap.put(symbol.getIdentifier(), symbol);

        if (!symbol.isFunction()) {
            fieldIndex.put(symbol.getIdentifier(), fieldNum);
            fieldNum++;
            Type type = symbol.getType();
            if (type.derivesFromClass()) type = type.getPointer();
            int symbolBitLen = type.getBitLen();
            bitLen += (symbolBitLen - bitLen % symbolBitLen) % symbolBitLen + symbolBitLen;
        }
    }

    @Override
    public Symbol getSymbol(String identifier, Position position) {
        Symbol symbol = symbolMap.get(identifier);
        if (symbol == null)
            return fatherScope.getSymbol(identifier, position);
        else
            return symbol;
    }

    @Override
    public boolean isString() {
        return getIdentifier().equals("string");
    }

    @Override
    public boolean isNullable() {
        return true;
    }

    @Override
    public boolean isClass() {
        return true;
    }

    public Position getPos() {
        return define.getPosition();
    }

    // for IR
    public int getFieldIndex(String identifier) {
        return fieldIndex.get(identifier);
    }

    public FunctionSymbol getConstructor() {
        return constructor;
    }

    public List<Type> getTypeList() {
        List<Type> list = new ArrayList<>();
        for (Map.Entry<String, Symbol> entry : symbolMap.entrySet()) {
            if (!entry.getValue().isFunction()) {
                Type type = entry.getValue().getType();
                list.add(type.derivesFromClass() ? type.getPointer() : type);
            }
        }
        return list;
    }

    public Type getFieldType(String identifier) {
        Symbol symbol = symbolMap.get(identifier);
        Type type = symbol.getType();
        if (type.derivesFromClass()) {
            type = type.getPointer();
        }
        return type;
    }

    public boolean getIsMethod(String identifier) {
        return symbolMap.get(identifier).isFunction();
    }

    @Override
    public String IRName() {
        if (getIdentifier().equals("string")) {
            return "i8*";
        }
        else {
            return "%struct." + getIdentifier();
        }
    }

    public void accept(IRVisitor visitor) {
        visitor.visit(this);
    }
}
