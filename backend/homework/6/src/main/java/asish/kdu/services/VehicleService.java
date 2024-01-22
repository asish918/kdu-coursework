package asish.kdu.services;

import asish.kdu.entities.Speaker;
import asish.kdu.entities.Tyre;
import asish.kdu.entities.Vehicle;
import asish.kdu.enums.SpeakerBrand;
import asish.kdu.enums.TyreBrand;
import asish.kdu.exceptions.SpeakerException;
import asish.kdu.exceptions.TyreException;
import asish.kdu.exceptions.VehicleException;
import asish.kdu.logging.CustomLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Component annotation ensures Bean of VehicleService is
 * created.
 */
@Component
public class VehicleService {

    private final TyreService tyreService;
    private final SpeakerService speakerService;

    /**
     * Autowired ensures that Spring takes care of the heavy lifting of Dependency Injection
     * @param tyreService Dependency of TyreService is injected here
     * @param speakerService Dependency of SpeakerService is injected here
     */
    @Autowired
    public VehicleService(TyreService tyreService, SpeakerService speakerService) {
        this.tyreService = tyreService;
        this.speakerService = speakerService;
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
            double price = generateRandomPrice();
            vehicles.add(new Vehicle(tyre, speaker, price));
        }

        return vehicles;
    }

    /**
     * Returns the most expensive Vehicle using streams if found or else null.
     * @param vehicles List of Vehicles
     * @return Most Expensive Vehicle
     */
    public Vehicle findMostExpensiveVehicle(List<Vehicle> vehicles) {
        return vehicles.stream()
                .max(Comparator.comparingDouble(Vehicle::getPrice))
                .orElse(null);
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
     * It will find the most expensive Vehicle before anything else happens with VehicleService Bean
     * @throws VehicleException When invalid arguments are passed to Vehicle constructor
     * @throws TyreException When invalid arguments are passed to TyreService
     * @throws SpeakerException When invalid arguments are passed to SpeakerService
     */

    @PostConstruct
    public void postConstruct() throws SpeakerException, VehicleException, TyreException {
        List<Vehicle> vehicles = generateVehicleList(5);
        Vehicle mostExpensiveVehicle = findMostExpensiveVehicle(vehicles);
        CustomLogger.printLogger("Most Expensive Vehicle Details - ", CustomLogger.LoggerType.INFO);
        CustomLogger.printLogger(mostExpensiveVehicle.toString(), CustomLogger.LoggerType.INFO);
    }
}