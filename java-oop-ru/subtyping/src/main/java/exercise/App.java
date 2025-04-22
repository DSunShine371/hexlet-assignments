package exercise;

import java.util.HashSet;
import java.util.Set;

// BEGIN
public class App {
    private static final String DEFAULT_VALUE = "default";

    public static void swapKeyValue(KeyValueStorage storage) {
        Set<String> keys = new HashSet<>(storage.toMap().keySet());
        for (String key : keys) {
            String value = storage.get(key, DEFAULT_VALUE);
            storage.set(value, key);
            storage.unset(key);
        }
    }
}
// END
