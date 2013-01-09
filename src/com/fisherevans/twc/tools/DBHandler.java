package com.fisherevans.twc.tools;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

public class DBHandler
{
	private static SQLiteConnection _db;
	private static String _curSaveName;
	private static File _curSave;
	
	public static void setSaveName(String name)
	{
		_curSaveName = name;
	}
	
	public static String getSaveName()
	{
		return _curSaveName;
	}
	
	public static void openDB()
	{
		Logger.getLogger("com.almworks.sqlite4java").setLevel(Level.WARNING);
		_curSave = new File(_curSaveName);
		_db = new SQLiteConnection(_curSave);
		try
		{
			_db.open(true);
		}
		catch (SQLiteException e)
		{
			e.printStackTrace();
			System.exit(9);
		}
	}
	
	public static void closeDB()
	{
		_db.dispose();
	}
	
	public static void setAttrib(String name, String value)
	{
		//System.out.println("String - " + name + " = " + value + " - Present: " +isRowPresent(name));
		if(isRowPresent(name))
			updateAttrib(name, "text", value);
		else
			insertAttrib(name, "text", value);
	}
	
	public static void setAttrib(String name, int value)
	{
		if(isRowPresent(name))
			updateAttrib(name, "text", "" + value);
		else
			insertAttrib(name, "text", "" + value);
	}
	
	public static void setAttrib(String name, float value)
	{
		if(isRowPresent(name))
			updateAttrib(name, "text", "" + value);
		else
			insertAttrib(name, "text", "" + value);
	}
	
	public static void setAttrib(String name, boolean value)
	{
		if(isRowPresent(name))
			updateAttrib(name, "text", value?"1":"0");
		else
			insertAttrib(name, "text", value?"1":"0");
	}
	
	public static void updateAttrib(String name, String type, String value)
	{
		String statement = "UPDATE  global_attribs SET \"" + type + "\"=\"" + value + "\" WHERE \"name\"=\"" + name + "\"";
		try
		{
			SQLiteStatement st = _db.prepare(statement);
			st.stepThrough();
			st.dispose();
		}
		catch (SQLiteException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static void insertAttrib(String name, String type, String value)
	{
		String statement = "INSERT INTO global_attribs (\"name\", \"" + type + "\") VALUES (\"" + name + "\", \"" + value + "\")";
		try
		{
			SQLiteStatement st = _db.prepare(statement);
			st.stepThrough();
			st.dispose();
		}
		catch (SQLiteException e1)
		{
			e1.printStackTrace();
		}
	}
	
	public static boolean isRowPresent(String name)
	{
		String statement = "SELECT row_id FROM global_attribs WHERE \"name\"=\"" + name + "\"";
		try
		{
			SQLiteStatement st = _db.prepare(statement);
			int rows = 0;
			while(st.step()) { rows++; }
			System.out.println(rows);
			st.step();
			if(rows > 0)
			{
				return true;
			}
			st.dispose();
		}
		catch (SQLiteException e1)
		{
			e1.printStackTrace();
		}
		return false;
	}
	
	public static String getAttribText(String name)
	{
		String result = "";
		
		try
		{
		    SQLiteStatement st = getAttribStatement(name, "text");
			result = st.columnString(0);
			st.dispose();
		}
		catch (SQLiteException e1)
		{
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public static int getAttribInt(String name)
	{
		int result = 0;
		
		try
		{
		    SQLiteStatement st = getAttribStatement(name, "int");
			result = st.columnInt(0);
			st.dispose();
		}
		catch (SQLiteException e1)
		{
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public static float getAttribFloat(String name)
	{
		float result = 0;
		
		try
		{
		    SQLiteStatement st = getAttribStatement(name, "float");
			result = (float) st.columnDouble(0);
			st.dispose();
		}
		catch (SQLiteException e1)
		{
			e1.printStackTrace();
		}
		
		return result;
	}
	
	public static boolean getAttribBoolean(String name)
	{
		boolean result = false;
		
		try
		{
		    SQLiteStatement st = getAttribStatement(name, "boolean");
			result = st.columnInt(0) == 1;
			st.dispose();
		}
		catch (SQLiteException e1)
		{
			e1.printStackTrace();
		}
		
		return result;
	}
	
	private static SQLiteStatement getAttribStatement(String name, String col)
	{
	    SQLiteStatement st = null;
		try
		{
			st = _db.prepare("SELECT " + col + " FROM global_attribs WHERE name=\"" + name + "\"");
			if(st.columnCount() > 0)
			{
				st.step();
			}
		}
		catch (SQLiteException e)
		{
			e.printStackTrace();
		}
		
		return st;
	}
}
