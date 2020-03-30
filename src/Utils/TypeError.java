package Utils;

public class TypeError extends SemanticError {
    public TypeError(Position position) {
        super("Type not compatible.", position);
    }
}
