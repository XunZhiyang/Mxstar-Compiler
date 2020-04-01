package Utils;

public class SyntaxError extends RuntimeException {

    Position position;

    public SyntaxError(String msg, Position position) {
        super(msg);
        this.position = position;
    }

    @Override
    public String getMessage() {
        return "[Syntax Error] " + super.getMessage() + " " + position.toString();
    }
}

