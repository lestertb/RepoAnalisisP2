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
public class EdgeWeighted implements Comparable<EdgeWeighted> {
    
    public NodeWeighted source;
    public NodeWeighted destination;
    public double weight;

    public EdgeWeighted(NodeWeighted s, NodeWeighted d, double w) {
        // Note that we are choosing to use the (exact) same objects in the Edge class
        // and in the GraphShow and GraphWeighted classes on purpose - this MIGHT NOT
        // be something you want to do in your own code, but for sake of readability
        // we've decided to go with this option
        source = s;
        destination = d;
        weight = w;
    }
    public String toString() {
    return String.format("(%s -> %s, %f)", source.n, destination.n, weight);
}

// We need this method if we want to use PriorityQueues instead of LinkedLists
// to store our edges, the benefits are discussed later, we'll be using LinkedLists
// to make things as simple as possible
public int compareTo(EdgeWeighted otherEdge) {

    // We can't simply use return (int)(this.weight - otherEdge.weight) because
    // this sometimes gives false results
    if (this.weight > otherEdge.weight) {
        return 1;
    }
    else return -1;
}
    
}

