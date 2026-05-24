import dto.ParkingDTO;
import service.ParkingService;

import java.util.List;
import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        ParkingService service = new ParkingService();

        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1. Add Parking Record");
            System.out.println("2. Get Parking Record By ID");
            System.out.println("3. Get All Parking Records");
            System.out.println("4. Update Parking Record");
            System.out.println("5. Delete Parking Record");
            System.out.println("6. Exit");

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    System.out.print("Enter Vehicle Number: ");
                    String vehicle = sc.next();

                    System.out.print("Enter Owner Name: ");
                    String owner = sc.next();

                    System.out.print("Enter Vehicle Type: ");
                    String type = sc.next();

                    System.out.print("Enter Parking Slot: ");
                    String slot = sc.next();

                    service.addParking(
                            new ParkingDTO(0, vehicle, owner, type, slot)
                    );

                    break;

                case 2:

                    System.out.print("Enter Record ID: ");
                    int id = sc.nextInt();

                    ParkingDTO p = service.getParking(id);

                    if (p != null) {

                        System.out.println(
                                p.getId() + " " +
                                p.getVehicleNumber() + " " +
                                p.getOwnerName() + " " +
                                p.getVehicleType() + " " +
                                p.getParkingSlot()
                        );
                    }

                    break;

                case 3:

                    List<ParkingDTO> list = service.getAllParkingRecords();

                    list.forEach(record ->
                            System.out.println(
                                    record.getId() + " " +
                                    record.getVehicleNumber() + " " +
                                    record.getOwnerName() + " " +
                                    record.getVehicleType() + " " +
                                    record.getParkingSlot()
                            )
                    );

                    break;

                case 4:

                    System.out.print("Enter Record ID: ");
                    int uid = sc.nextInt();

                    System.out.print("Enter Vehicle Number: ");
                    String newVehicle = sc.next();

                    System.out.print("Enter Owner Name: ");
                    String newOwner = sc.next();

                    System.out.print("Enter Vehicle Type: ");
                    String newType = sc.next();

                    System.out.print("Enter Parking Slot: ");
                    String newSlot = sc.next();

                    service.updateParking(
                            new ParkingDTO(uid, newVehicle, newOwner, newType, newSlot)
                    );

                    break;

                case 5:

                    System.out.print("Enter Record ID: ");
                    int did = sc.nextInt();

                    service.deleteParking(did);

                    break;

                case 6:
                    System.exit(0);
            }
        }
    }
}
