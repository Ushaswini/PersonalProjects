/*
 * VINNAKOTA VENKATA RATNA USHASWINI
 * 800983632
 * */

package edu.ushaswini;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

public class Driver {

	public static void main(String[] args) {
		
		BufferedReader br = null;
		try
		{
			
			br = new BufferedReader(new InputStreamReader(System.in));
			
			//Accept inputs from user and calculate file name and bit length	
			System.out.println("Enter Input in the format --> java Decoder textfilename(with path) bitlength");
			String input = br.readLine();
			String[] inputs = input.split(" ");
			String textFileName = inputs[2];
			String bitLength = inputs[3];
			
			//Output user choice
			System.out.println();
			System.out.println("Input file you entered is " + textFileName + " and bit length is " + bitLength);
			System.out.println();
			
			int bitLengthInt = Integer.parseInt(bitLength);
			Charset encoding = Charset.forName("UTF-16BE");
			
			//Create decoder object to decode
			Decoder decoder = new Decoder(bitLengthInt);
			

			boolean isDecodingSuccessful = decoder.decode(textFileName, encoding);

			if(isDecodingSuccessful){
				System.out.println(".... Decoding Completed ***");
				System.out.println("Output File saved at absolute path of Input file.");
			}else{
				System.out.println("Error occured. Check input.");
			}
			
		}
		catch(Exception oExcep)
		{
			System.out.println("Error occured. Check input.");
			//oExcep.printStackTrace();
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
					System.out.println("Error occured. Check input.");
					//e.printStackTrace();
				}
			}
		}
	}

}
