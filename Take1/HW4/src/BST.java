import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.max;

public class BST<E extends Comparable<E>> implements Tree<E> {

    private int height;
    private int size;
    private BinaryNode<E> root;

    public BST(){
        this.root = null;
        this.height = 0;
        this.size = 0;
    }

    // TODO: BST
    public BST(BinaryNode<E> root){
        this.root = root;
        this.height = root().height();
        this.size = root.size();
    }

    // Access field
    public BinaryNode<E> root() {
        return this.root;
    }

    // Basic properties
    public int height() {
        return this.height;
    }
    public int size() {
        return this.size;
    }
    public boolean isBalanced() {
        return root.isBalanced();
    }

    // TODO: updateHeight - Update the root height to reflect any changes
    public void updateHeight() {
        //set the height of each node, and its subtrees, starting with the root (recursively done until end of subtrees)
        this.setHeight(this.root());

        //setting tree height to height of root node
        this.height = getNodeHeight(this.root);
    }

    //HelperMethod to set the height of a node and its subtrees
    private void setHeight(BinaryNode<E> node){
        //if end of subtree return
        if(node == null)
            return;
        //set height of the node
        node.setHeight(getNodeHeight(node));
        //if left subtree
        if(node.hasLeft())
            //set height of left child node and its subtrees
            this.setHeight(node.left());
        //if right subtree
        if(node.hasRight())
            //set height of right child node and its subtrees
            this.setHeight(node.right());
    }

    private int getNodeHeight(BinaryNode<E> curNode){
        //if empty subtree return -1 : I'm gonna be honest, Idk why this is how it works
        //I just know that if node is null returning -1, and in the final return adding one
        //balances the height to the exact value it should be
        if(curNode == null)
            return -1;
        //get height of left subtree
        int lHeight = getNodeHeight(curNode.left());
        //get height of right subtree
        int rHeight = getNodeHeight(curNode.right());
        //node height is the largest height of either left or right subtree + 1 (1 is the addition of the current node)
        return (max(lHeight, rHeight) + 1);
    }

    // Traversals that return lists
    // TODO: Preorder traversal
    public List<E> preOrderList() {
        List<E> preOrderList = new ArrayList<>(this.size());
        preOrderList = getPreOrderList(this.root(), preOrderList);
        return preOrderList;
    }

    //HelperMethod - UNTESTED
    private List<E> getPreOrderList(BinaryNode<E> curNode, List<E> curList){
        if(curNode == null){
            return curList;
        }

        curList.add(curNode.data());
        curList = getPreOrderList(curNode.left(), curList);
        curList = getPreOrderList(curNode.right(), curList);

        return curList;
    }

    // TODO: Inorder traversal
    public List<E> inOrderList() {
        List<E> inOrderList = new ArrayList<>(this.size());
        inOrderList = getInOrderList(this.root, inOrderList);
        return inOrderList;
    }

    //HelperMethod - UNTESTED
    private List<E> getInOrderList(BinaryNode<E> curNode, List<E> curList){
        if(curNode == null){
            return curList;
        }

        curList = getInOrderList(curNode.left(), curList);
        curList.add(curNode.data());
        curList = getInOrderList(curNode.right(), curList);

        return curList;
    }

    // TODO: Postorder traversal
    public List<E> postOrderList() {
        List<E> postOrderList = new ArrayList<>(this.size());
        postOrderList = getPostOrderList(this.root, postOrderList);
        return postOrderList;
    }

    //HelperMethod - UNTESTED
    private List<E> getPostOrderList(BinaryNode<E> curNode, List<E> curList){
        if(curNode == null){
            return curList;
        }

        curList = getPostOrderList(curNode.left(), curList);
        curList = getPostOrderList(curNode.right(), curList);
        curList.add(curNode.data());

        return curList;
    }

    // Helpers for BST/AVL methods
    // TODO: extractRightMost
    //    This will be called on the left subtree and will get the maximum value.
    public BinaryNode<E> extractRightMost(BinaryNode<E> curNode) {
        if(curNode == null)
            return null;

        while(curNode.getRight()!=null){
            curNode = curNode.right();
        }

        return curNode;
    }

    // AVL & BST Search & insert same
    // TODO: search
    public BinaryNode<E> search(E elem) {
        return search(elem, this.root());
    }

    //HelperMethods -
    private BinaryNode<E> search(E elem, BinaryNode<E> curNode){
        BinaryNode<E> data = new BinaryNode<>(elem);

        if(curNode == null || curNode.data().compareTo(data.data()) == 0){
            return curNode;
        }else if(curNode.data().compareTo(data.data()) > 0){
            return search(elem, curNode.left());
        }
        return search(elem, curNode.right());
    }

    // TODO: insert
    public void insert(E elem) {
        //sets the root of this tree using our recursive insert call
        this.root = insert(elem, this.root(), null);
    }

    /**HelperMethod - loosely defined based off textbook; does nothing with duplicates
     *
     * @param root : the root node of the current subtree being inserted into
     * @param parent : parent node to root - previously traversed node
     */
    private BinaryNode<E> insert(E elem, BinaryNode<E> root, BinaryNode<E> parent){
        //if root of subtree is null (BASE CASE)
        //create new node from element AND set the root of current subtree to the node
        //set the root node's height
        //set the root node's parent to the passed parent
        if(root == null){
            root = new BinaryNode<>(elem);
            root.setHeight(1);
            root.setParent(parent);
            this.size++;
        }
        //Use compare to determine if the new data is less than
        int compareResult = elem.compareTo(root.data());

        //If this is not the location for the new node to be added, insert into left or right subtree
        //traverse the tree using our insert method
        if(compareResult < 0){
            root.setLeft(insert(elem, root.left(), root));
        }else if(compareResult > 0){
            root.setRight((insert(elem, root.right(), root)));
        }else ; //Duplicate value; Do nothing

        //Post insertion:
        //Update the tree height
        //Return root
        this.updateHeight();
        //root.setHeight(getNodeHeight(root));
        //if elem not duplicate, then elem was inserted
        return root;
    }

    // TODO: delete
    public BinaryNode<E> delete(E elem) {
        BinaryNode<E> nodeToDelete = new BinaryNode<>(null);
        BinaryNode<E> del = this.search(elem);
        if(del == null)
            return null;
        else {
            nodeToDelete.setData(del.data());
            nodeToDelete.setParent(del.parent());
            nodeToDelete.setLeft(del.left());
            nodeToDelete.setRight(del.right());
            nodeToDelete.setHeight(del.height());
            this.root = delete(elem, this.root());
            this.size--;
        }

        return nodeToDelete;
    }

    //HelperMethod -
    private BinaryNode<E> delete(E elem, BinaryNode<E> curNode){
        if(curNode == null){
            return curNode;
        }

        if(elem.compareTo(curNode.data()) < 0){
            curNode.setLeft(delete(elem, curNode.left()));
        }else if(elem.compareTo(curNode.data()) > 0){
            curNode.setRight(delete(elem, curNode.right()));
        }else {
            if(curNode.getLeft() == null && curNode.getRight() == null){
                return null; //return curNode
            }else if(curNode.getRight() == null){
                return curNode.left();
            } else if (curNode.left() == null){
                return curNode.right();
            } else {
                BinaryNode successor = this.extractRightMost(curNode.left());
                curNode.setLeft(this.delete((E) successor.data(), curNode.left()));
                curNode.setData((E) successor.data());
                curNode.setRight(delete((E) successor.data(), curNode.right()));
            }
        }
        this.updateHeight();
        return curNode;
    }

    // Stuff to help you debug if you want
    // Can ignore or use to see if it works.
    static <E extends Comparable<E>> Tree<E> mkBST (Collection<E> elems) {
        Tree<E> result = new BST<>();
        for (E e : elems) result.insert(e);
        return result;
    }
    public TreePrinter.PrintableNode getLeft() {
        return this.root.hasLeft() ? this.root.left() : null;
    }
    public TreePrinter.PrintableNode getRight() {
        return this.root.hasRight() ? this.root.right() : null;
    }
    public String getText() {
        return (this.root != null) ? this.root.getText() : "";
    }
}
