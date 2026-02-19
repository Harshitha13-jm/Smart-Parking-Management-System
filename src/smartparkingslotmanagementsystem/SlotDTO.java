package smartparkingslotmanagementsystem;

public class SlotDTO {

    private int slotId;
    private String vehicleType;
    private boolean available;

    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}

