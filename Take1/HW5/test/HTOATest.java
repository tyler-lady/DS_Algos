import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

public class HTOATest {
    // TODO: accuracy tests
    HashTableOpenAddressing table = new HashTableOpenAddressing(); //doublehash
    HashTableOpenAddressing table1 = new HashTableOpenAddressing<>(1);

    HashTableOpenAddressing table2 = new HashTableOpenAddressing<>(2);

    HashTableOpenAddressing loopTable = new HashTableOpenAddressing<>(10, 1);

    @Test
    void putTest(){
        table.put("today", "tomorrow");
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

        table1.put("today", "tomorrow");
        assertEquals(1, table1.size());
        table1.put("tomorrow", "news");
        assertEquals(2, table1.size());
        table1.put("yesterday", "eclectic");
        assertEquals(3, table1.size());
        table1.put("new", "stuff");
        assertEquals(4, table1.size());
        table1.put("old", "can");
        assertEquals(5, table1.size());
        table1.put("archive", "be");
        assertEquals(6, table1.size());
        table1.put("inbox", "fun");
        assertEquals(7, table1.size());
        table1.put("outbox", "but");
        assertEquals(8, table1.size());
        table1.put("today", "this");
        assertEquals(8, table1.size());

        table2.put("today", "tomorrow");
        assertEquals(1, table2.size());
        table2.put("tomorrow", "news");
        assertEquals(2, table2.size());
        table2.put("yesterday", "eclectic");
        assertEquals(3, table2.size());
        table2.put("new", "stuff");
        assertEquals(4, table2.size());
        table2.put("old", "can");
        assertEquals(5, table2.size());
        table2.put("archive", "be");
        assertEquals(6, table2.size());
        table2.put("inbox", "fun");
        assertEquals(7, table2.size());
        table2.put("outbox", "but");
        assertEquals(8, table2.size());
        table2.put("today", "this");
        assertEquals(8, table2.size());

        loopTable.put(1, "yoo");
        loopTable.put(3, "yoo");
        loopTable.put(5, "yoo");
        loopTable.put(7, "yoo");
        loopTable.put(9, "yoo");
        Throwable exception = assertThrows(RuntimeException.class, ()->{
            loopTable.put(47, "yoo");
        });
    }

    @Test
    void resizeTest(){
        putTest();
        table.put("hello", "bye");
        table.put("could", "woodchuck");
        table.put("chuck", "if");
        table.put("wood", "could");
        table.put("smelly", "feet");
        table.put("ewww", "haha");
        table.put("jello", "or");
        table.put("pudding", "ftw");
        table.put("burger", "dope");
        assertEquals(17, table.size());

        table1.put("hello", "bye");
        table1.put("could", "woodchuck");
        table1.put("chuck", "if");
        table1.put("wood", "could");
        table1.put("smelly", "feet");
        table1.put("ewww", "haha");
        table1.put("jello", "or");
        table1.put("pudding", "ftw");
        table1.put("burger", "dope");
        assertEquals(17, table1.size());

        table2.put("hello", "bye");
        table2.put("could", "woodchuck");
        table2.put("chuck", "if");
        table2.put("wood", "could");
        table2.put("smelly", "feet");
        table2.put("ewww", "haha");
        table2.put("jello", "or");
        table2.put("pudding", "ftw");
        table2.put("burger", "dope");
        assertEquals(17, table2.size());
    }

    @Test
    void getTest(){
        putTest();
        assertEquals(8, table.size());

        assertEquals("this", table.get("today"));
        assertEquals("can", table.get("old"));
        assertNull(table.get("dog"));

        assertEquals(8, table1.size());

        assertEquals("this", table1.get("today"));
        assertEquals("can", table1.get("old"));
        assertNull(table1.get("dog"));

        assertEquals(8, table2.size());

        assertEquals("this", table2.get("today"));
        assertEquals("can", table2.get("old"));
        assertNull(table2.get("dog"));


        Throwable exception = assertThrows(RuntimeException.class, ()->{
            loopTable.get(47);
        });
    }

    @Test
    void removeTest(){
        putTest();

        assertTrue(table.remove("today"));
        assertFalse(table.remove("dog"));

        assertTrue(table1.remove("today"));
        assertFalse(table1.remove("dog"));

        assertTrue(table2.remove("today"));
        assertFalse(table2.remove("dog"));

        Throwable exception = assertThrows(RuntimeException.class, ()->{
            loopTable.remove(47);
        });
    }
}
