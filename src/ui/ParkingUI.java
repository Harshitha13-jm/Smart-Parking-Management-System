package ui;

import dto.ParkingDTO;
import service.ParkingService;

import javax.swing.*;
import java.util.List;

public class ParkingUI {

    public static void main(String[] args) {

        JFrame frame = new JFrame("Smart Parking Management");
        frame.setSize(500, 400);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        ParkingService service = new ParkingService();

        JButton addBtn = new JButton("Add Parking Record");
        addBtn.setBounds(140, 80, 220, 30);

        JButton viewBtn = new JButton("View Parking Records");
        viewBtn.setBounds(140, 130, 220, 30);

        JButton deleteBtn = new JButton("Delete Parking Record");
        deleteBtn.setBounds(140, 180, 220, 30);

        frame.add(addBtn);
        frame.add(viewBtn);
        frame.add(deleteBtn);

        frame.setVisible(true);

        // ADD
        addBtn.addActionListener(e -> {

            String vehicle = JOptionPane.showInputDialog("Enter Vehicle Number:");
            String owner = JOptionPane.showInputDialog("Enter Owner Name:");
            String type = JOptionPane.showInputDialog("Enter Vehicle Type:");
            String slot = JOptionPane.showInputDialog("Enter Parking Slot:");

            service.addParking(
                    new ParkingDTO(0, vehicle, owner, type, slot)
            );

            JOptionPane.showMessageDialog(null, "Parking Record Added!");
        });

        // VIEW
        viewBtn.addActionListener(e -> {

            List<ParkingDTO> list = service.getAllParkingRecords();

            String[] cols = {
                    "ID",
                    "Vehicle Number",
                    "Owner Name",
                    "Vehicle Type",
                    "Parking Slot"
            };

            String[][] data = new String[list.size()][5];

            for (int i = 0; i < list.size(); i++) {

                ParkingDTO p = list.get(i);

                data[i][0] = String.valueOf(p.getId());
                data[i][1] = p.getVehicleNumber();
                data[i][2] = p.getOwnerName();
                data[i][3] = p.getVehicleType();
                data[i][4] = p.getParkingSlot();
            }

            JTable table = new JTable(data, cols);

            JOptionPane.showMessageDialog(null, new JScrollPane(table));
        });

        // DELETE
        deleteBtn.addActionListener(e -> {

            int id = Integer.parseInt(
                    JOptionPane.showInputDialog("Enter Record ID:")
            );

            service.deleteParking(id);

            JOptionPane.showMessageDialog(null, "Parking Record Deleted!");
        });
    }
}
