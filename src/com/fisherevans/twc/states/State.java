package com.fisherevans.twc.states;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.KeyListener;
import org.newdawn.slick.MouseListener;
import org.newdawn.slick.SlickException;

public abstract class State implements KeyListener, MouseListener
{
	private StateManager _sm;
	
	public State(StateManager sm)
	{
		_sm = sm;
	}
	
	public StateManager getSM()
	{
		return _sm;
	}

	public abstract void update(GameContainer gc, int detla) throws SlickException;
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
	public abstract void mouseClicked(int button, int x, int y, int clickCount);

	@Override
	public abstract void mouseDragged(int oldx, int oldy, int newx, int newy);

	@Override
	public abstract void mouseMoved(int oldx, int oldy, int newx, int newy);

	@Override
	public abstract void mousePressed(int button, int x, int y);

	@Override
	public abstract void mouseReleased(int button, int x, int y);

	@Override
	public abstract void mouseWheelMoved(int change);

	@Override
	public abstract void keyPressed(int key, char c);

	@Override
	public abstract void keyReleased(int key, char c);

}
