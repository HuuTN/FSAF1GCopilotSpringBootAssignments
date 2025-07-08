// FileProcessingUtility.java
// A comprehensive file processing utility using Java Collections, exception handling, and logging
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
    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            logger.info("Read " + lines.size() + " lines from " + path);
        } catch (IOException e) {
            logger.severe("Error reading file: " + e.getMessage());
        }
        return lines;
    }

    // Writes a collection of lines to a file
    public static void writeFile(String path, Collection<String> lines) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            logger.info("Wrote " + lines.size() + " lines to " + path);
        } catch (IOException e) {
            logger.severe("Error writing file: " + e.getMessage());
        }
    }

    // Removes duplicate lines from a file and writes to a new file
    public static void removeDuplicates(String inputPath, String outputPath) {
        List<String> lines = readFile(inputPath);
        Set<String> unique = new LinkedHashSet<>(lines);
        writeFile(outputPath, unique);
        logger.info("Removed duplicates: " + (lines.size() - unique.size()) + " lines removed.");
    }

    // Sorts lines in a file and writes to a new file
    public static void sortFile(String inputPath, String outputPath) {
        List<String> lines = readFile(inputPath);
        Collections.sort(lines);
        writeFile(outputPath, lines);
        logger.info("Sorted file: " + inputPath + " -> " + outputPath);
    }

    // Searches for a keyword in a file and returns matching lines
    public static List<String> searchInFile(String path, String keyword) {
        List<String> lines = readFile(path);
        List<String> result = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(keyword)) {
                result.add(line);
            }
        }
        logger.info("Found " + result.size() + " lines containing '" + keyword + "' in " + path);
        return result;
    }

    public static void main(String[] args) {
        // Example usage
        String input = "input.txt";
        String output = "output.txt";
        removeDuplicates(input, output);
        sortFile(input, "sorted.txt");
        List<String> found = searchInFile(input, "error");
        writeFile("errors.txt", found);
    }
}
