package asish.kdu.services;

import asish.kdu.entities.*;
import asish.kdu.enums.SpeakerBrand;
import asish.kdu.enums.TyreBrand;
import asish.kdu.exceptions.SpeakerException;
import asish.kdu.exceptions.TyreException;
import asish.kdu.exceptions.VehicleException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Component annotation ensures Bean of VehicleService1 is
 * created.
 */
@Component
public class VehicleService1 implements Factory {

    private final TyreService tyreService;
    private final SpeakerService speakerService;
    private final Inventory inventory;

    /**
     * Autowired ensures that Spring takes care of the heavy lifting of Dependency Injection
     *
     * @param tyreService    Dependency of TyreService is injected here
     * @param speakerService Dependency of SpeakerService is injected here
     * @param inventory Inventory Specific to this Factory is injected here
     */
    @Autowired
    public VehicleService1(TyreService tyreService, SpeakerService speakerService, Inventory inventory) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
        this.inventory = inventory;
    }

    /**
     * Generates a list of Vehicles with hardcoded Speaker and Tyre brand.
     * @param count Number of Vehicles to be generated
     * @return List of  {@link  asish.kdu.entities.Vehicle Vehicles}
     * @throws VehicleException When invalid arguments are passed to Vehicle constructor
     * @throws TyreException When invalid arguments are passed to TyreService
     * @throws SpeakerException When invalid arguments are passed to SpeakerService
     */
    public List<Vehicle> generateVehicleList(int count) throws VehicleException, TyreException, SpeakerException {
        List<Vehicle> vehicles = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            Tyre tyre = tyreService.generateTyre(TyreBrand.BRIDGESTONE);
            Speaker speaker = speakerService.generateSpeaker(SpeakerBrand.BOSE);
            double price = generateRandomPrice() + priceAdjustment(tyre.getPrice());
            vehicles.add(new Vehicle(tyre, speaker, price));
        }

        return vehicles;
    }

    /**
     * Function to generate random prices for vehicles
     * @return Random Price
     */
    private double generateRandomPrice() {
        return Math.random() * 1000 + 5000;
    }

    /**
     * PostConstruct method to demonstrate initialization after the bean is created.
     * This will be called after the bean is instantiated and its dependencies are injected.
     * It will find the most expensive Vehicle before anything else happens with VehicleService1 Bean
     * @throws VehicleException When invalid arguments are passed to Vehicle constructor
     * @throws TyreException When invalid arguments are passed to TyreService
     * @throws SpeakerException When invalid arguments are passed to SpeakerService
     */

    @PostConstruct
    public void postConstruct() throws SpeakerException, VehicleException, TyreException {
        List<Vehicle> vehicles = generateVehicleList(5);
        inventory.setVehicleList(vehicles);
    }

    /**
     * Adjust Price of the original tyre price.
     * @param price Original Price
     * @return Adjusted Price
     */
    @Override
    public double priceAdjustment(double price) {
        return price*0.15 + price;
    }
}