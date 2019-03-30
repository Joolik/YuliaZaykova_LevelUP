package ru.levelup.yulia.zaykova.qa.homework_3.task_2;

import java.io.*;
import java.util.*;


public class SkuCollections {

    public static void main(String[] args) {
        SkuCollections obj = new SkuCollections();
        obj.start();
    }

    /*
     * Создается список товаров и вызываются следующие методы:
     *  1) возвращающий список товаров, стоимость которых больше средней цены + 20
     *  2) возвращает список товаров в заданном диапазоне цен
     *  3) возвращает имя товара по заданной цене
     */
    public void start() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        SkuList list;
        int listSize = 1;
        double minPrice, maxPrice;
        double skuPrice;

        try {

            /*
             * Ввод с консоли размера списка товаров
             * Вывод на консоль сгенерированного списка, отсортированного по возрастанию цены
             */
            listSize = NumberInput.inputListSize("Задайте число товаров в списке: ", reader);
            list = new SkuList(listSize);
            System.out.println("Сгенерирован список товаров:");
            printList(list.getSkuList());
            System.out.println("\n");

            /*
             * Вывод на консоль списка товаров, стоимость которых больще средней цены + 20
             */
            System.out.println("1. Список товаров, стоимость которых больще средней цены + 20.");
            System.out.println("Средняя цена = " + list.avgPrice() + "\nСредняя цена + 20 = " + (list.avgPrice() + 20.0));
            System.out.println();
            printList(list.getSkuAvgPrice());
            System.out.println("\n");

            /*
             * Ввод с консоли диапазона цен
             * Вывод на консоль списка товаров в данном диапазоне
             */
            System.out.println("2. Найти товары в заданном диапазоне цен.");
            double[] priceRange = NumberInput.inputRange(reader);
            minPrice = priceRange[0];
            maxPrice = priceRange[1];
            System.out.println();
            printList(list.getSkuInRange(minPrice, maxPrice));
            System.out.println("\n");

            /*
             * Ввод с консоли цены для поиска товара
             * Вывод на консоль результата
             */
            System.out.println("3. Вывести имя товара по заданной цене.");
            skuPrice = NumberInput.inputDouble("Введите цену товара: ", reader);
            System.out.println(list.getNameByPrice(skuPrice));
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    // Метод для вывода списка товаров на консоль
    public void printList(List<Sku> list) {
        if (list != null) {
            for (Sku s : list) {
                System.out.println(list.indexOf(s) + "  " + s);
            }
        } else {
            System.out.println("Товары не найдены!");
        }
    }
}
