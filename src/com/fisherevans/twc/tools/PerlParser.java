package com.fisherevans.twc.tools;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.Scanner;
import java.util.concurrent.Executor;

public class PerlParser
{
	private enum OS { Windows, Linux, Mac }
	private static OS _os = null;
	
	public static BufferedReader parseLDR(String file) throws IOException
	{
		BufferedReader parsed = null, err = null;
		
		switch(getOS())
		{
			case Windows:
			{
				Runtime rt = Runtime.getRuntime();
				String command = "\"perl.exe\" " + file;
				String workingDir = System.getProperty("user.dir") + "\\res\\configs\\";
				//System.out.println("Running In: " + workingDir);
				//System.out.println("Command: " + command);
				Process proc = rt.exec(command, new String[] {}, new File(workingDir));
				parsed = new BufferedReader(new InputStreamReader(proc.getInputStream()));
				err = new BufferedReader(new InputStreamReader(proc.getErrorStream()));
			}
		}
		
		if(err != null)
		{
			String errLine; int errs = 0;
			while((errLine = err.readLine()) != null)
			{
				errs++;
				System.out.println(errLine);
			}
			if(errs > 0)
			{
				System.exit(7);
			}
		}
		
		return parsed;
	}
	
	private static OS getOS()
	{
		if(_os == null)
		{
			String osName = System.getProperty("os.name");
			if(osName.contains("Windows"))
			{
				_os = OS.Windows;
			}
		}
		
		return _os;
	}
}
