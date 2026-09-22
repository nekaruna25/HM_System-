import java.util.Scanner;

public class InputHelper {

    public static String readRequired(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String value = scanner.nextLine().trim();
            if (!value.isEmpty()) {
                return value;
            }
            System.out.println("This field cannot be empty.");
        }
    }

    public static String readOptional(Scanner scanner, String message, String oldValue) {
        System.out.print(message);
        String value = scanner.nextLine().trim();
        return value.isEmpty() ? oldValue : value;
    }

    public static int readInt(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid integer.");
            }
        }
    }

    public static int readPositiveInt(Scanner scanner, String message) {
        while (true) {
            int value = readInt(scanner, message);
            if (value > 0) return value;
            System.out.println("Value must be greater than zero.");
        }
    }

    public static double readDouble(Scanner scanner, String message) {
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();
            try {
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.println("Please enter a valid number.");
            }
        }
    }

    public static double readPositiveDouble(Scanner scanner, String message) {
        while (true) {
            double value = readDouble(scanner, message);
            if (value > 0) return value;
            System.out.println("Value must be greater than zero.");
        }
    }

    public static String readDate(Scanner scanner, String message) {
        while (true) {
            System.out.print(message + " (YYYY-MM-DD): ");
            String value = scanner.nextLine().trim();
            if (value.matches("\\d{4}-\\d{2}-\\d{2}")) {
                return value;
            }
            System.out.println("Use the format YYYY-MM-DD.");
        }
    }

    public static void pause(Scanner scanner) {
        System.out.println();
        System.out.print("Press Enter to continue...");
        scanner.nextLine();
    }

    public static boolean yesNo(Scanner scanner, String message) {
        while (true) {
            System.out.print(message + " (Y/N): ");
            String answer = scanner.nextLine().trim();
            if (answer.equalsIgnoreCase("Y")) return true;
            if (answer.equalsIgnoreCase("N")) return false;
            System.out.println("Please enter Y or N.");
        }
    }
}
