package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

public class Power {

    public double power(int base, int p) {
        double result=1;

        if ((p == 0) || (base == 1)) {
            return 1;
        } else if (base == 0) {
            result = (p > 0) ? 0 : Double.POSITIVE_INFINITY;
        } else {
            for(int i=1; i <= abs(p); i++) {
                result *= base;
            }
        }

        if (p < 0){
            result = 1 / result;
        }
        return result;
    }

    public double power(long base, int p) {
        double result=1;

        if ((p == 0) || (base == 1)) {
            return 1;
        } else if (base == 0) {
            result = (p > 0) ? 0 : Double.POSITIVE_INFINITY;
        } else {
            for(int i=1; i <= abs(p); i++) {
                result *= base;
            }
        }

        if (p < 0){
            result = 1 / result;
        }
        return result;
    }

    public double power(double base, int p) {
        double result=1;

        if ((p == 0) || (base == 1)) {
            return 1;
        } else if (base == 0) {
            result = (p > 0) ? 0 : Double.POSITIVE_INFINITY;
            return result;
        } else {
            for(int i=1; i <= abs(p); i++) {
                result *= base;
            }
        }

        if (p < 0){
            result = 1 / result;
        }
        return result;
    }

    int abs(int p) {
        return (p >= 0) ? p : -p;
    }

}
