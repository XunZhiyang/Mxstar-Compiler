package Symbol;

import AST.ClassDeclNode;
import Utils.Position;
import Utils.RedefError;

import java.util.LinkedHashMap;
import java.util.Map;

public class ClassType extends Type implements Scope {
    Map<String, Symbol> symbolMap = new LinkedHashMap<>();
    private Scope fatherScope;
    private ClassDeclNode define;

    public ClassType(String typeName, ClassDeclNode define, Scope fatherScope) {
        super(typeName);
        this.define = define;
        this.fatherScope = fatherScope;
    }

    @Override
    public void defineSymbol(Symbol symbol) {
        if (symbolMap.containsKey(symbol.getName())) {
            throw new RedefError(symbol.getName(), symbol.getPos());
        }
        symbolMap.put(symbol.getName(), symbol);
    }

    @Override
    public Symbol getSymbol(String identifier, Position position) {
        Symbol symbol = symbolMap.get(identifier);
        if (symbol == null)
            return fatherScope.getSymbol(identifier, position);
        else
            return symbol;
    }

//    public Type getType(String identifier, Position position) {
//        Type type = syolMap.get(identifier);
//        if (symbol == null)
//            return fatherScope.getSymbol(identifier, position);
//        else
//            return symbol;
//    }

    public Position getPos() {
        return define.getPosition();
    }
}
