package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

import java.io.*;

/**
 *  Класс для ввода данных с консоли через BufferedReader
 */
public class BufReaderInput {

    /** Ссылка на BufferedReader */
    BufferedReader bufReader;

    /** Поле для записи числа, введенного с консоли */
    Number num = null;

    /** Поле для строки, введенной в консоли*/
    private String line;


    /**
     * Конструктор
     *
     * @param bufReader
     */
    public BufReaderInput(BufferedReader bufReader) {
        this.bufReader = bufReader;
    }

    /**
     * Ввод с консоли числа типа Integer, Long или Double
     *
     * @param prompt Строка-приглашение
     * @return Введенное с консоли число типа Number
     * @throws IOException
     */
    public Number inputNumber(String prompt) throws IOException {
        input(prompt);
        num = new ParseString().parseNumber(line);
        while (num == null) {
            input("Wrong number format! Try again.  " + prompt);
            num = new ParseString().parseNumber(line);
        }
        return num;
    }

    /**
     * Ввод с консоли числа типа Integer
     *
     * @param prompt Строка-приглашение
     * @return Введенное с консоли целое число типа Number
     * @throws IOException
     */
    public Number inputIntNumber(String prompt) throws IOException {
        input(prompt);
        num = new ParseString().parseNumber(line);
        while ((num == null) || !(num instanceof Integer)) {
            input("It's not an integer number! Try again.  " + prompt);
            num = new ParseString().parseNumber(line);
        }
        return num;
    }

    /**
     * Ввод с консоли строки, соответствующей заданному шаблону.
     * Пробелы в начале и конце строки обрезаются.
     *
     * @param prompt Строка-приглашение
     * @param pattern Шаблон для ввода
     * @return Введенная с консоли строка
     * @throws IOException
     */
    public char inputCharByPattern(String prompt, String pattern) throws IOException {
        do {
            input(prompt);
        } while (!line.trim().matches(pattern));
        return line.trim().charAt(0);
    }

    public String inputStringByPattern(String prompt, String pattern) throws IOException {
        do {
            input(prompt);
        } while (!line.trim().matches(pattern));
        return line.trim();
    }

    /**
     * Вывод на консоль строки-приглашения prompt и считывание введенной с консоли строки в переменную line
     *
     * @param prompt Строка-приглашение
     * @throws IOException
     */
    private void input(String prompt) throws IOException {
        System.out.print(prompt + " ");
        line = bufReader.readLine();
    }



}
