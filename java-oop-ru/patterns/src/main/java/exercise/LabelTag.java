package exercise;

// BEGIN
public class LabelTag implements TagInterface {
    String label;
    TagInterface tagInterface;

    public LabelTag(String label, TagInterface inputTag) {
        this.label = label;
        this.tagInterface = inputTag;
    }

    @Override
    public String render() {
        return String.format("<label>%s%s</label>", label, tagInterface.render());
    }
}
// END
