//package
package BranchBound;
/**
 *
 * @author Alejandro Acuña
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
