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

public class Decoder {
	
	private HashMap<Integer,String> table;
	
	private int bitLength;
	
	private double MAX_TABLE_SIZE;

	public Decoder(int bitLength) {
		super();
		this.bitLength = bitLength;
		MAX_TABLE_SIZE = Math.pow(2, this.bitLength);
		//Initialize hash map for individual characters
		initializeHashMap();
	}
	
	public boolean decode(String fileName, Charset encoding){
		
		boolean isDecodingSuccessful = false;
		
		Writer writer = null;
		Reader reader = null;
		String outputFile = "";
		String STRING, NEW_STRING;
		int r;
		
		try
		{
			//Output file name is inputfilename_decoded.txt
			outputFile = fileName.split("\\.")[0]+ "_decoded.txt";

			reader = new BufferedReader(new InputStreamReader ( new FileInputStream (new File(fileName)), encoding));
			writer = new BufferedWriter(new OutputStreamWriter ( new FileOutputStream (outputFile)));
			
			System.out.println("*** Decoding started....");

			//Initialize r, STRING
			r = reader.read();
			STRING = table.get(r);
			
			//Output STRING
			writer.write(STRING);
			System.out.print(STRING + " ");
			
			//while there are still input codes
			while((r = reader.read()) != -1)
			{
				
				if(!table.containsKey(r))
				{
					NEW_STRING = STRING + STRING.charAt(0);
				}
				else
				{
					NEW_STRING = table.get(r);
				}
				
				System.out.print(NEW_STRING + " ");
				writer.write(NEW_STRING);
				
				//If table is not full
				if(table.size() < MAX_TABLE_SIZE)
				{
					//Update table with new string
					table.put(table.size(),STRING + NEW_STRING.charAt(0));
				}
				
				STRING = NEW_STRING;				
			}
			System.out.println();
			isDecodingSuccessful = true;
		}
		catch(Exception oExcep)
		{
			isDecodingSuccessful = false;
			//System.out.println("Error occured. Check input");
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
				isDecodingSuccessful = false;
				//System.out.println("Error occured. Check input");
				//e.printStackTrace();
			}
		}
		return isDecodingSuccessful;
	}
	
	
	
	private void initializeHashMap(){
		
		table = new HashMap<Integer, String>();
		
		for(int i = 0; i <= 255; i++){
			
			String character = Character.toString((char)i);
			table.put(i,character);
		}
		
	}
	

}
