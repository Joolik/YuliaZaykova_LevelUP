package ru.levelup.yulia.zaykova.qa.homework_3.task_2;

/**
 * Класс товара
 */
public class Sku implements Comparable<Sku> {
    String name;
    double price;

    public Sku(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sku{" +
                "name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public int compareTo(Sku o) {
        int i = -1;
        if (this.price > o.price) {
            i = 1;
        } else if (this.price == o.price) {
            i = 0;
        }
        return i;
    }
}
