package Parser;

import java.util.LinkedList;
import java.util.List;

public class MarkupElement implements ConvertToHTML {
    private final String startOfTag = "<";
    protected String firstTag;
    protected String lastTag;
    protected String htmlTag = "";

    List<MarkupElement> elements = new LinkedList<>();

    private String getFirstTag() {
        String endOfFirstTag = ">";
        return startOfTag + htmlTag + endOfFirstTag;
    }

    private String getLastTag() {
        String endOfLastTag = "/>";
        return startOfTag + htmlTag + endOfLastTag;
    }

    @Override
    public void convertToHTML(StringBuilder stringBuilder) {
        stringBuilder.append(this.getFirstTag());
        for (MarkupElement element : elements) {
            element.convertToHTML(stringBuilder);
        }
        stringBuilder.append(this.getLastTag());
    }
}
