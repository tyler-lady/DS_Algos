import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class BinarySearchTreeTest {

    /*
    TODO: tests
    - Make sure you have 100% code coverage
        + This also means you should break your tests up by method
    - Make sure you test the full functionality of this class...
      think edge cases (bounds, exceptions, etc...)
    - Use JUnit (you will not receive points for testing if you do
      not use JUnit)
     */

    BinarySearchTree bst = new BinarySearchTree();

    public static String GetPreOrder(BinaryTreeNode root, BinaryTreeNode curNode) {
        if (curNode == null)
            return "";

        String retStr = curNode.getItem() + ", "
                + GetPreOrder(root, curNode.getLeft())
                + GetPreOrder(root, curNode.getRight());

        if (curNode.equals(root))
            return retStr.substring(0, retStr.length()-2);
        else
            return retStr;
    }

    @Test
    void Insert_Test(){
        assertEquals(0, bst.getSize());
        bst.insert(45);
        assertEquals("45", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(1, bst.getSize());
        bst.insert(10);
        assertEquals("45, 10", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(2, bst.getSize());
        bst.insert(90);
        assertEquals("45, 10, 90", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(3, bst.getSize());
        bst.insert(7);
        assertEquals("45, 10, 7, 90", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(4, bst.getSize());
        bst.insert(12);
        assertEquals("45, 10, 7, 12, 90", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(5, bst.getSize());
        bst.insert(8);
        assertEquals("45, 10, 7, 8, 12, 90", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(6, bst.getSize());
        bst.insert(6);
        assertEquals("45, 10, 7, 6, 8, 12, 90", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(7, bst.getSize());
        bst.insert(6);
        assertEquals(7, bst.getSize());
        assertEquals("45, 10, 7, 6, 8, 12, 90", GetPreOrder(bst.getRoot(), bst.getRoot()));

    }

    @Test
    void Insert_Test2(){
        bst.insert(48);
        bst.insert(13);
        bst.insert(92);
        bst.insert(76);
        bst.insert(15);
    }

    @Test
    void remove_Test(){
        assertNull(bst.remove(90));
        assertNull(bst.remove(-5));
        assertEquals(0, bst.getSize());

        Insert_Test();
        assertEquals(7, bst.getSize());

        assertEquals(90, bst.remove(90).getItem());
        assertEquals("45, 10, 7, 6, 8, 12", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(6, bst.getSize());
        assertEquals(45, bst.getRoot().getItem());
        assertEquals(12, bst.remove(12).getItem());
        assertEquals("45, 10, 7, 6, 8", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(5, bst.getSize());
        assertEquals(45,bst.remove(45).getItem());
        assertEquals("10, 7, 6, 8", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(10, bst.getRoot().getItem());
        assertEquals(4, bst.getSize());
        assertEquals(7,bst.remove(7).getItem());
        assertEquals("10, 6, 8", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(3, bst.getSize());
        assertEquals(10, bst.remove(10).getItem());
        assertEquals("6, 8", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(2, bst.getSize());
        assertNull(bst.remove(45));
        assertEquals("6, 8", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(2, bst.getSize());
        assertEquals(6, bst.getRoot().getItem());
        assertEquals(6, bst.remove(6).getItem());
        assertEquals(1, bst.getSize());
        assertEquals(8, bst.getRoot().getItem());
        assertEquals("8", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(8, bst.remove(8).getItem());
        assertEquals("", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(0, bst.getSize());
    }

    @Test
    void remove_Test2(){
        assertNull(bst.remove(90));
        assertNull(bst.remove(-5));
        assertEquals(0, bst.getSize());

        Insert_Test();
        assertEquals(7, bst.getSize());

        bst.remove(45);
        assertEquals("12, 10, 7, 6, 8, 90", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(12, bst.getRoot().getItem());
        assertEquals(6, bst.getSize());
        bst.remove(12);
        assertEquals("10, 7, 6, 8, 90", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(10, bst.getRoot().getItem());
        assertEquals(5, bst.getSize());
        bst.remove(7);
        assertEquals("10, 6, 8, 90", GetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(10, bst.getRoot().getItem());
        assertEquals(4, bst.getSize());
    }

    @Test
    void search_Test(){
        Insert_Test();
        BinaryTreeNode node = bst.search(90);
        assertNotNull(node);
        assertEquals(90, node.getItem());
        assertNull(bst.search(-10));
        assertNull(bst.search(100));
    }

    @Test
    void getPreOrder_Test(){
        Insert_Test();
        assertEquals("45, 10, 7, 6, 8, 12, 90", bst.getPreOrderStr());
    }

    @Test
    void getInOrder_Test(){
        Insert_Test();
        assertEquals("6, 7, 8, 10, 12, 45, 90", bst.getInOrderStr());
    }

    @Test
    void getPostOrder_Test(){
        Insert_Test();
        assertEquals("6, 8, 7, 12, 10, 90, 45", bst.getPostOrderStr());
    }
}
