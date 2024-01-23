package asish.kdu.entities;

import asish.kdu.enums.TyreBrand;

/**
 * Tyre class with all its attributes, getters and setters
 */
public class Tyre {

    private TyreBrand brand;

    public TyreBrand getBrand() {
        return brand;
    }

    public void setBrand(TyreBrand brand) {
        this.brand = brand;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    private double price;

    public Tyre(TyreBrand brand, double price) {
        this.brand = brand;
        this.price = price;
    }
}
