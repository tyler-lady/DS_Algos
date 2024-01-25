import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class KruskalsTest {

    /**
     * Some test inspiration...
     *
     * What if a graph had a disconnected node?
     *
     * What if a MST was a line?
     *     Example:
     *      0 > 1
     *      1 > 2
     *      2 > 3
     *
     * What if a MST was NOT in a line?
     *     Example:
     *      0 > 1
     *      0 > 2
     *      0 > 3
     */
    @Test
    void mst() throws DisconnectedGraphException {
        int[][] matrix = new int[4][4];
        Graph g = new Graph(matrix);
        g.addEdge(0,1, 2);
        g.addEdge(3,2, 4);
        g.addEdge(0,2, 4);
        g.addEdge(1,2, 2);
        Kruskals.mst(g);
    }



}