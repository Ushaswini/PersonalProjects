/*
 * VINNAKOTA VENKATA RATNA USHASWINI
 * 800983632
 * */

package edu.ushaswini;

import java.util.ArrayList;

/*
 * Implementation of Min Heap for vertices
 * with priority based on their distances from the source vertex*/

public class BinaryHeap {
	
	private ArrayList<Vertex> vertexes; //List of vertices
	
	//Initialize min heap with no elements
	public BinaryHeap(){
		this.vertexes = new ArrayList<Vertex>();
	}

	//Initialize binary tree with list of vertices and build min-heap
	public BinaryHeap(ArrayList<Vertex> vertexes){
		this.vertexes = vertexes;
		this.buildMinHeap();
	}
	
	//Convert a binary tree to a valid min-heap
	private void buildMinHeap(){
		
		int middle = (this.vertexes.size() + 1) /2 -1;
		
		for(int i = middle ; i>=0; i--){
			this.minHeapify(i);
		}
	}
	
	//Restore to min heap by floating element at given index to its valid position
	private void minHeapify(int index){
		
		int leftIndex = 2*index +1;
		int rightIndex = 2*index +2;
		
		int smallestIndex = index;
		
		if(leftIndex < this.vertexes.size() && this.vertexes.get(leftIndex).compareTo(this.vertexes.get(index)) < 0){
			smallestIndex = leftIndex;
		}
		
		if(rightIndex < this.vertexes.size() && this.vertexes.get(rightIndex).compareTo(this.vertexes.get(smallestIndex)) < 0){
			smallestIndex = rightIndex;
		}
		
		if(smallestIndex != index){
			exchange(smallestIndex, index);
			minHeapify(smallestIndex);
		}
	}
	
	//exchange both vertices in the array
	private void exchange(int indexFirst, int indexSecond){
		
		Vertex vertexFirst = this.vertexes.get(indexFirst);
		Vertex vertexSecond = this.vertexes.get(indexSecond);		
		
		this.vertexes.set(indexFirst, vertexSecond);		
		this.vertexes.set(indexSecond, vertexFirst);
	}
	
	//Increase the priority of vertex in min-heap
	public void increasePriority(int index, float distance){
		Vertex vertex = this.vertexes.get(index);
		vertex.setDist(distance);
		this.floatUp(index);
	}
	
	//Float the vertex up the heap
	private void floatUp(int index){
		
		int parent = ((index+1)/2)-1;
		while(parent >= 0 && this.vertexes.get(parent).compareTo(this.vertexes.get(index)) > 0){
			exchange(parent, index);
			index = parent;
			parent = ((index + 1) / 2) - 1;
		}
	}
	
	//Insert new vertex to the heap and float up
	void insert(Vertex vertex){
		this.vertexes.add(vertex);
		this.floatUp(this.vertexes.size()-1);
	}
	
	//Extract the vertex with least distance from the source vertex
	
	public Vertex extractMin(){
		if(this.vertexes.size() == 0){
			return null;
		}
		
		Vertex min = this.vertexes.get(0);
		
		Vertex last = this.vertexes.get(this.vertexes.size() - 1);
		
		this.vertexes.set(0, last);
		
		this.vertexes.remove(vertexes.size() - 1);
		
		this.minHeapify(0);
		
		return min;
		
	}
	
	//Gives position of vertex from list
	public int getVertexPosition(Vertex vertex){
		return this.vertexes.indexOf(vertex);
	}
	
	
}
