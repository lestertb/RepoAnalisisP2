/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Grafo.MetodosGrafo;

/**
 *
 * @author Lester Trejos
 */
public class main {
    
    static MetodosGrafo mg = new MetodosGrafo();
    public static void main(String[] args) {
        
        medicionGrafo();    
        
        
    }
    
    static void medicionGrafo() {//medicion de los datos del grafos
        System.out.println("------------Grafo--------------");
         //Datos de entrada
        mg.insertAutomatico(5); 
    }
    
}
