import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        HotelSystem system = new HotelSystem();
        system.loadAllData();

        Scanner scanner = new Scanner(System.in);
        boolean running = true;

        System.out.println("=================================================");
        System.out.println("          WELCOME TO HOTEL MANAGEMENT            ");
        System.out.println("=================================================");

        while (running) {
            system.printMainMenu();
            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    system.customerMenu(scanner);
                    break;
                case "2":
                    system.roomMenu(scanner);
                    break;
                case "3":
                    system.bookingMenu(scanner);
                    break;
                case "4":
                    system.serviceMenu(scanner);
                    break;
                case "5":
                    system.paymentMenu(scanner);
                    break;
                case "6":
                    system.employeeMenu(scanner);
                    break;
                case "7":
                    system.searchMenu(scanner);
                    break;
                case "8":
                    system.reportMenu(scanner);
                    break;
                case "9":
                    system.saveAllData();
                    System.out.println("All data saved successfully.");
                    break;
                case "0":
                    system.saveAllData();
                    System.out.println("Thank you for using the Hotel Management System.");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }
}
