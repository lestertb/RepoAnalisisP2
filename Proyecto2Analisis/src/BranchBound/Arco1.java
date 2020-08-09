//package
package BranchBound;
/**
 *
 * @author Alejandro Acuña
 */
public class Arco1 {
   //Variables públicas necesarias para la creación y recorrido del arco en los 
   //vertices y su forma fuertemente conexa
   public Vertice1 destino;
   public Vertice1 origen;
   public Arco1 sigA;
   public int size;

   //Instancia de la clase para ser utilizada en todo el proyecto
    public Arco1(Vertice1 destino, int size) {
        this.destino = destino;
        this.sigA = null;
        this.size = size;
    }
}
