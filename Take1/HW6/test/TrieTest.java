import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;
public class TrieTest {
    // TODO: accuracy tests

    Trie t;
    @BeforeEach
    public void initTrie() {
        t = new Trie();
        t.root.children.put('c', new TrieNode()); //c
        t.root.children.get('c').isWord = true;

        t.root.children.get('c').children.put('a', new TrieNode()); //cab
        t.root.children.get('c').children.get('a').children.put('b', new TrieNode());
        t.root.children.get('c').children.get('a').children.get('b').isWord = true;

        t.root.children.get('c').children.get('a').children.put('r', new TrieNode()); //carts
        t.root.children.get('c').children.get('a').children.get('r').children.put('t', new TrieNode());
        t.root.children.get('c').children.get('a').children.get('r').children.get('t').children.put('s',new TrieNode());
        t.root.children.get('c').children.get('a').children.get('r').children.get('t').children.get('s').isWord = true;
    }

    @Test
    public void TrieConstructor() {
        Trie testT = new Trie();
        assertFalse(testT.root.isWord);
        assertTrue(testT.root.children.isEmpty());
    }

    @Test
    public void insert() {
        Trie testT = new Trie();

        testT.insert("c");
        assertTrue(testT.root.children.get('c').isWord);
        for (int i = 'a'; i < 'z'; i++) {
            assertNull(testT.root.children.get(i));
        }

        testT.insert("cab");
        assertTrue(testT.root.children.get('c').isWord);
        assertFalse(testT.root.children.get('c').children.get('a').isWord);
        assertTrue(testT.root.children.get('c').children.get('a').children.get('b').isWord);

        testT.insert("carts");
        assertTrue(testT.root.children.get('c').isWord);
        assertFalse(testT.root.children.get('c').children.get('a').isWord);
        assertTrue(testT.root.children.get('c').children.get('a').children.get('b').isWord);
        assertFalse(testT.root.children.get('c').children.get('a').children.get('r').isWord);
        assertFalse(testT.root.children.get('c').children.get('a').children.get('r').children.get('t').isWord);
        assertTrue(testT.root.children.get('c').children.get('a').children.get('r').children.get('t').children.get('s').isWord);

    }

    @Test
    public void search() {
        assertTrue(t.search("c"));
        assertTrue(t.search("cab"));
        assertTrue(t.search("carts"));

        assertFalse(t.search("hi"));
        assertFalse(t.search("goodbye"));
    }

    @Test
    public void generateFromPrefix(){
        ArrayList list = t.generateWordsFromPrefix("c");
        assertEquals(3, list.size());
        list = t.generateWordsFromPrefix("ca");
        assertEquals(2, list.size());
        list = t.generateWordsFromPrefix("car");
        assertEquals(1, list.size());
    }
}
