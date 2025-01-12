package CompositePattern;

import CompositePattern.calculator.Expression;
import CompositePattern.calculator.Number;
import CompositePattern.calculator.Operation;
import CompositePattern.filesystem.Directory;
import CompositePattern.filesystem.File;

public class CompositeMain {

    public void runFile() {
        File fileOne = new File("fileOne");
        Directory parentDirectory = new Directory("Parent directory");
        Directory childDirectory = new Directory("Child directory");
        File childFileOne = new File("child one");
        File childFileTwo = new File("child two");

        childDirectory.add(childFileOne);
        childDirectory.add(childFileTwo);
        parentDirectory.add(childDirectory);
        parentDirectory.add(fileOne);

        parentDirectory.ls();
    }

    public void runCalc() {
        Number one = new Number(1);
        Number two = new Number(2);
        Number seven = new Number(7);

        Expression expression = new Expression(Operation.DIV, seven, new Expression(Operation.SUB, one, two));
        System.out.println("value: " + expression.evaluate());
    }
}
