package smartparkingslotmanagementsystem;

import java.util.List;

public interface ParkingDAO {

    void addSlot(SlotDTO slot);
    List<SlotDTO> getAvailableSlots();
    SlotDTO getAvailableSlotByType(String type);

    void parkVehicle(String vehicleNumber, int slotId);
    RecordDTO getActiveRecord(String vehicleNumber);

    void exitVehicle(String vehicleNumber, double amount);

    double getTotalRevenue();
}

