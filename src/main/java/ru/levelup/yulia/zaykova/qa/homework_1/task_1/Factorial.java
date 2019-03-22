package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

public class Factorial {
    public long factorial(int n){
        if ((n == 1) || (n == 0)) {
            return 1;
        } if (n < 0) {
            return -1;
        }
        return n*factorial(n-1);
    }
}
