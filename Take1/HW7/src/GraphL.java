import java.util.*;

class CycleDetected extends Exception{};

public class GraphL implements Graph {
    // An Array of nodes where each item points to a list of adjacent nodes
    private List<Node>[] nodeArray; // The adjacency list
    // A List of nodes that will be referenced in the nodeArray
    private List<Node> nodes;
    // Number of edges in the graph
    private int numEdge;

    public GraphL(int n){
        this.nodeArray = new ArrayList[n];
        this.nodes = new ArrayList<>();
        init(n);
    }

    // TODO:
    public GraphL(List<Node>[] nodeArray, List<Node> nodes){
        this.nodeArray = nodeArray;
        this.nodes = nodes;
        for(List<Node> l : this.nodeArray){
            this.numEdge += l.size();
        }
    }

    // Implement the missing functions here:
    // TODO:
    // Initialize the graph with some number of vertices
    public void init(int n) {
        //num of vertices is n - assume values of verts is 0 to n-1
        for(int i = 0;i < n; i++){
            Node node = new Node(i);
            this.nodes.add(node);
            this.nodeArray[i] = new ArrayList<>();
        }
    }

    // Hint: may need a reset function for the Runner class (not in the Interface)
    public void reset(){

    }

    // TODO:
    // Return the number of vertices
    public int nodeCount() {
        return this.nodes.size();
    }

    // TODO:
    // Return the current number of edges
    public int edgeCount() {
        return numEdge;
    }

    // TODO:
    // Adds a new edge from node v to node w
    public void addEdge(int v, int w) {
        if(this.hasEdge(v,w))
            return;

        int index = 0;

        // Finding the index of the node containing v - used to find the list of neighboring nodes
        for(Node n : this.nodes){
            if(n.getName() == v)
                break;
            index ++;
        }

        if(index < this.nodes.size()){
            // Get ref to list
            List<Node> l = this.nodeArray[index];
            //reset index
            index = 0;
            for(Node n : this.nodes){
                // Get node for w and add to the list of adj nodes - increment number of edges and increase value of node for w
                if(n.getName() == w){
                    l.add(n);
                    n.setValue(n.getValue() + 1);
                    this.numEdge ++;
                    break;
                }
                index ++;
            }
        }
    }

    // TODO:
    // Removes the edge from the graph.
    public void removeEdge(int v, int w) {
        if(!this.hasEdge(v,w))
            return;

        int index = 0;

        // Finding the index of the node containing v - used to find the list of neighboring nodes
        for(Node n : this.nodes){
            if(n.getName() == v)
                break;
            index ++;
        }

        if(index < this.nodes.size()){
            // Get ref to list
            List<Node> l = this.nodeArray[index];
            //reset index
            index = 0;
            for(Node n : this.nodes){
                // Get node for w and add to the list of adj nodes - increment number of edges and increase value of node for w
                if(n.getName() == w){
                    l.remove(n);
                    n.setValue(n.getValue() - 1);
                    this.numEdge --;
                    break;
                }
                index ++;
            }
        }
    }

    // TODO:
    // Returns true iff the graph has the edge
    public boolean hasEdge(int v, int w) {
        int index = 0;

        // Finding the index of the node containing v - used to find the list of neighboring nodes
        for(Node n : this.nodes){
            if(n.getName() == v)
                break;
            index ++;
        }

        if(index < this.nodes.size()){
            List<Node> l = this.nodeArray[index];
            // if node with w in list, then there is an edge
            if(l != null){
                for(Node no : l){
                    if(no.getName() == w)
                        return true;
                }
            }
        }

        return false;
    }

    // TODO:
    // Returns an array containing the Nodes of the neighbors of v
    public List<Node> neighbors(int v) {
        int index = 0;

        // Finding the index of the node containing v - used to find the list of neighboring nodes
        for(Node n : this.nodes){
            if(n.getName() == v)
                break;
            index ++;
        }
        if(index < this.nodes.size()){
            return this.nodeArray[index];
        }
        return null;
    }

    // TODO: Implement topological sort with stack.
    /*
    Potential way to implement:
    1. For each node in the nodes, you can perform DFS
     */
    // Hint: think of how you can use the value of the Node (not the name)
    public List<Node> topologicalSortStack() throws CycleDetected {
        ArrayList<Node> sorted = new ArrayList<Node>();

        Node n = this.nodes.get(0);
        Stack<Node> s = new Stack<Node>();

        //this.reset();

        for(int i = 0; i < this.nodeCount(); i++){
            n = this.nodes.get(i);
            if(!n.getVisited()){
                topoStackHelper(n,s);
            }
        }

        while(!s.empty())
            sorted.add(s.pop());

        return sorted;
    }

    // Implements modified DFS to help in sort
    private void topoStackHelper(Node node, Stack<Node> stack) throws CycleDetected {
        //mark node as visited
        node.setVisited();
        Node n;

        Iterator<Node> iter = this.nodeArray[this.nodes.indexOf(node)].iterator();
        while(iter.hasNext()){
            n = iter.next();

            if(!n.getVisited()){
                topoStackHelper(n, stack);
            }
        }

        stack.push(node);
    }

    // TODO: Implement topological sort with queue.
    /*
    Potential way to implement:
    1. Go through the edges and set the value of the node to the number of incoming edges and unvisited.
    2. Push nodes that have 0 incoming edges into the queue.
    3. Implement BFS
        -Everytime you process a node, decrease it's value.
     */
    public List<Node> topologicalSortQueue() throws CycleDetected {
        Queue<Node> q = new LinkedList<Node>();
        for(Node n : this.nodes){
            if(n.getValue() == 0)
                q.add(n);
        }

        int cnt = 0;

        ArrayList<Node> sorted = new ArrayList<>();

        while(!q.isEmpty()){
            Node tmp = q.poll();
            sorted.add(tmp);

            int index = 0;

            // Finding the index of the node containing v - used to find the list of neighboring nodes
            for(Node n : this.nodes){
                if(n.getName() == tmp.getName())
                    break;
                index ++;
            }

            for(Node n : this.nodeArray[index]){
                n.setValue(n.getValue()-1);
                if(n.getValue() == 0)
                    q.add(n);
            }
            cnt++;
        }

        if(cnt != this.nodeCount()){
            throw new CycleDetected();
        }

        return sorted;
    }

}