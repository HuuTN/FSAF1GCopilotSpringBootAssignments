import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.util.logging.*;

public class FileProcessingUtility {
    private static final Logger logger = Logger.getLogger(FileProcessingUtility.class.getName());

    public static List<String> readFile(String path) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line);
            }
            logger.info("Read " + lines.size() + " lines from " + path);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error reading file: " + path, e);
        }
        return lines;
    }

    public static void writeFile(String path, List<String> lines) {
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(path))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
            logger.info("Wrote " + lines.size() + " lines to " + path);
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Error writing file: " + path, e);
        }
    }

    public static List<String> filterLines(List<String> lines, String keyword) {
        List<String> filtered = new ArrayList<>();
        for (String line : lines) {
            if (line.contains(keyword)) {
                filtered.add(line);
            }
        }
        logger.info("Filtered lines with keyword: " + keyword + ", found: " + filtered.size());
        return filtered;
    }

    public static void main(String[] args) {
        if (args.length < 2) {
            System.out.println("Usage: java FileProcessingUtility <inputFile> <outputFile> [keyword]");
            return;
        }
        String inputFile = args[0];
        String outputFile = args[1];
        String keyword = args.length > 2 ? args[2] : null;

        List<String> lines = readFile(inputFile);
        if (keyword != null) {
            lines = filterLines(lines, keyword);
        }
        writeFile(outputFile, lines);
    }
}
