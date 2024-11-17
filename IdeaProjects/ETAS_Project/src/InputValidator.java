public class InputValidator {

    public static boolean validateMemoryTag(String memoryTag) {
        if (memoryTag == null || memoryTag.length() != 2) {
            return false;
        }
        String validLetters = "ABCDEFGHJKLMNOPRSTUVWXYZ";
        //checks that the memoryTags 2 values are within validLetters, have to convert to String for contains
        return validLetters.contains(Character.toString(memoryTag.charAt(0))) && validLetters.contains(Character.toString(memoryTag.charAt(1)));
    }

    public static boolean validateDate(String date) {
        try {
            String[] parts = date.split("-");
            if (parts.length != 3) {
                return false;
            }
            int day = Integer.parseInt(parts[0]);
            int month = Integer.parseInt(parts[1]);
            int year = Integer.parseInt(parts[2]);

            if (year < 2001) {
                return false; // Make sure the year is at least 2001
            }
            if (month < 1 || month > 12) {
                return false; // Ensure the month is valid
            }

            int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
            // Check for leap year February
            if (year % 4 == 0 && (year % 100 != 0 || year % 400 == 0)) { //strange rules about leap year i learnt here
                System.out.println("This is a leap year!");
                daysInMonth[1] = 29;  // feb has 29 days in a leap year
            }

            if (day < 1 || day > daysInMonth[month - 1]) {
                return false; // Ensure day is within valid range for the month
            }

            //specifically making sure after march in 2001
            java.time.LocalDate enteredDate = java.time.LocalDate.of(year, month, day);
            java.time.LocalDate minValidDate = java.time.LocalDate.of(2001, 2, 28);

            return !enteredDate.isBefore(minValidDate); //if before march 2001 returns false
        } catch (Exception e) {
            return false;
        }
    }
}
