/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Main;

import Grafo.*;

/**
 *
 * @author Lester Trejos
 */
public class main {
    
   
    
    public static void main(String[] args) {
        
        GraphWeighted graphWeighted = new GraphWeighted(true);
        
        NodeWeighted zero = new NodeWeighted(0);
        NodeWeighted one = new NodeWeighted(1);
        NodeWeighted two = new NodeWeighted(2);
        NodeWeighted three = new NodeWeighted(3);
        NodeWeighted four = new NodeWeighted(4);
        NodeWeighted five = new NodeWeighted(5);
        NodeWeighted six = new NodeWeighted(6);

        graphWeighted.addEdge(zero, one, 8);
        graphWeighted.addEdge(zero, two, 11);
        graphWeighted.addEdge(one, three, 3);
        graphWeighted.addEdge(one, four, 8);
        graphWeighted.addEdge(one, two, 7);
        graphWeighted.addEdge(two, four, 9);
        graphWeighted.addEdge(three, four, 5);
        graphWeighted.addEdge(three, five, 2);
        graphWeighted.addEdge(four, six, 6);
        graphWeighted.addEdge(five, four, 1);
        graphWeighted.addEdge(five, six, 8);

        graphWeighted.DijkstraShortestPath(zero, six);
        
    }
    
    
}
