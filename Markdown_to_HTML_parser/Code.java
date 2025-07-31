package Parser;

import java.util.List;

public class Code extends MarkupElement {
    public Code(List<MarkupElement> elements) {
        this.elements = elements;
        this.htmlTag = "code";
    }
}
