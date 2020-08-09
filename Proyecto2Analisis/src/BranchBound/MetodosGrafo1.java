//package
package BranchBound;

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

    
    
    
    
    ArrayList<Vertice1> genetico(Vertice1 grafo, int cant,int poblacion)
    { 
        ArrayList<Vertice1> Generacion = new ArrayList<>();
        Generacion.add(grafo);
        for (int i = 0; i < poblacion-2; i++) {
            int num = (int) (Math.random() * cant-1) + 1;
            Vertice1 n = buscar(num);
            boolean k= true;
            while(k==true)
            {
                num = (int) (Math.random() * cant-1) + 1;
                n = buscar(num);
                k=false;
                for (Vertice1 x: Generacion)
                {
                    if ((x.num==num)|(num==(cant-1))) {
                        k=true;
                    }
                }  
            }
            Generacion.add(n);
        }
        Generacion.add(buscar(cant-1));
        //System.out.println(Generacion.size());
        for(Vertice1 t1 :Generacion)
        {
            System.out.print(t1.num+" - ");
            
            
        }
        System.out.println("------------------------");

        return Generacion;
     }
    
    public void cruceGenetico(Vertice1 grafo, int cant,int poblacion)
    { 
        int max=0;
        ArrayList<Vertice1> padre = genetico(grafo, cant,poblacion);
        ArrayList<Vertice1> madre = genetico(grafo, cant,poblacion);
        
        Map<Integer, ArrayList<Vertice1> > lists = apareo(padre, madre,cant);
        ArrayList<Vertice1>  l1 = lists.get(1);
        ArrayList<Vertice1>  l2 = lists.get(2);

        Map<Integer, ArrayList<Vertice1> > lists123 = apareo(l1, l2,cant);
        ArrayList<Vertice1>  l3 = lists123.get(1);
        ArrayList<Vertice1>  l4 = lists123.get(2);
        
        Map<Integer, ArrayList<Vertice1> > lists2 = apareo(l3,l4 ,cant);
        ArrayList<Vertice1>  l5 = lists2.get(1);
        ArrayList<Vertice1>  l6 = lists2.get(2);
        
        Map<Integer, ArrayList<Vertice1> > lists3 = apareo(l5, l6,cant);
        ArrayList<Vertice1>  l7 = lists3.get(1);
        ArrayList<Vertice1>  l8 = lists3.get(2);
        
        Map<Integer, ArrayList<Vertice1> > lists4 = apareo(l7, l8,cant);

        
    }
    
    public Map<Integer, ArrayList<Vertice1>> apareo(ArrayList<Vertice1> padre, ArrayList<Vertice1> madre,int cant)
    {
        Map<Integer, ArrayList<Vertice1> > listMap = new HashMap<Integer, ArrayList<Vertice1> >();
        
        int co = sacarComun(padre,madre,cant);
        ArrayList<Vertice1> reg = new ArrayList<>();
        for(Vertice1 t1 :padre){
            if(!reg.contains(t1)){
                    reg.add(t1);
               }
            

            if(padre.indexOf(t1)==co)
            {
                break;
            }
        }
        for(Vertice1 t2 :madre){
            
            if(madre.indexOf(t2)>co)
            {
                if(!reg.contains(t2)){
                    reg.add(t2);
               }
            
            }
        }
        
        ArrayList<Vertice1> reg2 = new ArrayList<>();
        for(Vertice1 t3 :madre){
            if(!reg2.contains(t3)){
                    reg2.add(t3);
               }
            
            if(madre.indexOf(t3)==co){
                break;
            }
        }
        for(Vertice1 t4 :padre){
            if(padre.indexOf(t4)>co)
            {
                if(!reg2.contains(t4)){
                    reg2.add(t4);
               }
            
            }
        }
        System.out.println("--------chamaco 1");
        for(Vertice1 t :reg){
            System.out.print(t.num+"-");
        }
        System.out.println("\n--------chamaco 2");
        for(Vertice1 t1 :reg2){
            System.out.print(t1.num+"-");
        }
        Generaciones.add(reg);
        Generaciones.add(reg2);
        
        listMap.put(1, reg);
        listMap.put(2, reg2);
        return listMap;

    }
    
    
    int sacarComun(ArrayList<Vertice1> padre, ArrayList<Vertice1> madre,int cant)
    { 
        for(Vertice1 pa :padre){
            for(Vertice1 ma :madre){
                if((pa.equals(ma))&(pa.num!=0)&(pa.num!=cant-1)){
                    
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
        if ((origen == null) || (origen.visitado == true)) {
            return;
        }
        if (origen == destino) {
            System.out.println("Ruta: " + ruta + destino.num);
            System.out.println("Con una distancia de: " + dist);

            if (("".equals(rutaMenor)) || (menorValor > dist)) {
                rutaMenor = ruta + destino.num;
                menorValor = dist;
            }
            existe = true;
            return;
        }
        origen.visitado = true;
        Arco1 a = origen.subListaArcos;
        while (a != null) {
            backtracking(a.destino, destino, (ruta + (origen.num)).toString(), dist + a.size);
            a = a.sigA;
        }
        origen.visitado = false;
    }
    public void var()
    {
        System.out.println(rutaMenor);
        System.out.println(menorValor);
    }
    
    
    String rutaMenor2 = "";
    int menorValor2 = 0;
    int max =0;
    public void ramiPoda(Vertice1 origen, Vertice1 destino, String ruta, int dist) {
        if ((origen == null) || (origen.visitado == true)) {
            return;
        }
        
        if (origen == destino) {
            System.out.println("Ruta: " + ruta + destino.num);
            System.out.println("Con una distancia de: " + dist);

            if (("".equals(rutaMenor2)) || (menorValor2 > dist)) {
                rutaMenor2 = ruta + destino.num;
                menorValor2 = dist;
            }
            //existe = true;
            return;
        } 
        if(dist > menorValor2){
            if(max<5){
                for(String s :podas){
                    if(s.equals(ruta)){
                        
                        return;
                    }
                }
                System.out.println(podas);
                podas.add(ruta);
                max++;
            }
            return;
        }
        origen.visitado = true;
        Arco1 a = origen.subListaArcos;
        while (a != null) {

            ramiPoda(a.destino, destino, (ruta + (origen.num)).toString(), dist + a.size);
            a = a.sigA;
        }
        origen.visitado = false;
    }
    
    public void var2()
    {
        
        System.out.println(rutaMenor2);
        System.out.println(menorValor2);
        System.out.println(podas);
        
    }
    

}


