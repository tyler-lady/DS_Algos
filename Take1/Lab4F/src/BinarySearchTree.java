public class BinarySearchTree {

    private int size;
    private BinaryTreeNode root;

    public BinarySearchTree() {
        this.root = null;
        this.size = 0;
    }

    public BinaryTreeNode getRoot() {
        return this.root;
    }

    public int getSize() {
        return this.size;
    }

    /**
     * Inserts the given integer and return nothing. It inserts this int such that the tree remains a BST.
     * @param data The integer to be inserted
     */
    public void insert(int data) {
        insert(data, this.getRoot());
    }

    /**
     * Inserts the given integer and return nothing. It inserts this int such that the tree remains a BST.
     * @param data The integer to be inserted
     * @param curNode The current Node in the traversal
     */
    private void insert(int data, BinaryTreeNode curNode) {
        this.root = insRec(data, this.getRoot());
    }

    //Helper
    private BinaryTreeNode insRec(int data, BinaryTreeNode curNode){
        BinaryTreeNode node = new BinaryTreeNode(data, null, null, null);
        if(curNode == null){
            curNode = node;
            this.size++;
            return curNode;
        }else if(data < curNode.getItem()){
            curNode.setLeft(insRec(data, curNode.getLeft()));
        }else if(data > curNode.getItem()){
            curNode.setRight(insRec(data, curNode.getRight()));
        }
        return curNode;
    }

    /**
     * Deletes a Node containing the given integer. If the Node has 2 children, replaces with the Node of the minimum
     * value in the right child of the node. If the data is not present, returns null.
     * @param data The integer to be removed
     * @return The Node containing the integer to remove or null if one is not found
     */
    public BinaryTreeNode remove(int data) {
        BinaryTreeNode nodeToDelete = new BinaryTreeNode(data, null, null,null);
        if(this.search(data) == null)
            return null;
        else {
            nodeToDelete.setItem(this.search(data).getItem());
            nodeToDelete.setParent(this.search(data).getParent());
            nodeToDelete.setLeft(this.search(data).getLeft());
            nodeToDelete.setRight(this.search(data).getRight());
        }
        this.root = this.remove(data, this.getRoot());
        return nodeToDelete;
    }


    /**
     * Deletes a Node containing the given integer. If the Node has 2 children, replaces with the Node of the maximum
     * value in the left child of the node. If the data is not present, returns null.
     * @param data The integer to be removed
     * @param curNode The current Node in the traversal
     * @return The Node containing the integer to remove or null if one is not found
     */
    private BinaryTreeNode remove(int data, BinaryTreeNode curNode) {
        // TODO

        if(curNode == null){
            return curNode;
        }

        if(data < curNode.getItem()){
            curNode.setLeft(remove(data, curNode.getLeft()));
        }else if(data > curNode.getItem()){
            curNode.setRight(remove(data, curNode.getRight()));
        }else {
            System.out.println("data is root");
            if(curNode.getLeft() == null && curNode.getRight() == null){
                System.out.println("node has no children");
                this.size --;
                return null; //return curNode
            }else if(curNode.getRight() == null){
                this.size --;
                return curNode.getLeft();
            } else if (curNode.getLeft() == null){
                this.size --;
                return curNode.getRight();
            } else {
                BinaryTreeNode successor = this.extractLeftMax(curNode.getLeft());
                curNode.setLeft(this.remove(successor.getItem(), curNode.getLeft()));
                curNode.setItem(successor.getItem());
                curNode.setRight(remove(successor.getItem(), curNode.getRight()));
            }
        }
        return curNode;
    }

    private boolean nodeHasChildren(BinaryTreeNode curNode){
        if(curNode == null)
            return false;
        return curNode.getLeft() != null || curNode.getRight() != null;
    }

    /**
     * A recursive method that starts at the left child of a parent and extracts the maximum from this child's tree.
     * @param curNode The current Node in the traversal
     * @return The minimum Node in the child's tree
     */
    private BinaryTreeNode extractLeftMax(BinaryTreeNode curNode) {
        // TODO
        /*
        while(curNode.getLeft()!=null){
            curNode = curNode.getLeft();
        }
        return curNode;*/
        while(curNode.getRight()!=null){
            curNode = curNode.getRight();
        }
        System.out.println("returning " + curNode.getItem() + " from leftmax" );
        return curNode;
    }

    /**
     * Returns a Node containing the given integer or null if one is not found
     * @param data The integer to search for
     * @return A Node containing the given integer or null if one is not found
     */
    public BinaryTreeNode search(int data) {
        return search(data, this.getRoot());
    }

    /**
     * Returns a Node containing the given integer or null if one is not found
     * @param data The integer to search for
     * @param curNode The current Node in the traversal
     * @return A Node containing the given integer or null if one is not found
     */
    private BinaryTreeNode search(int data, BinaryTreeNode curNode) {
        if(curNode == null || curNode.getItem()==data){
            return curNode;
        }else if(curNode.getItem() > data){
            return search(data, curNode.getLeft());
        }
        return search(data, curNode.getRight());
    }

    /**
     * Returns the pre-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getPreOrderStr() {
        return getPreOrderStr(this.getRoot());
    }

    /**
     * Returns the pre-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getPreOrderStr(BinaryTreeNode curNode) {
        return getPreOrderStr(curNode, "");
    }

    private BinaryTreeNode getRightMost(BinaryTreeNode curNode){
        while(curNode.getRight()!=null){
            curNode = curNode.getRight();
        }
        return curNode;
    }

    //Helper
    private String getPreOrderStr(BinaryTreeNode curNode, String curString) {
        if(curNode == null){
            return curString;
        }

        if(curNode == getRightMost(this.getRoot()))
            curString = curString + curNode.getItem();
        else
            curString = curString + curNode.getItem() + ", ";
        curString = getPreOrderStr(curNode.getLeft(), curString);
        curString = getPreOrderStr(curNode.getRight(), curString);

        return curString;
    }

    /**
     * Returns the in-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getInOrderStr() {
        return getInOrderStr(this.getRoot());
    }

    /**
     * Returns the in-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getInOrderStr(BinaryTreeNode curNode) {
        // TODO
        return getInOrderStrRec(curNode, "");
    }

    //Helper
    private String getInOrderStrRec(BinaryTreeNode curNode, String curString){
        if(curNode == null) {
            return curString;
        }

        curString = getInOrderStrRec(curNode.getLeft(), curString);
        if(curNode == getRightMost(this.getRoot()))
            curString = curString + curNode.getItem();
        else
            curString = curString + curNode.getItem() + ", ";
        curString = getInOrderStrRec(curNode.getRight(), curString);

        return curString;
    }

    /**
     * Returns the post-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    public String getPostOrderStr() {
        return getPostOrderStr(this.getRoot());
    }

    /**
     * Returns the post-order traversal of this. The output must be in the form of: "x, x, x, x, x, x". Each number
     * except the last is followed by ", ". (i.e. for a tree with one node, the output would take the form: "x")
     * @return A String representation of the traversal
     */
    private String getPostOrderStr(BinaryTreeNode curNode) {
        return getPostOrderStrRec(curNode, "");
    }

    //Helper
    private String getPostOrderStrRec(BinaryTreeNode curNode, String curString){
        if(curNode == null) {
            return curString;
        }
        curString = getPostOrderStrRec(curNode.getLeft(), curString);
        curString = getPostOrderStrRec(curNode.getRight(), curString);
        if(curNode == this.getRoot())
            curString = curString + curNode.getItem();
        else
            curString = curString + curNode.getItem() + ", ";

        return curString;
    }
}
