package graphtheoryjava.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Reader {
    
    public File file;
    public Graph graph;
    
    public Reader() {
        
    }
    
    public Graph readFile(String fileName) throws IOException {
        //Open file reader
        this.file = new File(fileName);
        FileReader reader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(reader);
        
        //Read header
        String[] line;
        line = bufferedReader.readLine().split(" ");
        int numVertices = Integer.parseInt(line[0]);
        int numEdges = Integer.parseInt(line[1]);

        //Initialize data structures
        ArrayList<Integer> vertices = new ArrayList<Integer>();
        ArrayList<ArrayList<Edge>> adjList = new ArrayList<ArrayList<Edge>>();
        int [][] adjMatrix = new int[numVertices][numVertices];
        ArrayList<Edge> edges = new ArrayList<Edge>();
        
        //Create adjacence list and matrix
        for(int i= 0; i < numVertices; ++i) {
            for(int j = 0; j < numVertices; ++j) {
                adjMatrix[i][j] = 0;
            }
        }

        for(int i= 0; i < numVertices; ++i) {
            adjList.add(new ArrayList<Edge>());
        }

        //Fill structures
        for(int i= 0; i < numVertices; ++i) {
            vertices.add(i);
        }
        
        for(int i = 0; i < numEdges; ++i) {
            String [] infoEdge = bufferedReader.readLine().split(" ");
            int source = Integer.parseInt(infoEdge[0]);
            int sink = Integer.parseInt(infoEdge[1]);
            Edge e = new Edge(source, sink);
            
            edges.add(e);
            adjList.get(source).add(e);
            adjMatrix[source][sink] = 0;
        }
        
        graph = new Graph(vertices, edges, adjList, adjMatrix);
        
        //Close file
        bufferedReader.close();
        reader.close();
        return graph;
    }
    
}