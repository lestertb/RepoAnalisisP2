/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.*;

/**
 *
 * @author Lester Trejos
 */
public class Vertice {
    
    //Globales
    public int id;//Indentificador
    public boolean visited;// se visitó?
    public LinkedList<Arco> arcos;
    //Constructor
    public Vertice(int n) {
        this.id = n;
        visited = false;
        arcos = new LinkedList<>();
    }

    boolean isVisited() {
        return visited;
    }

    void visit() {
        visited = true;
    }

    void unvisit() {
        visited = false;
    }
    
    class subset 
    { 
        int parent, rank; 
    }; 
  
    
}
