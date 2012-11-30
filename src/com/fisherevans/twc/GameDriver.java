package com.fisherevans.twc;

import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;

public class GameDriver extends BasicGame
{
	public static final int
		NATIVE_SCREEN_WIDTH = 1280,
		NATIVE_SCREEN_HEIGHT = 720;
	
	
	private Input _input;
	private StateManager _sm;
	
	public GameDriver(String title)
	{
		super(title);
	}

	@Override
	public void init(GameContainer gc) throws SlickException
	{
		_input = gc.getInput();
		_sm = new StateManager(this);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		_sm.updateState(gc, delta);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		_sm.renderState(gc, gfx);
	}
	
	public void setInputTarget(State newInputTarget)
	{
		_input.removeAllListeners();
		_input.addKeyListener(newInputTarget);
		_input.addMouseListener(newInputTarget);
	}
}