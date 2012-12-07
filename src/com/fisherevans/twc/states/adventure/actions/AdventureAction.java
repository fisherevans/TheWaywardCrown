package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.ActionManager;
import com.fisherevans.twc.states.adventure.AdventureState;

public abstract class AdventureAction
{
	private boolean _isComplete = false;
	private ActionManager _am;
	private boolean _hasStarted = false;
	private boolean _blockInput;
	
	public AdventureAction(ActionManager am, boolean blockInput)
	{
		_am = am;
		_blockInput = blockInput;
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
	
	public abstract void render(Graphics gfx);
	
	public abstract boolean keyPressed(int key, char c);

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
	
	public void setBlockingInput(boolean blockInput)
	{
		_blockInput = blockInput;
	}
	
	public boolean isBlockingInput()
	{
		return _blockInput;
	}
}
