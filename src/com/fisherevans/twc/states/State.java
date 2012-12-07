package com.fisherevans.twc.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.GameDriver;

public abstract class State implements KeyListener
{
	private static StateManager _sm; // The manager that holds this state
	private Input _input; // The slick2d input object
	
	/** create the state
	 * @param sm The manager that holds this state
	 * @param input The input from slick2d
	 */
	public State(StateManager sm, Input input)
	{
		_sm = sm;
		_input = input;
	}
	
	/** @return The this states state manager */
	public static StateManager getSM()
	{
		return _sm;
	}
	
	/** @return the slick2d input object associated with this state */
	public Input getInput()
	{
		return _input;
	}

	/** Update the state (step from game loop)
	 * @param gc The game's conatainer
	 * @param detla The time delta
	 * @throws SlickException
	 */
	public abstract void update(GameContainer gc, int detla) throws SlickException;
	
	/** Draw the state
	 * @param gc The game container
	 * @param gfx The slick2d graphics object
	 * @throws SlickException
	 */
	public abstract void render(GameContainer gc, Graphics gfx) throws SlickException;
	
	/* User Input */
	
	@Override
	public void inputEnded()
	{ }

	@Override
	public void inputStarted()
	{ }

	@Override
	public boolean isAcceptingInput()
	{ return true; }

	@Override
	public void setInput(Input input)
	{ }

	@Override
	public abstract void keyPressed(int key, char c);

	@Override
	public abstract void keyReleased(int key, char c);

}
