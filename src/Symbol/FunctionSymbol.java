package Symbol;

import AST.FuncDeclNode;
import Utils.Position;
import Utils.RedefError;

import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionSymbol extends Symbol implements Scope {
    Map<String, Symbol> symbolMap = new LinkedHashMap<>();
    private Scope fatherScope;
    private boolean isConstructor;

    public FunctionSymbol(Type type, String identifier, FuncDeclNode define, Scope fatherScope) {
        super(type, identifier, define);
        this.fatherScope = fatherScope;
        if (define == null)
            isConstructor = false;
        else
           isConstructor = define.getIsConstructor();
    }

    public int mapSize() {
        return symbolMap.size();
    }

    @Override
    public void defineSymbol(Symbol symbol) {
        if (symbolMap.containsKey(symbol.getIdentifier())) {
            throw new RedefError(symbol.getIdentifier(), symbol.getPos());
        }
        symbolMap.put(symbol.getIdentifier(), symbol);
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
    public boolean ifConstructor() {
        return isConstructor;
    }

    public void setConstructor(boolean isConstructor) {
        this.isConstructor = isConstructor;
    }
}
