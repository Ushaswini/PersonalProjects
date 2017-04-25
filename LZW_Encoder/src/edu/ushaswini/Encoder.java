/*
 * VINNAKOTA VENKATA RATNA USHASWINI
 * 800983632
 * */
package edu.ushaswini;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.HashMap;

public class Encoder {
	
	private HashMap< String, Integer> table;
	
	private int bitLength;
	
	private double MAX_TABLE_SIZE;	
	
	
	public Encoder(int bitLength) {
		super();	
	
		this.bitLength = bitLength;
		MAX_TABLE_SIZE = Math.pow(2, this.bitLength);
		//Initialize hash map for individual characters
		initializeHashMap();
	}

	public boolean encode(String fileName, Charset encoding){
		
		boolean isEncodingSuccessful = false;
		
		String SYMBOL,STRING = "";
		Writer writer = null;
		Reader reader = null;
		int r;
		String outputFile = "";
		
		try{
			// Output file name is input file name with lzw extension
			outputFile = fileName.split("\\.")[0]+ ".lzw";
			
			reader = new BufferedReader(new InputStreamReader ( new FileInputStream(new File(fileName))));
			writer = new BufferedWriter(new OutputStreamWriter ( new FileOutputStream (outputFile),encoding));

			System.out.println("*** Encoding started....");

			//while there are still input symbols
			while((r = reader.read()) != -1){
				
				// get input symbol
				SYMBOL =  Character.toString((char) r );
				
				//if STRING + SYMBOL is present in table				
				if(table.containsKey(STRING + SYMBOL))
				{
					STRING = STRING + SYMBOL;
				}
				else
				{
					//Output the code for new symbol
					writer.write(table.get(STRING));
					System.out.print(table.get(STRING) + " ");
					//If table is not full
					if(table.size() < MAX_TABLE_SIZE)
					{
						//add new code to table
						table.put(STRING + SYMBOL, table.size());
					}
					STRING = SYMBOL;
				}
			}
			//Output STRING
			writer.write(table.get(STRING));
			System.out.print(table.get(STRING) + " ");
			System.out.println();
			isEncodingSuccessful = true;
			
		}
		catch(Exception oExcep)
		{
			isEncodingSuccessful = false;
			//oExcep.printStackTrace();
		}
		finally
		{
			try 
			{
				if ( writer != null) writer.close();
			} 
			catch (Exception e) 
			{
				isEncodingSuccessful = false;
				//e.printStackTrace();
			}
		}
		return isEncodingSuccessful;
	}

	private void initializeHashMap(){
		
		table = new HashMap<String, Integer>();
		
		for(int i = 0; i <= 255; i++){
			
			String character = Character.toString((char)i);
			table.put(character, i);
		}
		
	}

}
