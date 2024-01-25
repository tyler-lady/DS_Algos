import java.util.ArrayList;
import java.util.HashMap;

public class TrieNode {
    HashMap<Character, TrieNode> children;
    boolean isWord;
    int frequency;

    // TODO: initialize the TrieNode's properties
    public TrieNode() {
        this.isWord = false;
        this.frequency = 0;
        this.children = new HashMap<>();
    }

    public String toString () {
        return children.toString();
    }

}
