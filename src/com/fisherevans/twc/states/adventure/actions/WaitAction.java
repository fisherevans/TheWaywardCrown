package com.fisherevans.twc.states.adventure.actions;

import com.fisherevans.twc.states.adventure.AdventureState;

public class WaitAction extends AdventureAction
{
	private long _endTime;

	public WaitAction(AdventureState as, int time)
	{
		super(as);
		_endTime = System.currentTimeMillis() + time;
	}

	@Override
	public void startAction()
	{
	}

	@Override
	public void updateAction()
	{
		if(_endTime <= System.currentTimeMillis())
		{
			setComplete(true);
		}
	}
	
}
