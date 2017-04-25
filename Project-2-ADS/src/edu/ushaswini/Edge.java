/*
 * VINNAKOTA VENKATA RATNA USHASWINI
 * 800983632
 * */
package edu.ushaswini;

import java.util.Comparator;

/*
 * 
 * Edge class to represent edge in a graph 
 * 
 * 
 * */


public class Edge implements Comparable<Edge>{

	private Vertex source;
	private Vertex destination;
	private float transmission_time;
	private boolean isDown;


	public Edge(Vertex source, Vertex destination, float transmission_time) {
		super();
		this.source = source;
		this.destination = destination;
		this.transmission_time = transmission_time;
	}


	public Vertex getSource() {
		return source;
	}


	public void setSource(Vertex source) {
		this.source = source;
	}


	public Vertex getDestination() {
		return destination;
	}


	public void setDestination(Vertex destination) {
		this.destination = destination;
	}


	public float getTransmission_time() {
		return transmission_time;
	}


	public void setTransmission_time(float transmission_time) {
		this.transmission_time = transmission_time;
	}


	public boolean isDown() {
		return isDown;
	}


	public void setDown(boolean isDown) {
		this.isDown = isDown;
	}


	@Override
	public int compareTo(Edge edge) {
		return edge.getDestination().getName().compareTo(this.destination.getName());
	}
	
	public static Comparator<Edge> edgeDestinationNameComparator = new Comparator<Edge>() {
		
		@Override
		public int compare(Edge o1, Edge o2) {
			
			return o1.getDestination().getName().compareTo(o2.getDestination().getName());
		}
	};
	
	/**
	 * Prints edge in specified format
	 */
	@Override
	public String toString() {
		String output = "  " + this.getDestination().getName() + " " + this.getTransmission_time();
		
		if(isDown){
			output += " DOWN";
		}
		
		return output;
	}	
	
	/**
	 * Two edges are equal when both their start and end vertices are same
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Edge other = (Edge) obj;
		if (destination == null) {
			if (other.destination != null)
				return false;
		} else if (!destination.equals(other.destination))
			return false;
		if (source == null) {
			if (other.source != null)
				return false;
		} else if (!source.equals(other.source))
			return false;
		return true;
	}

}
