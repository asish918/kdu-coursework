package asish.kdu;

import asish.kdu.configs.AppConfig;
import asish.kdu.entities.Vehicle;
import asish.kdu.exceptions.SpeakerException;
import asish.kdu.exceptions.TyreException;
import asish.kdu.exceptions.VehicleException;
import asish.kdu.logging.CustomLogger;
import asish.kdu.services.VehicleService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    /**
     * context is created from which VehicleService Bean is fetched and printed.
     * All the exceptions thrown are properly handled with Errors going to the log file and
     * INFO, DEBUG tagged logs being printed to the console. Refer the logback.xml file in resources folder
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        VehicleService vhs = context.getBean(VehicleService.class);
        try {
            List<Vehicle> listV = vhs.generateVehicleList(5);
            CustomLogger.printLogger("-------------\nVehicle List - \n", CustomLogger.LoggerType.INFO);
            listV.forEach(v -> CustomLogger.printLogger(v.toString(), CustomLogger.LoggerType.INFO));
        } catch (SpeakerException | TyreException | VehicleException e) {
            CustomLogger.printLogger("Some error occured while generating Vehicle List", CustomLogger.LoggerType.ERROR);
        }

        context.close();
    }
}