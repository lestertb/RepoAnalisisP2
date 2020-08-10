/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.io.UnsupportedEncodingException;
import java.util.*;

/**
 *
 * @author Lester Trejos
 */
public class MetodosGrafo {

    //Globals
    public Set<Vertice> arcos;
    public boolean directed;
    public static double memoriaVoraz;
    public static double memoriaDinamico;
    //Voraz
    public static int comparacionesVoraz;
    public static int asignacionesVoraz;
    public static int LineasVoraz;
     public static double FinVoraz = 0;
    public static double InicioVoraz = 0;
    public static double tiempoVoraz = 0;
    
    //PrograDinamica
    public static int comparacionesDinamica;
    public static int asignacionesDinamica;
    public static int LineasDinamica;
    public static double FinDinamica = 0;
    public static double InicioDinamica = 0;
    public static double tiempoDinamica = 0;
    
  

    //Globals para el génetico
    public static ArrayList<Integer> hechos = new ArrayList<>();
    
    //Globals para el ramificación y poda
    public String rutaMenor2 = "";
    public int menorValor2 = 0;
    public int max =0;
    public ArrayList<String> podas = new ArrayList<>();

    //Global para el BackTracking
    public String rutaMenor = "";
    public int menorValor = 0;
    
    
    public MetodosGrafo(boolean directed) {
        this.directed = directed;
        arcos = new HashSet<>();
    }

    //Agregar vertices
    public void agregarVertice(Vertice... n) {

        arcos.addAll(Arrays.asList(n));
    }

    //Agregar Arco entre los arcos
    public void agregarArco(Vertice origen, Vertice destino, double peso) {

        arcos.add(origen);
        arcos.add(destino);

        agregarArcoHelper(origen, destino, peso);

        if (!directed && origen != destino) {
            agregarArcoHelper(destino, origen, peso);
        }
    }

    //Comprueba que los arcos estén completos
    private void agregarArcoHelper(Vertice a, Vertice b, double weight) {
        //Recorre los arcos
        for (Arco edge : a.arcos) {
            if (edge.origen == a && edge.destino == b) {
                edge.peso = weight;
                return;
            }
        }
        a.arcos.add(new Arco(a, b, weight));
    }

    //Imprime los Arcos
    public void printArcos() {
        for (Vertice vertice : arcos) {
            LinkedList<Arco> edges = vertice.arcos;

            if (edges.isEmpty()) {
                System.out.println("Node:" + vertice.id + " no tiene arco.");
                continue;
            }
            System.out.print("Node: " + vertice.id + " tiene arco con: ");

            for (Arco edge : edges) {
                System.out.print(edge.destino.id + "(" + edge.peso + ") ");
            }
            System.out.println();
        }
    }

    //Verifica si 2 arcos tienen arco (Conexo)
    public boolean tieneArco(Vertice origen, Vertice destino) {
        LinkedList<Arco> edges = origen.arcos;
        for (Arco edge : edges) {
            if (edge.destino == destino) {
                return true;
            }
        }
        return false;
    }

    //Resetea todos los arcos en no visitados
    public void resetVerticesVisited() {
        for (Vertice node : arcos) {
            node.unvisit();
        }
    }

    //Voraz
    public void voraz(Vertice origen, Vertice destino, int tamaño) {
        Stack rutaVertice = new Stack();
        LineasVoraz++;
        asignacionesVoraz++;
        rutaVertice.push(origen);
         LineasVoraz++;
        while (!rutaVertice.isEmpty()) {
            asignacionesVoraz++;
            comparacionesVoraz++;
            Vertice vertice = (Vertice) rutaVertice.pop();
            LineasVoraz++;
            asignacionesVoraz++;
            double pesoActual = Double.MAX_VALUE;
            memoriaVoraz+=64;
            LineasVoraz++;
            asignacionesVoraz++;
            Vertice aux = vertice;
            LineasVoraz++;
            asignacionesVoraz++;
            if (vertice != destino) {
                comparacionesVoraz++;
                if (!vertice.isVisited()) {
                    comparacionesVoraz++;
                    vertice.visited = true;
                    memoriaVoraz+=8;
                    LineasVoraz++;
                    asignacionesVoraz++;
                    for (Arco arco : vertice.arcos)
                    {
                        comparacionesVoraz++;
                        if (arco.peso < pesoActual) {
                            comparacionesVoraz++;
                            if (arco.destino.id > vertice.id) {
                                comparacionesVoraz++;
                                pesoActual = arco.peso;
                                LineasVoraz++;
                                asignacionesVoraz++;
                                aux = arco.destino;
                                LineasVoraz++;
                                asignacionesVoraz++;
                            }
                        }
                    }
                    rutaVertice.remove(vertice);
                    LineasVoraz++;
                    rutaVertice.push(aux);
                    LineasVoraz++;
                    System.out.println("Origen:" + vertice.id);
                    System.out.println("Destino:" + aux.id);
                }
            }
        }
        System.out.println("Asignaciones"+" "+ asignacionesVoraz);
        System.out.println("Comparaciones"+" "+ comparacionesVoraz);
        System.out.println("Lineas voraz"+" "+ LineasVoraz);
        System.out.println("Memoria en Voraz"+" "+ memoriaVoraz);

    }

    //Progra Dinamica
    public void DijkstraShortestPath(Vertice inicio, Vertice fin) throws UnsupportedEncodingException {
        
        HashMap<Vertice, Vertice> changedAt = new HashMap<>();
        asignacionesDinamica++;
        LineasDinamica++;
        changedAt.put(inicio, null);
        LineasDinamica++;
        // Realiza un seguimiento de la ruta más corta que hemos encontrado hasta ahora para cada vertice
        HashMap<Vertice, Double> shortestPathMap = new HashMap<>();
        asignacionesDinamica++;
        LineasDinamica++;

        // Establecer el peso de la ruta más corta de cada vertice en infinito positivo para inicio
        // excepto el nodo inicial, cuyo peso de ruta más corto es 0
        for (Vertice node : arcos) {
            comparacionesDinamica++;
            LineasDinamica++;
            if (node == inicio) {
            comparacionesDinamica++;
            LineasDinamica++;
                shortestPathMap.put(inicio, 0.0);
            LineasDinamica++;
            } else {
                shortestPathMap.put(node, Double.POSITIVE_INFINITY);
                LineasDinamica++;
            }
        }

        // Ahora pasamos por todos los vértices a los que podemos ir desde el nodo inicial
        // (esto mantiene el ciclo un poco más simple)
        for (Arco edge : inicio.arcos) {
            comparacionesDinamica++;
            LineasDinamica++;
            shortestPathMap.put(edge.destino, edge.peso);
            LineasDinamica++;
            changedAt.put(edge.destino, inicio);
            LineasDinamica++;
        }

        inicio.visit();
        LineasDinamica++;

        // Este bucle se ejecuta siempre que haya un nodo no visitado que podamos
        // llegar desde cualquiera de los vértices que pudimos hasta entonces
        while (true) {
            comparacionesDinamica++;
            LineasDinamica++;
            Vertice currentNode = masCercanoAccesibleUnvisited(shortestPathMap);
            asignacionesDinamica++;
            LineasDinamica++;
            // Si aún no hemos llegado al nodo fin y no hay otro
            // vertice alcanzable la ruta entre inicio y fin no existe
            // (no están conectados)
            if (currentNode == null) {
                comparacionesDinamica++;
                LineasDinamica++;
                System.out.println("No hay un ruta entre " + inicio.id + " y " + fin.id);
                return;
            }

            // Si el nodo no visitado más cercano es nuestro destino, queremos imprimir la ruta
            if (currentNode == fin) {
                comparacionesDinamica++;
                LineasDinamica++;
                System.out.println("El camino con el menor peso entre "
                        + inicio.id + " y " + fin.id + " es:");

                Vertice hijo = fin;
                asignacionesDinamica++;
                LineasDinamica++;
                //Se convierte en string
                String str1 = Integer.toString(fin.id);
                byte[] b = str1.getBytes("UTF-8");
                System.out.println(b.length+"Hola");
                asignacionesDinamica++;
                LineasDinamica++;
                String path = str1;
                 byte[] p = path.getBytes("UTF-8");
                 System.out.println(p.length+"Hola");
                asignacionesDinamica++;
                LineasDinamica++;
                while (true) {
                comparacionesDinamica++;
                LineasDinamica++;
                    Vertice padre = changedAt.get(hijo);
                    asignacionesDinamica++;
                    LineasDinamica++;
                    if (padre == null) {
                         comparacionesDinamica++;
                         LineasDinamica++;
                        break;
                    }

                    // Dado que nuestro mapa changeAt realiza un seguimiento de las relaciones hijo -> padre
                    // para imprimir la ruta necesitamos agregar el padre antes que el hijo y
                    // son descendientes
                    path = padre.id + " " + path;
                    asignacionesDinamica++;
                    LineasDinamica++;
                    hijo = padre;
                    asignacionesDinamica++;
                    LineasDinamica++;
                }
                System.out.println(path);
                System.out.println("Peso total ruta minima: " + shortestPathMap.get(fin));
                System.out.println("Asignaciones"+" "+ asignacionesDinamica);
                System.out.println("Comparaciones"+" "+ comparacionesDinamica);
                System.out.println("Lineas dinamico"+" "+ LineasDinamica);
                System.out.println("Memoria"+" "+ memoriaDinamico);
                return;
            }
            currentNode.visit();

            // Ahora pasamos por todos los vértices no visitados que tienen un arco
            // y compruebe si su valor de ruta más corto es mejor al pasar por nuestro
            // vertice actual que el que teníamos antes
            for (Arco edge : currentNode.arcos) {
                 comparacionesDinamica++;
                    LineasDinamica++;
                if (edge.destino.isVisited()) {
                    comparacionesDinamica++;
                    LineasDinamica++;
                    continue;
                }

                if (shortestPathMap.get(currentNode)
                        + edge.peso
                        < shortestPathMap.get(edge.destino)) {
                    comparacionesDinamica++;
                    LineasDinamica++;
                    shortestPathMap.put(edge.destino,
                            shortestPathMap.get(currentNode) + edge.peso);
                    changedAt.put(edge.destino, currentNode);
                }
            }
        }
    }

    private Vertice masCercanoAccesibleUnvisited(HashMap<Vertice, Double> shortestPathMap) {

        double menorDistancia = Double.POSITIVE_INFINITY;
        memoriaDinamico+=64;
        asignacionesDinamica++;
        LineasDinamica++;
        Vertice verticeAccesibleMasCercano = null;
        memoriaDinamico+=8;
        asignacionesDinamica++;
        LineasDinamica++;
        for (Vertice node : arcos) {
            comparacionesDinamica++;
            LineasDinamica++;
            if (node.isVisited()) {
            comparacionesDinamica++;
            LineasDinamica++;
                continue;
            }

            double distanciaActual = shortestPathMap.get(node);
            memoriaDinamico+=64;
            asignacionesDinamica++;
            LineasDinamica++;
            if (distanciaActual == Double.POSITIVE_INFINITY) {
                comparacionesDinamica++;
                LineasDinamica++;
                continue;
            }

            if (distanciaActual < menorDistancia) {
                comparacionesDinamica++;
                LineasDinamica++;
                menorDistancia = distanciaActual;
                asignacionesDinamica++;
                LineasDinamica++;
                verticeAccesibleMasCercano = node;
                asignacionesDinamica++;
                LineasDinamica++;
            }
        }
        return verticeAccesibleMasCercano;
    }

    //Busca si existe
    public Vertice buscar(ArrayList<Vertice> listaVertices, int id) {
        for (Vertice vertice : listaVertices) {
            if (vertice.id == id) {
                return vertice;
            }
        }
        System.out.println("-----Vertice NO encontrado-----");
        return null;
    }
 
}
