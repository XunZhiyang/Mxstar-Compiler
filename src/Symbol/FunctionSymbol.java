package Symbol;

import AST.FuncDeclNode;
import Utils.Position;
import Utils.RedefError;

import java.util.LinkedHashMap;
import java.util.Map;

public class FunctionSymbol extends Symbol implements Scope {
    Map<String, Symbol> symbolMap = new LinkedHashMap<>();
    private Scope fatherScope;

    public FunctionSymbol(Type type, String identifier, FuncDeclNode define, Scope fatherScope) {
        super(type, identifier, define);
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
}
