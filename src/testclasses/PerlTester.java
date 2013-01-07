package testclasses;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Scanner;

import com.fisherevans.twc.tools.PerlParser;


public class PerlTester
{
	public PerlTester() throws IOException
	{
		try
		{
			BufferedReader input = PerlParser.parseLDR("forest_test.ldr");
			String line;
			while((line = input.readLine()) != null)
			{
				System.out.println(line);
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws IOException
	{
		new PerlTester();
	}
}
