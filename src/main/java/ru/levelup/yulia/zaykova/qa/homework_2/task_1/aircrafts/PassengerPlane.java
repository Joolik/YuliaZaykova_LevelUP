package ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts;

import java.util.Date;

public class PassengerPlane extends Plain {

    public PassengerPlane(String id, String type, double flightRange, double maxLoad, int seatingCapacity, double cruiseSpeed, Date productionDate, int crew, double wingSpan) {
        super(id, type, flightRange, maxLoad, seatingCapacity, cruiseSpeed, productionDate, crew, wingSpan);
    }

    public PassengerPlane(String id, String type, double flightRange, double maxLoad, int seatingCapacity, int crew, double wingSpan) {
        super(id, type, flightRange, maxLoad, seatingCapacity, crew, wingSpan);
    }

    // Назначить на регулярный рейс
    public void sendToFlight() {
        System.out.println("Airplane in flight");
        status = AircraftStatuses.InFlight;
    }

    @Override
    public String toString() {
        return "PassengerPlane{" +
                super.toString() +
                "}";
    }
}
