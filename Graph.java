// FILE: Graph.java
// A Bautista, Transy U
// PPL, Fall 2023
// 
//  Contains all functions that are needed to interact on the Graph data type
//

import java.io.*;
import java.util.*;

public class Graph {
    private ArrayList<Edge> graph = new ArrayList<Edge>();

    // adds edge to graph
    public void add(Edge edge){ graph.add(edge); }

    // returns edge at index of graph
    public Edge get(int index){ return graph.get(index); }

    // returns size of graph
    public int size(){ return graph.size(); }
    
    // loads the vertices of graph into vertices
    public void getVertices(Vertex vertices){
        Vertex vertex;
        for(int i=0;i<this.size();i++){
            if(!vertices.member(this.get(i).getVertex1())){
                vertex = new Vertex(this.get(i).getVertex1(),null,Double.POSITIVE_INFINITY,false);
                vertices.add(vertex);
            }
            if(!vertices.member(this.get(i).getVertex2())){
                vertex = new Vertex(this.get(i).getVertex2(),null,Double.POSITIVE_INFINITY,false);
                vertices.add(vertex);
            }
        }
    }

    // takes in vertices and updates vertices to have the final keys and parents for the MST of the graph
    public void MST(Vertex vertices){
        Vertex minVertex,tempVertex;
        Vertex connections = new Vertex();
        double weight;
        while(!vertices.allProcessed()){
            minVertex = vertices.extractMin();
            minVertex.findConnections(this,vertices,connections);
            for(int i=0;i<connections.size();i++){
                weight = this.getWeight(minVertex,connections.get(i));
                if(vertices.member(connections.get(i).getChild()) && connections.get(i).weightLessKey(weight)){
                    tempVertex = new Vertex(connections.get(i).getChild(),minVertex.getChild(),weight,false);
                    vertices.update(vertices.indexOf(connections.get(i)),tempVertex);
                }
            }
            connections.clear();
        }
    }

    /* takes in vertex1 and vertex2 and returns the weight of their edge
     if edge not found, returns -1.0 */
    public double getWeight(Vertex vertex1,Vertex vertex2){
        for(int i=0;i<this.size();i++){
            if(this.get(i).member(vertex1) && this.get(i).member(vertex2)){
                return this.get(i).getWeight();
            }
        }
        return -1.0;
    }

    // takes in a filename and root and loads a file into graph
    public void load(String fileName, Vertex root) {
        try{
            String vertex1,vertex2;
            double weight,temp;
            boolean validRoot = false;
            Edge currentEdge;
            FileReader graphFile = new FileReader(fileName);
            StreamTokenizer inputFile = new StreamTokenizer(graphFile);

            int tokenType = inputFile.nextToken();
            while(tokenType!=StreamTokenizer.TT_EOF){
                vertex1 = inputFile.sval;
                tokenType = inputFile.nextToken();
                vertex2 = inputFile.sval;
                tokenType = inputFile.nextToken();
                weight = inputFile.nval;

                // if sval does not evaluate to null then a string has been entered as a weight 
                if(weight<0 || inputFile.sval!=null){
                    System.out.println("\tERROR: Weights should be non-negative integers");
                    System.exit(1);
                }
                // sval does not read numbers as strings, but as null which cannot be a valid vertex name
                else if(vertex1==null || vertex2==null){
                    System.out.println("\tERROR: Vertices cannot be numbers or begin with numbers");
                    System.exit(1);
                }
                if(vertex1.equals(root.getChild()) || vertex2.equals(root.getChild())) validRoot = true;

                currentEdge = new Edge(vertex1,vertex2,weight);
                this.add(currentEdge);
                tokenType = inputFile.nextToken();
            }
            if(!validRoot){
                System.out.printf("\tERROR: Root %s not found in %s\n", root.getChild(), fileName);
                System.exit(1);
            }
        }
        catch(FileNotFoundException e){
            System.out.println("\tERROR: File not found");
            System.exit(1);
        }
        catch(IOException e){
            System.out.println("\tERROR: IOException");
            System.exit(1);
        }        
    }
}
