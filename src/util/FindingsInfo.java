package util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FindingsInfo {
    private static final Map<FindingName, String[]> findingsData = new HashMap<>();
    private static boolean isDataLoaded = false; // Flag to track data loading


    // Method to load data from CSV file
    private static void loadFindingsData(String csvFilePath) {
        try (BufferedReader br = new BufferedReader(new FileReader(csvFilePath))) {
            String line;

            // Read CSV file line by line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";", 3); // Split by ';' into 3 parts
                if (parts.length < 3) continue;     // Skip invalid rows

                String path = parts[0].trim();
                String message = parts[1].trim();
                String description = parts[2].trim();

                // Match findings based on paths or keywords
                if (path.contains("diskos")) {
                    findingsData.put(FindingName.PHAISTOS_DISC, new String[]{path, message, description});
                } else if (path.contains("ring")) {
                    findingsData.put(FindingName.MINOS_RING, new String[]{path, message, description});
                } else if (path.contains("kosmima")) {
                    findingsData.put(FindingName.MALIA_JEWELRY, new String[]{path, message, description});
                } else if (path.contains("ruto")) {
                    findingsData.put(FindingName.RHYTHON_OF_ZAKROS, new String[]{path, message, description});
                } else if (path.contains("snakes")) {
                    findingsData.put(FindingName.SNAKE_GODDESS, new String[]{path, message, description});
                } else if (path.contains("fresco1")) {
                    findingsData.put(FindingName.FRESCO_1, new String[]{path, message, description});
                } else if (path.contains("fresco2")) {
                    findingsData.put(FindingName.FRESCO_2, new String[]{path, message, description});
                } else if (path.contains("fresco3")) {
                    findingsData.put(FindingName.FRESCO_3, new String[]{path, message, description});
                } else if (path.contains("fresco4")) {
                    findingsData.put(FindingName.FRESCO_4, new String[]{path, message, description});
                } else if (path.contains("fresco5")) {
                    findingsData.put(FindingName.FRESCO_5, new String[]{path, message, description});
                } else if (path.contains("fresco6")) {
                    findingsData.put(FindingName.FRESCO_6, new String[]{path, message, description});
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the CSV file: " + e.getMessage());
        }
    }

    private static void ensureDataLoaded() {
        if (!isDataLoaded) {
            loadFindingsData("src/assets/csvFiles/csv_greeklish.csv");
            isDataLoaded = true;
        }
    }


    // Method to get details for a finding
    public static String[] getFindingDetails(FindingName findingName) {
        ensureDataLoaded();
        return findingsData.get(findingName);
    }

    public static String getFindingImagePath(FindingName findingName){
        String filePath = "src/assets/images/findings/";
        if (findingName == FindingName.RHYTHON_OF_ZAKROS) filePath += "ruto";
        else if (findingName == FindingName.PHAISTOS_DISC) filePath += "diskos";
        else if (findingName == FindingName.MALIA_JEWELRY) filePath += "kosmima";
        else if (findingName == FindingName.MINOS_RING) filePath += "ring";
        else if (findingName == FindingName.FRESCO_1) filePath += "fresco1_20";
        else if (findingName == FindingName.FRESCO_2) filePath += "fresco2_20";
        else if (findingName == FindingName.FRESCO_3) filePath += "fresco3_15";
        else if (findingName == FindingName.FRESCO_4) filePath += "fresco4_20";
        else if (findingName == FindingName.FRESCO_5) filePath += "fresco5_15";
        else if (findingName == FindingName.FRESCO_6) filePath += "fresco6_15";
        else if (findingName == FindingName.SNAKE_GODDESS) filePath += "snakes";
        else throw new IllegalArgumentException("Invalid finding name: " + findingName);
        filePath += ".jpg";
        return filePath;
    }
}
