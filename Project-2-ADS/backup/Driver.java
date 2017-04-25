package edu.ushaswini;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

public class Driver {

	static String instructions = "**** To add edge to graph enter 1" + "\n" +
			"To delete edge from graph enter 2" + "\n" +
			"To mark an edge down enter 3" + "\n" +
			"To mark an edge up enter 4" + "\n" +
			"To mark an vertex down enter 5" + "\n" +
			"To mark an vertex up enter 6" + "\n" + 
			"To print path to a destination edge enter 7" + "\n" +
			"To print entire graph enter 8" + "\n" +
			"To print reachable vertices enter 9" + "\n" +
			"To simply quit the program enter 10 ****";



	public static void main(String[] args) {

		Map<String,Vertex> vertexMap = new HashMap<String,Vertex>( );
		
		Map<String,Edge> edgesMap = new HashMap<String,Edge>( );		

		BufferedReader br = null;
		
		try
		{			
			br = new BufferedReader(new InputStreamReader(System.in));

			//Accept inputs from user and build graph			
			System.out.println("Enter graph information as --> graph filename");
			String input = br.readLine();
			String[] inputs = input.split(" ");
			String graphFileName = inputs[1];

			//System.out.printf("graph File name",graphFileName);

			FileReader fin = new FileReader( graphFileName );
			Scanner graphFile = new Scanner( fin );

			// Read the edges and insert
			String line;
			while( graphFile.hasNextLine( ) )
			{
				line = graphFile.nextLine( );
				StringTokenizer st = new StringTokenizer( line );

				try
				{
					if( st.countTokens( ) != 3 )
					{
						System.err.println( "Skipping ill-formatted line " + line );
						continue;
					}
					String source_name  = st.nextToken( );
					
					String dest_name    = st.nextToken( );
					
					float weight = Float.parseFloat(st.nextToken());

					Vertex source = vertexMap.get(source_name);

					Vertex destination = vertexMap.get(dest_name);
					
					if(source == null){	
						source = new Vertex(source_name);
						vertexMap.put(source_name, source);
					}

					if(destination == null){	
						destination = new Vertex(dest_name);
						vertexMap.put(dest_name, destination);
					}

					source.getAdj().add(destination);
					destination.getAdj().add(source);

					Edge edge_f = edgesMap.get(source_name+dest_name);

					if(edge_f == null){
						edge_f = new Edge(source,destination,weight);
						edgesMap.put(source_name + dest_name, edge_f);
					}


					Edge edge_b = edgesMap.get(dest_name + source_name);
					if(edge_b == null){
						edge_b = new Edge(destination,source,weight);
						edgesMap.put(dest_name + source_name, edge_b);
					}
				}catch( NumberFormatException e ){ 
					System.err.println( "Skipping ill-formatted line " + line );
				}
			}

			Graph inputGraph = new Graph( vertexMap,edgesMap);

			DijkstraAlgorithm algo = new DijkstraAlgorithm(inputGraph);

			System.out.println("File reading and Building Graph is completed");

			System.out.println(instructions);

			char _input = 'Y';



			do{
				System.out.println("Enter your choice");
				int inputChoice = Integer.parseInt(br.readLine());

				switch(inputChoice){
				case 1:{
					System.out.println("Enter input in the form ** addedge tailvertex headvertex transmit time **");
					StringTokenizer st = new StringTokenizer(br.readLine() );
					if(st.countTokens() != 4){
						System.out.println("ill- formatted input");
					}else{
						st.nextToken();
						inputGraph.addEdge(st.nextToken(), st.nextToken(), Float.parseFloat(st.nextToken()));
					}
					break;
				}	
				case 2: {
					System.out.println("Enter input in the form ** deleteedge tailvertex headvertex **");
					StringTokenizer st = new StringTokenizer(br.readLine() );
					if(st.countTokens() != 3){
						System.out.println("ill- formatted input");
					}else{
						st.nextToken();
						inputGraph.deleteEdge(st.nextToken(), st.nextToken());
					}
					break;
				}
				case 3:  {
					System.out.println("Enter input in the form ** edgedown tailvertex headvertex **");
					StringTokenizer st = new StringTokenizer(br.readLine() );
					if(st.countTokens() != 3){
						System.out.println("ill- formatted input");
					}else{
						st.nextToken();
						inputGraph.markEdgeDown(st.nextToken(), st.nextToken());
					}
					break;
				}
				case 4: {
					System.out.println("Enter input in the form ** edgeup tailvertex headvertex **");
					StringTokenizer st = new StringTokenizer(br.readLine() );
					if(st.countTokens() != 3){
						System.out.println("ill- formatted input");
					}else{
						st.nextToken();
						inputGraph.markEdgeUp(st.nextToken(), st.nextToken());
					}
					break;
				}
				case 5: {
					System.out.println("Enter input in the form ** vertexdown vertex **");
					StringTokenizer st = new StringTokenizer(br.readLine() );
					if(st.countTokens() != 2){
						System.out.println("ill- formatted input");
					}else{
						st.nextToken();
						inputGraph.markVertexDown(st.nextToken());
					}
					break;
				}
				case 6: {
					System.out.println("Enter input in the form ** vertexup vertex **");
					StringTokenizer st = new StringTokenizer(br.readLine() );
					if(st.countTokens() != 2){
						System.out.println("ill- formatted input");
					}else{
						st.nextToken();
						inputGraph.markVertexUp(st.nextToken());
					}
					break;
				}
				case 7: {
					System.out.println("Enter input in the form ** path from_vertex to_vertex **");
					StringTokenizer st = new StringTokenizer(br.readLine() );
					if(st.countTokens() != 3){
						System.out.println("ill- formatted input");
					}else{
						st.nextToken();

						String source = st.nextToken();
						String destination = st.nextToken();

						algo.execute(vertexMap.get(source));


						LinkedList<Vertex> path = algo.getPath(vertexMap.get(destination));

						for (Vertex vertex : path) {
							System.out.print(vertex + " ");
						}
						System.out.println(algo.getShortestDistance(vertexMap.get(destination)));
					}
					break;
				}
				case 8: {
					inputGraph.printPath();
					break;
				}
				case 9: {
					inputGraph.printReachableVertices();
					break;
				}
				case 10:{
					System.exit(0);
				}
				default:System.out.println("Invalid input choice");


				}

				System.out.println("Do you want to continue? Enter Y if yes or N if no");
				_input = br.readLine().charAt(0);
				System.out.println(_input);


			}while(_input == 'Y');

		}catch(Exception oExcep)
		{
			//System.out.println("Error occured. Check input.");
			oExcep.printStackTrace();
		}
		finally
		{
			if( null != br)
			{
				try
				{
					br.close();
				} 
				catch (IOException e) 
				{
					//e.printStackTrace();
				}
			}
		}
	}
}
