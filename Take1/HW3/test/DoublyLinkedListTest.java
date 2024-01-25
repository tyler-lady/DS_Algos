import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DoublyLinkedListTest {

    DoublyLinkedList DLL = new DoublyLinkedList<>();
    DoublyLinkedList DLL_NULL = new DoublyLinkedList<>(null,null);

    NodeDL node = new NodeDL<>(8);
    NodeDL node1 = new NodeDL<>(5);
    DoublyLinkedList DLL_1 = new DoublyLinkedList(node,node1);
    DoublyLinkedList DLL_2 = new DoublyLinkedList<>(node, node);

    @Test
    void insertTest(){
        DLL.insertAtHead(5);
        DLL.insertAtHead(8);
        DLL.insertAtTail(24);

        assertEquals(3, DLL.size());
        assertEquals(8, DLL.get(0));
        assertEquals(5, DLL.get(1));
        assertEquals(24, DLL.get(2));
    }

    @Test
    void insertForEqualsTest(){
        DLL_NULL.insertAtHead(5);
        DLL_NULL.insertAtHead(8);
        DLL_NULL.insertAtTail(24);
    }

    @Test
    void insertTailTest(){
        DLL_NULL.insertAtTail(15);

        assertEquals(1, DLL_NULL.size());
    }

    @Test
    void getTest(){
        Throwable exception1 = assertThrows(IndexOutOfBoundsException.class,()->{
            DLL.get(0);
        });

        insertTest();

        assertEquals(5, DLL.get(1));

        Throwable exception = assertThrows(IndexOutOfBoundsException.class,()->{
            DLL.get(DLL.size()+1);
        });
    }

    @Test
    void searchTest(){
        insertTest();

        assertEquals(0, DLL.search(8));
        assertEquals(1, DLL.search(5));
        assertEquals(-1, DLL.search(63));
    }

    @Test
    void deleteTest() throws EmptyListE {
        Throwable exception = assertThrows(EmptyListE.class,()->{
            DLL.deleteAtHead();
        });

        Throwable exception1 = assertThrows(EmptyListE.class,()->{
            DLL.deleteAtTail();
        });

        insertTest();

        DLL.deleteAtHead();
        assertEquals(2, DLL.size());
        DLL.deleteAtTail();
        DLL.deleteAtTail();
        assertEquals(0, DLL.size());
    }

    @Test
    void insertForEquals1(){
        DLL_1.insertAtTail(55);
    }

    @Test
    void equalsTest1(){
        insertTest();
        insertForEquals1();
        assertFalse(DLL.equals(DLL_NULL));
        assertFalse(DLL.equals(10));
        assertFalse(DLL.equals(DLL_1));
        insertForEqualsTest();

        assertTrue(DLL.equals(DLL_NULL));

    }

    @Test
    void printTest(){
        System.out.println(DLL.toString());
    }
}
