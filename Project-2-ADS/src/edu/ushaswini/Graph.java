/*
 * VINNAKOTA VENKATA RATNA USHASWINI
 * 800983632
 * */
package edu.ushaswini;

import java.io.IOException;
import java.io.Writer;

/*
 * Graph class to represent graph
 * Takes vertex map and edge map as parameters for constructor
 * VertexMap to hold set of vertices || EdgeMap to hold set of edges
 * 
 * addEdge - to add edge to graph
 * 
 * deleteEdge - to delete edge from graph
 * 
 * markEdgeUp - to mark edge up
 * 
 * markEdgeDown - to mark edge down
 * 
 * markVertexUp - to mark vertex up
 * 
 * markVertexDown - to mark vertex down
 * 
 * printReachableVertices - to print set of reachable vertexes for each vertex in graph
 * 
 * printGraph - to print graph
 * 
 * printPathWithTransmissionTime - to print path from source to vertex
 * 
 * 
 * */


import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/*
 * Graph class with methods for operating on Graph
 * Like add edge, delete edge, mark edge up/down, mark vertex up/down
 * print shortest path after performing dijkstra's algorithm
 * print graph
 * print set of reachable vertices for every vertex 
 * */

public class Graph
{
	public static final int INFINITY = Integer.MAX_VALUE;

	private HashMap<String,Vertex> vertexMap = new HashMap<String, Vertex>();


	List<Vertex> vertexesSorted;
	
	Writer writer;

	public HashMap<String, Vertex> getVertexMap() {
		return vertexMap;
	}

	public void setVertexMap(HashMap<String, Vertex> vertexMap) {
		this.vertexMap = vertexMap;
	}

	

	public Graph(Writer writer) {
		this.writer = writer;
	}

	/**
	 * Add a new edge to the graph between source and destination if edge is not already present
	 * Two edges are same if their source and destination vertices are same. Implemented this equals condition in edge class.
	 * Updates transmission time if one is present already
	 */
	public void addEdge( String sourceName, String destName,float transmissionTime )
	{

		Vertex source = getVertex( sourceName ); 
		Vertex destination = getVertex( destName ); 
		
		Edge edge = new Edge(source,destination,transmissionTime);
		
		int indexOfEdge = source.getAdjacent().indexOf(edge);
		
		if(indexOfEdge == -1){
			
			source.getAdjacent().add(edge);
		}else{
			source.getAdjacent().get(indexOfEdge).setTransmission_time(transmissionTime);
		}

	}

	/**
	 * Delete a edge from the graph and removes from adjacent list of source vertex
	 */
	public void deleteEdge(String sourceName, String destName){

		Vertex source = getVertex( sourceName ); 
		Vertex destination = getVertex( destName ); 
		
		Edge edge = new Edge(source,destination,0);
		source.getAdjacent().remove(edge);

	}

	/**
	 * Mark edge down if an edge is present
	 * @throws IOException 
	 */
	public void markEdgeDown(String sourceName, String destName) throws IOException{
		Vertex source = this.vertexMap.get(sourceName);
		Vertex destination = this.vertexMap.get(destName);
		
		Edge edge = new Edge(source, destination,0);
		int indexOfEdge = source.getAdjacent().indexOf(edge);
		
		if(indexOfEdge == -1){
			writer.write("Edge not found." + System.lineSeparator());
			System.out.println("Edge not found.");
		}else{
			source.getAdjacent().get(indexOfEdge).setDown(true);
		}
		

	}

	/**
	 * Mark edge up if edge is present
	 * @throws IOException 
	 */
	public void markEdgeUp(String sourceName, String destName) throws IOException{
		Vertex source = this.vertexMap.get(sourceName);
		Vertex destination = this.vertexMap.get(destName);
		
		Edge edge = new Edge(source, destination,0);
		int indexOfEdge = source.getAdjacent().indexOf(edge);
		
		if(indexOfEdge == -1){
			writer.write("Edge not found." + System.lineSeparator());
			System.out.println("Edge not found.");
		}else{
			source.getAdjacent().get(indexOfEdge).setDown(false);
		}
	}

	/**
	 * Mark vertex down if vertex is present
	 * @throws IOException 
	 */
	public void markVertexDown(String vertextName) throws IOException{
		
		if(vertexMap.get(vertextName) != null){
			vertexMap.get(vertextName).setDown(true);
		}else{
			writer.write("Vertex not found." + System.lineSeparator());
			System.out.println("Vertex is not found");
		}
	}

	/**
	 * Mark vertex up if vertex is present
	 * @throws IOException 
	 */
	public void markVertexUp(String vertextName) throws IOException{
		
		if(vertexMap.get(vertextName) != null){
			vertexMap.get(vertextName).setDown(false);
		}else{
			writer.write("Vertex not found." + System.lineSeparator());
			System.out.println("Vertex is not found");
		}
	}

	/**
	 * Print reachable vertices for each vertex in alphabetical order
	 * Uses Reachable algorithm to calculate.
	 * @throws IOException 
	 */
	public void printReachableVertices() throws IOException{
		
		//Get list of vertices name from map key set to array to sort them in alphabetical order
		
		ArrayList<String> vertexNames = new ArrayList<String>(vertexMap.keySet());
		
		Collections.sort(vertexNames);
		
		for(String vertexName: vertexNames){
			Vertex vertex = vertexMap.get(vertexName);
			
			//For each vertex in graph, execute reachable algorithm to find set of vertices reachable from it.
			
			ReachableAlgorithm algorithm = new ReachableAlgorithm(this,vertex);
			writer.write(algorithm.toString() + System.lineSeparator());
			System.out.println(algorithm);
			
		}
	}

	private Vertex getVertex( String vertexName ){ 
		//Get vertex from map with the given name for vertex if present already creates new vertex otherwise
		
		Vertex v = vertexMap.get( vertexName );    
		if( v == null )         {         
			v = new Vertex( vertexName );  
			vertexMap.put( vertexName, v );  
		}         
		return v;  
	}

	/**
	 * Print Graph
	 * @throws IOException 
	 */

	public void printGraph() throws IOException {
		
		// Get list of vertices names to array to sort them in alphabetical order

		vertexesSorted = new ArrayList<Vertex>(vertexMap.values());

		Collections.sort(vertexesSorted,Vertex.vertexNameSorter);
		
		for(Vertex v : vertexesSorted){
			
			writer.write(v.getName() + (v.isDown() ? " DOWN" : "") + System.lineSeparator());			
			System.out.println(v.getName() + (v.isDown() ? " DOWN" : ""));

			//Get list of adjacent edges of vertex and sort them according to their destination vertex names
			List<Edge> adjEdges = v.getAdjacent();
			
			Collections.sort(adjEdges, Edge.edgeDestinationNameComparator);

			for(Edge adjEdge : adjEdges){
				
				//for each adjacent edge print destination vertex name and transmission time. 
				
				writer.write("\t" + adjEdge.getDestination().getName() + " " + adjEdge.getTransmission_time() + (adjEdge.isDown() ? "DOWN" : "") + System.lineSeparator());
				System.out.println("\t" + adjEdge.getDestination().getName() + " " + adjEdge.getTransmission_time() + (adjEdge.isDown() ? "DOWN" : "") + System.lineSeparator());

			}
		}

	}

	public void printShortestPathWithTransmissionTime(Vertex source, Vertex dest) throws IOException{
		
		// Execute Dijkstra's algorithm on the given source vertex and print path from source to destination and transmission time involved

		DijkstraAlgorithm algorithm = new DijkstraAlgorithm(this);
		
		if(!source.isDown()){
			
			algorithm.executeAlgorithm(source,dest);
			printPath(dest);
			
			writer.write(Math.round(dest.getDist() * 100f) / 100f + System.lineSeparator());
			System.out.print(Math.round(dest.getDist() * 100f) / 100f + "\n");
		}else{
			writer.write("Source vertex is down");
			System.out.println("Source vertex is down");
		}

	}

	void printPath( Vertex dest) throws IOException
	{
		if( dest.getPrev() != null )
		{
			printPath( dest.getPrev() );
		}
		
		writer.write(dest.getName() + "  " );
		
		System.out.print( dest.getName() + "  " );
	}


}
