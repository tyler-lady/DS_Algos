import java.util.*;

public class Graph {
    private List<Node> nodes;

    public Graph() {
        nodes = new ArrayList<>();
    }

    public Graph(List<Node> nodes) {
        this.nodes = nodes;
    }

    // TODO: Initialize the nodes
    public void init(int n){
        //System.out.println("Init-ing nodes...");
        if(this.nodes.isEmpty()){
            for(int i = 0; i < n; i++){
                this.nodes.add(new Node(i));
            }
        }
        //no idea what is necessary to init here
    }

    // TODO: not tested for credit technically but this method should reset your node's value
    public void reset(){
        //System.out.println("Resetting...");
        for(Node n : this.nodes){
            n.setValue(0);
        }
    }

    // TODO:
    public void addEdge(Node src, Node dest, int weight){
        //System.out.println("Adding node...");
        LinkedList<Edge> edges = src.getEdges();
        edges.add(new Edge(dest, weight));
        dest.setValue(dest.getValue() + 1);
        dest.setPrev(src);
    }

    // TODO: remove the edge from the Node
    public void removeEdge(Node src, Node dest){
        //System.out.println("Removing node...");
        LinkedList<Edge> edges = src.getEdges();
        for(Iterator i = edges.iterator(); i.hasNext();){
            Edge e = (Edge) i.next();
            if(e.getDestVertex() == dest){
                edges.remove(e);
                if(dest.getValue() != 0)
                    dest.setValue(dest.getValue()-1);
                dest.setPrev(null);
                break;
            }
        }
    }

    // TODO: Return the shortest path from start to dest with the correct cost of the nodes; return null if no path possible
    public List<Node> shortestPath(int start, int dest){
        //System.out.println("Finding shortest path...");

        HashSet<Node> visNodes = new HashSet<Node>();
        HashSet<Node> unvisNodes = new HashSet<Node>();
        HashMap<Node, Integer> dist = new HashMap<Node, Integer>();
        HashMap<Node, Node> predecessors = new HashMap<Node, Node>();

        dist.put(this.nodes.get(start), 0);
        unvisNodes.add(this.nodes.get(start));
        while(unvisNodes.size() > 0){
            Node n = getMin(unvisNodes, dist);
            visNodes.add(n);
            unvisNodes.remove(n);
            fillDist(n, dist, predecessors, unvisNodes);
            //System.out.println("While loop iteration...");
        }

        Node d = this.nodes.get(dest);

        return getPath(d, predecessors);
    }

    private void fillDist(Node n, HashMap<Node, Integer> dist, HashMap<Node, Node> predecessors, HashSet<Node> unvisNodes) {
        LinkedList<Edge> edges = n.getEdges();
        for(Edge e : edges){
            Node node = e.getDestVertex();
            if(this.getShortDist(node, dist) > this.getShortDist(node, dist) + e.getWeight()){
                dist.put(node, getShortDist(node, dist) + e.getWeight());
                predecessors.put(node, n);
                unvisNodes.add(node);
            }
        }
    }

    private Node getMin(HashSet<Node> unvisNodes, HashMap<Node, Integer> dist) {
        Node min = null;
        for(Node n : unvisNodes){
            if(min == null){
                min = n;
            }else if(getShortDist(n, dist) < getShortDist(min, dist)) {
                min = n;
            }
        }
        return min;
    }

    private int getShortDist(Node dest, HashMap<Node, Integer> dist) {
        Integer d = dist.get(dest);
        if(d == null)
            return Integer.MAX_VALUE;
        else
            return d;
    }

    private List<Node> getPath(Node dest, HashMap<Node, Node> predecessors){
        //System.out.println("Getting Path...");
        LinkedList<Node> path = new LinkedList<Node>();
        Node step = dest;

        if(predecessors.get(step) == null)
            return null;
        path.add(step);
        while(predecessors.get(step) != null){
            step = predecessors.get(step);
            path.add(step);
        }

        Collections.reverse(path);
        return path;
    }
}

