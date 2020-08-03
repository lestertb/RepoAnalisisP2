/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

/**
 *
 * @author Lester Trejos
 */
public class MetodosGrafo {
    
    //globales
 //vertice raiz
 public static  Vertice grafo;
 //Instancia para que pueda ser usado en todo el proyecto
 public static MetodosGrafo instance = null;
        public static MetodosGrafo getInstance(){
            if(instance == null){
                instance = new MetodosGrafo();
            }
            return instance;
        }
    public MetodosGrafo() {
        this.grafo = null;
    }
    
    
    //comprueba y retorna el vertice buscado
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
    
    //metodo utilizado por el insertar automatico para insertar vertices
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
    
    //inserta grafos dada una cantidad maxima
    public void insertAutomatico(int cant) {
        //crea una cantidad lineal de nodos 
        for (int i = 0; i < cant; i++) {
            insertarVertices(i);      
        }
        //recorre los vertices
        for (int i = 0; i < cant; i++) {//orgien
            //se mueve y conecta todos los vertices, con todos los otros por medio de los arcos
            for (int j = 1; j < cant; j++) {//destino
                if (i==cant-1) {
                    break;
                }
                System.out.println("Orgien:"+i);
                System.out.println("destino:"+ j);
                System.out.println("---------------");
                //le inserta un peso random al arco
                int num = (int) (Math.random() * 99) + 1;
                //inserta del vertice A al vertice B con el peso y así con el resto hasta ser conexo total
                insertarArco(i, j, num);
            }

        }
    }
      
    //imprime todos los vertices
    public void print()
    {
        System.out.println("Iniciando impresión...");
        Vertice g = grafo;
        while (g != null) {
            System.out.println("-----Vertice: " + g.num);
            g = g.sigV;
        }
        System.out.println("-----Fin");
          
    };
    
    //metodo que inserta el arco que seria utilizado por el insertar automatico
    public void insertarArco(int origen, int destino, int peso) {
        Vertice vOrigen = buscar(origen); //Se busca el origen
        Vertice vDestino = buscar(destino);//Se busca el destino

        Arco nnArco = new Arco(vDestino, peso); //se crea el arco
        nnArco.sigA = vOrigen.subListaArcos; //se mueve en la sublista de arcos
        vOrigen.subListaArcos = nnArco;  //Se enlaza
    }
    
    
    //comprueba si es fuertemente conexo o no
    //se utilizó para prubas
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
    
    //lo utiliza fuerteConexo como sub metodo
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
    
    //Ruta corta del vértice inicial (1) al vértice final (N),
    //donde la suma de los pesos de los arcos que conforman la
    //ruta sea la menor (algoritmo de optimización).
    
    
    
}
