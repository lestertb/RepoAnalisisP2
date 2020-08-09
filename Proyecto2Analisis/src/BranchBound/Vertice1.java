//Package
package BranchBound;

/**
 *
 * @author Alejandro Acuña
 */
public class Vertice1 {
 //Variables públicas necesarias para la creación y recorrido del vertice
 public   int num;
 public   Vertice1 sigV;
 public   boolean visitado;
 public   Arco1 subListaArcos;
 
    //Instancia de la clase para ser utilizada en todo el proyecto
    public Vertice1(int num) {
        this.num = num;
        this.sigV = null;
        this.visitado = false;
    }
}
