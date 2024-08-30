// FILE: MST.java
// A Bautista, Transy U
// PPL, Fall 2023
// 
//	Driver for MST, takes in a root and a graph file and prints out a MST
//
// https://stackoverflow.com/questions/28798152/create-a-struct-in-java-like-c 11-7-2023
// https://www.javatpoint.com/java-arraylist 11-7-2023
// https://stackoverflow.com/questions/12952024/how-to-implement-infinity-in-java 11-7-2023
// 

import java.io.*;
import java.util.*;

public class MST{
	public static void main(String[] args) {
		Tree tree = new Tree();
		Graph graph = new Graph();
		Vertex vertices = new Vertex(), connections = new Vertex(), root = new Vertex();

		try{
			if(args.length > 2){
				System.out.println("\tERROR: Too many arguments - only need graph_file and root");
				System.exit(1);
			}
			root.setName(args[1]);
			graph.load(args[0],root);
		}
		catch(ArrayIndexOutOfBoundsException e){
			System.out.println("\tERROR: Not enough arguments - need graph_file and root");
			System.exit(1);
		}

		graph.getVertices(vertices);
		vertices.setRoot(root);
		graph.MST(vertices);
		tree.printMST(root,vertices);
	}
}
