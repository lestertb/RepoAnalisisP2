/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Grafo;

import java.util.*;

/**
 *
 * @author Lester Trejos
 */
public class MetodosGrafo {
    
    //Globals
    public Set<Vertice> vertices;
    public boolean directed;

    public MetodosGrafo(boolean directed) {
        this.directed = directed;
        vertices = new HashSet<>();
    }
    //Agregar vertices
    public void agregarVertice(Vertice... n) {

        vertices.addAll(Arrays.asList(n));
    }
    //Agregar Arco entre los vertices
    public void agregarArco(Vertice origen, Vertice destino, double peso) {

        vertices.add(origen);
        vertices.add(destino);

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
        for (Vertice node : vertices) {
            LinkedList<Arco> edges = node.arcos;

            if (edges.isEmpty()) {
                System.out.println("Node:" + node.id + " has no edges.");
                continue;
            }
            System.out.print("Node: " + node.id + " has edges to: ");

            for (Arco edge : edges) {
                System.out.print(edge.destino.id + "(" + edge.peso + ") ");
            }
            System.out.println();
        }
    }
    //Verifica si 2 vertices tienen arco (Conexo)
    public boolean tieneArco(Vertice origen, Vertice destino) {
        LinkedList<Arco> edges = origen.arcos;
        for (Arco edge : edges) {
            if (edge.destino == destino) {
                return true;
            }
        }
        return false;
    }
    //Resetea todos los vertices en no visitados
    public void resetVerticesVisited() {
        for (Vertice node : vertices) {
            node.unvisit();
        }
    }
    
    //Voraz
    
    
    //Progra Dinamica
    public void DijkstraShortestPath(Vertice inicio, Vertice fin) {

        HashMap<Vertice, Vertice> changedAt = new HashMap<>();
        changedAt.put(inicio, null);

        // Realiza un seguimiento de la ruta más corta que hemos encontrado hasta ahora para cada vertice
        HashMap<Vertice, Double> shortestPathMap = new HashMap<>();

        // Establecer el peso de la ruta más corta de cada vertice en infinito positivo para inicio
        // excepto el nodo inicial, cuyo peso de ruta más corto es 0
        for (Vertice node : vertices) {
            if (node == inicio)
                shortestPathMap.put(inicio, 0.0);
            else shortestPathMap.put(node, Double.POSITIVE_INFINITY);
        }

        // Ahora pasamos por todos los vértices a los que podemos ir desde el nodo inicial
        // (esto mantiene el ciclo un poco más simple)
        for (Arco edge : inicio.arcos) {
            shortestPathMap.put(edge.destino, edge.peso);
            changedAt.put(edge.destino, inicio);
        }

        inicio.visit();

        // Este bucle se ejecuta siempre que haya un nodo no visitado que podamos
        // llegar desde cualquiera de los vértices que pudimos hasta entonces
        while (true) {
            Vertice currentNode = masCercanoAccesibleUnvisited(shortestPathMap);
           // Si aún no hemos llegado al nodo fin y no hay otro
           // vertice alcanzable la ruta entre inicio y fin no existe
           // (no están conectados)
            if (currentNode == null) {
                System.out.println("No hay un ruta entre " + inicio.id + " y " + fin.id);
                return;
            }

            // Si el nodo no visitado más cercano es nuestro destino, queremos imprimir la ruta
            if (currentNode == fin) {
                System.out.println("El camino con el menor peso entre "
                                       + inicio.id + " y " + fin.id + " es:");

                Vertice hijo = fin;
                //Se convierte en string
                String str1 = Integer.toString(fin.id);
                String path = str1;
                while (true) {
                    Vertice padre = changedAt.get(hijo);
                    if (padre == null) {
                        break;
                    }

                    // Dado que nuestro mapa changeAt realiza un seguimiento de las relaciones hijo -> padre
                    // para imprimir la ruta necesitamos agregar el padre antes que el hijo y
                    // son descendientes
                    path = padre.id + " " + path;
                    hijo = padre;
                }
                System.out.println(path);
                System.out.println("The path costs: " + shortestPathMap.get(fin));
                return;
            }
            currentNode.visit();

             // Ahora pasamos por todos los vértices no visitados que tienen un arco
             // y compruebe si su valor de ruta más corto es mejor al pasar por nuestro
             // vertice actual que el que teníamos antes
            for (Arco edge : currentNode.arcos) {
                if (edge.destino.isVisited())
                    continue;

                if (shortestPathMap.get(currentNode)
                   + edge.peso
                   < shortestPathMap.get(edge.destino)) {
                    shortestPathMap.put(edge.destino,
                                       shortestPathMap.get(currentNode) + edge.peso);
                    changedAt.put(edge.destino, currentNode);
                }
            }
        }
    }
    
    private Vertice masCercanoAccesibleUnvisited(HashMap<Vertice, Double> shortestPathMap) {

        double menorDistancia = Double.POSITIVE_INFINITY;
        Vertice verticeAccesibleMasCercano = null;
        for (Vertice node : vertices) {
            if (node.isVisited())
                continue;

            double distanciaActual = shortestPathMap.get(node);
            if (distanciaActual == Double.POSITIVE_INFINITY)
                continue;

            if (distanciaActual < menorDistancia) {
                menorDistancia = distanciaActual;
                verticeAccesibleMasCercano = node;
            }
        }
        return verticeAccesibleMasCercano;
    }
    

}
