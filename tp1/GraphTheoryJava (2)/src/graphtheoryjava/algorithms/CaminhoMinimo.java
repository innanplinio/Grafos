/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheoryjava.algorithms;

import graphtheoryjava.util.Graph;
import java.util.ArrayList;

/**
 *
 * @author innan
 */
public class CaminhoMinimo {

    public Graph graph;
    private ArrayList<Integer> dist;
    private ArrayList<Integer> pred;
    private ArrayList<Integer> q;

    public void Dijkstra(Graph g, int s, int d) {
        this.graph = g;
        int u = 0;
        dist = new ArrayList<Integer>();
        pred = new ArrayList<Integer>();
        q = new ArrayList<Integer>();

        for (int i = 0; i < g.vertices.size(); i++) {
            dist.add(i, 99999999);
            pred.add(i, null);
            q.add(i);
        }
        dist.set(s, 0);

        while (!q.isEmpty()) {

            //Acha o vertice de menor distância
            int aux = 999999999;
            for (int i = 0; i < q.size(); i++) {
                if (dist.get(q.get(i)) < aux) {
                    aux = dist.get(q.get(i));
                    u = q.get(i);
                }
            }
            q.remove((Integer) u);

            for (int i = 0; i < g.adjList.get(u).size(); i++) {
                int v = g.adjList.get(u).get(i).sink;
                if (dist.get(v) > (dist.get(u) + g.adjList.get(u).get(i).weight)) {
                    dist.set(v, (dist.get(u) + g.adjList.get(u).get(i).weight));
                    pred.set(v, u);
                }
            }

        }
        System.out.println("Dijkstra:");
        recuperaCaminho(s, d, pred);
        System.out.println("Custo = " + dist.get(d));
    }

    public boolean BellmanFord(Graph g, int s, int d) {
        this.graph = g;
        dist = new ArrayList<Integer>();
        pred = new ArrayList<Integer>();
        q = new ArrayList<Integer>();

        for (int i = 0; i < g.vertices.size(); i++) {
            dist.add(i, 99999999);
            pred.add(0);
        }
        dist.set(s, 0);

        for (int i = 0; i < g.vertices.size(); i++) {
            for (int j = 0; j < g.edges.size(); j++) {
                if (dist.get(g.edges.get(j).sink) > dist.get(g.edges.get(j).source) + g.edges.get(j).weight) {
                    dist.set(g.edges.get(j).sink, (dist.get(g.edges.get(j).source) + g.edges.get(j).weight));
                    pred.set(g.edges.get(j).sink, g.edges.get(j).source);
                }

            }
        }
        for (int j = 0; j < g.edges.size(); j++) {
            if (dist.get(g.edges.get(j).sink) > dist.get(g.edges.get(j).source) + g.edges.get(j).weight) {
                recuperaCaminho(s, d, pred);
                return false;
            }
        }
        System.out.println("Bellman-Ford:");
        recuperaCaminho(s, d, pred);
        System.out.println("Custo = " + dist.get(d));
        return true;
    }

    public void FloydWarshall(Graph g, int s, int d) {
        this.graph = g;
        int[][] dist = new int[g.adjMatrix.length][g.adjMatrix.length];
        int[][] pred = new int[g.adjMatrix.length][g.adjMatrix.length];
        q = new ArrayList<Integer>();
        for (int i = 0; i < g.vertices.size(); i++) {
            for (int j = 0; j < g.vertices.size(); j++) {

                if (i == j) {
                    dist[i][j] = 0;
                } else if (g.adjMatrix[i][j] != 0) {
                    dist[i][j] = g.adjMatrix[i][j];
                    pred[i][j] = i;
                } else {
                    dist[i][j] = 99999999;
                }

            }
        }
        for (int k = 0; k < g.vertices.size(); k++) {
            for (int i = 0; i < g.vertices.size(); i++) {
                for (int j = 0; j < g.vertices.size(); j++) {
                    if (dist[i][j] > dist[i][k] + dist[k][j]) {
                        dist[i][j] = dist[i][k] + dist[k][j];
                        pred[i][j] = pred[k][j];
                    }
                }
            }
        }

        for (int i = 0; i < g.vertices.size(); i++) {
            q.add(pred[s][i]);
        }
        System.out.println("Floyd-Warshall:");
        recuperaCaminho(s, d, q);
        System.out.println("Custo = " + dist[s][d]);
    }

    public void recuperaCaminho(int s, int t, ArrayList<Integer> pred) {
        ArrayList<Integer> caminho = new ArrayList<Integer>();
        int aux = t;
        while (aux != s) {
            aux = pred.get(aux);
            caminho.add(0, aux);
        }
        caminho.add(t);
        System.out.println("Caminho minimo entre " + s + " e " + t + ": " + caminho.toString());
    }
}
