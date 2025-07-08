// FileProcessingUtility.java
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;

public class FileProcessingUtility {
    private static final Logger logger = Logger.getLogger(FileProcessingUtility.class.getName());

    static {
        try {
            LogManager.getLogManager().readConfiguration();
        } catch (IOException e) {
            logger.warning("Could not load logging configuration: " + e.getMessage());
        }
    }

    // Reads all lines from a file into a List
    public static List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filePath));
            logger.info("Read " + lines.size() + " lines from " + filePath);
        } catch (IOException e) {
            logger.severe("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    // Counts word occurrences in a file
    public static Map<String, Integer> countWords(String filePath) {
        Map<String, Integer> wordCount = new HashMap<>();
        List<String> lines = readLines(filePath);
        for (String line : lines) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    word = word.toLowerCase();
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        }
        logger.info("Counted words in " + filePath);
        return wordCount;
    }

    // Writes a collection of strings to a file
    public static void writeLines(String filePath, Collection<String> lines) {
        try {
            Files.write(Paths.get(filePath), lines);
            logger.info("Wrote " + lines.size() + " lines to " + filePath);
        } catch (IOException e) {
            logger.severe("Error writing file: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        if (args.length < 1) {
            logger.warning("Usage: java FileProcessingUtility <inputFile>");
            return;
        }
        String inputFile = args[0];
        List<String> lines = readLines(inputFile);
        Map<String, Integer> wordCount = countWords(inputFile);
        List<String> output = new ArrayList<>();
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            output.add(entry.getKey() + ": " + entry.getValue());
        }
        writeLines("wordcount_output.txt", output);
        logger.info("Processing complete. Output written to wordcount_output.txt");
    }
}
