package ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts;

import java.util.Date;

public class Helicopter extends MannedAircraft implements Rentable {

    // Диаметр винта, м
    private double diameterRotor;

    public Helicopter(String id, String type, double flightRange, double maxLoad, int seatingCapacity, int crew, double diameterRotor) {
        super(id, type, flightRange, maxLoad, seatingCapacity, crew);
        this.diameterRotor = diameterRotor;
    }

    public Helicopter(String id, String type, double flightRange, double maxLoad, int seatingCapacity, double cruiseSpeed, Date productionDate, int crew, double diameterRotor) {
        super(id, type, flightRange, maxLoad, seatingCapacity, cruiseSpeed, productionDate, crew);
        this.diameterRotor = diameterRotor;
    }

    // Аренда вертолета
    public void rent() {
        System.out.println("Helicopter: rent");
        super.status = AircraftStatuses.InFlight;
    }

    @Override
    public String toString() {
        return "Helicopter{" +
                super.toString() +
                ", diameterRotor=" + diameterRotor +
                '}';
    }
}