package graphtheoryjava.algorithms;

import graphtheoryjava.util.Edge;
import graphtheoryjava.util.Graph;
import java.util.ArrayList;

public class Info {
    
    public Graph graph;
    
    public Info(Graph graph) {
        this.graph = graph;
    }
    
    public double density() {
        double density = (double) graph.edges.size() / 
                ((double) graph.vertices.size() * (graph.vertices.size() - 1));
        return density;
    }
    
    public boolean completeAdjList() {
        for(int i = 0; i < graph.vertices.size(); ++i) {
            if(graph.adjList.get(i).size() != graph.vertices.size() - 1) {
                return false;
            }
        }
        return true;
    }
    
    public boolean regularAdjList() {
        int grauV0 = graph.adjList.get(0).size();
        for(int i = 1; i < graph.vertices.size(); ++i) {
            int grauVi = graph.adjList.get(i).size();
            if(grauV0 != grauVi) {
                return false;
            }
        }
        return true;
    }
    
    public boolean completeMatrix() {
        for (int i = 0; i < graph.adjMatrix.length; ++i) {
            for (int j = 0; j < graph.adjMatrix.length; ++j) {
                if (i != j) {
                    if (graph.adjMatrix[i][j] == 0) {
                        return false;
                    }
                }
            }
        }
        return true;
    }
    
    public boolean regularMatrix() {
        int previousDegree = -1;
        for (int i = 0; i < graph.adjMatrix.length; ++i) {
            int degree = 0;
            for (int j = 0; j < graph.adjMatrix.length; ++j) {
                if (graph.adjMatrix[i][j] != 0) {
                    ++degree;
                }
            }
            if (previousDegree != -1 && degree != previousDegree) {
                return false;
            }
            previousDegree = degree;
        }
        return true;
    }
    
    public ArrayList<ArrayList<Edge>> complementarAdjList() {
        ArrayList<ArrayList<Edge>> complement = 
                new ArrayList<ArrayList<Edge>>();
        for (int i = 0; i < graph.adjList.size(); ++i) {
            complement.add(new ArrayList<Edge>());
            for (int v = 0; v < graph.adjList.size(); ++v) {
                boolean vFound = false;
                for (int j = 0; j < graph.adjList.get(i).size(); ++j) {
                    if(graph.adjList.get(i).get(j).sink == v)
                        vFound = true;
                }
                if(! vFound && i != v) {
                    complement.get(i).add(new Edge(i, v, 0));
                }
            }
        }
        return complement;
    }
    
    public int[][] complementarMatrix() {
        int[][] complementar = graph.adjMatrix;
        for (int i = 0; i < graph.adjMatrix.length; ++i) {
            for (int j = 0; j < graph.adjMatrix.length; ++j) {
                if (i != j) {
                    if (graph.adjMatrix[i][j] != 0) {
                        complementar[i][j] = 0;
                    } else {
                        complementar[i][j] = 1;
                    }
                }
            }
        }
        return complementar;
    }
    
}
