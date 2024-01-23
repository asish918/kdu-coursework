package asish.kdu.entities;

import asish.kdu.enums.SpeakerBrand;
import asish.kdu.exceptions.SpeakerException;

/**
 * Speaker class with all its attributes, getters and setters
 */
public class Speaker {
    private SpeakerBrand brand;
    private double price;

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public SpeakerBrand getBrand() {
        return brand;
    }

    public void setBrand(SpeakerBrand brand) {
        this.brand = brand;
    }

    public Speaker(SpeakerBrand brand, double price) throws SpeakerException {
        if(brand == null || price < 0) {
            throw new SpeakerException("Invalid Speaker Parameters", new IllegalArgumentException());
        }
        this.brand = brand;
        this.price = price;
    }
}
