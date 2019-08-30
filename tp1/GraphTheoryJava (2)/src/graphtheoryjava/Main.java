package graphtheoryjava;

import graphtheoryjava.algorithms.CaminhoMinimo;
import graphtheoryjava.algorithms.Info;
import graphtheoryjava.algorithms.Search;
import graphtheoryjava.util.Reader;
import graphtheoryjava.util.Graph;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            ArrayList<Double> a = new ArrayList<>();
            ArrayList<Double> b = new ArrayList<>();
            ArrayList<Double> c2 = new ArrayList<>();
            System.out.println("Graph file: ");
            Scanner in = new Scanner(System.in);
            String fileName = in.nextLine();
            Reader reader = new Reader();
            Graph graph = reader.readFile(fileName);

//            System.out.println(graph.adjList);
//            Info info = new Info(graph);
//            System.out.println("Densidade: " + info.density());
//            System.out.println("Regular: " + info.regularAdjList());
//            System.out.println("Completo: " + info.completeAdjList());
//            System.out.println("Complementar: " + info.complementarAdjList());
//
//            Search search = new Search(graph);
//            System.out.println("Ordem busca largura: "
//                    + search.buscaLargura(0));
//            search.reset();
//            System.out.println("Ordem busca profundidade: "
//                    + search.buscaProfundidadeRec(0));
//            search.reset();
//            System.out.println("Componenetes conexas: "
//                    + search.connectedComponents());
//            search.reset();
//            System.out.println("Ordenação topológica: "
//                    + search.topologicalSort());
            CaminhoMinimo c = new CaminhoMinimo();
            System.out.println();
            Random r = new Random();

            int vert = 3353; //qtd de vertices no grafo 

            //Sorteio de Vértice De início e fim
            int s, d;
            s = r.nextInt(vert);
            d = r.nextInt(vert);
            while (s == d) {
                d = r.nextInt(vert);
            }
            System.out.println("\nS: " + s + " D: " + d + "\n");

            //Início do cálculo de caminho mínimo           
            Double time2;
            for (int i = 0; a.size() != 10; i++) {

                long time = System.nanoTime();
                c.Dijkstra(graph, s, d);
                time2 = (double) (System.nanoTime() - time) / 1000000000;
                a.add(time2);
                System.out.println("Tempo de Execução Dijkstra: " + time2 + "\n");

                time = System.nanoTime();
                c.BellmanFord(graph, s, d);
                time2 = (double) (System.nanoTime() - time) / 1000000000;
                b.add(time2);
                System.out.println("Tempo de Execução Bellman-Ford: " + time2 + "\n");

                time = System.nanoTime();
                c.FloydWarshall(graph, s, d);
                time2 = (double) (System.nanoTime() - time) / 1000000000;
                c2.add(time2);
                System.out.println("Tempo de Execução Floyd-Warshall: " + time2 + "\n");
            }
            System.out.println("Tempo dijkstra: " + a.toString() + "\nTempo Bellman-ford: " + b.toString() + "\nTempo Floyd-Warshall: " + c2.toString() + "\n\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
