public class Edge {
    private int weight;
    private Node destVertex;

    public Edge(Node dest, int w) {
        this.destVertex = dest;
        this.weight = w;
    }
    public Edge(Node dest) {
        this.destVertex = dest;
        this.weight = 1;
    }

    public void setWeight(int weight){
        this.weight = weight;
    }

    public int getWeight() {
        return weight;
    }

    public Node getDestVertex() {
        return destVertex;
    }
}
