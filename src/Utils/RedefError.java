package Utils;

public class RedefError extends SemanticError {
    public RedefError(String msg, Position position) {
        super("Redefinition of '" + msg + "'.", position);
    }
}
