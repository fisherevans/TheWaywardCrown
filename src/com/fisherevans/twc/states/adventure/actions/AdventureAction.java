package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.AdventureState;

public abstract class AdventureAction
{
	private boolean _isComplete = false;
	private ActionManager _am;
	private boolean _hasStarted = false;
	
	public AdventureAction(ActionManager am)
	{
		_am = am;
	}

	public boolean isComplete()
	{
		return _isComplete;
	}
	
	public void start()
	{
		_hasStarted = true;
		initAction();
	}
	
	public void end()
	{
		_hasStarted = false;
		_isComplete = false;
		reInitAction();
	}
	
	public abstract void initAction();
	
	public abstract void reInitAction();
	
	public abstract void updateAction(int delta);
	
	public abstract void keyPressed(int key, char c);

	public void setComplete(boolean isComplete)
	{
		_isComplete = isComplete;
	}

	public ActionManager getAM()
	{
		return _am;
	}

	public boolean hasStarted()
	{
		return _hasStarted;
	}
}
