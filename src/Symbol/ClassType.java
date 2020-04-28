package Symbol;

import AST.ClassDeclNode;
import Utils.Position;
import Utils.RedefError;
import Utils.SemanticError;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassType extends Type implements Scope {
    private Map<String, Symbol> symbolMap = new LinkedHashMap<>();
    private Scope fatherScope;
    private ClassDeclNode define;
    private boolean hasConstructor;

    //for IR
    int fieldNum = 0;
    private Map<String, Integer> fieldIndex = new LinkedHashMap<>();

    public ClassType(String typeName, ClassDeclNode define, Scope fatherScope) {
        super(typeName);
        this.define = define;
        this.fatherScope = fatherScope;
        hasConstructor = false;
    }

    @Override
    public void defineSymbol(Symbol symbol) {
        if (hasConstructor && symbol.ifConstructor())
            throw new SemanticError("Duplicate constructors.", symbol.getPosition());
        hasConstructor = symbol.ifConstructor();
        if (symbolMap.containsKey(symbol.getIdentifier())) {
            throw new RedefError(symbol.getIdentifier(), symbol.getPos());
        }
        symbolMap.put(symbol.getIdentifier(), symbol);

        if (!symbol.isFunction()) {
            fieldNum++;
            fieldIndex.put(symbol.getIdentifier(), fieldNum);
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

    public Type getFieldType(String identifier) {
        Symbol symbol = symbolMap.get(identifier);
        return symbol.getType();
    }
}
