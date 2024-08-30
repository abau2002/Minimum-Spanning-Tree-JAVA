// FILE: Tree.java
// A Bautista, Transy U
// PPL, Fall 2023
// 
//	Contains all functions needed to print out a tree and its weight
//

import java.io.*;
import java.util.*;

public class Tree{
	// takes in vertices and root and prints out a tree
	public void printTree(Vertex root,Vertex vertices){
		Tree tree = new Tree();
    	for(int i=0;i<vertices.size();i++){
    		String parent = vertices.get(i).getParent();
    		/* only encloses a vertex in parantheses if its parent is the current root and
    		 the null check ensures the root cannot be a child of another vertex */
        	if(parent!=null && parent.equals(root.getChild())){
            	System.out.printf(" (%s", vertices.get(i).getChild());
            	tree.printTree(vertices.get(i),vertices);
            	System.out.printf(")");
        	}
    	}
    }

    // takes in vertices and returns their total weight
    public double calcTreeWeight(Vertex vertices){
    	double treeWeight=0;
    	for(int i=0;i<vertices.size();i++){
    		treeWeight += vertices.get(i).getKey();
    	}
    	return treeWeight;
    }

    // takes in vertices and the root and prints out the MST with its weight
    public void printMST(Vertex root,Vertex vertices){
    	Tree tree = new Tree();
    	System.out.printf("Tree: (%s", root.getChild());
        tree.printTree(root,vertices);
        System.out.printf(") Weight: %f\n",calcTreeWeight(vertices));
    }
}