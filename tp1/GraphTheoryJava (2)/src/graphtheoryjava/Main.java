package graphtheoryjava;

import graphtheoryjava.algorithms.CaminhoMinimo;
import graphtheoryjava.algorithms.Info;
import graphtheoryjava.algorithms.Search;
import graphtheoryjava.util.Reader;
import graphtheoryjava.util.Graph;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
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

            long time = System.nanoTime();
            c.Dijkstra(graph, 0, 100);
            System.out.println("Tempo de Execução Dijkstra: " + (System.nanoTime() - time) + "\n");

            time = System.nanoTime();
            c.BellmanFord(graph, 0, 100);
            System.out.println("Tempo de Execução Bellman-Ford: " + (System.nanoTime() - time) + "\n");

            time = System.nanoTime();
            c.FloydWarshall(graph, 0, 100);
            System.out.println("Tempo de Execução Floyd-Warshall: " + (System.nanoTime() - time) + "\n");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
