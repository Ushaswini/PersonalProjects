/*
 * VINNAKOTA VENKATA RATNA USHASWINI
 * 800983632
 * */ 
package edu.ushaswini;

/*
 * 
 * Vertex class to represent vertex of graph
 * Has name, adjacent vertices, previous vertex on shortest path, distance ,
 * enum visit status , 
 * boolean to determine if vertex is down 
 * 
 * Comparators : Alphabetical sorting, Distance based sorting
 * 
 * */

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;


public class Vertex implements Comparable<Vertex>
{
	private String     name;   // Vertex name
	private List<Edge> adjacent;    // Adjacent vertices
	private boolean isDown = false; 
	
	
	
	private Vertex     prev;   // Previous vertex on shortest path
	private float        dist;   // Distance of path
	public enum VisitStatus{
		UNDISCOVERED,
		DISCOVERED,
		FINISHED
	}
	
	private VisitStatus visitStatus = VisitStatus.UNDISCOVERED;	

	public VisitStatus getVisitStatus() {
		return visitStatus;
	}

	public void setVisitStatus(VisitStatus visitStatus) {
		this.visitStatus = visitStatus;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	

	public List<Edge> getAdjacent() {
		return adjacent;
	}

	public void setAdjacent(List<Edge> adjacent) {
		this.adjacent = adjacent;
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
		adjacent = new LinkedList<Edge>( ); 
		reset( ); 
	}

	public void reset( )
	{ 
		dist = Graph.INFINITY; 
		prev = null;
		visitStatus = VisitStatus.UNDISCOVERED;
	}

	@Override
	public String toString() {
		return name;
	}


	@Override
	public int compareTo(Vertex vertex) {
		return Float.compare(this.getDist(), vertex.getDist());
	}
	
	public static Comparator<Vertex> vertexNameSorter = new Comparator<Vertex>() {
		
		@Override
		public int compare(Vertex arg0, Vertex arg1) {
			return arg0.getName().compareTo(arg1.getName());
		}
	};
	
	public static Comparator<Vertex> vertexDistanceComparator = new Comparator<Vertex>(){

		@Override
		public int compare(Vertex o1, Vertex o2) {
			
			return Float.compare(o1.getDist(), o2.getDist());
		}
		
	};	

	public void resetVistedStatus() {
		this.visitStatus = VisitStatus.UNDISCOVERED;
	}

}
