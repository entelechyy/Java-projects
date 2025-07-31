package Parser;

import java.util.List;

public class Header extends MarkupElement {
    private final int depth;

    public Header(List<MarkupElement> elements, int depth) {
        this.depth = depth;
        this.elements = elements;
        this.htmlTag = "h" + depth;
    }
}
