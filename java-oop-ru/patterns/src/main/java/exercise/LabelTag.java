package exercise;

// BEGIN
class LabelTag implements TagInterface {
    private String label;
    private TagInterface internalTag;

    public LabelTag(String label, TagInterface internalTag) {
        this.label = label;
        this.internalTag = internalTag;
    }

    @Override
    public String render() {
        return "<label>" + label + internalTag.render() + "</label>";
    }
}
// END
