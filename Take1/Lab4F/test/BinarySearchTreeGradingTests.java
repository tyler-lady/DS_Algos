import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BinarySearchTreeGradingTests {
    BinarySearchTree bst;
    @BeforeEach
    public void run() {
        bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(1);
        bst.insert(0);
        bst.insert(2);
        bst.insert(5);
        bst.insert(4);
        bst.insert(6);
    }

    public static String graderGetPreOrder(BinaryTreeNode root, BinaryTreeNode curNode) {
        if (curNode == null)
            return "";

        String retStr = curNode.getItem() + ", "
                + graderGetPreOrder(root, curNode.getLeft())
                + graderGetPreOrder(root, curNode.getRight());

        if (curNode.equals(root))
            return retStr.substring(0, retStr.length()-2);
        else
            return retStr;
    }

    @Test
    public void insertTest() {
        bst = new BinarySearchTree();
        assertEquals(0, bst.getSize());
        bst.insert(3);
        assertEquals(1, bst.getSize());
        bst.insert(1);
        assertEquals(2, bst.getSize());
        bst.insert(0);
        assertEquals(3, bst.getSize());
        bst.insert(2);
        assertEquals(4, bst.getSize());
        bst.insert(5);
        assertEquals(5, bst.getSize());
        bst.insert(4);
        assertEquals(6, bst.getSize());
        bst.insert(6);
        assertEquals(7, bst.getSize());
        assertEquals("3, 1, 0, 2, 5, 4, 6", graderGetPreOrder(bst.getRoot(), bst.getRoot()));
    }

    @Test
    public void removeTest() {
        assertNull(bst.remove(10));
        assertNull(bst.remove(-1));
        assertEquals(7, bst.getSize());

        System.out.println(graderGetPreOrder(bst.getRoot(), bst.getRoot()) + " : full list");

        assertEquals(3, bst.remove(3).getItem());
        assertEquals("2, 1, 0, 5, 4, 6", graderGetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(2, bst.getRoot().getItem());
        assertEquals(6, bst.getSize());
        System.out.println(graderGetPreOrder(bst.getRoot(), bst.getRoot()));

        assertEquals(1, bst.remove(1).getItem());
        assertEquals("2, 0, 5, 4, 6", graderGetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(5, bst.getSize());
        System.out.println(graderGetPreOrder(bst.getRoot(), bst.getRoot()));

        assertEquals(2, bst.remove(2).getItem());
        assertEquals("0, 5, 4, 6", graderGetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(4, bst.getSize());

        assertEquals(5, bst.remove(5).getItem());
        assertEquals("0, 4, 6", graderGetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(3, bst.getSize());

        assertEquals(6, bst.remove(6).getItem());
        assertEquals("0, 4", graderGetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(2, bst.getSize());

        assertEquals(4, bst.remove(4).getItem());
        assertEquals("0", graderGetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(1, bst.getSize());

        assertEquals(0, bst.remove(0).getItem());
        assertEquals("", graderGetPreOrder(bst.getRoot(), bst.getRoot()));
        assertEquals(0, bst.getSize());

        assertNull(bst.remove(0));


        BinarySearchTree bst1 = new BinarySearchTree();
        bst1.insert(3);
        bst1.insert(1);
        bst1.insert(0);
        bst1.insert(2);
        bst1.insert(5);
        bst1.insert(4);
        bst1.insert(6);

        assertEquals(2, bst1.remove(2).getItem());
        assertEquals("3, 1, 0, 5, 4, 6", graderGetPreOrder(bst1.getRoot(), bst1.getRoot()));
        assertEquals(3, bst1.remove(3).getItem());
        assertEquals("1, 0, 5, 4, 6", graderGetPreOrder(bst1.getRoot(), bst1.getRoot()));
    }

    @Test
    public void searchTest() {
        BinarySearchTree bst = new BinarySearchTree();
        bst.insert(3);
        bst.insert(1);
        bst.insert(0);
        bst.insert(2);
        bst.insert(8);
        bst.insert(4);
        bst.insert(10);

        assertNull(bst.search(-1));
        assertNull(bst.search(5));
        assertNull(bst.search(9));


        assertEquals(0, bst.search(0).getItem());
        assertEquals(1, bst.search(1).getItem());
        assertEquals(2, bst.search(2).getItem());
        assertEquals(3, bst.search(3).getItem());
        assertEquals(4, bst.search(4).getItem());
        assertEquals(8, bst.search(8).getItem());
        assertEquals(10, bst.search(10).getItem());
    }

    @Test
    public void getPreOrderStrTest() {
        assertEquals("3, 1, 0, 2, 5, 4, 6", bst.getPreOrderStr());
    }

    @Test
    public void getInOrderStrTest() {
        assertEquals("0, 1, 2, 3, 4, 5, 6", bst.getInOrderStr());
    }

    @Test
    public void getPostOrderStrTest() {
        assertEquals("0, 2, 1, 4, 6, 5, 3", bst.getPostOrderStr());
    }
}
