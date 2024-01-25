import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GraphTest {
    boolean[][] matrix = new boolean[6][6];
    List<Node> nodes = new ArrayList<>();
    Node n = new Node(0);
    Node n1 = new Node(1);
    Node n2 = new Node(2);
    Node n3 = new Node(3);
    Node n4 = new Node(4);
    Node n5 = new Node(5);

    Graph g;

    @BeforeEach
    void setupGraph(){
        nodes.add(n);
        nodes.add(n1);
        nodes.add(n2);
        nodes.add(n3);
        nodes.add(n4);
        nodes.add(n5);

        n1.setPath(n);
        n2.setPath(n1);
        n3.setPath(n2);

        matrix[0][1] = true;
        matrix[1][2] = true;
        matrix[2][3] = true;

        g = new Graph(nodes, matrix);
    }

    @Test
    void connected() {
        //TODO
        assertEquals(1, g.connected(n, n5).size());
        assertEquals(4, g.connected(n, n3).size());
        assertEquals(3, g.connected(n, n2).size());
    }

    @Test
    void getPath() {
        /**
         * This is not required since you need to call this method in connected()
         * but here is the template if you wish to make your own tests
         */
        System.out.println(g.getPath(n5).toString());
        Node[] path = {n,n1,n2,n3};
        assertEquals(Arrays.toString(path), g.getPath(n3).toString());
    }
}