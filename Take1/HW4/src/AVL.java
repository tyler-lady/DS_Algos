import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.lang.Math.max;

public class AVL<E extends Comparable<E>> implements Tree<E>{

    private int height;
    private int size;
    private BinaryNode<E> root;
    private int RRotations; // this will be used to see if the amount of rotations was correct
    private int LRotations; // this will be used to see if the amount of rotations was correct

    public AVL(){
        this.root = null;
        this.height = 0;
        this.size = 0;
        this.RRotations = 0;
        this.LRotations = 0;
    }

    public AVL(BinaryNode<E> root){
        this.root = root;
        this.height = root.height();
        this.size = root.size();
        this.RRotations = 0;
        this.LRotations = 0;
    }

    // Access fields
    public int getRRotations(){
        return this.RRotations;
    }
    public int getLRotations(){
        return this.LRotations;
    }
    public BinaryNode<E> root() {
        return this.root;
    }
    public int height() {
        return this.height;
    }
    public int size() {
        return this.size;
    }
    public boolean isBalanced() {
        return root.isBalanced();
    }

    // TODO: updateHeight - same as BST
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

    //HelperMethod to get the height of  a node
    private int getNodeHeight(BinaryNode<E> curNode){
        //if empty subtree return -1
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

    //Helper - returns the balance at a particular node
    public int getBalance(BinaryNode<E> node){
        //If node is null, return 0, otherwise return the height of right subtree minus the left #MyFirstJavaTernary
        return (node == null) ? 0 : getNodeHeight(node.right()) - getNodeHeight(node.left());
    }


    /*
    TODO: rotateRight
     *              x                          y
     *            /   \                      /   \
     *           y     C     ===>           A     x
     *         /   \                             /  \
     *        A    B                            B    C
     * You should never rotateRight if the left subtree is empty.
     * Make sure you increment the RRotations.
    */
    public void rotateRight(BinaryNode<E> node){
        System.out.println("rotating right: " + node.data());
        BinaryNode<E> parent = null;
        if(node.hasParent())
            parent = node.parent(); //setting ref for node above x

        //Get a reference to left node
        BinaryNode<E> lNode = node.left(); //set ref to y

        lNode.setParent(parent); //set y's parent ref

        if(lNode.hasRight())
            node.setLeft(lNode.right()); //set b to x's left child

        lNode.setRight(node); //set x as y's right child

        node.setParent(lNode); //set x's parent to lNode
        //update height
        this.updateHeight();
        //increment rotations
        RRotations++;
    }

    /*
     TODO: rotateLeft
      *              x                          y
      *            /   \                      /   \
      *           y     C     <==           A     x
      *         /   \                             /  \
      *        A    B                            B    C
      * You should never rotateLeft if the right subtree is empty.
      * Make sure you increment the LRotations.
      * Symmetrical to above.
     */
    public void rotateLeft(BinaryNode<E> node){
        System.out.println("Rotating Left: " + node.data());

        BinaryNode<E> parent = null;
        if(node.hasParent())
            parent = node.parent(); //setting ref for node above y

        //Get a reference to right node
        BinaryNode<E> rNode = node.right(); //set ref to x

        rNode.setParent(parent); //set y's parent ref

        if(rNode.hasLeft())
            node.setRight(rNode.left()); //set b to x's left child

        rNode.setLeft(node); //set x as y's right child

        node.setParent(rNode); //set x's parent to lNode
        //update height
        this.updateHeight();

        //increment rotations
        LRotations++;
    }

    /*
     TODO: possibleRotateRight
      * If the current node is unbalanced with the right tree height being smaller
      * than the left subtree height, rotate right. Otherwise, don't do anything.
    */
    public void possibleRotateRight(BinaryNode<E> node){
        //System.out.println("Possibly Rotating Right:" + node);

        if(!node.isBalanced()){
            this.rotateRight(node);
        }
    }

    /*
     TODO: possibleRotateLeft
      * If the current node is unbalanced with the left tree height being smaller
      * than the right subtree height, rotate left. Otherwise, don't do anything.
    */
    public void possibleRotateLeft(BinaryNode<E> node){
        //System.out.println("Possibly Rotating Left:" + node);

        if(!node.isBalanced()){
            this.rotateLeft(node);
        }

        //this.rotateLeft(node);
    }

    /*
     TODO: mkBalanced
      * Given a node, balance it if the heights are unbalanced.
      * Hint: rotations!!!
    */
    public void mkBalanced(BinaryNode<E> node){
        /*
        if(node == null)
            return;
        int balance = getBalance(node);
        if(balance > 0){ //left heavy
            if(this.getBalance(node.left()) < 0){
                this.possibleRotateLeft(node.left());
                this.possibleRotateRight(node);
            } else {
                this.possibleRotateRight(node);
            }
        }
        if(balance < 0){ //right heavy
            if(this.getBalance(node.right()) > 0){
                this.possibleRotateRight(node.right());
                this.possibleRotateLeft(node);
            } else
                this.possibleRotateLeft(node);
        }

        this.updateHeight();
        */

        //init balance value
        int balance = getBalance(node);

        //traverses back up the tree from the inserted/deleted node (where this is used) to the root
        //I also finally get why our nodes have parents, and it's to do something like this iteratively
        //using our node.isBalanced method allows us to keep working upward until balance is achieved *
        while(!node.isBalanced()){
            //if the current balance value is
            if(balance > 1){
                if(this.getBalance(node.right()) == 1){
                    //Single left rotation
                    this.possibleRotateLeft(node);
                }else {
                    //Right - Left Rotations
                    this.possibleRotateRight(node.right());
                    this.possibleRotateLeft(node);
                }
                break;
            }else if(balance < -1){
                if(this.getBalance(node.left()) == -1){
                    //Single right rotation
                    this.possibleRotateRight(node);
                }else {
                    //Left - Right Rotations
                    this.possibleRotateLeft(node.left());
                    this.possibleRotateRight(node);
                }
                break;
            }
            //if node has no parent - it is root
            if(!node.hasParent()){
                break;
            }
            //move up to parent node
            node = node.parent();
            //update balance value using new node
            balance = getBalance(node);
        }
    }

    // Helpers for BST/AVL methods
    // TODO: extractRightMost - identical to BST
    public BinaryNode<E> extractRightMost(BinaryNode<E> curNode) {
        while(curNode.left()!=null){
            curNode = curNode.left();
        }
        return curNode;
    }

    // AVL & BST Search & insert same
    // TODO: search - identical to BST
    public BinaryNode<E> search(E elem) {
        //Calls the recursive search, starting with the root
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

    /*
     TODO: insert - slightly different from BST but similar logic
      * Hint: mkBalanced will be your best friend here.
    */
    public void insert(E elem) {
        //sets the root of this tree using our recursive insert call
        //System.out.println("Inserting: " + elem);
        this.root = insert(elem, this.root(), null);

        //this.mkBalanced(this.root);
    }

    //HelperMethod - loosely defined based off textbook; does nothing with duplicates
    /*
     * @param root : the root node of the current subtree being inserted into
     * @param parent : parent node to root - previously traversed node
     */
    private BinaryNode<E> insert(E elem, BinaryNode<E> root, BinaryNode<E> parent){
        //if root of subtree is null
        //create new node from element AND set the root of current subtree to the node
        //set the root node's height
        //set the root node's parent to the passed parent
        if(root == null){
            root = new BinaryNode<>(elem);
            root.setHeight(1);
            root.setParent(parent);
            //this.mkBalanced(root);
            //incrementing size here has been the most accurate way of doing so
            this.size++;
        }
        //Use compare to determine if the new data is less than
        int compareResult = elem.compareTo(root.data());

        //If this is not the location for the new node to be added, insert into left or right subtree
        //traverse the tree using insert recursively
        if(compareResult < 0){
            root.setLeft(insert(elem, root.left(), root));
            //if(!root.left().isBalanced())
                //this.mkBalanced(root.left());
        }else if(compareResult > 0){
            root.setRight((insert(elem, root.right(), root)));
            //if(!root.right().isBalanced())
                //this.mkBalanced(root.right());
        }else ; //Duplicate value; Do nothing

        this.updateHeight();
        if(!root.isBalanced())
            this.mkBalanced(root);

        //return root of current avl tree (sub or otherwise)

        return root;
    }


    /*
     TODO: delete - slightly different from BST but similar logic
      * Hint: mkBalanced will be your best friend here.
    */
    public BinaryNode<E> delete(E elem) {
        BinaryNode<E> del = this.search(elem);
        if(del != null) {
            this.root = this.delete(elem, this.root());
            this.size--;
        }
        return del;
    }

    //Helper Method: recursive delete similar to BST, post delete - need to mkBalanced
    private BinaryNode<E> delete(E elem, BinaryNode<E> node){
        if(node == null){
            return node;
        }

        if(elem.compareTo(node.data()) < 0){
            node.setLeft(delete(elem, node.left()));
        }else if(elem.compareTo(node.data()) > 0){
            node.setRight(delete(elem, node.right()));
        }else {
            if(node.left() == null){
                return node.right();
            } else if (node.right() == null){
                return node.left();
            }

            node.setData(this.extractRightMost(node.right()).data());

            node.setRight(delete(elem, node.right()));
        }
        this.updateHeight();
        this.mkBalanced(node);
        return node;
    }

    // Stuff to help you debug if you want
    // Can ignore or use to see if it works.
    static <E extends Comparable<E>> Tree<E> mkAVL (Collection<E> elems) {
        Tree<E> result = new AVL<>();
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
