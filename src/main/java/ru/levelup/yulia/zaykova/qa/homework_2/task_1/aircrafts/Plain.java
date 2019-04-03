package ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts;

import java.util.Date;

public abstract class Plain extends MannedAircraft {

    // Размах крыла, м
    protected double wingSpan;

    public Plain(String id, String type, double flightRange, double maxLoad, int seatingCapacity, int crew, double wingSpan) {
        super(id, type, flightRange, maxLoad, seatingCapacity, crew);
        this.wingSpan = wingSpan;
    }

    public Plain(String id, String type, double flightRange, double maxLoad, int seatingCapacity, double cruiseSpeed, Date productionDate, int crew, double wingSpan) {
        super(id, type, flightRange, maxLoad, seatingCapacity, cruiseSpeed, productionDate, crew);
        this.wingSpan = wingSpan;
    }

    @Override
    public String toString() {
        return  super.toString() +
                ", wingSpan=" + wingSpan;
    }
}
