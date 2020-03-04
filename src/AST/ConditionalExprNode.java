package AST;

import Utils.Position;

public class ConditionalExprNode extends Expr {
    private Expr condition, opt1, opt2;

    public ConditionalExprNode(Expr condition, Expr opt1, Expr opt2, Position position) {
        super(position);
        this.condition = condition;
        this.opt1 = opt1;
        this.opt2 = opt2;
    }
}
