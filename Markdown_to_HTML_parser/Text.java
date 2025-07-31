package Parser;

public class Text extends MarkupElement {
    private String text;

    public Text(String text) {
        this.text = text;
    }

    @Override
    public void convertToHTML(StringBuilder sb) {
        for (char c : text.toCharArray()) {
            switch (c) {
                case '<' -> sb.append("&lt;");
                case '>' -> sb.append("&gt;");
                case '&' -> sb.append("&amp;");
                default -> sb.append(c);
            }
        }
    }
}
