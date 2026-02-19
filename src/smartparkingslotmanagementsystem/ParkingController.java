package smartparkingslotmanagementsystem;

public class ParkingController {

    private ParkingService service = new ParkingService();

    public void addSlot(String type) {
        service.addSlot(type);
    }

    public void parkVehicle(String number, String type) {
        service.parkVehicle(number, type);
    }

    public void exitVehicle(String number) {
        service.exitVehicle(number);
    }

    public void showRevenue() {
        service.showRevenue();
    }
}
