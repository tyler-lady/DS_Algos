import java.util.ArrayList;
public class Trie {
    TrieNode root;

    public Trie(){
        root = new TrieNode();
    }

    public Trie(TrieNode root){
        this.root = root;
    }

    // Setters & Getters
    public TrieNode getRoot(){
        return this.root;
    }

    public void setRoot(TrieNode root){
        this.root = root;
    }

    // Actual methods -- part of Lab7
    // TODO:
    void insert(String word) {
        insertHelper(this.getRoot(), word);
    }

    void insertHelper(TrieNode node, String word) {
        //TODO
        //only insert letters

        if(word.isEmpty()){
            node.isWord = true;
            node.frequency ++; //increment frequency here - if it's already a word it's freq inc as expected, if new word freq incr to 1
        }else {
            char newChar = word.charAt(0);
            //only insert if the char is a letter
            if(Character.isLetter(newChar)){
                TrieNode child = node.children.get(newChar);

                if(child == null){
                    child = new TrieNode();
                    node.children.put(newChar, child);
                }
                insertHelper(child, word.substring(1));
            }
        }
    }

    // TODO:
    boolean search(String word) {
        return searchHelper(this.getRoot(), word);
    }

    boolean searchHelper(TrieNode node, String word) {
        //TODO
        if(word.isEmpty()){
            return node.isWord;
        }

        char c = word.charAt(0);
        //System.out.println(c);

        if(!node.children.containsKey(c))
            return false;

        return searchHelper(node.children.get(c), word.substring(1));
    }

    /*
    TODO: Remove the TrieNodes associated with the word. There are 3 cases to be concerned with.
        - key is unique: no part of key contains another key nor is the key itself a prefix of another key in the trie: remove all nodes
        - key is prefix key of another key: unmark the leaf node
        - key has at least one other word as a prefix: delete the nodes from the end of the key :p
        This is NOT the delete you implemented in lab.
 */
    void delete(String word){
        delete(root, word, 0);
    }

    private boolean delete(TrieNode current, String word, int index) {
        if (index == word.length()) {
            if (!current.isWord) {
                return false;
            }
            current.isWord = false;
            return current.children.isEmpty();
        }
        char ch = word.charAt(index);
        TrieNode node = current.children.get(ch);
        if (node == null) {
            return false;
        }
        boolean shouldDeleteCurrentNode = delete(node, word, index + 1) && !node.isWord;

        if (shouldDeleteCurrentNode) {
            current.children.remove(ch);
            return current.children.isEmpty();
        }
        return false;
    }

    // TODO: Gets all possible words with the prefix through traversing the Trie. If it's a word, then turn it into an Entry.
    //       If not, ignore. Put the Entry's into a list and return.
    //       Hint: Look at your MazeSolver with a stack for inspiration for the traversal.
    //       EX: If you have prefix "ca", then it should look at all combinations of the words starting with "ca".
    public ArrayList<Entry> generateWordsFromPrefix(String prefix){
        ArrayList<Entry> ls = new ArrayList<>();

        TrieNode ln = this.getRoot();
        StringBuffer curr = new StringBuffer();
        for(char c : prefix.toCharArray()){
            ln = ln.children.get(c);
            if (ln == null)
                return ls;
            curr.append(c);
        }

        generateHelper(ln, ls, prefix.substring(0, prefix.length()));

        return ls;
    }

    public ArrayList<Entry> generateHelper(TrieNode ln, ArrayList<Entry> ls, String curr){

        if(ln.isWord){
            Entry e = new Entry(ln.frequency, curr);
            ls.add(e);
        }
        if(ln.children == null || ln.children.isEmpty())
            return ls;

        for(Character ch : ln.children.keySet()){
            generateHelper(ln.children.get(ch), ls, curr + ch);
        }

        return ls;
    }

}
