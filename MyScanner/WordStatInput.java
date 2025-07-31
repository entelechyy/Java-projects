import java.io.*;
import java.util.Map;
import java.util.LinkedHashMap;

import MyScanner.MyScanner;

//treemap, reversecomparator
public class WordStatInput {
    public static void main(String[] args) throws FileNotFoundException {
        //StringBuilder stringBuilder = new StringBuilder();
        Map<String, Integer> dictionary = new LinkedHashMap<>();
        String fileName = args[0];
        try {
            FileInputStream fileInputStream = new FileInputStream(fileName);
            MyScanner scan = new MyScanner(fileInputStream);
            String word = scan.nextWord();
            System.err.println(word);
            while (scan.hasNextLine()) {
                if (!word.isEmpty())
                    dictionary.put(word, dictionary.getOrDefault(word, 0) + 1);
                word = scan.nextWord();
            scan.close();
            }
        } catch (IOException e) {
            System.out.println("Input file reading error: " + e.getMessage());
        }
        try {
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(args[1]), "UTF-8"));
            try {
                for (Map.Entry<String, Integer> entry : dictionary.entrySet()) {
                    String str = entry.getKey() + " " + entry.getValue();
//                    System.err.println(str);
                    writer.write(str);
                    writer.newLine();
//                    System.err.println();
                }
            } finally {
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("Output file writing error: " + e.getMessage());
        }
    }
}