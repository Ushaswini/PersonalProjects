/*
 * VINNAKOTA VENKATA RATNA USHASWINI
 * 800983632
 * */
package edu.ushaswini;

import java.util.ArrayList;
import java.util.Collections;

import edu.ushaswini.Vertex.VisitStatus;

/*
 * Reachable algorithm class to find set of all reachable vertices 
 * from a given vertex in a graph
 * 
 * Here Depth First Search (DFS) is used to find the reachable vertices from source vertex
 * Starting from source vertex and reaching back to same ,
 * we would have covered all the vertices reachable from the given source vertex
 * 
 * Each time we discover a new vertex we add that vertex to set of reachable vertices list
 * 
 * We later sort this list to display them in alphabetical order.
 * 
 * */
public class ReachableAlgorithm {
	
	private Graph graph;
	
	private ArrayList<Vertex> reachableVertexes;
	
	private Vertex startVertex;
	
	/*
	 * Initializes the class
	 * Execute the algorithm for source vertex
	 * Store all the reachable vertices to list
	 * */	
	
	public ReachableAlgorithm(Graph graph, Vertex startVertex) {
		
		/*
		 * clearVisitedStatus takes O|V| time
		 * executeAlgorithm takes O|E| time
		 */
		super();
		this.graph = graph;
		this.startVertex = startVertex;
		this.initializeReachableVertices();
		this.clearVisitedStatus();
		this.executeAlgorithm(startVertex);
		

	}

	ArrayList<Vertex> executeAlgorithm(Vertex vertex){
		
		/*
		 * Set the status of discovered vertex
		 * For all the outgoing edges in adjacent list which are not down,
		 * if destination vertex of that edge is active and is being encountered for first time add it to reachable vertex list
		 * 
		 * For each vertex, this loop run for O(|adj(V)|). 
		 *  */
		
		vertex.setVisitStatus(VisitStatus.DISCOVERED);		
		
		if(!vertex.isDown()){
			
			//Collections.sort(vertex.getAdj(),Vertex.vertexNameSorter);

			
			for(Edge edge : vertex.getAdjacent()){		
				
								
				if(!edge.isDown() && !edge.getDestination().isDown()  && edge.getDestination().getVisitStatus() == VisitStatus.UNDISCOVERED){
					/*Add the vertex to reachableVertices list and recursively execute algorithm on reachable vertex*/
					
					reachableVertexes.add(edge.getDestination());
					executeAlgorithm(edge.getDestination());
				}
				
			}
			
			//When all the outgoing edges from vertex are processed change the status of vertex
			vertex.setVisitStatus(VisitStatus.FINISHED);
		}
		
		Collections.sort(reachableVertexes,Vertex.vertexNameSorter);
		
		return reachableVertexes;
		
		
		
		
	}
	
	void initializeReachableVertices(){
		reachableVertexes = new ArrayList<>();
	}
	
	/*
	 * Resets all the properties of vertices to default values
	 * This takes O|V| time |V| is number of vertices in graph.*/
	void clearVisitedStatus(){
		for(Vertex v : graph.getVertexMap().values()){
			
			v.resetVistedStatus();
		}
	}

	//Override to write list of reachable vertices from start vertex in alphabetical order.
	@Override
	public String toString() {
		String output = "";
		
		if(!startVertex.isDown()){
			
			output = this.startVertex.getName();
			
			Collections.sort(this.reachableVertexes,Vertex.vertexNameSorter);
			
			for(Vertex vertex : this.reachableVertexes){
				output += "\n  " + vertex.getName();
			}
			
		}
		return output;

		
	}
	
	

}
