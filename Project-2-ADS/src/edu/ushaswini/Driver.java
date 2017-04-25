/*
  * VINNAKOTA VENKATA RATNA USHASWINI
 * 800983632
 * */

package edu.ushaswini;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.NoSuchElementException;
import java.util.StringTokenizer;



public class Driver {

	public static void main(String[] args) {

	
		Writer writer = null;
		BufferedReader networkFileReader = null;
		BufferedReader queryFileReader = null;
		Graph inputGraph;	
		
		if(args.length != 3){
			System.err.println("Insufficient number of inputs.");
			return;
		}
		
		String networkFileName = args[0];
		String queryFileName = args[1];
		String outputFileName = args[2];

		try
		{			

			networkFileReader = new BufferedReader(new FileReader (networkFileName));
			queryFileReader = new BufferedReader(new FileReader(queryFileName));			
			writer = new BufferedWriter(new OutputStreamWriter ( new FileOutputStream (outputFileName)));

			//Create graph object and build it as you read network file.
			inputGraph   = new Graph(writer);
			
			// Read the edges and insert in graph
			String line;
			while( (line = networkFileReader.readLine()) != null ) 
			{
				StringTokenizer st = new StringTokenizer( line );

				try
				{
					if( st.countTokens( ) != 3 )
					{
						writer.write("Skipping ill-formatted line " + line + System.lineSeparator());
						System.err.println( "Skipping ill-formatted line " + line );
						continue;
					}
					String source_name  = st.nextToken( );
					String dest_name    = st.nextToken( );
					float weight = Float.parseFloat(st.nextToken());
					
					//Add edge between source and destination in both directions.
					
					inputGraph.addEdge(source_name, dest_name, weight);
					inputGraph.addEdge(dest_name, source_name, weight);				

					
				}catch( NumberFormatException e ){ 
					
					writer.write("Skipping ill-formatted line " + line + System.lineSeparator());
					System.err.println( "Skipping ill-formatted line " + line );
					return;
				} 
			}

			writer.write("Reading File and Building Graph is completed");
			System.out.println("Reading File and Building Graph is completed");			
			
			String lineCommand;
			
			/*
			 * Read command from query file one by one and execute them
			 * */
			
			while((lineCommand = queryFileReader.readLine()) != null){
				processRequest(lineCommand, inputGraph, writer);
			}
		}catch(Exception oExcep){
			
			
		}finally{
			try 
			{
				if ( writer != null) writer.close();
				if(networkFileReader != null){
					networkFileReader.close();
				}
				if(queryFileReader != null){
					queryFileReader.close();
				}
			} 
			catch (Exception e) 
			{
				//e.printStackTrace();
			}
		}
		
		
	}


	private static void processRequest(String input, Graph inputGraph, Writer writer){
		try{


			StringTokenizer st = new StringTokenizer(input);
			String keyword = st.nextToken();

			if(keyword.equals("addedge")){
				inputGraph.addEdge(st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()));

			}else if(keyword.equals("deleteedge")){
				inputGraph.deleteEdge(st.nextToken(), st.nextToken());

			}else if(keyword.equals("edgedown")){
				inputGraph.markEdgeDown(st.nextToken(), st.nextToken());

			}else if(keyword.equals("edgeup")){
				inputGraph.markEdgeUp(st.nextToken(), st.nextToken());

			}else if(keyword.equals("path")){
				
				String source = st.nextToken();
				String destination = st.nextToken();
				inputGraph.printShortestPathWithTransmissionTime(inputGraph.getVertexMap().get(source),inputGraph.getVertexMap().get(destination));
				
			}else if(keyword.equals("print")){
				inputGraph.printGraph();

			}else if(keyword.equals("vertexdown")){
				inputGraph.markVertexDown(st.nextToken());

			}else if(keyword.equals("vertexup")){
				inputGraph.markVertexUp(st.nextToken());

			}else if(keyword.equals("reachable")){
				inputGraph.printReachableVertices();

			}else if(keyword.equals("quit")){
				System.exit(0);
				writer.close();

			}else{
				writer.write("Invalid input choice");
				System.out.println("Invalid input choice");

			}
		}catch( NoSuchElementException e )
		{ 
			
		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally
		{
		}
		
		
	}
}
