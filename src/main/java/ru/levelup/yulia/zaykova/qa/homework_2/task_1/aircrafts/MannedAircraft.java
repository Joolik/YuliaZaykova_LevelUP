package ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts;

import java.util.Date;

// Пилотируемые воздушные суда
public abstract class MannedAircraft extends Aircraft {

    // Экипаж, кол-во человек
    protected int crew;

    // Максимальный объем грузов, м^3
    protected double maxVolume;

    // Размер грузового отсека
    protected String sizeCargoHold;

    // Размер грузового люка
    protected String sizeCargoDoor;


    protected MannedAircraft(String id, String type, double flightRange, double maxLoad, int seatingCapacity, int crew) {
        super(id, type, flightRange, maxLoad, seatingCapacity);
        this.crew = crew;
    }

    protected MannedAircraft(String id, String type, double flightRange, double maxLoad, int seatingCapacity, double cruiseSpeed, Date productionDate, int crew) {
        super(id, type, flightRange, maxLoad, seatingCapacity, cruiseSpeed, productionDate);
        this.crew = crew;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", crew=" + crew;
    }
}
