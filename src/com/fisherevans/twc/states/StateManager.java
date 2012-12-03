package com.fisherevans.twc.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.states.starting.StartingState;

public class StateManager
{
	private State _currentState;
	private static GameDriver _gd;
	
	public StateManager(GameDriver gd)
	{
		_gd = gd;
		
		setState(new StartingState(this, _gd.getInput()));
	}
	
	public void setState(State newState)
	{
		_currentState = newState;
		_gd.setInputTarget(_currentState);
	}
	
	public static GameDriver getGD()
	{
		return _gd;
	}

	public void updateState(GameContainer gc, int delta) throws SlickException
	{
		_currentState.update(gc, delta);
	}

	public void renderState(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.setColor(new Color(1f, 1f, 1f));
		_currentState.render(gc, gfx);
	}
}
