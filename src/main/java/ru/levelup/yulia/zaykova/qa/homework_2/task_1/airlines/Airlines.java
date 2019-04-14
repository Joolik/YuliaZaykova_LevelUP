package ru.levelup.yulia.zaykova.qa.homework_2.task_1.airlines;

import ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts.*;
import ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts.comparators.*;

import java.util.*;

public class Airlines {

    // Список всех летных средств компании
    private List<Aircraft> airFleet = new ArrayList<>();

    // Суммарная вместимость и грузоподъемность
    private int summarySeatCapacity;
    private double summaryLoad;

    public int getSummarySeatCapacity() {
        return summarySeatCapacity;
    }

    public double getSummaryLoad() {
        return summaryLoad;
    }

    public void addAircraft(Aircraft aircraft) {
        airFleet.add(aircraft);
        summarySeatCapacity += aircraft.getSeatingCapacity();
        summaryLoad += aircraft.getMaxLoad();
    }

    public void removeAircraft(Aircraft aircraft) {
        summarySeatCapacity -= aircraft.getSeatingCapacity();
        summaryLoad -= aircraft.getMaxLoad();
        airFleet.remove(aircraft);
    }

    // Возвращает список, отсортированный по убыванию дальности полета flightRange
    public List<Aircraft> sortByFlightRange() {
        List<Aircraft> resultList = new ArrayList<>(airFleet);
        resultList.sort(new RangeComparator().reversed());
        return resultList;
    }

    // Возвращает список всех самолетов
    public List<Aircraft> findPlains() {
        List<Aircraft> resultList = new ArrayList<>();
        for (Aircraft craft : airFleet) {
            if (craft instanceof Plain) {
                resultList.add(craft);
            }
        }
        if (resultList.size() == 0) {
            return null;
        }
        return resultList;
    }

    // Поиск самолета, соответствующего диапазону параметров
    public List<Aircraft> findPlainByLoad(double minValue, double maxValue) {
        List<Aircraft> resultList = findPlains();
        if (resultList != null) {
            resultList.sort(new LoadComparator());
            return findLoadInRange(resultList, minValue, maxValue);
        }
        return resultList;
    }

    // Список летных средств с грузоподъемность в диапазоне от minValue до maxValue
    public List<Aircraft> findLoadInRange(List<Aircraft> list, double minValue, double maxValue) {
        List<Aircraft> resultList = null;
        int indexMin = findAircraftByMaxLoad(list, minValue);
        if (indexMin >= list.size()) {
            resultList = null;
        } else {
            int indexMax = indexMin;
            while ((indexMax < list.size()) && (list.get(indexMax).getMaxLoad() <= maxValue)) {
                indexMax++;
            }
            if (indexMax != indexMin) {
                resultList = list.subList(indexMin, indexMax);
            } else {
                resultList = null;
            }

        }
        return resultList;
    }

    // Индекс первого элемента в отсортированном списке, maxLoad которого больше или равен заданному значению
    private int findAircraftByMaxLoad(List<Aircraft> list, double value) {
        int i = 0;
        while ((i < list.size()) && (list.get(i).getMaxLoad() < value)) {
            i++;
        }
        return i;
    }

    // Вывод списка всех летных средств на консоль
    public void printList() {
        System.out.println("*** All aircrafts : ");
        for (Aircraft ac : airFleet) {
            System.out.println(ac);
        }
    }

}