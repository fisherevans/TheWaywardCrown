package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.AdventureState;

public class WaitAction extends AdventureAction
{
	private long _endTime;
	private int _time;

	public WaitAction(ActionManager as, int time)
	{
		super(as);
		_time = time;
	}

	@Override
	public void initAction()
	{
		_endTime = System.currentTimeMillis() + _time;
	}

	@Override
	public void reInitAction()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAction(int delta)
	{
		if(_endTime <= System.currentTimeMillis())
		{
			setComplete(true);
		}
	}

	@Override
	public void keyPressed(int key, char c)
	{
		
	}
	
}
