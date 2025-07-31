//package Ws.gr36_37;

//String suffix = word.length() < 3 ? word : word.substring(word.length() - 3);

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

public class WordStatWordsSuffix {
    public static void main(String[] args) {
        Map<String, Integer> wordCount = new LinkedHashMap<>();
        Map<String, Integer> suffixCount = new LinkedHashMap<>();

        Path inputPath = Path.of(args[0]);

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(Files.newInputStream(Path.of(args[0])), StandardCharsets.UTF_8)
                );
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(Files.newOutputStream(Path.of(args[1])), StandardCharsets.UTF_8)
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

                        String suffix = word.length() < 3 ? lowerWord : lowerWord.substring(word.length() - 3);
                        suffixCount.put(suffix, suffixCount.getOrDefault(suffix, 0) + 1);

                    }
                    word.setLength(0);
                }
            }

            if (word.length() > 0) {
                String lowerWord = word.toString().toLowerCase();
                wordCount.put(lowerWord, wordCount.getOrDefault(lowerWord, 0) + 1);

                String suffix = word.length() < 3 ? lowerWord : lowerWord.substring(word.length() - 3);
                suffixCount.put(suffix, suffixCount.getOrDefault(suffix, 0) + 1);
            }

            Map<String, Integer> sortedSuffixCount = new TreeMap<>(Comparator.reverseOrder());
            sortedSuffixCount.putAll(suffixCount);

            for (Map.Entry<String, Integer> entry : sortedSuffixCount.entrySet()) {
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
