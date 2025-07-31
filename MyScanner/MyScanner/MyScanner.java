package MyScanner;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;

public class MyScanner implements AutoCloseable {

    private final Reader reader;
    //самый быстрый результат для реверса был на 128
    private final int bufferSize = 1024;
    private final char[] buffer = new char[bufferSize];
    private int charsInBuffer = 0; //кол-во прочитанных символов
    private int index = 0; //индекс указателя внутри буфера
    private final String lineSeparator = System.lineSeparator();

    public MyScanner(Reader reader) {
        this.reader = reader;
    }

    // чтение данных из строки
    public MyScanner(String inputLine) throws IllegalArgumentException {
        this.reader = new StringReader(inputLine);
    }

    //чтение данных из консоли
    public MyScanner() throws IOException {
        this.reader = new InputStreamReader(System.in, StandardCharsets.UTF_8);
    }

    //чтение данных из InputStream
    public MyScanner(InputStream in) {
        this.reader = new InputStreamReader(in, StandardCharsets.UTF_8);
    }

    //чтение данных из файла
    public MyScanner(FileInputStream file) throws IOException {
        this.reader = new InputStreamReader(file, StandardCharsets.UTF_8);
    }

    @Override
    //метод для закрытия сканнера с @Override, т.к. мой класс implements AutoCloseable
    public void close() {
        try {
            reader.close();
        } catch (IOException e) {
            System.err.println("Error while trying to close reader: " + e.getMessage());
        }
    }

    //метод для заполнения буфера новыми данными
    private void readData() throws IOException {
        if (index == charsInBuffer || charsInBuffer == -1) {
            charsInBuffer = reader.read(buffer);
            index = 0;
        }
    }

    //метод для разбиения последовательностей символов по пробелам
    public String next() throws IOException {
        StringBuilder strBuilder = new StringBuilder();
        while (true) {
            if (index < charsInBuffer) {
                if (!Character.isWhitespace(buffer[index])) {
                    strBuilder.append(buffer[index]);
                } else {
                    if (!strBuilder.isEmpty()) {
                        return strBuilder.toString();
                    }
                    strBuilder.setLength(0);
                }
                index++;
            } else if (charsInBuffer == -1) {
                if (strBuilder.isEmpty()) {
                    throw new NoSuchElementException("There is no data to fill buffer.");
                } else {
                    return strBuilder.toString();
                }
            } else {
                readData();
            }
        }
    }

    //метод, который возвращает слова по критериям ДЗ-4
    public String nextWord() throws IOException {
        StringBuilder str = new StringBuilder();
        readData();
        while (Character.isWhitespace(buffer[index]) && charsInBuffer != -1) {
            index++;
            readData();
        }
        readData();
        while (charsInBuffer != -1) {
            char c = buffer[index++];
            if (!Character.isWhitespace(c)) {
                if (Character.isLetter(c) || c == '\'' || Character.getType(c) == Character.DASH_PUNCTUATION) {
                    str.append(c);
                } else {
                    return (str.toString().toLowerCase());
                }
            } else {
                return (str.toString().toLowerCase());
            }
            readData();
        }
        return (str.toString().toLowerCase());
    }

    //метод, который возвращает следующее число
    public int nextInt() throws IOException {
        StringBuilder str = new StringBuilder();
        readData();
        while (Character.isWhitespace(buffer[index]) && charsInBuffer != -1) {
            index++;
            readData();
        }
        readData();
        while (charsInBuffer != -1) {
            char c = buffer[index++];

            if (!Character.isWhitespace(c)) {
                if (Character.isDigit(c) || (c == '-' && str.isEmpty())) {
                    str.append(c);
                } else {
                    return Integer.parseInt(str.toString());
                }
            } else {
                return Integer.parseInt(str.toString());
            }
            readData();
        }
        return Integer.parseInt(str.toString());
    }

    public boolean hasNextInt() throws IOException {
        readData();
        return charsInBuffer != -1;
    }

    //метод, который возвращает следующее число в восьмиричной системе счисления
    public long nextIntOct() throws IOException {
        StringBuilder str = new StringBuilder();
        readData();
        while (Character.isWhitespace(buffer[index]) && charsInBuffer != -1) {
            index++;
            readData();
        }
        readData();
        while (charsInBuffer != -1) {
            char c = buffer[index++];

            if (!Character.isWhitespace(c)) {
                if ((Character.isDigit(c) && !(c == '9') && !(c == '8')) || (c == '-' && str.isEmpty())) {
                    str.append(c);
                } else {
                    return Long.parseLong(str.toString());
                }
            } else {
                return Long.parseLong(str.toString());
            }
            readData();
        }
        return Long.parseLong(str.toString());
    }

    public boolean hasNextIntOct() throws IOException {
        readData();
        return charsInBuffer != -1;
    }

    public String nextLine() throws IOException {
        StringBuilder strBuilder = new StringBuilder();
        StringBuilder sepWindow = new StringBuilder();
        int separatorIndex = 0;

        readData();
        while (charsInBuffer != -1) {
            char c = buffer[index++];

            strBuilder.append(c);

            if (separatorIndex < lineSeparator.length() && c == lineSeparator.charAt(separatorIndex)) {
                sepWindow.append(c);
                separatorIndex++;
            } else {
                sepWindow.setLength(0);
                separatorIndex = 0;
            }
            if (isLineSep(sepWindow.toString(), lineSeparator)) {
                return strBuilder.toString().trim();
            }

            readData();
        }
        return strBuilder.toString().trim();
    }

    public boolean hasNextLine() throws IOException {
        readData();
        return charsInBuffer != -1;
    }

    //метод, который проверяет на стандартный разделитель линии
    private boolean isLineSep(String sepWindow, String lineSeparator) {
        return sepWindow.equals(lineSeparator) ||
                (sepWindow.endsWith("\n") && lineSeparator.endsWith("\n")) ||
                (sepWindow.endsWith("\r") && lineSeparator.endsWith("\r")) ||
                (sepWindow.endsWith("\n\r") && lineSeparator.endsWith("\n\r")) ||
                (sepWindow.endsWith("\r\n") && lineSeparator.endsWith("\r\n"));
    }
}


