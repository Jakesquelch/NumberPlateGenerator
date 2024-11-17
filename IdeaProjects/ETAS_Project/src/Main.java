import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        System.out.println("Hello and Welcome to Jake's Number Plate Generator!");

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nChoose an option: ");
            System.out.println("1. View all generated plates");
            System.out.println("2. Generate a number plate with hardcoded values");
            System.out.println("3. Generate a number plate with custom values");
            System.out.println("4. Exit");
            System.out.println("\nEnter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // view all generated plates
                    viewAllGeneratedPlates();
                    break;
                case 2: // hardcoded values
                    generateHardCodedValues(scanner);
                    break;
                case 3: // custom values
                    generateCustomValues(scanner);
                    break;
                case 4:
                    System.out.println("Successfully exited, Goodbye!");
                    scanner.close();
                    return; // exit the main method
                default:
                    System.out.println("Invalid choice, Try again!");
            }
        }
    }

    private static void viewAllGeneratedPlates() {
        System.out.println("\nDisplaying all generated plates:");
        NumberPlateGenerator.listGeneratedPlates();
    }

    private static void generateHardCodedValues(Scanner scanner) {
        String memoryTag = NumberPlateGenerator.getRandMemTag();
        String date = NumberPlateGenerator.getRandDate();

        System.out.println("Hardcoded values: Memory Tag = " + memoryTag + ", Date = " + date + "\n");

        // Call the plate generator
        try {
            String plate = NumberPlateGenerator.generatePlate(memoryTag, date);
            System.out.println("Successfully generated Plate: " + plate);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    private static void generateCustomValues(Scanner scanner) {

        System.out.print("\nEnter the memory tag (2 letters, excluding 'I' and 'Q'): ");
        String memoryTag = scanner.nextLine().toUpperCase();
        // Input and validate the memory tag value
        while (!InputValidator.validateMemoryTag(memoryTag)) {
            System.out.println("Invalid memory tag, please ensure it is 2 letters and excludes 'I' and 'Q'.");
            System.out.print("Enter the memory tag (2 letters, excluding 'I' and 'Q'): ");
            memoryTag = scanner.nextLine().toUpperCase();
        }

        System.out.print("\nEnter the date (DD-MM-YYYY): ");
        String date = scanner.nextLine();
        while (!InputValidator.validateDate(date)) {
            System.out.println("Invalid date, please ensure the date is at least 1st March 2001.");
            System.out.print("Enter the date (DD-MM-YYYY): ");
            date = scanner.nextLine();
        }

        try {
            String plate = NumberPlateGenerator.generatePlate(memoryTag, date);
            System.out.println("Successfully generated Plate: " + plate);
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
