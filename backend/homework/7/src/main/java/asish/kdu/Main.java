package asish.kdu;

import asish.kdu.configs.AppConfig;
import asish.kdu.entities.Vehicle;
import asish.kdu.exceptions.SpeakerException;
import asish.kdu.exceptions.TyreException;
import asish.kdu.exceptions.VehicleException;
import asish.kdu.logging.CustomLogger;
import asish.kdu.services.VehicleService1;
import asish.kdu.services.VehicleService2;
import asish.kdu.utils.Utils;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;

public class Main {
    /**
     * context is created from which VehicleService1 Bean is fetched and printed.
     * All the exceptions thrown are properly handled with Errors going to the log file and
     * INFO, DEBUG tagged logs being printed to the console. Refer the logback.xml file in resources folder
     */
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Utils util = Utils.getINSTANCE();
        VehicleService1 vhs1 = context.getBean(VehicleService1.class);
        VehicleService2 vhs2 = context.getBean(VehicleService2.class);

        try {
            List<Vehicle> listV1 = vhs1.generateVehicleList(5);
            List<Vehicle> listV2 = vhs2.generateVehicleList(5);
            Vehicle maxPrice = util.findHighestPrice(listV1, listV2);
            Vehicle minPrice = util.findLowestPrice(listV1, listV2);
            CustomLogger.printLogger("-------------\nMost Expensive Vehicle \n", CustomLogger.LoggerType.INFO);
            CustomLogger.printLogger(maxPrice.toString(), CustomLogger.LoggerType.INFO);
            CustomLogger.printLogger("-------------\nLeast Expensive Vehicle - \n", CustomLogger.LoggerType.INFO);
            CustomLogger.printLogger(minPrice.toString(), CustomLogger.LoggerType.INFO);
        } catch (SpeakerException | TyreException | VehicleException e) {
            CustomLogger.printLogger("Some error occured while generating Vehicle List", CustomLogger.LoggerType.ERROR);
        }

        context.close();
    }
}