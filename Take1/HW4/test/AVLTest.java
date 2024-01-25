import org.junit.jupiter.api.Test;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AVLTest {
    // TODO: Accuracy test

    AVL tree = new AVL<>();

    @Test
    void insertTest(){
        tree.insert(57);
        tree.insert(35);
        tree.insert(75);
        tree.insert(52);

        assertEquals(57, tree.root().data());
        assertEquals(52, tree.root().left().right().data());

        assertEquals(4, tree.size());
        assertTrue(tree.isBalanced());
    }

    @Test
    void insert2Test(){
        tree.insert(88);
        List<BinaryNode> list = tree.inOrderList();
        System.out.println(list);
        tree.insert(4);
        list = tree.inOrderList();
        System.out.println(list);
        tree.insert(37);
        list = tree.inOrderList();
        System.out.println(list);
        System.out.println(tree.search(88));
        System.out.println(tree.search(4));
        tree.insert(16);
        list = tree.inOrderList();
        System.out.println(list);
        System.out.println(tree.search(88));
        tree.insert(12);
        list = tree.inOrderList();
        System.out.println(list);
        tree.insert(11);
        list = tree.inOrderList();
        System.out.println(list);

        //assertEquals(3, tree.getRRotations());
        //Actually expected because my test tree is a bit left heavy
        //assertEquals(0, tree.getLRotations());

        assertTrue(tree.isBalanced());
        assertTrue(tree.root().isBalanced());
    }

    @Test
    void balanceTest(){
        insertTest();

        insert2Test();

        System.out.println(tree.size());

        System.out.println(tree.search(12));

        assertTrue(tree.isBalanced());
        assertTrue(tree.root().isBalanced());
        //th
        assertEquals(57, tree.root().data());
    }

    @Test
    void heightTest(){
        //Ensure return 0 for empty tree
        assertEquals(0,tree.height());

        insertTest();
        assertEquals(2, tree.height());
        insert2Test();
        assertEquals(3, tree.height());
    }

    @Test
    void orderingTests(){
        insertTest();
        List<BinaryNode> list = tree.inOrderList();
        assertEquals(35, list.get(0));
        assertEquals(57, list.get(2));
        assertEquals(4, list.size());

        insert2Test();
        list = tree.inOrderList();
        assertEquals(11, list.get(0));
        assertEquals(52, list.get(2));
        System.out.println(list);

        list = tree.preOrderList();
        assertEquals(57, list.get(0));
        assertEquals(88, list.get(5));
        System.out.println(list);

        list = tree.postOrderList();
        assertEquals(11, list.get(0));
        assertEquals(75, list.get(4));
        System.out.println(list);
    }

    @Test
    void sizeTest(){
        insertTest();
        assertEquals(4, tree.size());
        insert2Test();
        assertEquals(10, tree.size());
    }

    @Test
    void searchTest(){
        insertTest();
        BinaryNode node = tree.search(35);
        assertNotNull(node);
        assertEquals(35, node.data());
        assertNull(tree.search(-10));
        assertNull(tree.search(100));
    }

    @Test
    void deleteTest(){
        assertNull(tree.delete(13));
        insertTest();
        insert2Test();
        assertEquals(10, tree.size());

        assertEquals(52, tree.delete(52).data());
        tree.delete(52);
        assertEquals(9, tree.size());


        tree.delete(12);
        assertEquals(9, tree.size());

        assertEquals(2, tree.height());
        assertEquals(57, this.tree.root().data());

        tree.insert(36);
        assertEquals(9, tree.size());

        tree.delete(35);
        assertEquals(8, tree.size());

        tree.delete(4);
        assertEquals(7, tree.size());
    }
}
