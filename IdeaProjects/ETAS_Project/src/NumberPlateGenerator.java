import java.io.*;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class NumberPlateGenerator {

    //create a set to track previously generated plates
    private static final String FILE_NAME = "plates_data.ser";
    private static Set<String> generatedPlates = loadPlatesFromFile();;
    private static Random rand = new Random();

    public static String getRandMemTag() {
        return generateRandomLetters(2);
    }

    public static String getRandLetters() {
        return generateRandomLetters(3);
    }

    //helper method for memorytag and randletters
    private static String generateRandomLetters(int count) {
        String letters = "ABCDEFGHJKLMNOPRSTUVWXYZ"; // Excludes I and Q
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < count; i++) {
            //generating a random number between 0 and 24 then add to StringBuilder, from letters
            result.append(letters.charAt(rand.nextInt(letters.length())));
        }

        return result.toString();
    }

    public static String getRandDate() {
        int year = rand.nextInt(24) + 2001;
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        int month = rand.nextInt(12) + 1;
        int day = rand.nextInt(daysInMonth[month - 1]) + 1;

        return String.format("%02d-%02d-%04d", day, month, year);
    }

    public static String generatePlate(String memoryTag, String date) {
        String ageIdentifier = calculateAgeIdentifier(date);
        String randomLetters = getRandLetters();
        String plate = memoryTag + ageIdentifier + randomLetters;

        if (generatedPlates.contains(plate)) {
            throw new IllegalArgumentException("Plate: " + plate + " has already been generated");
        }
        generatedPlates.add(plate);

        savePlatesToFile();

        return plate;
    }

    public static String calculateAgeIdentifier(String date) {
        String[] parts = date.split("-");
        int year = Integer.parseInt(parts[2]);
        int month = Integer.parseInt(parts[1]);

        //to satisfy march-august(last 2 digits of year) & september-february(last 2 digits + 50)
        if (month >= 3 && month <= 8) {
            return String.format("%02d", year % 100);
        } else {
            return String.format("%02d", (year % 100) + 50);
        }
    }

    public static void listGeneratedPlates() {
        System.out.println("\nGenerated Plates:");
        if (generatedPlates.isEmpty()) {
            System.out.println("No plates have been generated yet.");
        } else {
            for (String plate : generatedPlates) {
                System.out.println(plate);
            }
        }
    }

    private static Set<String> loadPlatesFromFile() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(FILE_NAME))) {
            // Deserialize the file into a HashSet and casts it appropriately
            return (Set<String>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            // If file doesn't exist or an error occurs, return an empty HashSet
            return new HashSet<>();
        }
    }

    // Save the HashSet to a file to hold the generated plates
    private static void savePlatesToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            // Serialize the HashSet to a file
            oos.writeObject(generatedPlates);
        } catch (IOException e) {
            // Handle any errors during file write
            System.out.println("Error saving plates to file: " + e.getMessage());
        }
    }
}
