package ru.levelup.yulia.zaykova.qa.homework_3.task_1;

import java.io.*;

/**
 * Класс для ввода чисел с консоли
 */
public class NumberInput {

    // Возвращает число, введенное с консоли
    public double inputDouble(String prompt, BufferedReader reader) throws IOException {
        System.out.print(prompt);
        String line = reader.readLine();
        while (!isDouble(line)) {
            System.out.print(" Wrong number format! Try again. " + prompt);
            line = reader.readLine();
        }
        return Double.parseDouble(line);
    }

    // Проверяет преобразование строки в число double
    private boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
