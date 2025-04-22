package exercise;

import java.util.Map;

import static exercise.Utils.deserialize;
import static exercise.Utils.readFile;
import static exercise.Utils.serialize;
import static exercise.Utils.writeFile;

// BEGIN
public class FileKV implements KeyValueStorage {
    String pathToStorageKV;

    public FileKV(String path, Map<String, String> content) {
        this.pathToStorageKV = path;
        String serializeContent = serialize(content);
        writeFile(pathToStorageKV, serializeContent);
    }

    @Override
    public void set(String key, String value) {
        Map<String, String> storageKV = toMap();
        storageKV.put(key, value);
        String serializeContent = serialize(storageKV);
        writeFile(pathToStorageKV, serializeContent);
    }

    @Override
    public void unset(String key) {
        Map<String, String> storageKV = toMap();
        storageKV.remove(key);
        String serializeContent = serialize(storageKV);
        writeFile(pathToStorageKV, serializeContent);
    }

    @Override
    public String get(String key, String defaultValue) {
        Map<String, String> storageKV = toMap();
        return storageKV.getOrDefault(key, defaultValue);
    }

    @Override
    public Map<String, String> toMap() {
        String content = readFile(pathToStorageKV);
        return deserialize(content);
    }
}
// END
