package Parser;

import java.util.*;

public class Parser {
    private final List<String> inputData;

    public Parser(List<String> inputData) {
        this.inputData = inputData;
    }

    public List<String> parse() {
        List<String> output = new ArrayList<>();
        int i = 0;

        while (i < inputData.size()) {
            // Пропустить пустые строки
            while (i < inputData.size() && inputData.get(i).trim().isEmpty()) {
                i++;
            }
            if (i >= inputData.size()) break;

            // Проверка на заголовок
            int headerLevel = countHeaderLevel(inputData.get(i));
            if (headerLevel > 0) {
                StringBuilder block = new StringBuilder();
                while (i < inputData.size() && !inputData.get(i).trim().isEmpty()) {
                    if (block.length() > 0) block.append('\n');
                    block.append(inputData.get(i));
                    i++;
                }
                List<MarkupElement> inline = parseInline(block.substring(headerLevel + 1).strip());
                Header header = new Header(inline, headerLevel);
                output.add(toHTML(header));
                continue;
            }

            // Иначе абзац
            StringBuilder paragraph = new StringBuilder();
            while (i < inputData.size() && !inputData.get(i).trim().isEmpty()) {
                if (paragraph.length() > 0) paragraph.append('\n');
                paragraph.append(inputData.get(i));
                i++;
            }
            List<MarkupElement> inline = parseInline(paragraph.toString());
            Paragraph p = new Paragraph(inline);
            output.add(toHTML(p));
        }

        return output;
    }

    private int countHeaderLevel(String line) {
        int i = 0;
        while (i < line.length() && line.charAt(i) == '#') i++;
        if (i > 0 && i < line.length() && line.charAt(i) == ' ') {
            return Math.min(i, 6);
        }
        return 0;
    }

    private String toHTML(MarkupElement element) {
        StringBuilder sb = new StringBuilder();
        element.convertToHTML(sb);
        return sb.toString();
    }

    // Парсинг inline-разметки
    private List<MarkupElement> parseInline(String text) {
        List<MarkupElement> result = new ArrayList<>();
        Stack<String> tagStack = new Stack<>();
        Stack<List<MarkupElement>> elementsStack = new Stack<>();
        elementsStack.push(result);

        StringBuilder plainText = new StringBuilder();

        int i = 0;
        while (i < text.length()) {
            char c = text.charAt(i);

            // --- Экранирование ---
            if (c == '\\' && i + 1 < text.length()) {
                plainText.append(text.charAt(i + 1));
                i += 2;
                continue;
            }

            // --- Проверка на маркеры ---
            String marker = null;
            if (i + 1 < text.length()) {
                String two = text.substring(i, i + 2);
                if (two.equals("**") || two.equals("__") || two.equals("--")) {
                    marker = two;
                }
            }
            if (marker == null) {
                if (c == '*' || c == '_' || c == '`') {
                    marker = String.valueOf(c);
                }
            }

            if (marker != null) {
                // Добавляем накопленный plainText в текущий блок
                if (plainText.length() > 0) {
                    elementsStack.peek().add(new Text(plainText.toString()));
                    plainText.setLength(0);
                }

                // Закрытие или открытие тега
                if (!tagStack.isEmpty() && tagStack.peek().equals(marker)) {
                    tagStack.pop();
                    List<MarkupElement> inner = elementsStack.pop();
                    MarkupElement el = switch (marker) {
                        case "**", "__" -> new Strong(inner);
                        case "*", "_" -> new Emphasis(inner);
                        case "--" -> new Strikeout(inner);
                        case "`" -> new Code(inner);
                        default -> new Text(marker);
                    };
                    elementsStack.peek().add(el);
                } else {
                    tagStack.push(marker);
                    elementsStack.push(new ArrayList<>());
                }

                i += marker.length();
            } else {
                plainText.append(c);
                i++;
            }
        }

        if (plainText.length() > 0) {
            elementsStack.peek().add(new Text(plainText.toString()));
        }

        // Закрываем незакрытые теги
        while (!tagStack.isEmpty() && elementsStack.size() > 1) {
            String unclosed = tagStack.pop();
            List<MarkupElement> unclosedElements = elementsStack.pop();

            elementsStack.peek().add(new Text(unclosed));
            elementsStack.peek().addAll(unclosedElements);
        }

        return result;
    }
}
