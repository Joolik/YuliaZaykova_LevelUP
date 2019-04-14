package ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts;

import java.util.Date;

public class CargoPlane extends Plain implements Rentable {

    public CargoPlane(String id, String type, double flightRange, double maxLoad, double maxVolume, int seatingCapacity, int crew, double wingSpan) {
        super(id, type, flightRange, maxLoad, seatingCapacity, crew, wingSpan);
        super.maxVolume = maxVolume;
    }

    public CargoPlane(String id, String type, double flightRange, double maxLoad, int seatingCapacity, double cruiseSpeed, Date productionDate, int crew, double wingSpan) {
        super(id, type, flightRange, maxLoad, seatingCapacity, cruiseSpeed, productionDate, crew, wingSpan);
    }

    // Аренда грузового самолета
    public void rent() {
        System.out.println("Cargo plain: rent");
        status = AircraftStatuses.InFlight;
    }

    @Override
    public String toString() {
        return "CargoPlane{" +
                super.toString() +
                ", maxVolume=" + super.maxVolume +
                "}";
    }
}
