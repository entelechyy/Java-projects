//package Ws;не мопомжет каст

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashMap;
import java.util.Map;

public class WordStatInputBase {
    public static void main(String[] args) {
        Map<String, Integer> wordCount = new LinkedHashMap<>();

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(new FileInputStream(args[0]), StandardCharsets.UTF_8),
                        8192
                );
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(new FileOutputStream(args[1]), StandardCharsets.UTF_8),
                        8192
                )
        ) {
            StringBuilder word = new StringBuilder();
            int ch;
            while ((ch = reader.read()) != -1) {
                if (isWordChar((char) ch)) {
                    word.append((char) ch);
                } else {
                    if (word.length() > 0) {
                        String lowerWord = word.toString().toLowerCase();
                        wordCount.put(lowerWord, wordCount.getOrDefault(lowerWord, 0) + 1);
                        word.setLength(0);
                    }
                }
            }

            if (word.length() > 0) {
                String lowerWord = word.toString().toLowerCase();
                wordCount.put(lowerWord, wordCount.getOrDefault(lowerWord, 0) + 1);
            }

            for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
                writer.write(entry.getKey() + " " + entry.getValue());
                writer.newLine();
            }

        } catch (IOException e) {
            System.err.println("Error processing files: " + e.getMessage());
        }
    }

    private static boolean isWordChar(char c) {
        return Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION;
    }
}