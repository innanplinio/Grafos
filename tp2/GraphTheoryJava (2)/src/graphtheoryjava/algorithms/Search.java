package graphtheoryjava.algorithms;

import graphtheoryjava.util.Graph;
import java.util.ArrayList;

public class Search {
    
    public Graph graph;
    public int[] visitado;
    public ArrayList<Integer> ordem;
    
    public Search(Graph graph) {
        this.graph = graph;
        this.visitado = new int[graph.vertices.size()];
        this.ordem = new ArrayList<Integer>();
    }
    
    public void reset() {
        ordem = new ArrayList<Integer>();
        for(int i = 0; i < graph.vertices.size(); ++i) {
            visitado[i] = 0;
        }
    }

    public ArrayList<Integer> buscaLargura(int s) {
        ArrayList<Integer> fila = new ArrayList<Integer>();
        visitado[s] = 1;
        fila.add(s);
        ordem.add(s);
        while(! fila.isEmpty()) {
           int u = fila.remove(0);
           for(int i = 0; i < graph.adjList.get(u).size(); ++i) {
               int v = graph.adjList.get(u).get(i).sink;
               if(visitado[v] == 0) {
                   visitado[v] = 1;
                   ordem.add(v);
                   fila.add(v);
               }
           }
        }
        return ordem;
    }

    public ArrayList<Integer> buscaProfundidade(int s) {
        ArrayList<Integer> pilha = new ArrayList<Integer>();
        visitado[s] = 1;
        pilha.add(s);
        ordem.add(s);
        while(! pilha.isEmpty()) {
            int u = pilha.get(pilha.size() - 1);
            int v = -1;
            for(int i = 0; i < graph.adjList.get(u).size(); ++i) {
                int temp = graph.adjList.get(u).get(i).sink;
                if(visitado[temp] == 0) {
                    v = temp;
                    break;
                }
            }
            if(v != -1) {
                visitado[v] = 1;
                ordem.add(v);
                pilha.add(v);
            } else {
                pilha.remove(pilha.size() - 1);
            }
        }
        return ordem;
    }
    
    public ArrayList<Integer> buscaProfundidadeRec(int u) {
        visitado[u] = 1;
        ordem.add(u);
        for(int i = 0; i < graph.adjList.get(u).size(); ++i) {
            int v = graph.adjList.get(u).get(i).sink;
            if(visitado[v] == 0) {
                buscaProfundidadeRec(v);
            }
        }
        return ordem;
    }

    public void comp(int u, int component) {
        visitado[u] = component;
        for(int i = 0; i < graph.adjList.get(u).size(); ++i) {
            int v = graph.adjList.get(u).get(i).sink;
            if(visitado[v] == 0) {
                comp(v, component);
            }
        }
    }

    public int connectedComponents() {
        int component = 0;
        for(int i = 0; i < graph.vertices.size(); ++i) {
            if(visitado[i] == 0) {
                ++component;
                comp(i, component);
            }
        }
        return component;
    }




    public void topSort(int u) {
        visitado[u] = 1;
        for(int i = 0; i < graph.adjList.get(u).size(); ++i) {
            int v = graph.adjList.get(u).get(i).sink;
            if(visitado[v] == 0) {
                topSort(v);
            }
        }
        ordem.add(0, u);
    }

    public ArrayList<Integer> topologicalSort() {
        for(int i = 0; i < graph.vertices.size(); ++i) {
            if(visitado[i] == 0) {
                topSort(i);
            }
        }
        return ordem;
    }

}