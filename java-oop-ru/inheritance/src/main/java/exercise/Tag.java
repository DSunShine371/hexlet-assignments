package exercise;

import java.util.stream.Collectors;
import java.util.Map;

// BEGIN
public class Tag {
    protected final String tagName;
    protected Map<String, String> attributes;

    public Tag(String tagName, Map<String, String> attributes) {
        this.tagName = tagName;
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        StringBuilder result = new StringBuilder();
        String stringOfAttributes = "";
        if (attributes != null && !attributes.isEmpty()) {
            stringOfAttributes = " " + attributes.entrySet().stream()
                    .map(entry -> entry.getKey() + "=\"" + entry.getValue() + "\"")
                    .collect(Collectors.joining(" "));
        }
        result.append("<")
                .append(tagName)
                .append(stringOfAttributes)
                .append(">");
        return result.toString();
    }
}
// END
