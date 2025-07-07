package utility;

import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;

public class FileProcessor {
    private static final Logger logger = Logger.getLogger(FileProcessor.class.getName());

    /**
     * Reads all lines from a file into a List.
     */
    public List<String> readLines(String filePath) {
        List<String> lines = new ArrayList<>();
        try {
            lines = Files.readAllLines(Paths.get(filePath));
            logger.info("Read " + lines.size() + " lines from " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to read file: " + filePath, e);
        }
        return lines;
    }

    /**
     * Counts word occurrences in a list of lines.
     */
    public Map<String, Integer> countWords(List<String> lines) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String line : lines) {
            String[] words = line.split("\\W+");
            for (String word : words) {
                if (!word.isEmpty()) {
                    word = word.toLowerCase();
                    wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
                }
            }
        }
        logger.info("Counted words. Unique words: " + wordCount.size());
        return wordCount;
    }

    /**
     * Writes word counts to a file.
     */
    public void writeWordCounts(Map<String, Integer> wordCount, String outputPath) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputPath))) {
            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                writer.write(entry.getKey() + ": " + entry.getValue());
                writer.newLine();
            }
            logger.info("Wrote word counts to " + outputPath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Failed to write file: " + outputPath, e);
        }
    }

    /**
     * Example usage of the FileProcessor utility.
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java FileProcessor <inputFile> <outputFile>");
            return;
        }
        FileProcessor processor = new FileProcessor();
        List<String> lines = processor.readLines(args[0]);
        Map<String, Integer> wordCount = processor.countWords(lines);
        processor.writeWordCounts(wordCount, args[1]);
    }
}
