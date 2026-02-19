package smartparkingslotmanagementsystem;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ParkingController controller = new ParkingController();
        Scanner sc = new Scanner(System.in);

        while (true) {

            System.out.println("\n1.Add Slot");
            System.out.println("2.Park Vehicle");
            System.out.println("3.Exit Vehicle");
            System.out.println("4.Show Revenue");
            System.out.println("5.Exit");

            int ch = sc.nextInt();

            switch (ch) {

                case 1:
                    System.out.print("Vehicle Type (Car/Bike): ");
                    controller.addSlot(sc.next());
                    break;

                case 2:
                    System.out.print("Vehicle Number: ");
                    String num = sc.next();
                    System.out.print("Vehicle Type: ");
                    controller.parkVehicle(num, sc.next());
                    break;

                case 3:
                    System.out.print("Vehicle Number: ");
                    controller.exitVehicle(sc.next());
                    break;

                case 4:
                    controller.showRevenue();
                    break;

                case 5:
                	System.out.println("Thank you for Visting");
                    System.exit(0);
            }
        }
    }
}

