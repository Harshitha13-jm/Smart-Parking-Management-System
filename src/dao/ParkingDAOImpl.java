package dao;

import dto.ParkingDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ParkingDAOImpl implements ParkingDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/rnsitdb";
    private static final String USER = "root";
    private static final String PASS = "YOUR_PASSWORD";

    @Override
    public void addParking(ParkingDTO parking) {

        try {

            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "INSERT INTO parking_records(vehicle_number, owner_name, vehicle_type, parking_slot) VALUES (?, ?, ?, ?)";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, parking.getVehicleNumber());
            pst.setString(2, parking.getOwnerName());
            pst.setString(3, parking.getVehicleType());
            pst.setString(4, parking.getParkingSlot());

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ParkingDTO getParkingById(int id) {

        ParkingDTO parking = null;

        try {

            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "SELECT * FROM parking_records WHERE id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, id);

            ResultSet rs = pst.executeQuery();

            if (rs.next()) {

                parking = new ParkingDTO(
                        rs.getInt("id"),
                        rs.getString("vehicle_number"),
                        rs.getString("owner_name"),
                        rs.getString("vehicle_type"),
                        rs.getString("parking_slot")
                );
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return parking;
    }

    @Override
    public List<ParkingDTO> getAllParkingRecords() {

        List<ParkingDTO> list = new ArrayList<>();

        try {

            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "SELECT * FROM parking_records";

            PreparedStatement pst = con.prepareStatement(sql);

            ResultSet rs = pst.executeQuery();

            while (rs.next()) {

                ParkingDTO parking = new ParkingDTO(
                        rs.getInt("id"),
                        rs.getString("vehicle_number"),
                        rs.getString("owner_name"),
                        rs.getString("vehicle_type"),
                        rs.getString("parking_slot")
                );

                list.add(parking);
            }

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }

        return list;
    }

    @Override
    public void updateParking(ParkingDTO parking) {

        try {

            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "UPDATE parking_records SET vehicle_number=?, owner_name=?, vehicle_type=?, parking_slot=? WHERE id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setString(1, parking.getVehicleNumber());
            pst.setString(2, parking.getOwnerName());
            pst.setString(3, parking.getVehicleType());
            pst.setString(4, parking.getParkingSlot());
            pst.setInt(5, parking.getId());

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void deleteParking(int id) {

        try {

            Connection con = DriverManager.getConnection(URL, USER, PASS);

            String sql = "DELETE FROM parking_records WHERE id=?";

            PreparedStatement pst = con.prepareStatement(sql);

            pst.setInt(1, id);

            pst.executeUpdate();

            con.close();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
