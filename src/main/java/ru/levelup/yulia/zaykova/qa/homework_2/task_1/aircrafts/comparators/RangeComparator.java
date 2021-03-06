package ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts.comparators;

import ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts.Aircraft;

import java.util.Comparator;

public class RangeComparator implements Comparator<Aircraft> {

        public int compare(Aircraft craft1, Aircraft craft2) {
            return (int) (craft1.getFlightRange() - craft2.getFlightRange());
        }
}
