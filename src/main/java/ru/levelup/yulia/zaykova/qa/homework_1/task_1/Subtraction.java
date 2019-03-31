package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

/**
 * Вычитание чисел
 * int int
 * long long
 * double double
 */
public class Subtraction {

    public double subtract(int x, int y) {
        return (double) (x - y);
    }

    public double subtract(long x, long y) {
        return (double) (x - y);
    }

    public double subtract(double x, double y) {
        return x - y;
    }

    public void subResult(Number arg1, Number arg2) {
        if ((arg1 instanceof Double) || (arg2 instanceof Double)) {
            System.out.println(arg1.doubleValue() + " - " + arg2.doubleValue() + " = " +
                    subtract(arg1.doubleValue(), arg2.doubleValue()));
        } else if ((arg1 instanceof Long) || (arg2 instanceof Long)) {
            System.out.println(arg1.longValue() + " - " + arg2.longValue() + " = " +
                    subtract(arg1.longValue(), arg2.longValue()));
        } else {
            System.out.println(arg1.intValue() + " - " + arg2.intValue() + " = " +
                    subtract(arg1.intValue(), arg2.intValue()));
        }
    }
}
