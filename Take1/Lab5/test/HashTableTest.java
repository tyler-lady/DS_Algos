import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HashTableTest {
    HashTable table = new HashTable();

    List<Entry> entryGen(){
        List<Entry> list = new ArrayList<Entry>();
        Entry entry = new Entry("today", "meeting");
        Entry entry1 = new Entry("tomorrow", "news");
        Entry entry2 = new Entry("yesterday", "eclectic");
        Entry entry3 = new Entry("new", "stuff");
        Entry entry4 = new Entry("old", "can");
        Entry entry5 = new Entry("archive", "be");
        Entry entry6 = new Entry("inbox", "fun");
        Entry entry7 = new Entry("outbox", "but");
        Entry entry8 = new Entry("today", "this");
        Entry entry9 = new Entry("last week", "boring");

        list.add(entry);
        list.add(entry1);
        list.add(entry2);
        list.add(entry3);
        list.add(entry4);
        list.add(entry5);
        list.add(entry6);
        list.add(entry7);
        list.add(entry8);
        list.add(entry9);

        return list;
    }

    @Test
    void putTest(){
        List<Entry> list = entryGen();

        System.out.println("\nInserting key: today");
        table.put(list.get(0).key, list.get(0).value);
        assertEquals(1, table.getSize());
        System.out.println("Inserting finished!");
        System.out.println("\nInserting key: tomorrow");
        table.put(list.get(1).key, list.get(1).value);
        assertEquals(2, table.getSize());
        System.out.println("Inserting finished!");
        System.out.println("\nInserting key: yesterday");
        table.put(list.get(2).key, list.get(2).value);
        assertEquals(3, table.getSize());
        System.out.println("Inserting finished!");
        System.out.println("\nInserting key: new");
        table.put(list.get(3).key, list.get(3).value);
        assertEquals(4, table.getSize());
        System.out.println("Inserting finished!");
        System.out.println("\nInserting key: old");
        table.put(list.get(4).key, list.get(4).value);
        assertEquals(5, table.getSize());
        System.out.println("Inserting finished!");
        System.out.println("\nInserting key: archive");
        table.put(list.get(5).key, list.get(5).value);
        assertEquals(6, table.getSize());
        System.out.println("Inserting finished!");
        System.out.println("\nInserting key: inbox");
        table.put(list.get(6).key, list.get(6).value);
        assertEquals(7, table.getSize());
        System.out.println("Inserting finished!");
        System.out.println("\nInserting key: outbox");
        table.put(list.get(7).key, list.get(7).value);
        assertEquals(8, table.getSize());
        System.out.println("Inserting finished!");
        System.out.println("\nInserting key: today");
        table.put(list.get(8).key, list.get(8).value);
        assertEquals(8, table.getSize());
        System.out.println("Inserting finished!");
    }

    @Test
    void getTest(){
        putTest();
        assertEquals(8, table.getSize());

        assertEquals("this", table.get("today"));
        assertEquals("can", table.get("old"));
        assertNull(table.get("dog"));
    }

    @Test
    void removeTest(){
        putTest();

        table.remove("today");
        assertEquals(7, table.getSize());
    }
}