// FILE: Vertex.java
// A Bautista, Transy U
// PPL, Fall 2023
// 
//	Contains all functions needed to interact with Vertex
//

import java.io.*;
import java.util.*;

public class Vertex{
	private ArrayList<Vertex> vertices = new ArrayList<Vertex>();
	private String child, parent;
	private double key;
	private boolean processed;

	// empty constructor
	public Vertex(){}

	// constructor to initialize child, parent, weight, and processed of a vertex to given values
	public Vertex(String child,String parent,double key,boolean processed){
		this.child = child;
		this.parent = parent;
		this.key = key;
		this.processed = processed;
	}
	
	// adds given vertex to the end of vertices
	public void add(Vertex vertex){ vertices.add(vertex); }
    
    // returns the vertex at the given index in vertices
    public Vertex get(int index){ return vertices.get(index); }
    
    // returns the size of vertices
    public int size(){ return vertices.size(); }

    // replaces the vertex at the given index in vertices with the given vertex
    public void update(int index,Vertex vertex){ vertices.set(index,vertex); }
    
    // returns the index of vertex withing vertices
    public int indexOf(Vertex vertex){ return vertices.indexOf(vertex); }
    
    // clears vertices of all its elements
    public void clear(){ vertices.clear(); }

    // returns child of vertex
	public String getChild(){ return child; }
    
	// returns parent of vertex
    public String getParent(){ return parent; }
    
    // returns key of vertex
    public double getKey(){ return key; }

    // returns processed boolean of vertex
    public boolean checkProcessed(){ return this.processed; }

    // takes in a name and sets a vertex to have that name with no parent, a key of infinity, and marked as unprocessed
    public void setName(String vertexName){
    	this.child = vertexName;
    	this.parent = null;
    	this.key = Double.POSITIVE_INFINITY;
    	this.processed = false;
    }

    // returns the vertex whose child has the same name, returns an empty vertex if that name is not found
    public Vertex find(String vertexName){
    	for(int i=0;i<this.size();i++){
    		if(this.get(i).getChild().equals(vertexName)){
    			return this.get(i);
    		}
    	}
    	return new Vertex();
    }

    // returns true if weight is less than the vertex's key
    public boolean weightLessKey(double weight){
		if(weight<this.getKey()) return true;
    	return false;
    }

    // takes in graph, vertices, and connections and finds vertices in graph connected to vertex and loads them into connections
    public void findConnections(Graph graph,Vertex vertices,Vertex connections){
        Vertex connected = new Vertex();
        for(int i=0;i<graph.size();i++){
            if(graph.get(i).getVertex1().equals(this.getChild())){
                connected = vertices.find(graph.get(i).getVertex2());
                connections.add(connected);
            }
            else if(graph.get(i).getVertex2().equals(this.getChild())){
                connected = vertices.find(graph.get(i).getVertex1());
                connections.add(connected);
            }
        }
    }

    // returns true if all vertices have been processed
    public boolean allProcessed(){
    	boolean processed = true;
    	for(int i=0;i<this.size();i++){
    		if(!this.get(i).checkProcessed()) processed = false;
    	}
    	return processed;
    }

    // returns the vertex with the minimum key
    public Vertex extractMin(){
    	Vertex minVertex = new Vertex();
    	double minKey = Double.POSITIVE_INFINITY;
    	int minIndex = -1;
    	for(int i=0;i<this.size();i++){
    		if(this.get(i).getKey() < minKey && !this.get(i).checkProcessed()){
    			minIndex = i;
    			minKey = this.get(i).getKey();
    		}
    	}

    	/* if minIndex is -1, no minimum was found because a vertex has been left with a key of infinity and has not yet been processed
    	 this shouldn't be possible unless the vertex is not connected to a graph which would mean at no point does its key get changed from
    	 its initial value of infinity since it'll never be loaded in as a connection to be updated */
    	try{
    		minVertex = new Vertex(this.get(minIndex).getChild(),this.get(minIndex).getParent(),this.get(minIndex).getKey(),true);
    	}
    	catch(IndexOutOfBoundsException e){
    		System.out.println("\tERROR: Minimum vertex not found, check that all edges are connected");
    		System.exit(1);
    	}
    	
    	this.update(minIndex,minVertex);
    	return minVertex;
    }

    // returns true if vertex is in the edge
    public boolean member(String vertex){
    	boolean present = false;
    	for(int i=0;i<this.size();i++){
    		if(this.get(i).getChild().equals(vertex) && !this.get(i).checkProcessed()){
    			present = true;
    		}
    	}
    	return present;
    }

    // prints out vertex
    public void print(){
    	System.out.printf("%s %s %f %b\n",this.getChild(),this.getParent(),this.getKey(),this.checkProcessed());
    }

    // makes a root into a rootVertex
	public void setRoot(Vertex root){
		Vertex rootVertex = new Vertex(root.getChild(),null,(double) 0,false);
		for(int i=0;i<this.size();i++){
			if(this.get(i).getChild().equals(root.getChild())){
				this.update(i,rootVertex);
			}
		}
	}
}
