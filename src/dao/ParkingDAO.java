package dao;

import dto.ParkingDTO;
import java.util.List;

public interface ParkingDAO {

    void addParking(ParkingDTO parking);

    ParkingDTO getParkingById(int id);

    List<ParkingDTO> getAllParkingRecords();

    void updateParking(ParkingDTO parking);

    void deleteParking(int id);
}
