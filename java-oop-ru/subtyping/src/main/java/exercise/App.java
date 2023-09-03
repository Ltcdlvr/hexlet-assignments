package exercise;

import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

// BEGIN
class App {
    public static void swapKeyValue(KeyValueStorage db) {
        var internalDb = db.toMap();

        for (String key: internalDb.keySet()) {
            db.unset(key);
        }

        for (Map.Entry<String, String> row: internalDb.entrySet()) {
            db.set(row.getValue(), row.getKey());
        }
    }
}
// END
