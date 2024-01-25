//Worked with Mihir Sastri

public class TrieWithHashTable {
    TrieNodeWHashTable root;

    public TrieWithHashTable() {
        root = new TrieNodeWHashTable();
    }

    //root is always empty
    //each node contains a letter
    //iff the node's isWord is true, then the node is the end of a string of Characters and is a word
    //only insert characters - no special characters

    /**
     * Insert the word into the Trie by making new TrieNodes and marking the last TrieNode as a word.
     * @param word The word to be inserted
     */
    void insert( String word ) {
        //TODO
        insertHelper(this.root, word);
    }

    void insertHelper(TrieNodeWHashTable node, String word) {
        //TODO
        //only insert letters

        if(word.isEmpty()){
            node.isWord = true;
        }else {
            char newChar = word.charAt(0);
            //only insert if the char is a letter
            if(Character.isLetter(newChar)){
                TrieNodeWHashTable child = node.children.get(newChar);

                if(child == null){
                    child = new TrieNodeWHashTable();
                    node.children.put(newChar, child);
                }
                insertHelper(child, word.substring(1));
            }
        }
    }

    /**
     * Given a word, returns if it is represented in this Trie.
     * @param word The word to be searched for
     */
    boolean search(String word) {
        //TODO
        return searchHelper(this.root, word);
    }

    boolean searchHelper(TrieNodeWHashTable node, String word) {
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

    /**
     * Marks the TrieNode representing the last char in the given word is no longer a word.
     * @param word The word to be deleted
     */
    void delete(String word) {
        //TODO
        deleteHelper(this.root, word);
    }

    void deleteHelper(TrieNodeWHashTable node, String word) {
        //TODO
        if(word.isEmpty()){
            node.isWord = false;
            return;
        }

        char cur = word.charAt(0);

        if(!node.children.containsKey(cur)) {
            return;
        }

        TrieNodeWHashTable child = node.children.get(cur);

        if(child == null) {
            return;
        }

        //pass on the rest of the word
        deleteHelper(child, word.substring(1));


        /*
        //Works iteratively
        // All nodes below 'deleteBelow' and on the path starting with 'deleteChar' (including itself) will be deleted if needed
        TrieNodeWHashTable deleteBelow = null;
        char deleteChar = '\0';

        // Search to ensure word is present
        TrieNodeWHashTable parent = root;
        for (int i = 0; i < word.length(); i++) {
            char cur = word.charAt(i);

            TrieNodeWHashTable child = parent.children.get(cur); // Check if having a TrieNode associated with 'cur'
            if (child == null) {
                return;
            }
            // Update 'deleteBelow' and 'deleteChar'
            if (parent.children.size() > 1 || parent.isWord) {
                deleteBelow = parent;
                deleteChar = cur;
            }

            parent = child;
        }

        if (!parent.isWord) { // word isn't in trie
            return;
        }

        if (parent.children.isEmpty()) {
            deleteBelow.children.remove(deleteChar);
        } else {
            parent.isWord = false; // Delete word by mark it as not the end of a word
        }
        */
    }
}