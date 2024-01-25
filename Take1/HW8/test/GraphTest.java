import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class GraphTest {

    // TODO: Write accuracy tests + test different graphs :)

    @Test
    public void shortestPath1(){
        Node n0 = new Node(0);
        Node n1 = new Node(1);
        Node n2 = new Node(2);
        Node n3 = new Node(3);

        List<Node> nodes = List.of(n0,n1,n2,n3);
        Graph g = new Graph(nodes);

        g.addEdge(n0, n1, 1);
        g.addEdge(n0, n2, 1);
        g.addEdge(n1, n3, 4);
        g.addEdge(n2, n3,2);

        List<Node> traverse = g.shortestPath(0, 3);
        assertEquals(0, n0.getValue());
        assertEquals(1, n2.getValue());
        assertEquals(2, n3.getValue());
        assertEquals(3, traverse.size());
        g.reset();

        traverse = g.shortestPath(2, 0);
        assertNull(traverse);
    }

}
