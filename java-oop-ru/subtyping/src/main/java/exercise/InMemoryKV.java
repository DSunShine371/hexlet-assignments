package exercise;

import java.util.Map;
import java.util.HashMap;

// BEGIN
public class InMemoryKV implements KeyValueStorage {
    Map<String, String> storageKV;

    public InMemoryKV(Map<String, String> storageKV) {
        this.storageKV = new HashMap<>(storageKV);
    }

    @Override
    public void set(String key, String value) {
        storageKV.put(key, value);
    }

    @Override
    public void unset(String key) {
        storageKV.remove(key);
    }

    @Override
    public String get(String key, String defaultValue) {
        return storageKV.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        return new HashMap<>(storageKV);
    }
}
// END
