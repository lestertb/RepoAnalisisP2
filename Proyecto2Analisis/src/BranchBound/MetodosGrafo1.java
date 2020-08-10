//package
package BranchBound;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Alejandro Acuña
 */
public class MetodosGrafo1 {

 public static  Vertice1 grafo;
 
 public static double memoriaGenetico;
 public static double memoriaBacktraking;
 public static double memoriaRamiyPoda;
 
  //Genetico
    public static int comparacionesGenetico;
    public static int asignacionesGenetico;
    public static int LineasGenetico;
     public static double FinGenetico = 0;
    public static double InicioGenetico = 0;
    public static double tiempoGenetico = 0;
    
    //RamiyPoda
     public static int comparacionesRamiyPoda;
    public static int asignacionesRamiyPoda;
    public static int LineasRamiyPoda;
     public static double FinRamiyPoda = 0;
    public static double InicioRamiyPoda = 0;
    public static double tiempoRamiyPoda = 0;
    
     //Backtraking
    public static int comparacionesBack;
    public static int asignacionesBack;
    public static int LineasBack;
     public static double FinBack = 0;
    public static double InicioBack = 0;
    public static double tiempoBack = 0;

 public static MetodosGrafo1 instance = null;
    public static MetodosGrafo1 getInstance(){
            if(instance == null){
                instance = new MetodosGrafo1();
            }
            return instance;
        }
    static  ArrayList<ArrayList> Generaciones = new ArrayList<>();
    static  ArrayList<Integer> back = new ArrayList<>();
    static  ArrayList<String> rami = new ArrayList<>();
    static  ArrayList<String> podas = new ArrayList<>();
    public MetodosGrafo1() {
        this.grafo = null;
    }

    public Vertice1 buscar(int nombre) {
        //comprueba si el inicio es null
        if (grafo == null) {
            return null;
        }
        Vertice1 temp = grafo;
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
        Vertice1 nuevo = new Vertice1(num);
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
        Vertice1 g = grafo;
        while (g != null) {
            System.out.println("-----Vertice: " + g.num);
            g = g.sigV;
        }
        System.out.println("-----Fin");
          
    };
    
    
    public void insertarArco(int origen, int destino, int peso) {
        Vertice1 vOrigen = buscar(origen);
        Vertice1 vDestino = buscar(destino);

        Arco1 nnArco = new Arco1(vDestino, peso);
        nnArco.sigA = vOrigen.subListaArcos;
        vOrigen.subListaArcos = nnArco;
    }
    
    
    public String fuerteConexo(){
        if (grafo != null) {
            Vertice1 aux = grafo;
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
    
    
    public int gradoExterno(Vertice1 vertice){
        int cont = 0;
        if(vertice.subListaArcos.sigA!=null){
            Arco1 aux = vertice.subListaArcos;
            
          while(aux!=null){         
            cont++;
            
            System.out.println(aux.destino.num);
            aux = aux.sigA;
          }
        }
        return cont;
    }

    
    
    
    
    ArrayList<Vertice1> genetico(Vertice1 grafo, int cant,int poblacion){      
        ArrayList<Vertice1> Generacion = new ArrayList<>();
         asignacionesGenetico++;
         LineasGenetico++;
        Generacion.add(grafo);
        for (int i = 0; i < poblacion-2; i++) {
            comparacionesGenetico++;
            LineasGenetico++;
            int num = (int) (Math.random() * cant-1) + 1;
            memoriaGenetico+=32;
            asignacionesGenetico++;
            LineasGenetico++;
            Vertice1 n = buscar(num);
            asignacionesGenetico++;
            LineasGenetico++;
            boolean k= true;
            memoriaGenetico+=8;
            asignacionesGenetico++;
            LineasGenetico++;
            while(k==true)
            {
            comparacionesGenetico++;
            LineasGenetico++;
                num = (int) (Math.random() * cant-1) + 1;
                asignacionesGenetico++;
                LineasGenetico++;
                n = buscar(num);
                asignacionesGenetico++;
                LineasGenetico++;
                k=false;
                asignacionesGenetico++;
                LineasGenetico++;
                for (Vertice1 x: Generacion)
                {
                    comparacionesGenetico++;
                    LineasGenetico++;
                    if ((x.num==num)|(num==(cant-1))) {
                        comparacionesGenetico++;
                        LineasGenetico++;
                        k=true;
                        asignacionesGenetico++;
                        LineasGenetico++;
                    }
                }  
            }
            Generacion.add(n);
            LineasGenetico++;
        }
        Generacion.add(buscar(cant-1));
        LineasGenetico++;
        //System.out.println(Generacion.size());
        for(Vertice1 t1 :Generacion)
        {
            comparacionesGenetico++;
            LineasGenetico++;
            System.out.print(t1.num+" - ");
            
            
        }
        System.out.println("------------------------");

        return Generacion;
     }
    
    public void cruceGenetico(Vertice1 grafo, int cant,int poblacion){ 
        int max=0;
        memoriaGenetico+=32;
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1> padre = genetico(grafo, cant,poblacion);
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1> madre = genetico(grafo, cant,poblacion);
        asignacionesGenetico++;
        LineasGenetico++;
        
        Map<Integer, ArrayList<Vertice1> > lists = apareo(padre, madre,cant);
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1>  l1 = lists.get(1);
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1>  l2 = lists.get(2);
        asignacionesGenetico++;
        LineasGenetico++;

        Map<Integer, ArrayList<Vertice1> > lists123 = apareo(l1, l2,cant);
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1>  l3 = lists123.get(1);
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1>  l4 = lists123.get(2);
        asignacionesGenetico++;
        LineasGenetico++;
        Map<Integer, ArrayList<Vertice1> > lists2 = apareo(l3,l4 ,cant);
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1>  l5 = lists2.get(1);
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1>  l6 = lists2.get(2);
        asignacionesGenetico++;
        LineasGenetico++;
        
        Map<Integer, ArrayList<Vertice1> > lists3 = apareo(l5, l6,cant);
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1>  l7 = lists3.get(1);
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1>  l8 = lists3.get(2);
        asignacionesGenetico++;
        LineasGenetico++;
        
        Map<Integer, ArrayList<Vertice1> > lists4 = apareo(l7, l8,cant);
        asignacionesGenetico++;
        LineasGenetico++;
        System.out.println("");
        System.out.println("Asignaciones"+" "+ asignacionesGenetico);
        System.out.println("Comparaciones"+" "+ comparacionesGenetico);
        System.out.println("Lineas Genetico"+" "+ LineasGenetico);
        System.out.println("Memoria"+" "+ memoriaGenetico);
        
    }
    
    public Map<Integer, ArrayList<Vertice1>> apareo(ArrayList<Vertice1> padre, ArrayList<Vertice1> madre,int cant){
        Map<Integer, ArrayList<Vertice1> > listMap = new HashMap<Integer, ArrayList<Vertice1> >();
        asignacionesGenetico++;
        LineasGenetico++;
        
        int co = sacarComun(padre,madre,cant);
        memoriaGenetico+=32;
        asignacionesGenetico++;
        LineasGenetico++;
        ArrayList<Vertice1> reg = new ArrayList<>();
        asignacionesGenetico++;
        LineasGenetico++;
        for(Vertice1 t1 :padre){
            comparacionesGenetico++;
            LineasGenetico++;
            if(!reg.contains(t1)){
                comparacionesGenetico++;
                LineasGenetico++;
                    reg.add(t1);
                    LineasGenetico++;
               }
            

            if(padre.indexOf(t1)==co)
            {
                comparacionesGenetico++;
                LineasGenetico++;
                break;
            }
        }
        for(Vertice1 t2 :madre){
            comparacionesGenetico++;
            LineasGenetico++;
            if(madre.indexOf(t2)>co)
            {
                comparacionesGenetico++;
                LineasGenetico++;
                if(!reg.contains(t2)){
                    comparacionesGenetico++;
                    LineasGenetico++;
                    reg.add(t2);
                    LineasGenetico++;
               }
            
            }
        }
        
        ArrayList<Vertice1> reg2 = new ArrayList<>();
        asignacionesGenetico++;
        LineasGenetico++;
        for(Vertice1 t3 :madre){
            comparacionesGenetico++;
            LineasGenetico++;
            if(!reg2.contains(t3)){
                comparacionesGenetico++;
                LineasGenetico++;
                    reg2.add(t3);
                     LineasGenetico++;
               }
            
            if(madre.indexOf(t3)==co){
                comparacionesGenetico++;
                LineasGenetico++;
                break;
            }
        }
        for(Vertice1 t4 :padre){
            comparacionesGenetico++;
                LineasGenetico++;
            if(padre.indexOf(t4)>co)
            {
                comparacionesGenetico++;
                LineasGenetico++;
                if(!reg2.contains(t4)){
                comparacionesGenetico++;
                LineasGenetico++;
                    reg2.add(t4);
                    LineasGenetico++;
               }
            
            }
        }
        System.out.println("--------chamaco 1");
        for(Vertice1 t :reg){
            comparacionesGenetico++;
            LineasGenetico++;
            System.out.print(t.num+"-");
        }
        System.out.println("\n--------chamaco 2");
        for(Vertice1 t1 :reg2){
            comparacionesGenetico++;
            LineasGenetico++;
            System.out.print(t1.num+"-");
        }
        LineasGenetico++;
        Generaciones.add(reg);
        LineasGenetico++;
        Generaciones.add(reg2);
        
        listMap.put(1, reg);
        LineasGenetico++;
        listMap.put(2, reg2);
        LineasGenetico++;
        return listMap;

    }
    
    
    int sacarComun(ArrayList<Vertice1> padre, ArrayList<Vertice1> madre,int cant){ 
        for(Vertice1 pa :padre){
            comparacionesGenetico++;
            LineasGenetico++;
            for(Vertice1 ma :madre){
                comparacionesGenetico++;
                LineasGenetico++;
                if((pa.equals(ma))&(pa.num!=0)&(pa.num!=cant-1)){
                    comparacionesGenetico++;
                    LineasGenetico++;
                    System.out.println("\nnumero de la suerte: "+pa.num);
                    return padre.indexOf(pa);
                }
            }
        }
        System.out.println("no se repiten");
        return 0;
    }
    
    
    boolean existe = false;
    void existeRuta(Vertice1 origen, Vertice1 destino) {

        if ((origen == null) || (origen.visitado == true)) {
            return;
        }
        if (origen == destino) {
            existe = true;
            return;
        }
        origen.visitado = true;
        Arco1 a = origen.subListaArcos;
        while (a != null) {
            System.out.println(a.size+""+destino.num+origen.num);
            existeRuta(a.destino, destino);
            a = a.sigA;
        }

    }
    
    
    
    String rutaMenor = "";
    int menorValor = 0;
    public void backtracking(Vertice1 origen, Vertice1 destino, String ruta, int dist) {
        memoriaBacktraking+=32;
        comparacionesBack++;
        LineasBack++;
        if ((origen == null) || (origen.visitado == true)) {
            LineasBack++;
            return;
        }
        if (origen == destino) {
            comparacionesBack++;
            LineasRamiyPoda++;
            System.out.println("Ruta: " + ruta + destino.num);
            System.out.println("Con una distancia de: " + dist);

            if (("".equals(rutaMenor)) || (menorValor > dist)) {
                comparacionesBack++;
                LineasBack++;
                rutaMenor = ruta + destino.num;
                
                asignacionesBack++;
                LineasBack++;
                menorValor = dist;
                memoriaBacktraking+= 32;
                 asignacionesBack++;
                LineasBack++;
            }
            existe = true;
            memoriaBacktraking+= 8;
            asignacionesBack++;
            LineasBack++;
            return;
        }
        origen.visitado = true;
        memoriaBacktraking+= 8;
        asignacionesBack++;
        LineasBack++;
        Arco1 a = origen.subListaArcos;
        asignacionesBack++;
        LineasBack++;
        while (a != null) {
            LineasBack++;
            comparacionesBack++;
            asignacionesBack++;
            backtracking(a.destino, destino, (ruta + (origen.num)).toString(), dist + a.size);
            a = a.sigA;
            LineasBack++;
            asignacionesBack++;
        }
        origen.visitado = false;
        memoriaBacktraking+= 8;
        LineasBack++;
        asignacionesBack++;
    }
    public void var() throws UnsupportedEncodingException
    {
        System.out.println(rutaMenor);
        byte[] b = rutaMenor.getBytes("UTF-8");
        System.out.println(b.length+"Hola");
        System.out.println(menorValor);
        System.out.println("");
        System.out.println("Asignaciones"+" "+ asignacionesBack);
        System.out.println("Comparaciones"+" "+ comparacionesBack);
        System.out.println("Lineas backtracking"+" "+ LineasBack);
        System.out.println("Memoria backtracking"+" "+ memoriaBacktraking);
    }
    
    
    String rutaMenor2 = "";
    int menorValor2 = 0;
    int max =0;
    public void ramiPoda(Vertice1 origen, Vertice1 destino, String ruta, int dist) {
        memoriaRamiyPoda+= 32;
        memoriaRamiyPoda+= 32;
        comparacionesRamiyPoda++;
        LineasRamiyPoda++;
        if ((origen == null) || (origen.visitado == true)) {
            LineasRamiyPoda++;
            return;
        }
        
        if (origen == destino) {
            comparacionesRamiyPoda++;
            LineasRamiyPoda++;
            System.out.println("Ruta: " + ruta + destino.num);
            System.out.println("Con una distancia de: " + dist);

            if (("".equals(rutaMenor2)) || (menorValor2 > dist)) {
                comparacionesRamiyPoda++;
                LineasRamiyPoda++;
                rutaMenor2 = ruta + destino.num;
                
                asignacionesRamiyPoda++;
                LineasRamiyPoda++;
                menorValor2 = dist;
                memoriaRamiyPoda+= 32;
                asignacionesRamiyPoda++;
                LineasRamiyPoda++;
            }
            //existe = true;
            return;
        } 
        if(dist > menorValor2){
            comparacionesRamiyPoda++;
            LineasRamiyPoda++;
            if(max<5){
                LineasRamiyPoda++;
                comparacionesRamiyPoda++;
                for(String s :podas){
                    LineasRamiyPoda++;
                    comparacionesRamiyPoda++;
                    if(s.equals(ruta)){
                        LineasRamiyPoda++;
                        comparacionesRamiyPoda++;
                        return;
                    }
                }
                System.out.println(podas);
                podas.add(ruta);
                LineasRamiyPoda++;
                max++;
            }
            return;
        }
        asignacionesRamiyPoda++;
        LineasRamiyPoda++;
        origen.visitado = true;
        memoriaRamiyPoda+= 8;
         asignacionesRamiyPoda++;
        LineasRamiyPoda++;
        Arco1 a = origen.subListaArcos;
         asignacionesRamiyPoda++;
        LineasRamiyPoda++;
        while (a != null) {
             comparacionesRamiyPoda++;
             LineasRamiyPoda++;
             asignacionesRamiyPoda++;
            ramiPoda(a.destino, destino, (ruta + (origen.num)).toString(), dist + a.size);
            LineasRamiyPoda++;
             asignacionesRamiyPoda++;
            a = a.sigA;
        }
        LineasRamiyPoda++;
        asignacionesRamiyPoda++;
        origen.visitado = false;
        memoriaRamiyPoda+= 8;
    }
    
    public void var2() throws UnsupportedEncodingException
    {
        
        System.out.println(rutaMenor2);
         byte[] b = rutaMenor2.getBytes("UTF-8");
        System.out.println(b.length+"Hola");
        System.out.println(menorValor2);
        System.out.println(podas);
        System.out.println("Asignaciones"+" "+ asignacionesRamiyPoda);
        System.out.println("Comparaciones"+" "+ comparacionesRamiyPoda);
        System.out.println("Lineas Ramificacion y Poda"+" "+ LineasRamiyPoda);
        System.out.println("Memoria"+ " " + memoriaRamiyPoda);
        
    }
    

}


