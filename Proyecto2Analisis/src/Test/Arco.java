/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

/**
 *
 * @author Lester Trejos
 */
public class Arco {
    
    //Variables públicas necesarias para la creación y recorrido del arco en los 
   //vertices y su forma fuertemente conexa
   public Vertice destino;
   public Vertice origen;
   public Arco sigA;
   public int size;

   //Instancia de la clase para ser utilizada en todo el proyecto
    public Arco(Vertice destino, int size) {
        this.destino = destino;
        this.sigA = null;
        this.size = size;
    }
    
}
