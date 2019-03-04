/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package graphtheoryjava.algorithms;

import graphtheoryjava.util.Graph;
import java.util.ArrayList;
import java.util.Random;
import java.lang.Math;

/**
 *
 * @author Innan
 */
public class Coloração {

    public Graph g;
    private ArrayList<Integer> s;
    private ArrayList<Integer> cores;
    private ArrayList<Integer> corespossiveis;
    private ArrayList<Integer> qtdcores = new ArrayList<>();
    private ArrayList<Integer> s2;
    private ArrayList<Integer> cores2;
    Random gerador = new Random();
    private double resultado=0;
    private double desv=0;

    public Coloração(Graph g) {
        this.g = g;
        this.s = new ArrayList<>();
        this.cores = new ArrayList<>();
        this.corespossiveis = new ArrayList<>();
    }

    public ArrayList<Integer> CoresUsadas(ArrayList<Integer> s) {
        cores = new ArrayList<>();
        for (int i = 0; i < s.size(); i++) {
            if (!cores.contains(s.get(i))) {
                cores.add(s.get(i));
            }
        }
        return cores;
    }

    public void Greedycol(Graph g) {
        s = new ArrayList<>();
        for (int i = 0; i < g.vertices.size(); i++) {
            s.add(i);
            cores.add(i);
        }
        for (int i = 0; i < g.vertices.size(); i++) {
            corespossiveis = new ArrayList<>();
            corespossiveis.addAll(cores);
            for (int j = 0; j < cores.size(); j++) {
                for (int k = 0; k < g.adjList.get(i).size(); k++) {
                    if (s.get(g.adjList.get(i).get(k).sink) == j && corespossiveis.contains(j)) {
                        corespossiveis.remove((Integer) j);
                    }
                }
                s.set(i, corespossiveis.get(0));
            }
        }
        System.out.println("GreedyCol: " + s.toString());
        Recol(s, g);
    }

    public void Recol(ArrayList<Integer> s, Graph g) {
        cores = new ArrayList<>();
        cores.addAll(CoresUsadas(s));
        int rand, u=0, c=0;
        s2 = new ArrayList<>();
        cores2 = new ArrayList<>();
        long time = System.currentTimeMillis();
        while (System.currentTimeMillis() - time <= 100000) {
            rand = gerador.nextInt(g.vertices.size());
            u = g.vertices.get(rand);
            rand = gerador.nextInt(cores.size() - 1);
            c = cores.get(rand);
            s2 = new ArrayList<>();
            s2.addAll(s);
            s2.set(u, c);
            if (Verifica(u, c, s)) {
                cores2 = new ArrayList<>();
                cores2.addAll(CoresUsadas(s2));
                if (cores2.size() <= cores.size()) {
                    s = new ArrayList<>();
                    s.addAll(s2);
                    cores = new ArrayList<>();
                    cores.addAll(cores2);
                }
            }
        }
        System.out.println("ReCol: " + s.toString());
        ProcedimentoFinal(s);
    }

    public boolean Verifica(int u, int c, ArrayList<Integer> s) {
        for (int i = 0; i < g.adjList.get(u).size(); i++) {
            if (s.get(g.adjList.get(u).get(i).sink) == c) {
                return false;
            }
        }
        return true;
    }

    public void ProcedimentoFinal(ArrayList<Integer> s) {
        System.out.println("Cores Usadas: " + cores.toString() + "\n" + "Qtd de cores: " + cores.size());
        qtdcores.add(cores.size());
        System.out.println("Vetor qtdcores: " + qtdcores);

        System.out.println();
    }

    public void Media() {
        for (int i = 0; i < qtdcores.size(); i++) {
            resultado = resultado + qtdcores.get(i);
        }
        resultado = resultado / qtdcores.size();
        System.out.println("Media: " + resultado);
    }

    public void DesvioPadrao() {
        for (int i =0; i<qtdcores.size();i++) {
            desv = desv + Math.pow((qtdcores.get(i) - resultado), 2);
        }
        desv = desv/(qtdcores.size()-1);
        System.out.println("Desvio padrão: "+desv);
    }
    
    public void Clear(){
        s = new ArrayList<>();
        corespossiveis = new ArrayList<>();
        cores = new ArrayList<>();
        s2 = new ArrayList<>();
        cores2 = new ArrayList<>();
        resultado = 0;
        desv = 0;
    }
}
