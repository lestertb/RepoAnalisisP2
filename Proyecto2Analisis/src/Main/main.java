/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import BranchBound.MetodosGrafo;
import Grafo.*;
import java.util.*;

/**
 *
 * @author Lester Trejos
 */
public class main {
    
   static GraphWeighted graphWeighted = new GraphWeighted(true);

   static MetodosGrafo mg = new MetodosGrafo();
   
   static  ArrayList<NodeWeighted> test = new ArrayList<>();
    
    public static void main(String[] args) {

        
        mg.insertAutomatico(1000);
        mg.genetico(mg.buscar(0), 1000,0);
        mg.backtracking(mg.buscar(0));
        
   }
    
    
    
    

        
        



    
    
    
    
    
    
  
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    public static void crearGrafo(int n){
        
        for (int i = 1; i <= n; i++) {
           NodeWeighted vt = new NodeWeighted(i);
           test.add(vt);     
        }
        
        for (int i = 0; i < n; i++) {//orgien
            for (int j = 1; j < n; j++) {//destino
                if (i==n-1) {
                    break;
                }
                //System.out.println("Orgien:"+i);
                //System.out.println("destino:"+ j);
                //le inserta un peso random al arco
                int num = (int) (Math.random() * 99) + 1;
                //inserta del vertice A al vertice B con el peso y así con el resto hasta ser conexo total
                //System.out.println("Peso:"+num);
                //System.out.println("---------------");
                graphWeighted.addEdge(test.get(i), test.get(j), num);
            }

        }
    
    }
    
    
}
