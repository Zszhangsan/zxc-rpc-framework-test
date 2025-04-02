package zxc.rpc.zxcrpcremote.map;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class MyHashMap1Test {

    @Test
    void testApi() {
        MyHashMap1<String, String> myHashMap1 = new MyHashMap1<>();
        int count = 10000;

        for (int i = 0; i < count; i++) {
            myHashMap1.put(String.valueOf(i), String.valueOf(i));
        }

        assertEquals(count, myHashMap1.size());
        for (int i = 0; i < count; i++) {
            assertEquals(String.valueOf(i), myHashMap1.get(String.valueOf(i)));
        }
        myHashMap1.remove("8");

        assertNull(myHashMap1.get("8"));
        assertEquals(count - 1, myHashMap1.size());
    }
    @Test
    void testApi2() {
        MyHashMap2<String, String> myHashMap = new MyHashMap2<>();
        int count = 10000;

        for (int i = 0; i < count; i++) {
            myHashMap.put(String.valueOf(i), String.valueOf(i));
        }

        assertEquals(count, myHashMap.size());
        for (int i = 0; i < count; i++) {
            assertEquals(String.valueOf(i), myHashMap.get(String.valueOf(i)));
        }
        myHashMap.remove("8");

        assertNull(myHashMap.get("8"));
        assertEquals(count - 1, myHashMap.size());
    }
    @Test
    void testApi3() {
        MyHashMap3<String, String> myHashMap = new MyHashMap3<>();
        int count = 1000000;

        for (int i = 0; i < count; i++) {
            myHashMap.put(String.valueOf(i), String.valueOf(i));
        }

        assertEquals(count, myHashMap.size());
        for (int i = 0; i < count; i++) {
            assertEquals(String.valueOf(i), myHashMap.get(String.valueOf(i)));
        }
        myHashMap.remove("8");

        assertNull(myHashMap.get("8"));
        assertEquals(count - 1, myHashMap.size());
    }
    @Test
    void testApi4() {
        MyHashMap4<String, String> myHashMap = new MyHashMap4<>();
        int count = 1000000;

        for (int i = 0; i < count; i++) {
            myHashMap.put(String.valueOf(i), String.valueOf(i));
        }

        assertEquals(count, myHashMap.size());
        for (int i = 0; i < count; i++) {
            assertEquals(String.valueOf(i), myHashMap.get(String.valueOf(i)));
        }
        myHashMap.remove("8");

        assertNull(myHashMap.get("8"));
        assertEquals(count - 1, myHashMap.size());
    }
}
