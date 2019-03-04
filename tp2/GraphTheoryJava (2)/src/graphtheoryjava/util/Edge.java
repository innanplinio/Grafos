package graphtheoryjava.util;

public class Edge {
    public int source;
    public int sink;

    public Edge(int source, int sink) {
        this.source = source;
        this.sink = sink;
    }

    @Override
    public String toString() {
        return "(" + source + ", " + sink + ")";
    }

}
