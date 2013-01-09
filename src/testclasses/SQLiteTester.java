package testclasses;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;
import com.fisherevans.twc.tools.DBHandler;

// http://code.google.com/p/sqlite4java/
// http://sourceforge.net/projects/sqlitebrowser/?source=dlp
public class SQLiteTester
{
	public SQLiteTester() throws SQLiteException
	{
		DBHandler.setSaveName("res/saves/save001.sqlite");
		
		DBHandler.openDB();

		System.out.println(DBHandler.getAttribText("test"));
		DBHandler.setAttrib("test4", "does this work?");
		System.out.println(DBHandler.getAttribText("test3"));
		System.out.printf("There are %d %s that are %s to the number %f.\n",
				DBHandler.getAttribInt("test2"), DBHandler.getAttribText("test2"), DBHandler.getAttribBoolean("test2")?"true":"false", DBHandler.getAttribFloat("test2"));
		
		DBHandler.closeDB();
	}
	
	public static void main(String[] args)
	{
		try
		{
			new SQLiteTester();
		}
		catch (SQLiteException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
