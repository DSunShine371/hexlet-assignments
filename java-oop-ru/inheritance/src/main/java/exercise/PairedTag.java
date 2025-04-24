package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
public class PairedTag extends SingleTag {
    public final String tagsBody;
    List<? super SingleTag> singleTags;

    public PairedTag(
            String tagName,
            Map<String, String> attributes,
            String tagsBody,
            List<? super SingleTag> singleTags
    ) {
        super(tagName, attributes);
        this.tagsBody = tagsBody;
        this.singleTags = singleTags;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String childTags = singleTags.stream().map(Object::toString).collect(Collectors.joining());
        result.append(super.toString())
                .append(childTags)
                .append(tagsBody)
                .append("</")
                .append(tagName)
                .append(">");
        return result.toString();
    }
}
// END
