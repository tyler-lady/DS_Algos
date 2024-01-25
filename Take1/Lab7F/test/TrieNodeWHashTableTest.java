import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TrieNodeWHashTableTest {
    //TODO: Your own JUnit tests for every method

    TrieNodeWHashTable node;

    @Test
    void constrTest(){
        node = new TrieNodeWHashTable();
        assertNotNull(node);
        assertFalse(node.isWord);
        assertEquals(0, node.children.size());
        
    }
}
