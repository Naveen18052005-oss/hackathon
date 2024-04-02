import java.util.*;

public class CRCCorrection {
    private static final int GENERATOR_LENGTH = 4; // Length of the generator polynomial
    private static final int DATA_LENGTH = 8; // Length of the data word
    private static final int TOTAL_LENGTH = DATA_LENGTH + GENERATOR_LENGTH - 1; // Total length of the codeword

    // Method to perform CRC error correction
    public static String performCRCCorrection(String receivedData, String generator) {
        int[] receivedArray = new int[TOTAL_LENGTH];
        for (int i = 0; i < DATA_LENGTH; i++) {
            receivedArray[i] = Integer.parseInt(String.valueOf(receivedData.charAt(i)));
        }

        int[] generatorArray = new int[GENERATOR_LENGTH];
        for (int i = 0; i < GENERATOR_LENGTH; i++) {
            generatorArray[i] = Integer.parseInt(String.valueOf(generator.charAt(i)));
        }

        // Perform CRC division
        for (int i = 0; i <= DATA_LENGTH - GENERATOR_LENGTH; i++) {
            if (receivedArray[i] == 1) {
                for (int j = 0; j < GENERATOR_LENGTH; j++) {
                    receivedArray[i + j] ^= generatorArray[j];
                }
            }
        }

        // Check if remainder is zero
        for (int i = DATA_LENGTH; i < TOTAL_LENGTH; i++) {
            if (receivedArray[i] != 0) {
                return "Error detected! Data is corrupted.";
            }
        }

        // Extract original data
        StringBuilder originalData = new StringBuilder();
        for (int i = 0; i < DATA_LENGTH; i++) {
            originalData.append(receivedData.charAt(i));
        }
        return "Data is correct. Original Data: " + originalData.toString();
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("Enter the received data word (binary): ");
        String receivedData = scanner.nextLine();
        
        System.out.println("Enter the generator polynomial (binary): ");
        String generator = scanner.nextLine();

        String result = performCRCCorrection(receivedData, generator);
        System.out.println(result);

        scanner.close();
    }
}
