package Parser;

import java.util.List;

public class Strikeout extends MarkupElement {
    public Strikeout (List<MarkupElement> elements) {
        this.elements = elements;
        this.htmlTag = "s";
    }
}
