package com.fisherevans.twc.states.pausemenu;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;
import com.fisherevans.twc.tools.ResourceTools;

public class PauseMenuState extends State
{
	private State _bgState;
	private Image _bgImage;
	
	public PauseMenuState(StateManager sm, Input input, State bgState)
	{
		super(sm, input);
		_bgState = bgState;
	}

	@Override
	public void load()
	{
		_bgImage = ResourceTools.getImage("res/gui/pausemenu_bg.png");
	}

	@Override
	public void update(GameContainer gc, int detla) throws SlickException
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		_bgState.render(gc, gfx);
		_bgImage.draw();
	}

	@Override
	public void keyPressed(int key, char c)
	{
		getSM().setStateQuick(_bgState);
	}

	@Override
	public void keyReleased(int key, char c)
	{
		// TODO Auto-generated method stub
		
	}

}
