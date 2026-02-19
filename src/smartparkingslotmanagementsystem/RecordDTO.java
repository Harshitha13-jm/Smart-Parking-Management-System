package smartparkingslotmanagementsystem;
import java.time.LocalDateTime;

public class RecordDTO {

    private int recordId;
    private String vehicleNumber;
    private int slotId;
    private LocalDateTime entryTime;
    private LocalDateTime exitTime;
    private double amount;

    public int getRecordId() { return recordId; }
    public void setRecordId(int recordId) { this.recordId = recordId; }

    public String getVehicleNumber() { return vehicleNumber; }
    public void setVehicleNumber(String vehicleNumber) { this.vehicleNumber = vehicleNumber; }

    public int getSlotId() { return slotId; }
    public void setSlotId(int slotId) { this.slotId = slotId; }

    public LocalDateTime getEntryTime() { return entryTime; }
    public void setEntryTime(LocalDateTime entryTime) { this.entryTime = entryTime; }

    public LocalDateTime getExitTime() { return exitTime; }
    public void setExitTime(LocalDateTime exitTime) { this.exitTime = exitTime; }

    public double getAmount() { return amount; }
    public void setAmount(double amount) { this.amount = amount; }
}

