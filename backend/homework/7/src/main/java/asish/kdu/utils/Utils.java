package asish.kdu.utils;

import asish.kdu.entities.Vehicle;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

/**
 * We also make a Utils as Singleton class.
 * We define two utilities to find Highest and Lowest Vehicles and return them.
 */
public class Utils {
    public static Utils getINSTANCE() {
        if (INSTANCE == null) {
            INSTANCE = new Utils();
        }

        return INSTANCE;
    }

    private static Utils INSTANCE;

    public Vehicle findHighestPrice(List<Vehicle> list1, List<Vehicle> list2) {
        return Stream.concat(list1.stream(), list2.stream()).max(Comparator.comparingDouble(Vehicle::getPrice)).orElse(null);
    }

    public Vehicle findLowestPrice(List<Vehicle> list1, List<Vehicle> list2) {
        return Stream.concat(list1.stream(), list2.stream()).min(Comparator.comparingDouble(Vehicle::getPrice)).orElse(null);
    }
}
