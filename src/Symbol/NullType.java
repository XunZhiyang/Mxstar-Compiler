package Symbol;

public class NullType extends Type {
    public NullType() {
        super("null");
    }

    @Override
    public boolean isNull() {
        return true;
    }
}
