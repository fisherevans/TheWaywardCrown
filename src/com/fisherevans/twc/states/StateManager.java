package com.fisherevans.twc.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.states.starting.StartingState;

public class StateManager
{
	public enum States { Starting, MainMenu, NewGame, Adventure, PauseMenu, Combat }
	
	private State _currentState;
	private GameDriver _gd;
	
	public StateManager(GameDriver gd)
	{
		_gd = gd;
		
		setState(new StartingState(this));
	}
	
	public void setState(State newState)
	{
		_currentState = newState;
		_gd.setInputTarget(_currentState);
	}

	public void updateState(GameContainer gc, int delta) throws SlickException
	{
		_currentState.update(gc, delta);
	}

	public void renderState(GameContainer gc, Graphics gfx) throws SlickException
	{
		_currentState.render(gc, gfx);
	}
}
