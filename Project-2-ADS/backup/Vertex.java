package edu.ushaswini;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

//Represents a vertex in the graph.
public class Vertex implements Comparable<Vertex>
{
	String     name;   // Vertex name
	List<Vertex> adj;    // Adjacent vertices
	Vertex     prev;   // Previous vertex on shortest path
	float        dist;   // Distance of path

	public boolean isDown; 




	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Vertex> getAdj() {
		return adj;
	}

	public void setAdj(List<Vertex> adj) {
		this.adj = adj;
	}

	public Vertex getPrev() {
		return prev;
	}

	public void setPrev(Vertex prev) {
		this.prev = prev;
	}

	public float getDist() {
		return dist;
	}

	public void setDist(float dist) {
		this.dist = dist;
	}

	public boolean isDown() {
		return isDown;
	}

	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}

	public Vertex( String nm )
	{
		name = nm; 
		adj = new LinkedList<Vertex>( ); 
		reset( ); 
	}

	public void reset( )
	{ 
		dist = Graph.INFINITY; 
		prev = null;
	}

	@Override
	public String toString() {
		return name;
	}


	@Override
	public int compareTo(Vertex vertex) {
		//For sorting alphabetical order
		return vertex.getName().compareTo(this.name);
	}
	
	public static Comparator<Vertex> vertexDistanceComparator = new Comparator<Vertex>(){

		@Override
		public int compare(Vertex o1, Vertex o2) {
			if(o1.getDist() > o2.getDist()){
				return 1;
			}else if(o1.getDist() < o2.getDist()){
				return -1;
			}else{
				return 0;
			}
		}
		
	};
	
	



}
