package ru.levelup.yulia.zaykova.qa.homework_1.task_1;

/**
 * Класс для преобразования строки в число
 */
public class ParseString {

    /**
     * Преобразование строки в число типа Integer, Long или Double
     *
     * @param str Строка
     * @return Число типа Number или null, если строка не может быть преобразована в число
     */
    public static Number parseNumber(String str) {
        if (isInteger(str)) {
            return new Integer(str);
        } else if (isLong(str)) {
            return new Long(str);
        } else if (isDouble(str)) {
            return Double.valueOf(str);
        }
        return null;
    }

    /**
     * Проверка возможности преобразования строки в число типа int
     *
     * @param str Строка
     * @return Возвращает true, если строка может быть преобразована в int, иначе false
     */
    private static boolean isInteger(String str) {
        try {
            Integer.parseInt(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Проверка возможности преобразования строки в число типа long
     *
     * @param str Строка
     * @return Возвращает true, если строка может быть преобразована в long, иначе false
     */
    private static boolean isLong(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Проверка возможности преобразования строки в число типа double
     *
     * @param str Строка
     * @return Возвращает true, если строка может быть преобразована в double, иначе false
     */
    private static boolean isDouble(String str) {
        try {
            Double.parseDouble(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}
