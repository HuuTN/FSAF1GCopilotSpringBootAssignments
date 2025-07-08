import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class FileProcessingUtility {
    private static final Logger logger = Logger.getLogger(FileProcessingUtility.class.getName());

    /**
     * Reads all lines from a file into a List.
     */
    public static List<String> readLinesToList(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            logger.log(Level.INFO, "Successfully read lines into List from: {0}", filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + filePath, e);
        }
        return lines;
    }

    /**
     * Reads all unique lines from a file into a Set.
     */
    public static Set<String> readLinesToSet(String filePath) {
        Set<String> lines = new HashSet<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            logger.log(Level.INFO, "Successfully read unique lines into Set from: {0}", filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + filePath, e);
        }
        return lines;
    }

    /**
     * Counts the occurrences of each line in a file and returns a Map.
     */
    public static Map<String, Integer> countLineOccurrences(String filePath) {
        Map<String, Integer> occurrences = new HashMap<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                occurrences.put(line, occurrences.getOrDefault(line, 0) + 1);
            }
            logger.log(Level.INFO, "Successfully counted line occurrences in: {0}", filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + filePath, e);
        }
        return occurrences;
    }

    public static void main(String[] args) {
        if (args.length == 0) {
            logger.warning("No file path provided.");
            return;
        }
        String filePath = args[0];
        List<String> lines = readLinesToList(filePath);
        Set<String> uniqueLines = readLinesToSet(filePath);
        Map<String, Integer> lineCounts = countLineOccurrences(filePath);

        logger.log(Level.INFO, "Total lines: {0}", lines.size());
        logger.log(Level.INFO, "Unique lines: {0}", uniqueLines.size());
        logger.log(Level.INFO, "Line occurrences: {0}", lineCounts);
    }
}
