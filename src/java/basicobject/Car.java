package basicobject;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author overw
 */
public class Car {
    private String carID;
    private String carName;
    private String description;
    private double price;
    private int speed;
    private boolean status = false;

    public Car() {
    }

    public Car(String carID, String carName, String description, double price, int speed, boolean status) {
        this.carID = carID;
        this.carName = carName;
        this.description = description;
        this.price = price;
        this.speed = speed;
        this.status = status;
    }
    
    public String getCarID() {
        return carID;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public String getCarName() {
        return carName;
    }

    public void setCarName(String carName) {
        this.carName = carName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
    
    
}
