import java.io.*;
import java.util.*;


/**
 * Adjacency matrix implementation for the FriendshipGraph interface.
 * 
 * Your task is to complete the implementation of this class.  You may add methods, but ensure your modified class compiles and runs.
 *
 * @author Jeffrey Chan, 2016.
 */
public class AdjMatrix <T extends Object> implements FriendshipGraph<T>
{
	private int verticesNum; //Number of vertices
	private int edgesNum; //Number of edges
	private static int[][] adjacency_matrix;
	int[][] old_matrix ;

	private  ArrayList<T> vertex = new ArrayList<T>();
	private int availableIndex = -1;

	public AdjMatrix() {


	} // end of AdjMatrix()


	public int getIndexVertex(T v) {
		return vertex.indexOf(v);
	}
	//Array size check method
	public boolean arraySizeVertexCheck() {
		//		System.out.println("avail" + availableIndex);
		boolean check=true;
		for(int i=0;i<vertex.size();i++) {

			if(vertex.equals(null)) {
				check = false ;
				availableIndex = i;
				break;
			}
		}
		return check;

	}

	//When the edge array size changes addition of new vertex with the change in vertex list
	public void extendEdgeArraySize() {

		int size = vertex.size();

		if(adjacency_matrix == null) {
			adjacency_matrix = new int[size][size];

		}
		else {

			old_matrix = new int[adjacency_matrix.length][adjacency_matrix.length];

			old_matrix = adjacency_matrix;
			adjacency_matrix = new int[size][size ];
			for(int i=0;i<old_matrix.length;i++)
				for(int j=0;j<old_matrix.length;j++) {
					adjacency_matrix[i][j] = old_matrix[i][j];
				}
		}
	}

	public void addVertex(T vertLabel) {

		//check vertex is not null and not a duplicate one
		if(vertLabel!=null && getIndexVertex(vertLabel)==-1)
		{	   
			//check whether the vertex list is full and add vertex at right place
			if(arraySizeVertexCheck()) {
				vertex.add(vertLabel);	
				extendEdgeArraySize();
				
			}
			else {
				
				vertex.set(availableIndex, vertLabel);
			}
		}
		//System.out.println(vertex);
	} // end of addVertex()


	public void addEdge(T srcLabel, T tarLabel) {

		//check whether they are not null and equals to each other
		if(srcLabel !=null && tarLabel !=null && !srcLabel.equals(tarLabel)) {
			int indexOfsrc = vertex.indexOf(srcLabel);
			int indexOfTar = vertex.indexOf(tarLabel);

			//check whether both present in the vertex list
			if(indexOfsrc !=-1 && indexOfTar !=-1) {
				adjacency_matrix[indexOfsrc][indexOfTar] = 1;
				adjacency_matrix[indexOfTar][indexOfsrc] = 1;
			}
		}
	}

	//		for(int i=0;i<adj_size;i++){
	//			System.out.print(" ");
	//			for(int j=0;j<adj_size;j++) 
	//				System.out.print(adjacency_matrix[i][j]);
	//			System.out.println();
	//		}end of addEdge()


	public ArrayList<T> neighbours(T vertLabel) {
		ArrayList<T> neighbours = new ArrayList<T>();
		int index;
		index = getIndexVertex(vertLabel);
		if(index!=-1) {
			for(int i=0;i<vertex.size();i++) {
				if(adjacency_matrix[index][i]==1) {
					neighbours.add(vertex.get(i));
				}
			}
		}
		return neighbours;
		} // end of neighbours()


	public void removeVertex(T vertLabel) {
		
		int index = getIndexVertex(vertLabel);
		
		if(vertLabel != null && index != -1) {
			vertex.set(index, null);
			for(int i=0;i<adjacency_matrix.length;i++) {
					adjacency_matrix[i][index] = 0;
					adjacency_matrix[index][i] = 0;
				}
		}


	} // end of removeVertex()


	public void removeEdge(T srcLabel, T tarLabel) {
		
		int a = getIndexVertex(srcLabel);
		int b = getIndexVertex(tarLabel);

		adjacency_matrix[a][b] = 0;
		adjacency_matrix[b][a] = 0;

	} // end of removeEdges()


	public void printVertices(PrintWriter os) {
		
		
		for(int i = 0 ; i < vertex.size() ; i ++){
    		
    		if(vertex.get(i) != null){
    			os.print(vertex.get(i).toString() + " ");
    		}
    	}
    	os.println();
	} // end of printVertices()


	public void printEdges(PrintWriter os) {
		ArrayList<String> printEdge = new ArrayList<String>();
		for(int i=0;i<vertex.size();i++) {
			for(int j=0;j<vertex.size();j++) {
				if(adjacency_matrix[i][j]==1) {
					printEdge.add("\n");
					printEdge.add((String) vertex.get(i));
					printEdge.add((String) vertex.get(j));	
				}
			}
		}
		os.println(printEdge.toString().replace("[", "").replace("]", "").replace(",", " "));
	} // end of printEdges()


	public int shortestPathDistance(T vertLabel1, T vertLabel2) {

		return disconnectedDist;    	
	} // end of shortestPathDistance()

} // end of class AdjMatrix