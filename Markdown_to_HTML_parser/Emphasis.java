package Parser;

import java.util.List;

public class Emphasis extends MarkupElement {
    public Emphasis(List<MarkupElement> elements) {
        this.elements = elements;
        this.htmlTag = "em";
    }
}
