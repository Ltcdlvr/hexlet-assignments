package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
class InMemoryKV implements KeyValueStorage {

    Map<String, String> internalStorage;

    public InMemoryKV(Map<String, String> internalStorage) {
        this.internalStorage = new HashMap<>(internalStorage);
    }

    @Override
    public void set(String key, String value) {
        this.internalStorage.put(key, value);
    }

    @Override
    public void unset(String key) {
        this.internalStorage.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return this.internalStorage.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(this.internalStorage);
    }
}
// END
