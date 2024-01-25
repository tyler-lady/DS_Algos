import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class NodeTest {
        /*
    TODO: write test cases testing the accuracy of the methods marked with TODO.s.
     */

    Node node = new Node("String", 1);
    Node node_1 = new Node("String", 2);

    Node node_2 = new Node("String", 1);
    Node node_3 = new Node("String", 2);

    @Test
    void getKeyword_Test(){
        String keyword1 = node.getKeyword();
        String keyword2 = node_1.getKeyword();

        assertEquals(0, keyword1.compareTo(keyword2));

    }

    @Test
    void insertRefs_Test(){
        node.insertReference("Hello");
        node.insertReference("Bye");
        node.insertReference("School");
    }

    @Test
    void insertRefs1_Test(){
        node_3.insertReference("Hello");
        node_3.insertReference("Bye");
        node_3.insertReference("School");
    }

    @Test
    void insertRefs2_Test(){
        node_2.insertReference("Hello");
        node_2.insertReference("Bye");
        node_2.insertReference("Telephone");
    }

    @Test
    void getRefs_Test(){
        insertRefs_Test();
        insertRefs1_Test();
        insertRefs2_Test();

        List<String> refs = node.getReferences();
        for(int i = 0; i < refs.size(); i++){
            System.out.println(refs.get(i));
        }
        List<String> refs1 = node_3.getReferences();
        for(int i = 0; i < refs1.size(); i++){
            System.out.println(refs1.get(i));
        }
        List<String> refs2 = node_2.getReferences();
        for(int i = 0; i < refs2.size(); i++){
            System.out.println(refs2.get(i));
        }
    }

    @Test
    void compareTo_Test(){
        assertEquals(0, node.compareTo(node_1));

        assertEquals(-1, node.compareTo("foo"));

        insertRefs_Test();
        //assertEquals(1, node.compareTo(node_1));
        insertRefs1_Test();
        //assertEquals(-1, node_2.compareTo(node_3));
        insertRefs2_Test();
        System.out.println(node_2.compareTo(node_3));
        System.out.println(node.compareTo(node_1));
        //assertEquals(1, node_2.compareTo(node_3));
    }

    @Test
    void equals_Test(){
        assertTrue(node.equals(node_1));
        assertFalse(node.equals(new ArrayList<>()));
    }

}
