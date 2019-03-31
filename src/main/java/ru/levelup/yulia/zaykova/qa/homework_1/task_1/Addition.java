package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

/**
 * Сложение чисел
 * int int
 * long long
 * double double
 */
public class Addition {

    public double add(int x, int y) {
        return (double) (x + y);
    }

    public double add(long x, long y) {
        return (double) (x + y);
    }

    public double add(double x, double y) {
        return x + y;
    }

    public void addResult(Number arg1, Number arg2) {
        if ((arg1 instanceof Double) || (arg2 instanceof Double)) {
            System.out.println(arg1.doubleValue() + " + " + arg2.doubleValue() + " = " +
                    add(arg1.doubleValue(), arg2.doubleValue()));
        } else if ((arg1 instanceof Long) || (arg2 instanceof Long)) {
            System.out.println(arg1.longValue() + " + " + arg2.longValue() + " = " +
                    add(arg1.longValue(), arg2.longValue()));
        } else {
            System.out.println(arg1.intValue() + " + " + arg2.intValue() + " = " +
                    add(arg1.intValue(), arg2.intValue()));
        }
    }
}
