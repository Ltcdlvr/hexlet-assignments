package exercise;

import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Objects;

// BEGIN
class App {
    public static List<Map<String, String>> findWhere(List<Map<String, String>> books, Map<String, String> conditions) {
        List<Map<String, String>> res = new ArrayList<>();
        for (Map<String, String> book: books) {
            boolean thisIsOk = true;
            for (Map.Entry <String, String> condition: conditions.entrySet()) {
                if (!Objects.equals(book.get(condition.getKey()), condition.getValue())) {
                    thisIsOk = false;
                    break;
                }
            }
            if (thisIsOk) {
                res.add(book);
            }
        }
        return res;
    }
}
//END
