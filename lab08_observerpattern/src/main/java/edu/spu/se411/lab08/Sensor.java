
package edu.spu.se411.lab08;

import java.util.ArrayList;
import java.util.List;

public class Sensor implements Subject, Cloneable {

    private String sensorType;
    private double reading;
    private List<Observer> observers;

    public Sensor(String sensorType) {
        this.sensorType = sensorType;
        this.observers = new ArrayList<>();
    }

    public String getSensorType() {
        return sensorType;
    }

    public double getReading() {
        return reading;
    }

    public void setReading(double reading) {
        this.reading = reading;
        notifyObservers();
    }

    @Override
    public void register(Observer observer) {
        if (observer != null && !observers.contains(observer)) {
            observers.add(observer);
        }
    }

    @Override
    public void unregister(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : new ArrayList<>(observers)) {
            observer.update(this);
        }
    }

    @Override
    public Sensor clone() {
        try {
            Sensor clonedSensor = (Sensor) super.clone();
            clonedSensor.observers = new ArrayList<>();
            return clonedSensor;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(e);
        }
    }
}
