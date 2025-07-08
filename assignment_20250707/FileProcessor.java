import java.io.*;
import java.util.*;
import java.util.logging.*;

/**
 * FileProcessor - A comprehensive utility for file processing
 * Demonstrates usage of Collections, exception handling, and logging in Java
 */
public class FileProcessor {
    private static final Logger logger = Logger.getLogger(FileProcessor.class.getName());

    /**
     * Reads all lines from a file into a List<String>
     */
    public static List<String> readFile(String filePath) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
            logger.info("Read " + lines.size() + " lines from " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + filePath, e);
        }
        return lines;
    }

    /**
     * Writes a list of strings to a file
     */
    public static void writeFile(String filePath, List<String> lines) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))) {
            for (String line : lines) {
                bw.write(line);
                bw.newLine();
            }
            logger.info("Wrote " + lines.size() + " lines to " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing file: " + filePath, e);
        }
    }

    /**
     * Counts the number of lines in a file
     */
    public static int countLines(String filePath) {
        int count = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            while (br.readLine() != null) {
                count++;
            }
            logger.info("File " + filePath + " has " + count + " lines.");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error counting lines in file: " + filePath, e);
        }
        return count;
    }

    /**
     * Searches for a keyword in a file and returns the lines containing it
     */
    public static List<String> searchInFile(String filePath, String keyword) {
        List<String> result = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 1;
            while ((line = br.readLine()) != null) {
                if (line.contains(keyword)) {
                    result.add("Line " + lineNumber + ": " + line);
                }
                lineNumber++;
            }
            logger.info("Found " + result.size() + " lines containing '" + keyword + "' in " + filePath);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error searching in file: " + filePath, e);
        }
        return result;
    }

    // Example usage
    public static void main(String[] args) {
        String filePath = "example.txt";
        List<String> lines = Arrays.asList("Hello world", "Java file processing", "Logging example", "Collections demo");
        writeFile(filePath, lines);

        List<String> readLines = readFile(filePath);
        System.out.println("File content:");
        for (String line : readLines) {
            System.out.println(line);
        }

        int lineCount = countLines(filePath);
        System.out.println("Total lines: " + lineCount);

        List<String> found = searchInFile(filePath, "Java");
        System.out.println("Lines containing 'Java':");
        for (String l : found) {
            System.out.println(l);
        }
    }
} 