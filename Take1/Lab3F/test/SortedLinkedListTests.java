import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class SortedLinkedListTests {

    /*
    TODO: tests
    - Make sure you have 100% code coverage
        + This also means you should break your tests up by method
    - Make sure you test the full functionality of this class...
      think edge cases (bounds, exceptions, etc...)
    - Use JUnit (you will not receive points for testing if you do
      not use JUnit)
     */

    SortedLinkedList LL = new SortedLinkedList();

    @Test
    void newSortedLL_test(){
        assertNotNull(new SortedLinkedList());
    }

    @Test
    void InsertSorted_Test(){
        System.out.println("InsertSorted_Test Beginning");
        LL.insertSorted(8);
        LL.insertSorted(11);
        LL.insertSorted(3);
        LL.insertSorted(1);

        assertEquals(4, LL.getLength());
        System.out.println(LL.getLength());

        System.out.println(LL);

        System.out.println("InsertSorted_Test Finished\n");

    }

    @Test
    void delete_Test(){
        assertFalse(LL.delete(0));

        InsertSorted_Test();

        assertTrue(LL.delete(2));

        assertTrue(LL.delete(0));
        assertTrue(LL.delete(0));
        assertTrue(LL.delete(0));

        assertFalse(LL.delete(0));
    }

    @Test
    void get_Test(){
        //assertEquals(0, LL.get(0));
        InsertSorted_Test();
        assertEquals(3, LL.get(1));
        //System.out.println(LL.get(1));
        Throwable exception = assertThrows(IndexOutOfBoundsException.class,()->{
            LL.get(LL.getLength()+1);
        });
    }

    @Test
    void getNode_Test(){
        InsertSorted_Test();

        Throwable exception = assertThrows(IndexOutOfBoundsException.class,()->{
            LL.getNode(LL.getLength()+1);
        });
    }

    @Test
    void search_Test(){
        InsertSorted_Test();
        assertEquals(1,LL.search(3));
        assertEquals(-1,LL.search(51));
    }
}
