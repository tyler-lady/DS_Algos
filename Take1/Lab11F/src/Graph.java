public class Graph {

    private int[][] graph;
    private int numNodes;

    public Graph(int[][] graph) {
        this.graph = graph;
        this.numNodes = graph.length;
    }

    // Returns the weight of an edge connected any 2 nodes u and v
    public int getEdge(int u, int v) {
        return graph[u][v];
    }

    // Returns the number of nodes in a graph
    public int getNumNodes() {
        return numNodes;
    }

    // not needed but could be useful for testing.
    void printMST(int mst[])
    {
        System.out.println("Edge \tWeight");
        for (int i = 1; i < numNodes; i++)
            System.out.println(i + " - " + mst[i] + "\t" + graph[mst[i]][i]);
    }

    public void addEdge(int v, int w, int value) {
        graph[v][w] = value;
    }

    public void removeEdge(int v, int w) {
        graph[v][w] = 0;
    }
}
