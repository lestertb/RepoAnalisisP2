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
    public Set<Vertice> arcos;
    public boolean directed;

    //Global para el génetico
    static ArrayList<Integer> hechos = new ArrayList<>();

    //Global para el BackTracking
    public MetodosGrafo(boolean directed) {
        this.directed = directed;
        arcos = new HashSet<>();
    }

    //Agregar arcos
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

        rutaVertice.push(origen);
        
        while (!rutaVertice.isEmpty()) {
            Vertice vertice = (Vertice) rutaVertice.pop();
            double pesoActual = Double.MAX_VALUE;
            Vertice aux = vertice;
            if (vertice != destino) {
                if (!vertice.isVisited()) {
                    vertice.visited = true;
                    for (Arco arco : vertice.arcos) {
                        if (arco.peso < pesoActual) {

                            if (arco.destino.id > vertice.id) {
                                pesoActual = arco.peso;
                                aux = arco.destino;
                            }
                        }
                    }
                    rutaVertice.remove(vertice);
                    rutaVertice.push(aux);
                    System.out.println("Origen:" + vertice.id);
                    System.out.println("Destino:" + aux.id);
                }
            }
        }

    }

    //Progra Dinamica
    public void DijkstraShortestPath(Vertice inicio, Vertice fin) {

        HashMap<Vertice, Vertice> changedAt = new HashMap<>();
        changedAt.put(inicio, null);

        // Realiza un seguimiento de la ruta más corta que hemos encontrado hasta ahora para cada vertice
        HashMap<Vertice, Double> shortestPathMap = new HashMap<>();

        // Establecer el peso de la ruta más corta de cada vertice en infinito positivo para inicio
        // excepto el nodo inicial, cuyo peso de ruta más corto es 0
        for (Vertice node : arcos) {
            if (node == inicio) {
                shortestPathMap.put(inicio, 0.0);
            } else {
                shortestPathMap.put(node, Double.POSITIVE_INFINITY);
            }
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
                System.out.println("Peso total ruta minima: " + shortestPathMap.get(fin));
                return;
            }
            currentNode.visit();

            // Ahora pasamos por todos los vértices no visitados que tienen un arco
            // y compruebe si su valor de ruta más corto es mejor al pasar por nuestro
            // vertice actual que el que teníamos antes
            for (Arco edge : currentNode.arcos) {
                if (edge.destino.isVisited()) {
                    continue;
                }

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
        for (Vertice node : arcos) {
            if (node.isVisited()) {
                continue;
            }

            double distanciaActual = shortestPathMap.get(node);
            if (distanciaActual == Double.POSITIVE_INFINITY) {
                continue;
            }

            if (distanciaActual < menorDistancia) {
                menorDistancia = distanciaActual;
                verticeAccesibleMasCercano = node;
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

    //Génetico
    public void genetico(Vertice grafo, int cant, ArrayList<Vertice> listaVertices) {
        System.out.println(grafo.id);
        if ((grafo == null) | (grafo.visited == true) | (grafo.id == cant - 1)) {//condiciones de parada
            return;
        } else {
            int num = (int) (Math.random() * cant - 1) + 1;
            Vertice n = buscar(listaVertices, num);
            boolean k = true;
            while (k == true) {
                num = (int) (Math.random() * cant - 1) + 1;
                n = buscar(listaVertices, num);
                k = false;

                for (int x : hechos) {
                    if (x == num) {
                        k = true;
                    }
                }
            }
            hechos.add(num);
            genetico(n, cant, listaVertices);
        }
    }

    //BackTracking
}
