package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

public class Factorial {

    /**
     * Вычисление факториала
     *
     * @param n >= 0
     * @return n! = 1*2*..*n, 0!=1.
     * @return -1 при переполнении
     * @return 0, если входной параметр n < 0
     */
    public double factorial(int n){
        double result = 1;

        if (n == 0) {
            return 1;
        } else if (n < 0) {
            return 0;
        } else {

            // Вычисление n!
            for (int i=2; i<=n; i++) {
                result *= i;

                // Проверка на переполнение
                if ((result <= 0) || (result == Double.POSITIVE_INFINITY) || (result == Double.NEGATIVE_INFINITY)) {
                    return -1;
                }
            }
        }
        return result;
    }
}
