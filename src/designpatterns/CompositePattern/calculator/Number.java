package designpatterns.CompositePattern.calculator;

public class Number implements ArithmeticExpression {
    int value;
    public Number(int value) {
        this.value = value;
    }

    @Override
    public int evaluate() {
        return this.value;
    }
}
