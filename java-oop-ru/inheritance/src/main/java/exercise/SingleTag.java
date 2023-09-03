package exercise;

import java.util.Map;

// BEGIN
class SingleTag implements Tag {
    private String name;
    private Map<String, String> attributes;

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("<" + name);
        for (Map.Entry attribute: attributes.entrySet()) {
            res.append(" " + attribute.getKey() + "=\"" + attribute.getValue() + "\"");
        }
        res.append(">");
        return res.toString();
    }

    public SingleTag(String name, Map<String, String> attributes) {
        this.name = name;
        this.attributes = attributes;
    }
}
// END
