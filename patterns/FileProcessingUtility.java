import java.io.*;
import java.util.*;

public class FileProcessingUtility {
    private SingletonLogger logger = SingletonLogger.getInstance();

    // Read all lines from a file into a List
    public List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            logger.log("Read " + lines.size() + " lines from " + filePath);
        } catch (IOException e) {
            logger.log("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    // Write a list of strings to a file
    public void writeFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            logger.log("Wrote " + lines.size() + " lines to " + filePath);
        } catch (IOException e) {
            logger.log("Error writing file: " + e.getMessage());
        }
    }

    // Count word frequency in a file
    public Map<String, Integer> countWordFrequency(String filePath) {
        Map<String, Integer> freqMap = new HashMap<>();
        List<String> lines = readFile(filePath);
        for (String line : lines) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    freqMap.put(word, freqMap.getOrDefault(word, 0) + 1);
                }
            }
        }
        logger.log("Counted word frequencies in " + filePath);
        return freqMap;
    }

    // Remove duplicate lines from a file
    public Set<String> removeDuplicateLines(String filePath) {
        List<String> lines = readFile(filePath);
        Set<String> uniqueLines = new LinkedHashSet<>(lines);
        logger.log("Removed duplicates, " + uniqueLines.size() + " unique lines found.");
        return uniqueLines;
    }

    // Example main method
    public static void main(String[] args) {
        FileProcessingUtility util = new FileProcessingUtility();
        String path = "example.txt";
        List<String> lines = util.readFile(path);
        util.writeFile("output.txt", lines);
        Map<String, Integer> freq = util.countWordFrequency(path);
        Set<String> unique = util.removeDuplicateLines(path);
        util.writeFile("unique.txt", new ArrayList<>(unique));
    }
}
