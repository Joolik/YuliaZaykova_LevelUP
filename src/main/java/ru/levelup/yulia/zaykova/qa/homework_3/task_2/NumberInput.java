package ru.levelup.yulia.zaykova.qa.homework_3.task_2;

import java.io.*;

/**
 * Класс для ввода данных с консоли через BufferedReader
 */
public class NumberInput {

    /**
     * Ввод с консоли
     *
     * @param prompt
     * @param reader
     * @return
     * @throws IOException
     */
    public static double inputDouble(String prompt, BufferedReader reader) throws IOException {
        String line;
        System.out.print(prompt);
        line = reader.readLine();
        while (!isDouble(line)) {
            System.out.print("ERROR: неверный формат. " + prompt);
            line = reader.readLine();
        }
        return Double.parseDouble(line);
    }

    public static int inputListSize(String prompt, BufferedReader reader) throws IOException {
        String line;
        System.out.print(prompt);
        line = reader.readLine();
        while ((!isInteger(line)) || (Integer.parseInt(line) <= 0) || (Integer.parseInt(line) > SkuList.MAX_LIST_SIZE)) {
            System.out.print("ERROR: число должно быть целым от 1 до " + SkuList.MAX_LIST_SIZE + "\n" + prompt);
            line = reader.readLine();
        }
        return Integer.parseInt(line);
    }

    public static double[] inputRange(BufferedReader reader) throws IOException {
        double[] range = new double[2];
        range[0] = inputDouble("Введите минимальную цену: ", reader);
        range[1] = inputDouble("Введите максимальную цену: ", reader);
        while (range[1] < range[0]) {
            System.out.println("ERROR: максимальная цена не может быть меньше минимальной");
            range[1] = inputDouble("Введите максимальную цену: ", reader);
        }
        return range;
    }

    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
