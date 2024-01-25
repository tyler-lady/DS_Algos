import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class SortedArrayListTest {
    /*
    TODO: write test cases testing the accuracy of the methods marked with TODO. Write an efficiency test which asserts that the
    SortedArrayTest is faster than the ArrayList. Be sure to test for edge cases.
     */

    SortedArrayList<String> List = new SortedArrayList<String>();
    SortedArrayList<String> List_1 = new SortedArrayList<String>();

    @Test
    void constr_Test(){
        SortedArrayList<String> List_2 = new SortedArrayList<String>(15);
    }

    @Test
    void add_Test(){
        List.add("Hello");
        List.add("Goodbye");
        List.add("Tomorrow");
        List.add("Zoo");

        System.out.println(List);
    }

    @Test
    void capacity_Test(){
        add_Test();
        add_Test();
        add_Test();
    }

    @Test
    void get_Test(){
        add_Test();

        Throwable exception = assertThrows(IndexOutOfBoundsException.class,()->{
            List.get(List.size()+1);
        });
    }

    @Test
    void add_Test2(){
        List_1.add("Hello");
        List_1.add("Goodbye");
        List_1.add("Tomorrow");
        List_1.add("Zoo");
    }

    @Test
    void add_Test3(){
        List_1.add("Barn");
        List_1.add("Chickens");
        List_1.add("Manure");
        List_1.add("Cow");
    }

    @Test
    void delete_Test(){
        add_Test();

        List.delete(1);

        System.out.println(List);

        Throwable exception = assertThrows(IndexOutOfBoundsException.class,()->{
            List.delete(List.size()+1);
        });
    }

    @Test
    void search_Test(){
        add_Test();

        assertEquals(3, List.search("Zoo"));

        assertEquals(-1, List.search("Tractor"));
    }

    @Test
    void equal_Test(){
        add_Test();
        add_Test2();

        assertTrue(List.equals(List_1));
    }

    @Test
    void equal_Test2(){
        add_Test();
        add_Test3();

        assertFalse(List.equals(List_1));
    }

}
