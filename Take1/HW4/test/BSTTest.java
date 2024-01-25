import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class BSTTest {
    // TODO: Accuracy tests


    BST bst = new BST();

    public static String GetPreOrder(BinaryNode root, BinaryNode curNode) {
        if (curNode == null)
            return "";

        String retStr = curNode.data() + ", "
                + GetPreOrder(root, curNode.left())
                + GetPreOrder(root, curNode.right());

        if (curNode.equals(root))
            return retStr.substring(0, retStr.length()-2);
        else
            return retStr;
    }

    @Test
    void Insert_Test(){
        assertEquals(0, bst.height());
        assertEquals(0, bst.size());
        bst.insert(45);
        assertEquals("45", GetPreOrder(bst.root(), bst.root()));
        assertEquals(1, bst.size());
        bst.insert(10);
        assertEquals("45, 10", GetPreOrder(bst.root(), bst.root()));
        assertEquals(2, bst.size());
        bst.insert(90);
        assertEquals("45, 10, 90", GetPreOrder(bst.root(), bst.root()));
        assertEquals(3, bst.size());
        bst.insert(7);
        assertEquals("45, 10, 7, 90", GetPreOrder(bst.root(), bst.root()));
        assertEquals(4, bst.size());
        bst.insert(12);
        assertEquals("45, 10, 7, 12, 90", GetPreOrder(bst.root(), bst.root()));
        assertEquals(5, bst.size());
        bst.insert(8);
        assertEquals("45, 10, 7, 8, 12, 90", GetPreOrder(bst.root(), bst.root()));
        assertEquals(6, bst.size());
        bst.insert(6);
        assertEquals("45, 10, 7, 6, 8, 12, 90", GetPreOrder(bst.root(), bst.root()));
        assertEquals(7, bst.size());
        bst.insert(6);
        assertEquals(7, bst.size());
        assertEquals("45, 10, 7, 6, 8, 12, 90", GetPreOrder(bst.root(), bst.root()));

        assertEquals(3, bst.root().height());
        assertEquals(2, bst.root().left().height());
        assertEquals(0, bst.root().right().height());
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
        assertNull(bst.delete(90));
        assertNull(bst.delete(-5));
        assertEquals(0, bst.size());

        Insert_Test();
        assertEquals(7, bst.size());

        assertEquals(90, bst.delete(90).data());
        assertEquals("45, 10, 7, 6, 8, 12", GetPreOrder(bst.root(), bst.root()));
        assertEquals(6, bst.size());
        assertEquals(45, bst.root().data());
        assertEquals(12, bst.delete(12).data());
        assertEquals("45, 10, 7, 6, 8", GetPreOrder(bst.root(), bst.root()));
        assertEquals(5, bst.size());
        assertEquals(45,bst.delete(45).data());
        assertEquals("10, 7, 6, 8", GetPreOrder(bst.root(), bst.root()));
        assertEquals(10, bst.root().data());
        assertEquals(4, bst.size());
        assertEquals(7,bst.delete(7).data());
        assertEquals("10, 6, 8", GetPreOrder(bst.root(), bst.root()));
        assertEquals(3, bst.size());
        assertEquals(10, bst.delete(10).data());
        assertEquals("6, 8", GetPreOrder(bst.root(), bst.root()));
        assertEquals(2, bst.size());
        assertNull(bst.delete(45));
        assertEquals("6, 8", GetPreOrder(bst.root(), bst.root()));
        assertEquals(2, bst.size());
        assertEquals(6, bst.root().data());
        assertEquals(6, bst.delete(6).data());
        assertEquals(1, bst.size());
        assertEquals(8, bst.root().data());
        assertEquals("8", GetPreOrder(bst.root(), bst.root()));
        assertEquals(8, bst.delete(8).data());
        assertEquals("", GetPreOrder(bst.root(), bst.root()));
        assertEquals(0, bst.size());
    }

    @Test
    void remove_Test2(){
        assertNull(bst.delete(90));
        assertNull(bst.delete(-5));
        assertEquals(0, bst.size());

        Insert_Test();
        assertEquals(7, bst.size());

        bst.delete(45);
        assertEquals("12, 10, 7, 6, 8, 90", GetPreOrder(bst.root(), bst.root()));
        assertEquals(12, bst.root().data());
        assertEquals(6, bst.size());
        bst.delete(12);
        assertEquals("10, 7, 6, 8, 90", GetPreOrder(bst.root(), bst.root()));
        assertEquals(10, bst.root().data());
        assertEquals(5, bst.size());
        bst.delete(7);
        assertEquals("10, 6, 8, 90", GetPreOrder(bst.root(), bst.root()));
        assertEquals(10, bst.root().data());
        assertEquals(4, bst.size());
    }

    @Test
    void extractRightMax(){
        Insert_Test();
        assertEquals(12, bst.extractRightMost(bst.root().left()).data());
        assertEquals(8, bst.extractRightMost(bst.root().left().left()).data());

        BST bst1 = new BST();
        assertNull(bst1.extractRightMost(bst1.root()));
        bst1.insert(15);
        assertEquals(15, bst1.extractRightMost(bst1.root()).data());
    }

    @Test
    void search_Test(){
        Insert_Test();
        BinaryNode node = bst.search(90);
        assertNotNull(node);
        assertEquals(90, node.data());
        assertNull(bst.search(-10));
        assertNull(bst.search(100));
    }

    @Test
    void getPreOrder_Test(){
        Insert_Test();
        assertEquals("[45, 10, 7, 6, 8, 12, 90]", bst.preOrderList().toString());
    }

    @Test
    void getInOrder_Test(){
        Insert_Test();
        assertEquals("[6, 7, 8, 10, 12, 45, 90]", bst.inOrderList().toString());
    }

    @Test
    void getPostOrder_Test(){
        Insert_Test();
        assertEquals("[6, 8, 7, 12, 10, 90, 45]", bst.postOrderList().toString());
    }
}
