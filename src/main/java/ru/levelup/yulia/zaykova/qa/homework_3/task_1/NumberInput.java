package ru.levelup.yulia.zaykova.qa.homework_3.task_1;

import java.io.*;

public class NumberInput {

    public double inputDouble(String prompt, BufferedReader reader) throws IOException {
        String line;
        System.out.print(prompt);
        line = reader.readLine();
        while (!isDouble(line)) {
            System.out.print(" Wrong number format! Try again. " + prompt);
            line = reader.readLine();
        }
        return Double.parseDouble(line);
    }

    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
