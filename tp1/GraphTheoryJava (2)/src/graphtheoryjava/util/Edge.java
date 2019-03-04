package graphtheoryjava.util;

public class Edge {

    public int source;
    public int sink;
    public int weight;

    public Edge(int source, int sink, int weight) {
        this.source = source;
        this.sink = sink;
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "(" + source + ", " + sink + ", " + weight + ')';
    }

}
