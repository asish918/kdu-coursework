package asish.kdu.q3;

import java.util.Arrays;

import asish.kdu.logging.CustomLogger;
import asish.kdu.logging.CustomLogger.LoggerType;

public class ArrayUtil {

    public static <T> void swapElements(T[] array, int index1, int index2) throws IllegalArgumentException {
        if (index1 < 0 || index1 >= array.length || index2 < 0 || index2 >= array.length) {
            throw new IllegalArgumentException("Invalid indices");
        }

        T temp = array[index1];
        array[index1] = array[index2];
        array[index2] = temp;
    }

    public static void main(String[] args) {
        Integer[] intArray = { 1, 2, 3, 4, 5 };
        CustomLogger.printLogger("Original Integer Array: " + Arrays.toString(intArray), LoggerType.INFO);

        swapElements(intArray, 1, 3);

        CustomLogger.printLogger("Array after swapping elements at indices 1 and 3: " + Arrays.toString(intArray),
                LoggerType.INFO);

        String[] strArray = { "apple", "banana", "orange", "grape" };
        CustomLogger.printLogger("Original String Array: " + Arrays.toString(strArray), LoggerType.INFO);

        swapElements(strArray, 0, 2);

        CustomLogger.printLogger("Array after swapping elements at indices 0 and 2: " + Arrays.toString(strArray),
                LoggerType.INFO);
    }
}
