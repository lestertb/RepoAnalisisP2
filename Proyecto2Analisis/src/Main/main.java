/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Grafo.*;
import BranchBound.*;
import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *
 * @author Lester Trejos
 */
public class main {
    
   //Variables Globales 
    
   //Clase con los métodos del grafo
   static MetodosGrafo1 mg1 = new MetodosGrafo1();
   static MetodosGrafo mg = new MetodosGrafo(true);
   
   //Array list para los nodos del grafo
   static  ArrayList<Vertice> Vertices = new ArrayList<>();
    //Método main
    public static void main(String[] args) throws UnsupportedEncodingException {
        //Tamaño del grafo
        int tamaño = 10;
        crearGrafo(tamaño);
        mg1.insertAutomatico(tamaño);
        
        //Voraz
        System.out.println("------------------Voraz------------------"); 
        MetodosGrafo.InicioVoraz = System.currentTimeMillis();//Tiempo inicio del algoritmo
        mg.voraz(Vertices.get(0),Vertices.get(tamaño-1),tamaño);
          MetodosGrafo.FinVoraz = System.currentTimeMillis();//Tiempo fin del algoritmo
        MetodosGrafo.tiempoVoraz = ((MetodosGrafo.FinVoraz - MetodosGrafo.InicioVoraz) / 1000);//Total tiempo
        System.out.println("Cantidad de tiempo ejecucion en milisegundos:" + " " + MetodosGrafo.tiempoVoraz);
        
        //Aplicar el algoritmo Dijkstra
        //System.out.println("------------------Programacion Dinamica------------------");
        MetodosGrafo.InicioDinamica = System.currentTimeMillis();//Tiempo inicio del algoritmo
        mg.DijkstraShortestPath(Vertices.get(0), Vertices.get(tamaño-1));
         MetodosGrafo.FinDinamica = System.currentTimeMillis();//Tiempo fin del algoritmo
        MetodosGrafo.tiempoDinamica = ((MetodosGrafo.FinDinamica - MetodosGrafo.InicioDinamica) / 1000);//Total tiempo
        System.out.println("Cantidad de tiempo ejecucion en milisegundos:" + " " + MetodosGrafo.tiempoDinamica);
        mg.resetVerticesVisited();
        
        System.out.println("------------------Genetico------------------");
        
        //Aplicar el algoritmo genetico
       MetodosGrafo1.InicioGenetico = System.currentTimeMillis();//Tiempo inicio del algoritmo
        int poblacion = (int) (Math.random() * tamaño-1) + 1;

        mg1.cruceGenetico(mg1.buscar(0), tamaño,poblacion);
         MetodosGrafo1.FinGenetico = System.currentTimeMillis();//Tiempo fin del algoritmo
        MetodosGrafo1.tiempoGenetico = ((MetodosGrafo1.FinGenetico - MetodosGrafo1.InicioGenetico) / 1000);//Total tiempo
        System.out.println("Cantidad de tiempo ejecucion en milisegundos:" + " " + MetodosGrafo1.tiempoGenetico);
        mg.resetVerticesVisited();
        
        System.out.println("\n------------------BackTracking------------------");
        
        //Aplicar el algoritmo BackTracking
        
          MetodosGrafo1.InicioBack = System.currentTimeMillis();//Tiempo inicio del algoritmo
        mg1.backtracking(mg1.buscar(0), mg1.buscar(tamaño-1), "", 0);
        mg1.var();
        MetodosGrafo1.FinBack = System.currentTimeMillis();//Tiempo fin del algoritmo
        MetodosGrafo1.tiempoBack = ((MetodosGrafo1.FinBack - MetodosGrafo1.InicioBack) / 1000);//Total tiempo
        System.out.println("Cantidad de tiempo ejecucion en milisegundos:" + " " + MetodosGrafo1.tiempoBack);

        
        System.out.println("------------------Ramificación y Poda------------------");
        
          MetodosGrafo1.InicioRamiyPoda = System.currentTimeMillis();//Tiempo inicio del algoritmo
        //Aplicar el algoritmo Ramificación y Poda
        
        mg1.ramiPoda(mg1.buscar(0), mg1.buscar(tamaño-1), "", 0);
        mg1.var2();
         MetodosGrafo1.FinRamiyPoda = System.currentTimeMillis();//Tiempo fin del algoritmo
         MetodosGrafo1.tiempoRamiyPoda = ((MetodosGrafo1.FinRamiyPoda - MetodosGrafo1.InicioRamiyPoda) / 1000);//Total tiempo
         System.out.println("Cantidad de tiempo ejecucion en milisegundos:" + " " + MetodosGrafo1.tiempoRamiyPoda);
        
    }
    //Método que crea el grafo conexo con la unión de sus respectivos arcos
    public static void crearGrafo(int n){
        //Crea los vertices y de le asigna un valor
        for (int i = 0; i < n; i++) {
           Vertice vt = new Vertice(i);
           Vertices.add(vt);     
        }
        //Se conectan los arcos (origen, destino)
        for (int i = 0; i < n; i++) {//orgien
            for (int j = 1; j < n; j++) {//destino
                if (i == n-1) {
                    break;
                }
                if (i != j) {//Inserta sin ciclos
                //System.out.println("Orgien:"+i);
                //System.out.println("destino:"+ j);
                //le inserta un peso random al arco
                int num = (int) (Math.random() * 99) + 1;
                //inserta del vertice A al vertice B con el peso y así con el resto hasta ser conexo total
                //System.out.println("Peso:"+num);
                //System.out.println("---------------");
                mg.agregarArco(Vertices.get(i), Vertices.get(j), num);
                }
            }

        }
        
    }
    
    
}
