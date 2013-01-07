package testclasses;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.almworks.sqlite4java.SQLiteConnection;
import com.almworks.sqlite4java.SQLiteException;
import com.almworks.sqlite4java.SQLiteStatement;

// http://code.google.com/p/sqlite4java/
public class SQLiteTester
{
	public SQLiteTester() throws SQLiteException
	{
		File saveFile = new File("res/saves/save001.db");
		Logger.getLogger("com.almworks.sqlite4java").setLevel(Level.WARNING);
		SQLiteConnection db = new SQLiteConnection(saveFile);
	    db.open(true);
	    
	    SQLiteStatement st = db.prepare("SELECT * FROM save_info");

    	System.out.printf(" %-3s | %20s | %-20s\n", "ROW", "INFO NAME", "INFO VALUE");
    	System.out.print("-----+----------------------+----------------------\n");
	    int step = 0;
	    while(st.step())
	    {
	    	System.out.printf(" %-3s | %20s | %-60s\n", step++, st.columnString(0), st.columnString(1));
	    }
	    
	    st.dispose();
	    db.dispose();
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
