package ru.levelup.yulia.zaykova.qa.homework_2.task_1.aircrafts;

import java.util.Date;

// Общий родительский класс для всех летных средств
public abstract class Aircraft {

    // Идентификатор воздушного средства
    protected String id;

    // Тип воздушного средства
    protected String type;

    // Дальность полета, км
    protected double flightRange;

    // Грузоподъемность, кг
    protected double maxLoad;

    // Пассажировместимость
    protected int seatingCapacity;

    // Крейсерская скорость
    protected double cruiseSpeed;

    // Текущее состояние
    protected AircraftStatuses status;

    // Дата выпуска
    protected Date productionDate;

    // Дата последнего тех.осмотра
    protected Date lastServiceDate;


    protected Aircraft(String id, String type, double flightRange, double maxLoad, int seatingCapacity) {
        this.id = id;
        this.type = type;
        this.flightRange = flightRange;
        this.maxLoad = maxLoad;
        this.seatingCapacity = seatingCapacity;
        this.status = AircraftStatuses.Ready;
    }

    protected Aircraft(String id, String type, double flightRange, double maxLoad, int seatingCapacity, double cruiseSpeed, Date productionDate) {
        this.id = id;
        this.type = type;
        this.flightRange = flightRange;
        this.maxLoad = maxLoad;
        this.seatingCapacity = seatingCapacity;
        this.cruiseSpeed = cruiseSpeed;
        this.productionDate = productionDate;
        this.status = AircraftStatuses.Ready;
    }

    public double getFlightRange() {
        return flightRange;
    }

    public double getMaxLoad() {
        return maxLoad;
    }

    public int getSeatingCapacity() {
        return seatingCapacity;
    }

    public String getType() {
        return type;
    }

    // Отправить на тех.обслуживание
    public void sendToMaintenance() {
        System.out.println("Aircraft: status set to Maintenance");
        status = AircraftStatuses.Maintenance;
    }

    // Вернуть с тех.обслуживания
    public void returnFromMaintenance() {
        System.out.println("Aircraft: Maintenance is over");
        status = AircraftStatuses.Ready;
        lastServiceDate = new Date();
    }

    @Override
    public String toString() {
        return  "id='" + id + '\'' +
                ", type='" + type + '\'' +
                ", flightRange=" + flightRange +
                ", maxLoad=" + maxLoad +
                ", seatingCapacity=" + seatingCapacity +
                ", status=" + status;
    }
}