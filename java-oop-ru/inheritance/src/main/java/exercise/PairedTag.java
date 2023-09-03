package exercise;

import java.util.Map;
import java.util.List;
import java.util.stream.Collectors;

// BEGIN
class PairedTag implements Tag {
    private String name;
    private Map<String, String> attributes;
    private String body;
    private List<Tag> childrens;

    public PairedTag(String name, Map<String, String> attributes, String body, List<Tag> childrens) {
        this.name = name;
        this.attributes = attributes;
        this.body = body;
        this.childrens = childrens;
    }

    @Override
    public String toString() {
        StringBuilder res = new StringBuilder();
        res.append("<" + name);
        for (Map.Entry attribute: attributes.entrySet()) {
            res.append(" " + attribute.getKey() + "=\"" + attribute.getValue() + "\"");
        }
        res.append(">");
        res.append(body);
        for (Tag child: childrens) {
            res.append(child.toString());
        }
        res.append("</" + name + ">");
        return res.toString();
    }
}
// END
