import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class HTCTest {
    // TODO: accuracy tests
    HashTableWithChaining table = new HashTableWithChaining();

    @Test
    void putTest(){
        table.put("today", "meeting");
        assertEquals(1, table.size());
        table.put("tomorrow", "news");
        assertEquals(2, table.size());
        table.put("yesterday", "eclectic");
        assertEquals(3, table.size());
        table.put("new", "stuff");
        assertEquals(4, table.size());
        table.put("old", "can");
        assertEquals(5, table.size());
        table.put("archive", "be");
        assertEquals(6, table.size());
        table.put("inbox", "fun");
        assertEquals(7, table.size());
        table.put("outbox", "but");
        assertEquals(8, table.size());
        table.put("today", "this");
        assertEquals(8, table.size());
    }

    @Test
    void resizeTest(){
        putTest();
        table.put("hello", "bye");
        //assertEquals(1, table.size());
        table.put("could", "woodchuck");
        //assertEquals(2, table.size());
        table.put("chuck", "if");
        //assertEquals(3, table.size());
        table.put("wood", "could");
        //assertEquals(4, table.size());
        table.put("smelly", "feet");
        //assertEquals(5, table.size());
        table.put("ewww", "haha");
        //assertEquals(6, table.size());
        table.put("jello", "or");
        //assertEquals(7, table.size());
        table.put("pudding", "ftw");
        //assertEquals(8, table.size());
        table.put("burger", "dope");
        assertEquals(17, table.size());
    }

    @Test
    void getTest(){
        putTest();
        assertEquals(8, table.size());

        assertEquals("this", table.get("today"));
        assertEquals("can", table.get("old"));
        assertNull(table.get("dog"));
    }

    @Test
    void removeTest(){
        putTest();

        assertTrue(table.remove("today"));
        assertEquals(7, table.size());
        assertFalse(table.remove("dog"));
        assertEquals(7, table.size());
    }
}
