package service;

import dao.*;
import dto.ParkingDTO;

import java.util.List;

public class ParkingService {

    private ParkingDAO dao = new ParkingDAOImpl();

    public void addParking(ParkingDTO parking) {

        if (parking.getVehicleNumber() == null || parking.getVehicleNumber().isEmpty()) {
            System.out.println("Vehicle number cannot be empty");
            return;
        }

        if (parking.getOwnerName() == null || parking.getOwnerName().isEmpty()) {
            System.out.println("Owner name cannot be empty");
            return;
        }

        dao.addParking(parking);
    }

    public ParkingDTO getParking(int id) {
        return dao.getParkingById(id);
    }

    public List<ParkingDTO> getAllParkingRecords() {
        return dao.getAllParkingRecords();
    }

    public void updateParking(ParkingDTO parking) {
        dao.updateParking(parking);
    }

    public void deleteParking(int id) {
        dao.deleteParking(id);
    }
}
