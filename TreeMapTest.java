import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import java.util.Arrays;

public class TreeMapTest {
    private TreeMap<Integer, String> map;

    @BeforeEach
    public void setup() {
        map = new TreeMap<>();
    }

    @Test
    public void testPutAndGet() {
        map.put(1, "BMW");
        map.put(2, "Mercedes");

        assertEquals(2, map.size());
        assertEquals("BMW", map.get(1));
        assertEquals(2, map.size());
        assertEquals("Mercedes", map.get(2));
    }

    @Test
    public void testClear() {
        map.put(1, "BMW");
        map.put(2, "Mercedes");
        map.clear();

        assertEquals(0, map.size());
        assertNull(map.get(1));
        assertNull(map.get(2));
    }

    @Test
    public void testContainsKey() {
        assertFalse(map.containsKey(1));
        map.put(1, "BMW");
        assertTrue(map.containsKey(1));
        boolean containsNull = map.containsKey(null);
        assertFalse(containsNull);
    }

    @Test
    public void testArrayBehaviors() {
        map.put(1, "BMW");
        map.put(2, "Mercedes");

        Integer[] keysOfSize5 = map.toKeyArray(new Integer[5]);
        assertArrayEquals(new Integer[]{1, 2, null, null, null}, keysOfSize5);

        String[] valuesOfSize5 = map.toValueArray(new String[5]);
        assertArrayEquals(new String[]{"BMW", "Mercedes", null, null, null}, valuesOfSize5);

        Integer[] resizedKeys = map.toKeyArray(new Integer[1]);
        assertArrayEquals(new Integer[]{1, 2}, resizedKeys);

        String[] resizedValues = map.toValueArray(new String[1]);
        assertArrayEquals(new String[]{"BMW", "Mercedes"}, resizedValues);
    }

    @Test
    public void testGetBehaviors() {
        map.put(10, "BMW");
        assertEquals("BMW", map.get(10));
        assertNull(map.get(20));
        map.put(5, "Mercedes");
        assertEquals("Mercedes", map.get(5));
        map.put(15, "Toyota");
        assertEquals("Toyota", map.get(15));
    }

    @Test
    public void testContainsKeyBehaviors() {
        assertFalse(map.containsKey(10));
        map.put(10, "Mercedes");
        assertTrue(map.containsKey(10));
        map.put(5, "BMW");
        assertTrue(map.containsKey(5));
        map.put(15, "Toyota");
        assertTrue(map.containsKey(15));
        assertFalse(map.containsKey(20));
    }

    @Test
    public void testKeyBehaviors() {
        assertThrows(IllegalArgumentException.class, () -> map.put(null, "nullKey"));
        assertThrows(IllegalArgumentException.class, () -> map.get(null));
        assertNull(map.get(30));
    }

    @Test
    public void testToValueArray() {
        String[] expectedValues = {"a", "b", "c", "d", "e", "f", "g"};
        Integer[] keys = {1, 2, 3, 4, 5, 6, 7};
        for (int i = 0; i < expectedValues.length; i++) {
            map.put(keys[i], expectedValues[i]);
        }

        String[] valuesOfSize8 = map.toValueArray(new String[8]);
        assertArrayEquals(expectedValues, Arrays.copyOf(valuesOfSize8, expectedValues.length));
        assertNull(valuesOfSize8[7]);

        String[] valuesOfSize3 = map.toValueArray(new String[3]);
        assertEquals(expectedValues.length, valuesOfSize3.length);

        String[] resizedValues = map.toValueArray(new String[1]);
        assertEquals(expectedValues.length, resizedValues.length);
    }

    @Test
    public void testToKeyArray() {
        Integer[] expectedKeys = {1, 2, 3, 4, 5, 6, 7};
        String[] values = {"a", "b", "c", "d", "e", "f", "g"};
        for (int i = 0; i < expectedKeys.length; i++) {
            map.put(expectedKeys[i], values[i]);
        }

        Integer[] keysOfSize8 = map.toKeyArray(new Integer[8]);
        assertArrayEquals(expectedKeys, Arrays.copyOf(keysOfSize8, expectedKeys.length));
        assertNull(keysOfSize8[7]);

        Integer[] keysOfSize3 = map.toKeyArray(new Integer[3]);
        assertEquals(expectedKeys.length, keysOfSize3.length);

        Integer[] resizedKeys = map.toKeyArray(new Integer[1]);
        assertEquals(expectedKeys.length, resizedKeys.length);
    }
}
