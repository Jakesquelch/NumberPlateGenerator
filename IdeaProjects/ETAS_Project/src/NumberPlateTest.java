public class NumberPlateTest {
    public static void main(String[] args) {

        // test plate generation as required
        String plate1 = NumberPlateGenerator.generatePlate("YC", "04-07-2019");
        System.out.println("Generated Plate: " + plate1);

        String plate2 = NumberPlateGenerator.generatePlate("LT", "23-01-2003");
        System.out.println("Generated Plate: " + plate2);

        String plate3 = NumberPlateGenerator.generatePlate("FF", "30-05-2032");
        System.out.println("Generated Plate: " + plate3);
    }
}