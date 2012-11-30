package com.fisherevans.twc.states.mainmenu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;

public class MainMenuState extends State
{

	public MainMenuState(StateManager sm)
	{
		super(sm);
	}

	@Override
	public void update(GameContainer gc, int detla) throws SlickException
	{
		
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.drawString("Main Menu", 10, 10);
		
	}

	@Override
	public void keyPressed(int key, char c)
	{
		
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount) { }

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy) { }

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy) { }

	@Override
	public void mousePressed(int button, int x, int y) { }

	@Override
	public void mouseReleased(int button, int x, int y) { }

	@Override
	public void mouseWheelMoved(int change) { }

	@Override
	public void keyReleased(int key, char c) { }
}
