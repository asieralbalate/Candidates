import java.io.*;
import java.util.*;

public class Main {
    private static Map<String, Set<String>> map = new HashMap<>();

    public static void main(String[] args) throws IOException {
        readCSV("candidates1.csv");
        readCSV("candidates2.csv");
        printCSV("candidatesTotal.csv");


    }

    public static void readCSV(String filename) throws IOException {
        BufferedReader in = null;
        try {
            in = new BufferedReader(new FileReader(filename));
            String line = "";
            while ((line = in.readLine()) != null) {
                String[] items = line.split(",");
                String language = items[0];
                Set<String> candidates = map.get(language);
                if (candidates == null) {
                    candidates = new HashSet<>();
                }
                for (int i = 1; i < items.length; i++) {
                    candidates.add(items[i]);
                }
                map.put(language, candidates);
            }
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }

    public static void printCSV(String filename) throws IOException {
        PrintWriter out = null;
        try {
            out = new PrintWriter(new FileWriter(filename));
            Set<String> keys = map.keySet();
            for (String key : keys) {
                String line = key;
                Set<String> candidates = map.get(key);
                for (String candidate : candidates) {
                    line += (", " + candidate);
                }
                out.println(line);
            }
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }
}
