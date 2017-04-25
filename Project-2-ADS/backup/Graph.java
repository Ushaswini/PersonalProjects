package edu.ushaswini;
// Graph.java
// Graph code, modified from code by Mark A Weiss.
// Computes Unweighed shortest paths.


import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;


public class Graph
{
	public static final int INFINITY = Integer.MAX_VALUE;



	Map<String,Vertex> vertexMap;

	Map<String,Edge> edgeMap;


	public Graph(Map<String,Vertex> vertexMap, Map<String,Edge> edgeMap) {
		this.vertexMap = vertexMap;
		this.edgeMap = edgeMap;
	}

	public Collection<Vertex> getVertexes() {
		return vertexMap.values();
	}

	public Collection<Edge> getEdges() {
		return edgeMap.values();
	}

	/**
	 * Add a new edge to the graph.
	 */
	public void addEdge( String sourceName, String destName,float transmissionTime )
	{

		Vertex v = getVertex( sourceName ); 
		Vertex w = getVertex( destName );   
		v.adj.add( w );
	}

	/**
	 * Delete a edge from the graph.
	 */
	public void deleteEdge(String sourceName, String destName){
		Vertex v = getVertex( sourceName ); 
		Vertex w = getVertex( destName );   
		v.adj.remove( w );

	}

	/**
	 * Mark edge down
	 */
	public void markEdgeDown(String sourceName, String destName){

		edgeMap.get(sourceName + destName).setDown(true);
	}

	/**
	 * Mark edge up
	 */
	public void markEdgeUp(String sourceName, String destName){
		edgeMap.get(sourceName + destName).setDown(false);
	}

	/**
	 * Mark vertex down
	 */
	public void markVertexDown(String vertextName){
		vertexMap.get(vertextName).setDown(true);
	}

	/**
	 * Mark vertex up
	 */
	public void markVertexUp(String vertextName){
		vertexMap.get(vertextName).setDown(false);
	}

	/**
	 * Print graph
	 */
	public void printPath(){
		
		List<Vertex> vertexes = new ArrayList<Vertex>(getVertexes());
		
		Collections.sort(vertexes);
		for(Vertex v : vertexes){
			System.out.println(v.getName() + (v.isDown() ? " DOWN" : ""));
			
			List<Vertex> adjVertexes = v.getAdj();
			Collections.sort(adjVertexes);
			
			for(Vertex adjVertex : adjVertexes){
				Edge edge = edgeMap.get(v.getName() + adjVertex.getName());
				System.out.println(adjVertex.getName() +  "  " + edge.getTransmission_time() + (edge.isDown() ? " DOWN" : ""));

			}
		}
	}

	/**
	 * Print reachable vertices
	 */
	public void printReachableVertices(){
		//TODO
	}


	private Vertex getVertex( String vertexName ){ 
		Vertex v = vertexMap.get( vertexName );    
		if( v == null )         {         
			v = new Vertex( vertexName );  
			vertexMap.put( vertexName, v );  
		}         
		return v;  
	}

}
