/*
VINNAKOTA VENKATA RATNA USHASWINI
*/

ITCS 6114 : Programming Project-2

Programming Language: Java
Java Version: 8
JRE Version: 1.8

/*Project Structure*/

Project has 7 files.

1)Vertex.java - Vertex class for representing vertices of graph

2)Edge.java - Edge class for representing edges of a graph

3)Graph.java - Graph class that provides all related operations that can be performed

4)BinaryHeap.java - Binaryheap class to provide all minheap operations

5)DijkstrasAlgorithm.java - Dijkstra's algorithm implementation

6)ReachableAlgorithm.java - Reachable Algorithm implemenattion to find set of reachable vertices from a given vertex

7)Driver.java - main class for reading input network and query files and initialiing output writer.

/*Design of each class*/

Vertex class: Two vertices are said to be equal when they have the same name

	Properties:
		1) name - Name of the vertex
		2)adjacent - list of adjacent edges
		3)isDown - boolean to indicate if vertx is down
		4)prev - previous vertex on shortest path
		5)dist - float to represent distance from source in shortest path 
		6)VisitStatus - enum to represent visit status in graph traversing
	Methods:
		1)reset - rest status of vertex to default values
		2)vertexNameComparator - comparator to compare vertices based on their names
		
Edge class: Two edges are said to be equal when their source and destination vertices are same

	Properties:
		1)source - source vertex
		2)destination - destination vertex
		3)transmission_time - float to represent weight of the edge
		4)isDown - boolean to represent if the edge is down
	Methods:
		1)destinationVertexNameComparator: comparator to sort edges based on destination vertex names

Graph class:
	
	Properties: vertexMap - HashMap to hold vertices against its name
	Methods:
		1) addEdge : Add edge between source source and vertex if no edge is present or updates it's transmission time if one is present already
		2) deleteEdge : Delete edge between source and destination vertices
		3)markEdgeDown : marks edge down
		4)markEdgeUp: marks edge up
		5)markVertexUp : marks vertex up
		6)markVertexDown : marks vertex down
		7)printReachableVertices: prints reachable vertices for each vertex in graph
		8)getVertex : returns vertex for the given name if present already in hashmap otherwise creates one and returns
		9)printGraph : prints current graph status
		10)printShortestPathWithTransmissionTime: prints shortest path between source and destination along with transmission time for path
		
BinaryHeap class:
	
	Properties: vertexes - List of vertices
	
	Methods: 
		1)insert :  Insert new vertex into binary heap and heapify the binary tree
		2)increasePriority : Increase priority of vertex after updating its distance
		3)extractMin : Extract the min distance vertex from the heap and restore heap properties

	Description: Implementation of MinHeap priority queue to use in Dijkstra's algorithm.

DijkstrasAlgorithm class:

	Properties: 
		1)graph - Graph object on which algorithm is to be executed
		2)vertexes - Min heap of vertices based on distance from source vertex
	
	Methods: 
		1)executeAlgorithm : algorithm to find shortest path from source
		2)resetAllVertices : reset vertices properties to default value
			
ReachableAlgorithm class:

	Properties:
		1)graph - Graph object on which algorithm is to be executed
		2)reachableVertexes - list of reachable vertices from source vertex
		3)startVertex - source vertex on which algorithm is to be executed
 	Description:

		Algorithm first reset all vertices status to UNDISCOVERED. This step takes O(|V|) time.
		Set the discovered vertex status to DISCOVERED
		Check all the outgoing edges ACTIVE in adjacent list of edges of given source vertex.
		If the vertex is active and is being visited for the first time, add it to the reachable list and recursively execute algorithm starting from that discovered vertex.
		When all outgoing edges from the source vertex is processed, mark the vertex's Visit status as FINISHED.
		Overall this algorithm take O(|V| + |E|) time for a given vertex.

Driver class:

	main class to initiate program execution.
	Input arguments to program are <networkfilename>, <queryfilename> and <outputfilename>

Set of acceptable commands:

1) addedge tailvertex headvertex transmittime 
2) deleteedge tailvertex headvertex
3) edgedown tailvertex headvertex
4) edgeup tailvertex headvertex	
5) vertexdown vertex 
6) vertexup vertex 
7) path from_vertex to_vertex
8) print
9) reachable
10)quit
