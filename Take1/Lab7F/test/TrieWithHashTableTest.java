import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TrieWithHashTableTest {
    //TODO: Your own JUnit tests for every method

    TrieWithHashTable trie = new TrieWithHashTable();

    @Test
    void insertTest(){
        trie.insert("hello");
        trie.insert("horse");
        trie.insert("car");

        assertTrue(trie.search("hello"));
        assertTrue(trie.search("horse"));
        assertTrue(trie.search("car"));
    }

    @Test
    void searchTest(){
        assertFalse(trie.search(""));
        insertTest();
        assertTrue(trie.search("car"));
        assertTrue(trie.search("horse"));
        trie.delete("hello");
        assertFalse(trie.search("hello"));
        assertFalse(trie.search("hoard"));
    }

    @Test
    void deleteTest(){
        insertTest();

        trie.delete("hello");
        assertFalse(trie.search("hello"));
        assertTrue(trie.search("horse"));
        assertTrue(trie.search("car"));
    }
}