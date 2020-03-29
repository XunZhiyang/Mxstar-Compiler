package Symbol;

import Utils.Position;

public interface Scope {
    void defineSymbol(Symbol symbol);

    Symbol getSymbol(String identifier, Position position);
}
