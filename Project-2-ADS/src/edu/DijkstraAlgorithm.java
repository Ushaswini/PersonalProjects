/*
 * VINNAKOTA VENKATA RATNA USHASWINI
 * 800983632
 * */

package edu.ushaswini;


import java.util.NoSuchElementException;
import edu.ushaswini.Vertex.VisitStatus;


/*
 * Implementation of Dijkstra's algorithm to find shortest path between two vertices of a graph
 * 
 * */
public class DijkstraAlgorithm {

	private Graph graph; // Graph on which algorithm is to be performed
	
	private BinaryHeap vertexes; // Priority Queue to sort vertices based on their distance from source vertex
	
	public DijkstraAlgorithm(Graph graph) {
		this.graph = graph;
	}

	//Algorithm to execute on source vertex
	public void executeAlgorithm(Vertex source,Vertex destination){
		
		
		if(source == null){
			throw new NoSuchElementException( "Start vertex not found" );
		}
		this.resetAllVertexes();
		
		int index = vertexes.getVertexPosition(source);
		
		//Initialize start vertex distance to zero and status as DISCOVERED
		this.vertexes.increasePriority(index, 0);
		
		source.setVisitStatus(VisitStatus.DISCOVERED);
		
		
		Vertex min;
		
		//Extract the vertex with minimum distance from start vertex from heap
		
		while((min = this.vertexes.extractMin()) != null){
			
			for(Edge edge : min.getAdjacent()){	
				
				//Consider only edges among adjacent edges which are not down and vertices which are not down
				
				if(!edge.isDown() && (!edge.getDestination().isDown())&& !(edge.getDestination().getVisitStatus() == VisitStatus.FINISHED)){
					
					// If new distance to vertex is less than the vertex's present distance which was calculated previously 
					// update the distance and increase priority 
					if((min.getDist() + edge.getTransmission_time() < edge.getDestination().getDist())){
						
						edge.getDestination().setPrev(min);
						int position = vertexes.getVertexPosition(edge.getDestination());

						vertexes.increasePriority(position, min.getDist() + edge.getTransmission_time() );
					}
					
					//Set the status of vertex to DISCOVERED
					
					edge.getDestination().setVisitStatus(VisitStatus.DISCOVERED);
				}
			}
			
			//Set the status of vertex as FINISHED once all adjacent edges are done
			
			min.setVisitStatus(VisitStatus.FINISHED);	
			
		}
	}

	
	
	/**
     * Initializes priority queue with all vertices in graph after reset them
     */
    void resetAllVertexes( )
    {
    	this.vertexes = new BinaryHeap();
        for( Vertex v : graph.getVertexMap().values( ) ){
            v.reset( );
            this.vertexes.insert(v);
        }
        
    }

}


