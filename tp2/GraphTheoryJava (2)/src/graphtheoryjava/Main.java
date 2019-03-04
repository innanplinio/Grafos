package graphtheoryjava;

import graphtheoryjava.algorithms.Coloração;
import graphtheoryjava.util.Reader;
import graphtheoryjava.util.Graph;
import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            //Inicializando grafo
            System.out.println("Graph file: ");
            Scanner in = new Scanner(System.in);
            String fileName = in.nextLine();
            Reader reader = new Reader();
            Graph graph = reader.readFile(fileName);

            //Inicializando coloração
            Coloração col = new Coloração(graph);
            for (int i = 0; i < 5; i++) {
                col.Greedycol(graph);
                col.Clear();
            }
            col.Media();
            col.DesvioPadrao();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
