package graphtheoryjava.util;

import java.util.ArrayList;

public class Graph {
    
    public ArrayList<Integer> vertices;
    public ArrayList<Edge> edges;
    public ArrayList<ArrayList<Edge>> adjList;
    public int [][] adjMatrix;

    public Graph(ArrayList<Integer> vertices, ArrayList<Edge> edges, 
            ArrayList<ArrayList<Edge>> adjList, int[][] adjMatrix) {
        this.vertices = vertices;
        this.edges = edges;
        this.adjList = adjList;
        this.adjMatrix = adjMatrix;
    }
    
    
    
}
