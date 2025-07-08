package assignment_2;

import java.io.*;
import java.util.*;
import java.util.logging.*;

public class ProcessingUtils {

    private static final Logger logger = Logger.getLogger(ProcessingUtils.class.getName());

    static {
        try {
            FileHandler fileHandler = new FileHandler("processingUtils.log", true);
            fileHandler.setFormatter(new SimpleFormatter());
            logger.addHandler(fileHandler);
            logger.setLevel(Level.ALL);
        } catch (IOException e) {
            System.err.println("Failed to initialize logger: " + e.getMessage());
        }
    }

    // Method to read lines from a file
    public static List<String> readLinesFromFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            logger.info("Successfully read lines from file: " + filePath);
        } catch (IOException e) {
            logger.severe("Error reading file: " + filePath + " - " + e.getMessage());
        }
        return lines;
    }

    // Method to write lines to a file
    public static void writeLinesToFile(String filePath, List<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            logger.info("Successfully wrote lines to file: " + filePath);
        } catch (IOException e) {
            logger.severe("Error writing to file: " + filePath + " - " + e.getMessage());
        }
    }

    // Method to process lines with a custom function
    public static List<String> processLines(List<String> lines, Function<String, String> processor) {
        List<String> processedLines = new ArrayList<>();
        for (String line : lines) {
            try {
                processedLines.add(processor.apply(line));
            } catch (Exception e) {
                logger.warning("Error processing line: " + line + " - " + e.getMessage());
            }
        }
        logger.info("Successfully processed lines.");
        return processedLines;
    }

    // Method to filter lines based on a predicate
    public static List<String> filterLines(List<String> lines, Predicate<String> predicate) {
        List<String> filteredLines = new ArrayList<>();
        for (String line : lines) {
            try {
                if (predicate.test(line)) {
                    filteredLines.add(line);
                }
            } catch (Exception e) {
                logger.warning("Error filtering line: " + line + " - " + e.getMessage());
            }
        }
        logger.info("Successfully filtered lines.");
        return filteredLines;
    }

    // Method to sort lines
    public static List<String> sortLines(List<String> lines) {
        List<String> sortedLines = new ArrayList<>(lines);
        try {
            Collections.sort(sortedLines);
            logger.info("Successfully sorted lines.");
        } catch (Exception e) {
            logger.severe("Error sorting lines: " + e.getMessage());
        }
        return sortedLines;
    }

    public static void main(String[] args) {
        // Example usage
        String filePath = "example.txt";
        List<String> lines = Arrays.asList("Line 3", "Line 1", "Line 2");

        writeLinesToFile(filePath, lines);
        List<String> readLines = readLinesFromFile(filePath);
        List<String> sortedLines = sortLines(readLines);

        System.out.println("Sorted Lines:");
        sortedLines.forEach(System.out::println);
    }
}