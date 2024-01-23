package asish.kdu.entities;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    public Inventory() {
        this.vehicleList = new ArrayList<>();
    }

    public List<Vehicle> vehicleList;

    public List<Vehicle> getVehicleList() {
        return vehicleList;
    }

    public void setVehicleList(List<Vehicle> vehicleList) {
        this.vehicleList = vehicleList;
    }
}
