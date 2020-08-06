//package
package BranchBound;

import java.util.ArrayList;

/**
 *
 * @author Alejandro Acuña
 */
public class MetodosGrafo {

 public static  Vertice grafo;

 public static MetodosGrafo instance = null;
    public static MetodosGrafo getInstance(){
            if(instance == null){
                instance = new MetodosGrafo();
            }
            return instance;
        }
    static  ArrayList<Integer> hechos = new ArrayList<>();
    public MetodosGrafo() {
        this.grafo = null;
    }

    public Vertice buscar(int nombre) {
        //comprueba si el inicio es null
        if (grafo == null) {
            return null;
        }
        Vertice temp = grafo;
        //se mueve entre vertices hasta que sea null
        while (temp != null) {
            //si encuentra el vertice lo retorna
            if (temp.num == nombre) {
                return temp;
            }
            //se mueve al siquiente vertice
            temp = temp.sigV;
        }
        System.out.println("-----Vertice NO encontrado");
        return null;
    }
    
    void insertarVertices(int num) {
        //crea un nuevo vertice para insertarlo
        Vertice nuevo = new Vertice(num);
        //confirma si es el primero
        if (grafo == null) {
            grafo = nuevo;
            return;
        }
        nuevo.sigV = grafo;
        grafo = nuevo;
    }
    

    
    
    public void insertAutomatico(int n){
        for (int i = 0; i < n; i++) {
            insertarVertices(i);      
        }
        for (int i = 0; i < n; i++) {//orgien
            for (int j = 1; j < n; j++) {//destino
                if (i == n-1) {
                    break;
                }
                if (i != j) {
                int num = (int) (Math.random() * 99) + 1;
                insertarArco(i, j, num);
                    
                }
            }

        }
    }
      
    public void print(){
        System.out.println("Iniciando impresión...");
        Vertice g = grafo;
        while (g != null) {
            System.out.println("-----Vertice: " + g.num);
            g = g.sigV;
        }
        System.out.println("-----Fin");
          
    };
    
    public void insertarArco(int origen, int destino, int peso) {
        Vertice vOrigen = buscar(origen);
        Vertice vDestino = buscar(destino);

        Arco nnArco = new Arco(vDestino, peso);
        nnArco.sigA = vOrigen.subListaArcos;
        vOrigen.subListaArcos = nnArco;
    }
    
    public String fuerteConexo(){
        if (grafo != null) {
            Vertice aux = grafo;
            int cantV = 0;
            while (aux != null) {
                cantV++;
                aux = aux.sigV;
            }
            aux = grafo;
            while (aux != null) {
                if (gradoExterno(aux) != cantV-1) {
                    return "No es fuertemente Conexo";
                }
                aux = aux.sigV;
            }
            return "Si es fuertemente Conexo";
        }
        return "No se encontró un grafo";  
    }
    
    public int gradoExterno(Vertice vertice){
        int cont = 0;
        if(vertice.subListaArcos.sigA!=null){
            Arco aux = vertice.subListaArcos.sigA;
          while(aux!=null){         
            cont++;
            aux = aux.sigA;
          }
        }
        return cont;
    }

    public void genetico(Vertice grafo, int cant,int r)
    {
        //System.out.println(grafo.num);
        if ((grafo == null) | (grafo.visitado == true)|(grafo.num==cant-1)) {//condiciones de parada
            return;
        }
        else {
            int num = (int) (Math.random() * cant-1) + 1;
            Vertice n = buscar(num);
            boolean k=true;
            while(k==true)
            {
                num = (int) (Math.random() * cant-1) + 1;
                n = buscar(num);
                k=false;
                
                for (int x: hechos)
                {
                    if (x==num) {
                        k=true;
                    }
                } 
            }
            hechos.add(num);
            r=r+1;
            genetico(n, cant,r);
            System.out.println(r);
            }
     }
    
    
    public void backtracking(Vertice grafo)
    {
        if ((grafo == null) | (grafo.visitado == true)) {

            return;
        } else {
            grafo.visitado = true;
            System.out.println("Vertice:" + grafo.num);
            Arco aux = grafo.subListaArcos;
            while (aux != null) {
                backtracking(aux.destino);

                aux = aux.sigA;
            }
        }
    }
    
    
    }



    
    
    
    

