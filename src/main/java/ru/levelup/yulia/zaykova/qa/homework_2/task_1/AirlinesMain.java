package ru.levelup.yulia.zaykova.qa.homework_2.task_1;

import ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts.*;
import ru.levelup.yulia.zaykova.qa.homework_2.task_1.airlines.Airlines;

import java.util.List;

public class AirlinesMain {

    public static void main(String[] args) {

        // Создание авиакомпании
        Airlines boomerang = new Airlines();

        // Добавление летных средств в парк компании
        boomerang.addAircraft(new Helicopter("Copter01", "Robinson R66", 648, 12500, 8, 1, 10.6));
        boomerang.addAircraft(new PassengerPlane("PsPlane01", "Boeing 747-400", 13430, 70620, 522, 5, 64.4));
        boomerang.addAircraft(new CargoPlane("CgPlane01", "Boeing 737-400SF", 2800, 20000, 121.5, 0, 2, 42.1));
        boomerang.addAircraft(new PassengerPlane("PsPlane02", "АН-24", 2000, 5300, 48, 2, 29.2));
        boomerang.addAircraft(new PassengerPlane("PsPlane03", "Катран", 580, 12, 1, 1, 15.4));
        boomerang.addAircraft(new Quadrocopter("QuadroCop01", "Parrot Bebop", 0.250, 0.5, 0));


        // Вывод на экран списка летных средств
        boomerang.printList();
        System.out.println();

        // Вывод общей вместимости и грузоподъемности
        System.out.println("Summary seating capacity : " + boomerang.getSummarySeatCapacity());
        System.out.println("Summary load, kg : " + boomerang.getSummaryLoad());
        System.out.println();

        // Сортировка летных средств по дальности полета, по убыванию
        System.out.println("** Aircrafts, sorted by flightRange (from max to min): ");
        AirlinesMain.printList(boomerang.sortByFlightRange());
        System.out.println();

        // Поиск самолетов с грузоподъемностью в заданном диапазоне
        double minLoad = 5000;
        double maxLoad = 20000;
        System.out.println("** Find plains with maxLoad between " + minLoad + " and " + maxLoad + " : ");
        AirlinesMain.printList(boomerang.findPlainByLoad(minLoad, maxLoad));
        System.out.println();
    }

    // Вывод элементов списка на консоль
    public static void printList(List<Aircraft> list) {
        if (list != null) {
            for (Aircraft s : list) {
                System.out.println(s);
            }
        } else {
            System.out.println("Empty list!");
        }
    }
}
