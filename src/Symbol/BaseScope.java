package Symbol;

import Utils.RedefError;
import Utils.SemanticError;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class BaseScope implements Scope {
    Map<String, Symbol> symbolMap = new LinkedHashMap<>();

    @Override
    public void defineSymbol(Symbol symbol) {
        String name = symbol.getName();
        if (symbolMap.containsKey(name)) {
            throw new RedefError(name, symbol.getPos());
        } else {
            symbolMap.put(name, symbol);
            symbol.setScope(this);
        }
    }


}