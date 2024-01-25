class Node {

    private final int name;
    private boolean visited;
    private Node path = null;

    Node(int name) {
        this.name = name;
        this.visited = false;
    }

    int getName() {
        return name;
    }

    void setVisited() { this.visited = true; }

    void setUnvisited() {
        this.visited = false;
    }

    boolean isNotVisited() {
        return !visited;
    }

    public void setPath(Node path) {
        this.path = path;
    }

    public Node getPath() {
        return path;
    }

    public String toString() {
        return name + "";
    }

    public boolean equals(Object o) {
        if (o instanceof Node) {
            Node that = (Node) o;
            return name == that.getName();
        } else return false;
    }
}