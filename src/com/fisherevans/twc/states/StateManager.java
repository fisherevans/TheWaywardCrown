package com.fisherevans.twc.states;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.states.starting.StartingState;

public class StateManager
{
	private State _currentState; // The current state to apply updates and renders to
	private static GameDriver _gd; // The game driver using this manager
	
	/** create the manager
	 * @param gd The driver using this manager
	 */
	public StateManager(GameDriver gd)
	{
		_gd = gd;
		
		setState(new StartingState(this, _gd.getInput()));
	}
	
	/** Sets the current state to the passed state
	 * @param newState The new state to be updated and rendered.
	 */
	public void setState(State newState)
	{
		_currentState = newState;
		_gd.setInputTarget(_currentState);
	}
	
	/** @return the Game driver using this state */
	public static GameDriver getGD()
	{
		return _gd;
	}

	/** Called the current state's update method
	 * @param gc The game controler
	 * @param delta The time delta
	 * @throws SlickException
	 */
	public void updateState(GameContainer gc, int delta) throws SlickException
	{
		_currentState.update(gc, delta);
	}
	
	/** Calls the current state's render method
	 * @param gc The game container
	 * @param gfx the slick2d graphics object
	 * @throws SlickException
	 */
	public void renderState(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.setColor(new Color(1f, 1f, 1f));
		_currentState.render(gc, gfx);
	}
}
