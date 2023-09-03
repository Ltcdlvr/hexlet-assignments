package exercise;

// BEGIN
class InputTag implements TagInterface {
    private String inputType;
    private String value;
    public InputTag(String inputType, String value) {
        this.inputType = inputType;
        this.value = value;
    }
    @Override
    public String render() {
        return "<input type=\"" + inputType + "\" value=\"" + value + "\">";
    }
}
// END
