package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

/**
 * Возведение числа (int, long, double) в степень (int)
 */
public class Power {

    /**
     * Возведение числа в степень
     *
     * @param base
     * @param p
     * @return Возвращает значение типа double или Infinity при возведении нуля в отрицательную степень
     */
    public double power(int base, int p) {
        double result = 1;

        if ((p == 0) || (base == 1)) {
            return 1;
        } else if (base == 0) {
            result = (p > 0) ? 0 : Double.POSITIVE_INFINITY;
            return result;
        } else {
            for (int i = 1; i <= abs(p); i++) {
                result *= base;
            }
        }

        if (p < 0) {
            result = 1 / result;
        }
        return result;
    }

    public double power(long base, int p) {
        double result = 1;

        if ((p == 0) || (base == 1)) {
            return 1;
        } else if (base == 0) {
            result = (p > 0) ? 0 : Double.POSITIVE_INFINITY;
            return result;
        } else {
            for (int i = 1; i <= abs(p); i++) {
                result *= base;
            }
        }

        if (p < 0) {
            result = 1 / result;
        }
        return result;
    }

    public double power(double base, int p) {
        double result = 1;

        if ((p == 0) || (base == 1)) {
            return 1;
        } else if (base == 0) {
            result = (p > 0) ? 0 : Double.POSITIVE_INFINITY;
            return result;
        } else {
            for (int i = 1; i <= abs(p); i++) {
                result *= base;
            }
        }

        if (p < 0) {
            result = 1 / result;
        }
        return result;
    }

    public void powResult(Number arg1, Number arg2) {
        if (arg1 instanceof Double) {
            System.out.println(arg1.doubleValue() + " ^ " + arg2.intValue() + " = " +
                    power(arg1.doubleValue(), arg2.intValue()));
        } else if (arg1 instanceof Long) {
            System.out.println(arg1.longValue() + " ^ " + arg2.intValue() + " = " +
                    power(arg1.longValue(), arg2.intValue()));
        } else {
            System.out.println(arg1.intValue() + " ^ " + arg2.intValue() + " = " +
                    power(arg1.intValue(), arg2.intValue()));
        }
    }

    /**
     * Определение модуля целого числа
     *
     * @param p
     * @return Модуль числа p
     */
    private int abs(int p) {
        return (p >= 0) ? p : -p;
    }


}
