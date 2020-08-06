/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

/**
 *
 * @author Lester Trejos
 */
public class main {

 //Depende de la entrada va a dar false si es menor y true si es mayor
    private static int numeros[] = { 1, 1, 1, 1, 1, 1, 1, 0, 1, 1 };
    
    
   // private static int numeros[] = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
    
    public static void main(String[] args) {
       System.out.println( "¿Es mayor:?: "+ busquedaLinealProbabilistica(numeros));
    }
    
    static boolean busquedaLinealProbabilistica(int[] arreglo) {
        int suma = 0;
        for (int i = 0 ; i < arreglo.length ; i++) {
            suma += arreglo[i];
        }   
        
        if (suma > arreglo.length  ) {
            
            return true;
            
        }else
            return false;
    }
}