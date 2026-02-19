package smartparkingslotmanagementsystem;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ParkingDAOImpl implements ParkingDAO {

    private static final String URL =
        "jdbc:mysql://localhost:3306/parking?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "#Kulsum786";

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addSlot(SlotDTO slot) {
        String sql = "INSERT INTO parking_slots(vehicle_type, available) VALUES(?, true)";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, slot.getVehicleType());
            pst.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public List<SlotDTO> getAvailableSlots() {
        List<SlotDTO> list = new ArrayList<>();
        String sql = "SELECT * FROM parking_slots WHERE available=true";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            while (rs.next()) {
                SlotDTO s = new SlotDTO();
                s.setSlotId(rs.getInt("slot_id"));
                s.setVehicleType(rs.getString("vehicle_type"));
                s.setAvailable(rs.getBoolean("available"));
                list.add(s);
            }

        } catch (Exception e) { e.printStackTrace(); }

        return list;
    }

    public SlotDTO getAvailableSlotByType(String type) {
        String sql = "SELECT * FROM parking_slots WHERE vehicle_type=? AND available=true LIMIT 1";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, type);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                SlotDTO s = new SlotDTO();
                s.setSlotId(rs.getInt("slot_id"));
                s.setVehicleType(rs.getString("vehicle_type"));
                s.setAvailable(rs.getBoolean("available"));
                return s;
            }

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public void parkVehicle(String vehicleNumber, int slotId) {
        String insert = "INSERT INTO parking_records(vehicle_number, slot_id, entry_time) VALUES(?,?,?)";
        String update = "UPDATE parking_slots SET available=false WHERE slot_id=?";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            PreparedStatement pst1 = con.prepareStatement(insert);
            pst1.setString(1, vehicleNumber);
            pst1.setInt(2, slotId);
            pst1.setTimestamp(3, Timestamp.valueOf(LocalDateTime.now()));
            pst1.executeUpdate();

            PreparedStatement pst2 = con.prepareStatement(update);
            pst2.setInt(1, slotId);
            pst2.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public RecordDTO getActiveRecord(String vehicleNumber) {
        String sql = "SELECT * FROM parking_records WHERE vehicle_number=? AND exit_time IS NULL";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement(sql)) {

            pst.setString(1, vehicleNumber);
            ResultSet rs = pst.executeQuery();

            if (rs.next()) {
                RecordDTO r = new RecordDTO();
                r.setRecordId(rs.getInt("record_id"));
                r.setSlotId(rs.getInt("slot_id"));
                r.setEntryTime(rs.getTimestamp("entry_time").toLocalDateTime());
                return r;
            }

        } catch (Exception e) { e.printStackTrace(); }

        return null;
    }

    public void exitVehicle(String vehicleNumber, double amount) {
        String updateRecord = "UPDATE parking_records SET exit_time=?, amount=? WHERE vehicle_number=? AND exit_time IS NULL";
        String freeSlot = "UPDATE parking_slots SET available=true WHERE slot_id=(SELECT slot_id FROM parking_records WHERE vehicle_number=? AND exit_time IS NULL LIMIT 1)";

        try (Connection con = DriverManager.getConnection(URL, USER, PASS)) {

            PreparedStatement pst1 = con.prepareStatement(updateRecord);
            pst1.setTimestamp(1, Timestamp.valueOf(LocalDateTime.now()));
            pst1.setDouble(2, amount);
            pst1.setString(3, vehicleNumber);
            pst1.executeUpdate();

            PreparedStatement pst2 = con.prepareStatement(freeSlot);
            pst2.setString(1, vehicleNumber);
            pst2.executeUpdate();

        } catch (Exception e) { e.printStackTrace(); }
    }

    public double getTotalRevenue() {
        String sql = "SELECT SUM(amount) AS total FROM parking_records";
        try (Connection con = DriverManager.getConnection(URL, USER, PASS);
             PreparedStatement pst = con.prepareStatement(sql);
             ResultSet rs = pst.executeQuery()) {

            if (rs.next())
                return rs.getDouble("total");

        } catch (Exception e) { e.printStackTrace(); }

        return 0;
    }
}

