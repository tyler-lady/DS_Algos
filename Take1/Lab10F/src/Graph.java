import java.util.*;

public class Graph {

    private List<Node> nodes; // List of all nodes in a graph
    private boolean[][] adjacencyMatrix; // directional adjacency matrix that has adjacencyMatrix[v][w] = true if v is connected to w

    public Graph(List<Node> nodes, boolean[][] adjacencyMatrix){
        this.nodes = nodes;
        this.adjacencyMatrix = adjacencyMatrix;
    }


    /** CONNECTED
     *
     * This method is a very simple graph traversal problem.
     *
     * Using the traversal algorithm of your choosing, (BFS/DFS recommended)
     * Traverse the graph starting from a node v until you find a node w.
     * Once found, return the path from v to w.
     *
     * This path does not need to be the shortest nor the most efficient,
     * simply a path. You will have difference outputs depending on which
     * traversal you use.
     *
     * Use the helper method getPath() to retrieve the path once you have
     * found w.
     *
     * @param v starting node
     * @param w ending node
     * @return Arraylist path from v to w
     */
    public ArrayList<Node> connected(Node v, Node w)
    {
        //TODO
        Stack<Node> s = new Stack<>();
        Node n = v;

        System.out.println("V is: " + v.getName());
        System.out.println("W is: " + w.getName());
        System.out.println(Arrays.deepToString(adjacencyMatrix));

        s.push(v);
        while(!s.isEmpty()){
            n = s.pop();
            if(n.isNotVisited()){
                n.setVisited();
                for(Node j : nodes){
                    if(adjacencyMatrix[n.getName()][j.getName()]){
                        System.out.println(j.getName());
                        s.push(j);
                    }
                }
            }
        }
        ArrayList<Node> path = getPath(w);
        System.out.println("Path is: " + path.toString());
        return path;
    }


    /** GETPATH
     *
     * Each node has a path variable that stores a node. This node is
     * the previous node in the path. If the path variable is equal to
     * null that means that it is the origin node in the path.
     *
     * Recursively add those nodes to an arraylist and then return
     * that arraylist once you encounter a null path variable to retrieve
     * the entire path.
     *
     * If no path exists, simply return an arraylist only containing
     * the given node.
     *
     * @param node ending node in the path
     * @return ordered arraylist that is a path from the
     *          origin node to the specified node.
     */
    ArrayList<Node> getPath(Node node)
    {
        //TODO
        return path(node, new ArrayList<Node>());
    }

    ArrayList<Node> path(Node node, ArrayList<Node> p){
        if(node.getPath() == null){
            p.add(0,node);
            return p;
        }

        p.add(0,node);
        return path(node.getPath(), p);
    }


}
