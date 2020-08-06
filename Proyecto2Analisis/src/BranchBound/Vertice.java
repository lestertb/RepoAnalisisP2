//Package
package BranchBound;

/**
 *
 * @author Alejandro Acuña
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
