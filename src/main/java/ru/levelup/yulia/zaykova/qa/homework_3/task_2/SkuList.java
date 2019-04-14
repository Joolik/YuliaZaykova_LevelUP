package ru.levelup.yulia.zaykova.qa.homework_3.task_2;

import java.util.*;

/**
 * Класс для работы со списком товаров
 */
public class SkuList {

    // Константы используются в методе generatePrice()
    public static final double MIN_PRICE = 0.01;
    public static final double MAX_PRICE = 100.00;

    // Максимальный размер списка, генерируемого случайным образом
    public static final int MAX_LIST_SIZE = 50000;

    // Дефолтный список наименований товаров. Используется для генерации имени товара в методе generateName()
    private static final String[] defaultSkuNames = {"Мыло/рук Мистер Чистер", "Шапка-ушанка жен. XL",
                                                     "Перчатки кож. муж. №8", "Рассекатель (газовый)",
                                                     "Шило портновское ####", "Сушка \"Малышок\" 350г'",
                                                     "Пломбир mansikka Suomi", "Масло* слив.жирн.82,5%"};

    // Список товаров
    private List<Sku> skuList = new ArrayList<>();

    // Суммарная стоимость всех товаров в списке
    private double summaryPrice;


    // В конструкторе генерируется список заданного размера и сортируется по возрастанию цены
    public SkuList(int size) {
        initSkuList(size);
    }

    public List<Sku> getSkuList() {
        return skuList;
    }

    // Генерация списка товаров заданного размера. Имя и цена товара выбираются случайным образом
    private void initSkuList(int size) {
        for (int i = 1; i <= size; i++) {
            addSkuToList(new Sku(generateName(), generatePrice()));
        }
    }

    // Добавление товара в список
    public void addSkuToList(Sku s) {
        skuList.add(s);
        summaryPrice += s.getPrice();
        Collections.sort(skuList);
    }

    // Возвращает строку, выбранную случайным образом из массива defaultSkuNames
    private String generateName() {
        int i = (int) (Math.random() * defaultSkuNames.length);
        return defaultSkuNames[i];
    }

    // Возвращает число из диапазона [MIN_PRICE, MAX_PRICE], округленное до двух знаков после запятой
    private double generatePrice() {
        double randomPrice = MIN_PRICE + Math.random() * MAX_PRICE;
        // TODO явное привеление типов тут не требуется
        randomPrice = Math.floor(randomPrice * 100) / 100d;
        return randomPrice;
    }

    // Возвращает среднюю стоимость товаров в списке
    public double avgPrice() {
        // TODO явное привеление типов тут не требуется
        return Math.round(summaryPrice * 100 / skuList.size()) / 100d;
    }

    // Возвращает список товаров в заданном диапазоне цен (включая границы диапазона)
    public List<Sku> getSkuInRange(double minPrice, double maxPrice) {
        List<Sku> outputList = null;
        // TODO Не обязательно явно инициалзировать переменные int indexMin = findSkuByPrice(skuList, minPrice);
        int indexMin = findSkuByPrice(skuList, minPrice);
        if (indexMin >= skuList.size()) {
            outputList = null;
        } else {
            int indexMax = indexMin;
            while ((indexMax < skuList.size()) && (skuList.get(indexMax).getPrice() <= maxPrice)) {
                indexMax++;
            }
            if (indexMax != indexMin) {
                outputList = skuList.subList(indexMin, indexMax);
            } else {
                outputList = null;
            }

        }
        return outputList;
    }

    // Возвращает список товаров, стоимость которых больше средней цены + 20
    public List<Sku> getSkuAvgPrice() {
        List<Sku> outputList = null;

        int index = findSkuByPrice(skuList, avgPrice() + 20.0);
        if (index < skuList.size()) {
            outputList = skuList.subList(index, skuList.size());
        }
        return outputList;
    }

    /* Возвращает имя товара по цене. Если таких товаров несколько, то имя второго товара.
     * Если товаров нет, то возвращает сообщение об отсутствии товара
     */
    public String getNameByPrice (double priceToFind) {
        List<Sku> list = getSkuInRange(priceToFind, priceToFind);
        if (list != null) {
            if (list.size() == 1) {
                return list.get(0).getName();
            } else {
                return list.get(1).getName();
            }
        } else {
            return "Товар не найден!";
        }
    }

    // Поиск первого товара в отсортированном списке, цена которого больше или равна заданному значению
    private int findSkuByPrice(List<Sku> list, double price) {
        int i = 0;
        while ((i < list.size()) && (list.get(i).getPrice() < price)) {
            i++;
        }
        return i;
    }

    // Удаление товара из списка
    public void removeSkuFromList(Sku s) {
        summaryPrice -= s.getPrice();
        skuList.remove(s);
    }
}
