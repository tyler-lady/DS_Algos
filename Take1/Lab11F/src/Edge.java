public class Edge implements Comparable<Edge>{

    int u;
    int v;
    int weight;

    public Edge(int u, int v, int weight) {
        this.u = u;
        this.v = v;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge o) {
        return weight - o.weight;
    }

    // not necessary but if you want to use it :)
    @Override
    public boolean equals(Object o) {

        if (o == this) {
            return true;
        }

        if (!(o instanceof Edge)) {
            return false;
        }

        Edge edge = (Edge) o;

        if (u == edge.v && v == edge.u && weight == edge.weight) {
            return true;
        }

        return false;
    }
}
