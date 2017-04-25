package edu.ushaswini;

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
		// TODO Auto-generated method stub
		return edge.getDestination().compareTo(this.destination);
	}










}
