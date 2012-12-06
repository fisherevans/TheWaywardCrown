package com.fisherevans.twc.states.adventure.actions;

import com.fisherevans.twc.states.adventure.AdventureState;

public abstract class AdventureAction
{
	private boolean _isComplete = false;
	private AdventureState _as;
	private boolean _hasStarted = false;
	
	public AdventureAction(AdventureState as)
	{
		_as = as;
	}

	public boolean isComplete()
	{
		return _isComplete;
	}
	
	public abstract void startAction();
	
	public abstract void updateAction();

	public void setComplete(boolean isComplete)
	{
		_isComplete = isComplete;
	}

	public AdventureState getAS()
	{
		return _as;
	}

	public void setAS(AdventureState as)
	{
		_as = as;
	}

	public boolean isHasStarted()
	{
		return _hasStarted;
	}

	public void setHasStarted(boolean hasStarted)
	{
		_hasStarted = hasStarted;
	}
}
