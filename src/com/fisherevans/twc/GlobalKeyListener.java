package com.fisherevans.twc;

import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;

public class GlobalKeyListener implements KeyListener
{
	private GameDriver _gd; // The parent driver onject
	
	/** Creates the listener
	 * @param gd The parent driver
	 */
	public GlobalKeyListener(GameDriver gd)
	{
		_gd = gd;
	}

	@Override
	public void inputEnded()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void inputStarted()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isAcceptingInput()
	{
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public void setInput(Input input)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key, char c)
	{
		//System.out.println("Key: " + key + " - Char: " + c);
		
		if(key == 87)
		{
			_gd.getFrame().cycleReso();
		}
	}

	@Override
	public void keyReleased(int key, char c)
	{
		// TODO Auto-generated method stub
		
	}

}
