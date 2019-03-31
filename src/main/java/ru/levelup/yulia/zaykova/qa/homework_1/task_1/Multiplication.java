package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

/**
 * Умножение чисел
 * int int
 * long long
 * double double
 */
public class Multiplication {

    public double multiply(int x, int y) {
        return (double) (x * y);
    }

    public double multiply(long x, long y) {
        return (double) (x * y);
    }

    public double multiply(double x, double y) {
        return x * y;
    }

    public void multResult(Number arg1, Number arg2) {
        if ((arg1 instanceof Double) || (arg2 instanceof Double)) {
            System.out.println(arg1.doubleValue() + " * " + arg2.doubleValue() + " = " +
                    multiply(arg1.doubleValue(), arg2.doubleValue()));
        } else if ((arg1 instanceof Long) || (arg2 instanceof Long)) {
            System.out.println(arg1.longValue() + " * " + arg2.longValue() + " = " +
                    multiply(arg1.longValue(), arg2.longValue()));
        } else {
            System.out.println(arg1.intValue() + " * " + arg2.intValue() + " = " +
                    multiply(arg1.intValue(), arg2.intValue()));
        }
    }
}
