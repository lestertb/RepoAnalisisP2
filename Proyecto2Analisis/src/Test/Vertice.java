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
public class Vertice {
    
     //Variables públicas necesarias para la creación y recorrido del vertice
     public   int num;
     public   Vertice sigV;
     public   boolean visitado;
     public   Arco subListaArcos;
 
    //Instancia de la clase para ser utilizada en todo el proyecto
    public Vertice(int num) {
        this.num = num;
        this.sigV = null;
        this.visitado = false;
    }
    
}
