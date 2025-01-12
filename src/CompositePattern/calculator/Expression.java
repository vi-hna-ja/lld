package CompositePattern.calculator;

public class Expression implements ArithmeticExpression {

    ArithmeticExpression left;
    ArithmeticExpression right;
    Operation operation;

    public Expression(Operation operation, ArithmeticExpression left, ArithmeticExpression right) {
        this.operation = operation;
        this.left = left;
        this.right = right;
    }

    @Override
    public int evaluate() {
        return switch (operation) {
            case ADD -> left.evaluate() + right.evaluate();
            case SUB -> left.evaluate() - right.evaluate();
            case MUL -> left.evaluate() * right.evaluate();
            case DIV -> left.evaluate() / right.evaluate();
        };
    }
}
