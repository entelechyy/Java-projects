package Parser;

import java.util.List;

public class Strong extends MarkupElement {
    public Strong(List<MarkupElement> elements) {
        this.elements = elements;
        this.htmlTag = "strong";
    }
}
