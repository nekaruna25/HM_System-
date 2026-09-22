import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    private static final String DATA_FOLDER = "hotel_data";

    static {
        File folder = new File(DATA_FOLDER);
        if (!folder.exists()) {
            folder.mkdirs();
        }
    }

    public static String clean(String value) {
        if (value == null) return "";
        return value.replace("|", "/").replace("\n", " ").replace("\r", " ");
    }

    public static String[] split(String line, int expected) {
        if (line == null) return new String[0];
        String[] parts = line.split("\\|", -1);
        if (parts.length == expected) return parts;
        return parts;
    }

    public static void writeLines(String fileName, List<String> lines) {
        File file = new File(DATA_FOLDER, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (String line : lines) {
                writer.write(line);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error writing " + fileName + ": " + e.getMessage());
        }
    }

    public static List<String> readLines(String fileName) {
        List<String> lines = new ArrayList<>();
        File file = new File(DATA_FOLDER, fileName);

        if (!file.exists()) {
            return lines;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            System.out.println("Error reading " + fileName + ": " + e.getMessage());
        }

        return lines;
    }

    public static void appendLine(String fileName, String line) {
        File file = new File(DATA_FOLDER, fileName);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            System.out.println("Error appending data: " + e.getMessage());
        }
    }

    public static void createEmptyFile(String fileName) {
        File file = new File(DATA_FOLDER, fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.out.println("Could not create " + fileName);
            }
        }
    }
}
