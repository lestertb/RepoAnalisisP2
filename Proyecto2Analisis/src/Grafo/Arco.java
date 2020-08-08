/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author Lester Trejos
 */
public class Arco implements Comparable<Arco> {
    //Globals
    public Vertice origen;
    public Vertice destino;
    public double peso;
    
    //Constructor
    public Arco(Vertice orig, Vertice desti, double pes) {
            origen = orig;
            destino = desti;
            peso = pes;
        }
        public String toString() {
        return String.format("(%s -> %s, %f)", origen.id, destino.id, peso);
    }
    //Comparar arcos
    public int compareTo(Arco otherEdge) {

        if (this.peso > otherEdge.peso) {
            return 1;
        }
        else return -1;
    }
    
}

