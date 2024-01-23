package asish.kdu.entities;

import asish.kdu.exceptions.VehicleException;

/**
 * Vehicle class with all its attributes, getters and setters
 */
public class Vehicle {

    private Tyre tyre;

    public Tyre getTyre() {
        return tyre;
    }

    public void setTyre(Tyre tyre) {
        this.tyre = tyre;
    }

    public Speaker getSpeaker() {
        return speaker;
    }

    public void setSpeaker(Speaker speaker) {
        this.speaker = speaker;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private Speaker speaker;
    private double price;

    public Vehicle(Tyre tyre, Speaker speaker, double price) throws VehicleException {
        if(tyre == null || speaker == null || price < 0) {
            throw new VehicleException("Invalid Parameters for Vehicle", new IllegalArgumentException());
        }

        this.tyre = tyre;
        this.speaker = speaker;
        this.price = price + tyre.getPrice() + speaker.getPrice();
    }

    /**
     * THe default toString() is overridden to have a nice output in logger
     * @return Nicely formatted String
     */
    @Override
    public String toString() {
        return "Vehicle(tyre - " + tyre.getBrand() + "\n" +
                "vehicleTotalPrice - " + price + "\n" +
                "tyrePrice - " + tyre.getPrice() + "\n" +
                "speaker - " + speaker.getBrand() + "\n" +
                "speakerPrice - " + speaker.getPrice() + ")\n";
    }
}