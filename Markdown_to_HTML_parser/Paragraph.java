package Parser;

import java.util.List;

public class Paragraph extends MarkupElement {
    public Paragraph(List<MarkupElement> elements) {
        this.elements = elements;
        this.htmlTag = "p";
    }
}
