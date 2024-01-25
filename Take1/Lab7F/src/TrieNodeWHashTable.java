import java.util.HashMap;

public class TrieNodeWHashTable {
    boolean isWord;
    HashMap<Character, TrieNodeWHashTable> children;

    public TrieNodeWHashTable() {
        //TODO
        this.isWord = false;
        this.children = new HashMap<>();
    }
}