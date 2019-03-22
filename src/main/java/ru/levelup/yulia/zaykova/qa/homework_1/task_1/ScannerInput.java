package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

import java.util.Scanner;

public class ScannerInput {
    Scanner scanner;
    Number num = null;
    private String str;

    public ScannerInput(Scanner scanner) {
        this.scanner = scanner;
    }

    public Number inputNumber(String text) {
        input(text);
        while (num == null) {
            input("Wrong number format! Try again.  " + text);
        }
        return num;
    }

    public Number inputIntNumber(String text) {
        input(text);
        while ( (num == null) || !(num instanceof Integer) ) {
            input("It's not an integer number! Try again.  " + text);
        }
        return num;
    }

    public Number inputIntPositiveNumber(String text) {
        input(text);
        while ( (num == null) || !(num instanceof Integer) || (num.intValue() < 0) ) {
            input("Integer number must be >=0 ! Try again.  " + text);
        }
        return num;
    }

    private void input(String text) {
        System.out.print(text + " ");
        str = scanner.nextLine();
        num = new ParseString().parseNumber(str);
    }

    public String inputStringByPattern(String text, String pattern) {
        do {
            System.out.print(text + " ");
            str = scanner.nextLine();
        } while (!str.matches(pattern));
        return str;
    }

    public void resetNumber() {
        num = null;
    }



}
