package Parser;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<String> inputData = new LinkedList<>();
        List<String> outputData;

        try (
                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(
                                Files.newInputStream(Path.of(args[0])),
                                StandardCharsets.UTF_8
                        )
                )
                ;
                BufferedWriter writer = new BufferedWriter(
                        new OutputStreamWriter(
                                Files.newOutputStream(Path.of(args[1])),
                                StandardCharsets.UTF_8)
                )
        ) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                inputData.add(currentLine);
            }

            Parser parser = new Parser(inputData);
            outputData = parser.parse();
            for (String str : outputData) {
                writer.write(str);
                writer.newLine();
            }

        } catch (Exception e) {
            System.err.println("Возникла ошибка при чтении содержимого из файла: " + e.getMessage());
        }



    }
}