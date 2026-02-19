package smartparkingslotmanagementsystem;

import java.time.Duration;

public class ParkingService {

    private ParkingDAO dao = new ParkingDAOImpl();

    public void addSlot(String type) {
        SlotDTO s = new SlotDTO();
        s.setVehicleType(type);
        dao.addSlot(s);
        System.out.println("Slot added successfully");
    }

    public void parkVehicle(String number, String type) {

        SlotDTO slot = dao.getAvailableSlotByType(type);

        if (slot == null) {
            System.out.println("No available slots!");
            return;
        }

        dao.parkVehicle(number, slot.getSlotId());
        System.out.println("Vehicle parked in slot: " + slot.getSlotId());
    }

    public void exitVehicle(String number) {

        RecordDTO record = dao.getActiveRecord(number);

        if (record == null) {
            System.out.println("Vehicle not found!");
            return;
        }

        long hours = Duration.between(
                record.getEntryTime(),
                java.time.LocalDateTime.now()).toHours();

        if (hours == 0) hours = 1;

        double amount = hours * 20; // ₹20 per hour

        dao.exitVehicle(number, amount);

        System.out.println("Total amount: ₹" + amount);
    }

    public void showRevenue() {
        System.out.println("Total Revenue: ₹" + dao.getTotalRevenue());
    }
}

