package exercise;

import java.util.HashMap;
import org.junit.jupiter.api.BeforeEach;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.Map;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;

import static exercise.Utils.serialize;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class FileKVTest {

    private static Path filepath = Paths.get("src/test/resources/file").toAbsolutePath().normalize();

    @BeforeEach
    public void beforeEach() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String content = mapper.writeValueAsString(new HashMap<String, String>());
        Files.writeString(filepath, content, StandardOpenOption.TRUNCATE_EXISTING);
    }

    // BEGIN
    @Test
    void testConstructorInitializesWithData() throws Exception {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("key1", "value1");
        initialData.put("key2", "value2");

        FileKV fileKV = new FileKV(filepath.toString(), initialData);

        String fileContent = Files.readString(filepath);
        assertEquals(serialize(initialData), fileContent);

        assertEquals(initialData, fileKV.toMap());
    }

    @Test
    void testConstructorInitializesWithEmptyData() {
        Map<String, String> initialData = new HashMap<>();

        FileKV fileKV = new FileKV(filepath.toString(), initialData);

        assertTrue(fileKV.toMap().isEmpty());
    }


    @Test
    void testSetAddsNewKey() {
        FileKV fileKV = new FileKV(filepath.toString(), new HashMap<>());

        String newKey = "newKey";
        String newValue = "newValue";
        fileKV.set(newKey, newValue);

        Map<String, String> expectedData = new HashMap<>();
        expectedData.put(newKey, newValue);

        assertEquals(expectedData, fileKV.toMap());
        assertEquals(newValue, fileKV.get(newKey, "default"));
    }

    @Test
    void testSetUpdatesExistingKey() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("key1", "value1");
        initialData.put("key2", "value2");

        FileKV fileKV = new FileKV(filepath.toString(), initialData);

        String existingKey = "key1";
        String updatedValue = "updatedValue";
        fileKV.set(existingKey, updatedValue);

        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("key1", updatedValue);
        expectedData.put("key2", "value2");
        assertEquals(expectedData, fileKV.toMap());
        assertEquals(updatedValue, fileKV.get(existingKey, "default"));
    }

    @Test
    void testUnsetRemovesKey() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("key1", "value1");
        initialData.put("key2", "value2");

        FileKV fileKV = new FileKV(filepath.toString(), initialData);

        String keyToRemove = "key1";
        fileKV.unset(keyToRemove);

        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("key2", "value2");

        assertEquals(expectedData, fileKV.toMap());
        assertEquals("default", fileKV.get(keyToRemove, "default"));
    }

    @Test
    void testUnsetNonExistingKeyDoesNothing() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("key1", "value1");

        FileKV fileKV = new FileKV(filepath.toString(), initialData);

        String nonExistingKey = "nonExistingKey";
        fileKV.unset(nonExistingKey);

        assertEquals(initialData, fileKV.toMap());
    }


    @Test
    void testGetExistingKey() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("testKey", "expectedValue");
        initialData.put("otherKey", "otherValue");

        FileKV fileKV = new FileKV(filepath.toString(), initialData);

        String value = fileKV.get("testKey", "defaultValue");

        assertEquals("expectedValue", value);
    }

    @Test
    void testGetNonExistingKey() {
        Map<String, String> initialData = new HashMap<>();
        initialData.put("key1", "value1");

        FileKV fileKV = new FileKV(filepath.toString(), initialData);

        String value = fileKV.get("nonExistingKey", "defaultValue");

        assertEquals("defaultValue", value);
    }

    @Test
    void testToMapReturnsCurrentState() {
        FileKV fileKV = new FileKV(filepath.toString(), new HashMap<>());

        fileKV.set("a", "1");
        fileKV.set("b", "2");
        fileKV.unset("a");
        fileKV.set("c", "3");

        Map<String, String> expectedData = new HashMap<>();
        expectedData.put("b", "2");
        expectedData.put("c", "3");

        assertEquals(expectedData, fileKV.toMap());
    }

    @Test
    void testGetOnEmptyStorage() {
        FileKV fileKV = new FileKV(filepath.toString(), new HashMap<>());

        String value = fileKV.get("anyKey", "default");

        assertEquals("default", value);
    }
    // END
}
