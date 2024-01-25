import java.util.LinkedList;

public class Node implements Comparable<Node>{
    private final int name;
    private LinkedList<Edge> edgeList;
    private int value; //in degree
    private Node prev; // don't understand how this is supposed to work if the node could have more than 1 as value

    // TODO: set up prev, edgeList, and the value
    public Node(int name) {
        this.name = name;
        this.edgeList = new LinkedList<Edge>(); //edgelist
        this.value = 0; //value
        this.prev = null; //previous node - I assume (di-graph)
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Node n = (Node) o;
        return n.getValue() == this.value &&
                this.name == n.name;
    }

    @Override
    public int compareTo(Node o) {
        return Integer.compare(this.value, o.getValue());
    }

    public void setValue(int val){
        this.value = val;
    }

    public void setPrev(Node prev){
        this.prev = prev;
    }

    public Node getPrev(){
        return this.prev;
    }

    public int getValue(){
        return value;
    }

    public int getName() {
        return name;
    }

    public LinkedList <Edge> getEdges() {
        return edgeList;
    }

    public String toString(){
        return name + " " + value;
    }

}
