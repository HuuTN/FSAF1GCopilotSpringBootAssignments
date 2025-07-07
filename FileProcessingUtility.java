import java.io.*;
import java.util.*;
import java.util.logging.*;

// Singleton Logger
class AppLogger {
    private static final Logger logger = Logger.getLogger("FileProcessorLogger");
    private static AppLogger instance;
    private AppLogger() {
        try {
            Handler fh = new FileHandler("app.log", true);
            logger.addHandler(fh);
            logger.setUseParentHandlers(false);
        } catch (IOException e) {
            logger.warning("Failed to initialize file handler: " + e.getMessage());
        }
    }
    public static synchronized AppLogger getInstance() {
        if (instance == null) instance = new AppLogger();
        return instance;
    }
    public Logger getLogger() { return logger; }
}

// Strategy Pattern for file processing
interface FileProcessingStrategy {
    void process(File file) throws IOException;
}

class LineCountStrategy implements FileProcessingStrategy {
    public void process(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            long count = br.lines().count();
            AppLogger.getInstance().getLogger().info("Line count: " + count);
        }
    }
}

class WordCountStrategy implements FileProcessingStrategy {
    public void process(File file) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            long count = br.lines().flatMap(line -> Arrays.stream(line.split("\\s+"))).count();
            AppLogger.getInstance().getLogger().info("Word count: " + count);
        }
    }
}

// Factory Pattern for strategies
class FileProcessingStrategyFactory {
    public static FileProcessingStrategy getStrategy(String type) {
        switch (type.toLowerCase()) {
            case "line": return new LineCountStrategy();
            case "word": return new WordCountStrategy();
            default: throw new IllegalArgumentException("Unknown strategy type");
        }
    }
}

// Observer Pattern
interface FileProcessObserver {
    void onProcess(File file, String result);
}

class ConsoleObserver implements FileProcessObserver {
    public void onProcess(File file, String result) {
        System.out.println("Processed " + file.getName() + ": " + result);
    }
}

class FileProcessor {
    private final List<FileProcessObserver> observers = new ArrayList<>();
    private FileProcessingStrategy strategy;
    public void setStrategy(FileProcessingStrategy strategy) {
        this.strategy = strategy;
    }
    public void addObserver(FileProcessObserver observer) {
        observers.add(observer);
    }
    public void processFiles(Collection<File> files) {
        for (File file : files) {
            try {
                if (strategy != null) strategy.process(file);
                notifyObservers(file, "Success");
            } catch (Exception e) {
                AppLogger.getInstance().getLogger().severe("Error processing " + file.getName() + ": " + e.getMessage());
                notifyObservers(file, "Failed: " + e.getMessage());
            }
        }
    }
    private void notifyObservers(File file, String result) {
        for (FileProcessObserver o : observers) {
            o.onProcess(file, result);
        }
    }
}

// Example usage
class Main {
    public static void main(String[] args) {
        FileProcessor processor = new FileProcessor();
        processor.setStrategy(FileProcessingStrategyFactory.getStrategy("line"));
        processor.addObserver(new ConsoleObserver());
        List<File> files = Arrays.asList(new File("test1.txt"), new File("test2.txt"));
        processor.processFiles(files);
    }
}
