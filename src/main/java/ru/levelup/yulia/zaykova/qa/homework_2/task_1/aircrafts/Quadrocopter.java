package ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts;

import java.util.Date;

public class Quadrocopter extends Drone {

    public Quadrocopter(String id, String type, double flightRange, double maxLoad, int seatingCapacity) {
        super(id, type, flightRange, maxLoad, seatingCapacity);
    }

    public Quadrocopter(String id, String type, double flightRange, double maxLoad, int seatingCapacity, double cruiseSpeed, Date productionDate) {
        super(id, type, flightRange, maxLoad, seatingCapacity, cruiseSpeed, productionDate);
    }

    @Override
    public String toString() {
        return "Quadrocopter{" +
                super.toString() +
                "}";
    }
}
