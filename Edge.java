// FILE: Edge.java
// A Bautista, Transy U
// PPL, Fall 2023
// 
//  Contains all functions that operate directly on Edge
//

import java.io.*;
import java.util.*;

public class Edge{
	private String vertex1, vertex2;
	private double weight;


    // constructor to initialize vertex1, vertex2, and weight of an edge to given values
    public Edge(String vertex1, String vertex2, double weight) {
        this.vertex1 = vertex1;
        this.vertex2 = vertex2;
        this.weight = weight;
    }

    // returns vertex1 of edge
    public String getVertex1(){ return vertex1; }

    // returns vertex2 of edge
    public String getVertex2(){ return vertex2; }

    // returns weight of edge
    public double getWeight(){ return weight; }

    // prints out edge
    public void print(){
    	System.out.printf("%s %s %f\n",this.getVertex1(),this.getVertex2(),this.getWeight());
    }

    // returns true if vertex is in the edge
    public boolean member(Vertex vertex){
    	if(vertex1.equals(vertex.getChild()) || vertex2.equals(vertex.getChild())){
    		return true;
    	}
    	return false;
    }
}