package Symbol;

import Utils.Position;

public class LocalScope extends BaseScope {
    private Scope fatherScope;

    public LocalScope(Scope fatherScope) {
        this.fatherScope = fatherScope;
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
