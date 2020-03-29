package Utils;

public class SemanticError extends RuntimeException {
    Position position;

    public SemanticError(String msg, Position position) {
        super(msg);
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "[Semantic Error] " + super.getMessage() + position.toString();
    }
}
