import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class GraphTraversalTest {
    @Test
    public void topologicalStack() throws CycleDetected {
        Node v0 = new Node(0);
        Node v1 = new Node(1);
        Node v2 = new Node(2);
        Node v3 = new Node(3);

        ArrayList<Node> nodes = new ArrayList<>(Arrays.asList(v0,v1,v2,v3));

        ArrayList<Node>[] neighbors = new ArrayList[4];
        neighbors[0] = new ArrayList<>(Arrays.asList(v1, v2));
        neighbors[1] = new ArrayList<>(Collections.singletonList(v3));
        neighbors[2] = new ArrayList<>(Collections.singletonList(v3));
        neighbors[3] = new ArrayList<>();


        GraphL g = new GraphL(neighbors, nodes);
        List<Node> sort = g.topologicalSortStack();
        System.out.println(sort);
        assertEquals(4, sort.size());
        assertEquals(v0, sort.get(0));
        assertEquals(v2, sort.get(1)); // these could potentially be switched
        assertEquals(v1, sort.get(2)); // these could potentially be switched
        assertEquals(v3, sort.get(3));
    }

    @Test
    public void topologicalQueue() throws CycleDetected {
        Node v0 = new Node(0);
        Node v1 = new Node(1);
        Node v2 = new Node(2);
        Node v3 = new Node(3);

        ArrayList<Node> nodes = new ArrayList<>(Arrays.asList(v0,v1,v2,v3));

        ArrayList<Node>[] neighbors = new ArrayList[4];
        neighbors[0] = new ArrayList<>(Arrays.asList(v1, v2));
        neighbors[1] = new ArrayList<>(Collections.singletonList(v3));
        neighbors[2] = new ArrayList<>(Collections.singletonList(v3));
        neighbors[3] = new ArrayList<>();


        GraphL g = new GraphL(neighbors, nodes);
        List<Node> sort = g.topologicalSortQueue();
        System.out.println(sort);
        assertEquals(4, sort.size());
        assertEquals(v0, sort.get(0));
        assertEquals(v1, sort.get(1)); // these could potentially be switched
        assertEquals(v2, sort.get(2)); // these could potentially be switched
        assertEquals(v3, sort.get(3));
    }

    @Test
    void initTest(){
        GraphL g = new GraphL(4);
        assertEquals(4, g.nodeCount());

        g.addEdge(0, 2);
        assertEquals(1, g.edgeCount());
        g.addEdge(0,3);
        assertEquals(2, g.edgeCount());
        g.removeEdge(0,3);
        assertEquals(1, g.edgeCount());

        g.addEdge(0, 6);
        assertEquals(1, g.edgeCount());
        g.addEdge(2,3);
        assertEquals(2, g.edgeCount());
        g.removeEdge(2,3);
        assertEquals(1, g.edgeCount());
        g.addEdge(1,3);
        g.addEdge(1,2);
        assertEquals(2, g.neighbors(1).size());
        assertNull(g.neighbors(5));
        g.addEdge(2,0);

        Throwable exception1 = assertThrows(CycleDetected.class, () ->{
            g.topologicalSortQueue();
        });
//        Throwable exception2 = assertThrows(CycleDetected.class, () ->{
//            g.topologicalSortStack();
//        });
    }

}