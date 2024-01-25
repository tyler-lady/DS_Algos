import java.util.*;

public class Kruskals {

    //Class to be used by MST
    //holds values as properties that will be needed
    //"makeset" procedure
    static class Subset {
        int parent, rank;

        public Subset(int parent, int rank)
        {
            this.parent = parent;
            this.rank = rank;
        }
    }

    /** MST
     *
     * implement Kruskal's MST algorithm from class.
     *
     * As a refresher,
     *
     * Get a list of edges and their weights (use the
     * Edge class for this).
     * Sort the edges. (You may use Collections.sort()).
     * Then, add the edges in order of smallest to
     * largest into the MST.
     * If a cycle is created, then don't add the edge.
     * Once each node has an edge, you're done!
     *
     * For a quick, more visual explanation see:
     *  https://www.youtube.com/watch?v=71UQH7Pr9kU
     *
     * One important note about the mst array.
     * It does not contain information on the weights,
     * only the nodes of the edges.
     *         i        = u : source node
     *       mst[i]     = v : dest node
     * graph[i][mst[i]] = weight(u, v)
     *
     * @param graph graph of edges and weights.
     * @return a Minimum Spanning Tree of the graph.
     */
    public static int[] mst(Graph graph) {
        //TODO
        //Get list of edges and their weights
        List<Edge> edges = new ArrayList<Edge>();
        for(int u = 0; u < graph.getNumNodes(); u++){
            for(int v = 0; v < graph.getNumNodes(); v++){
                if(graph.getEdge(u,v) != 0){
                    edges.add(new Edge(u,v,graph.getEdge(u,v)));
                }
            }
        }
        //Sort list of edges
        Collections.sort(edges);
        //init index for while and numEdges
        int j = 0;
        int numEdges = 0;

        Subset subs[] = new Subset[graph.getNumNodes()];

        Edge res[] = new Edge[graph.getNumNodes()];

        for(int i = 0; i < graph.getNumNodes(); i++){
            subs[i] = new Subset(i,0);
        }

        while(numEdges < graph.getNumNodes() - 1){
            Edge e;
            try {
                e = edges.get(j);
            } catch (Exception err){
                throw new DisconnectedGraphException();
            }
            int x = find(subs, e.u);
            int y = find(subs, e.v);

            //Cycle detection below - edge not added if cycle detected
            if(x != y){
                res[numEdges] = e;
                union(subs, x, y);
                numEdges++;
            }
            j++;
        }
        int mst[] = new int[graph.getNumNodes()];
        for(Edge edge : res){
            if(edge != null)
                mst[edge.u] = edge.v;
        }
        return mst;
    }

    // union procedure
    private static void union(Subset[] subs, int x, int y)
    {
        int rootX = find(subs, x);
        int rootY = find(subs, y);

        if (subs[rootY].rank < subs[rootX].rank) {
            subs[rootY].parent = rootX;
        }
        else if (subs[rootX].rank < subs[rootY].rank) {
            subs[rootX].parent = rootY;
        }
        else {
            subs[rootY].parent = rootX;
            subs[rootX].rank++;
        }
    }

    // Find procedure
    private static int find(Subset[] subs, int i)
    {
        if (subs[i].parent == i)
            return subs[i].parent;

        subs[i].parent = find(subs, subs[i].parent);

        return subs[i].parent;
    }

    /** CYCLEEXISTS
     *
     * Use any cycle detection algorithm of your choosing.
     *
     * BFS/DFS recommended
     *
     * Simply traverse the graph and if you encounter a node
     * twice, then there is a cycle. Return true.
     *
     * Otherwise, there is no cycle. Return false.
     *
     * @param mst The MST in progress.
     * @return True, if a cycle is detected, otherwise false.
     */
//    public static boolean cycleExists(int[] mst) {
//        //TODO
//    }
}

