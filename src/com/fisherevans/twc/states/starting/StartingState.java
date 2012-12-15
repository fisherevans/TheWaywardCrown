package com.fisherevans.twc.states.starting;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.tools.*;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;
import com.fisherevans.twc.states.mainmenu.MainMenuState;

public class StartingState extends State
{
	private float _ready = 180f; // Flashes the "press enter" text
	private Image titleScreen; // Image that is faded in
	private final String READY_STRING = "[ Press Enter ]"; // Text that flashes when "ready"
	private final String TITLE = "The Wayward Crown"; // Title of the game
	
	/** Create the starting state
	 * @param sm state manager that holds this state
	 * @param input The slick2d player input
	 */
	public StartingState(StateManager sm, Input input)
	{
		super(sm, input);
		titleScreen = ResourceTools.getImage("res/starting/images/title_screen.png");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		_ready += 0.1f*delta;
		_ready = _ready > 360 ? _ready - 360 : _ready;
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.setColor(new Color(1f, 1f, 1f));
		gfx.setFont(ResourceTools.fontMC32());
		gfx.drawString(TITLE, GameDriver.NATIVE_SCREEN_WIDTH/2 - ResourceTools.fontMC32().getWidth(TITLE)/2, GameDriver.NATIVE_SCREEN_HEIGHT*0.2f);
		float readyColor = ((float)Math.cos(Math.toRadians(_ready)))*0.25f + 0.25f;
		gfx.setColor(new Color(readyColor, readyColor, readyColor));
		gfx.setFont(ResourceTools.fontMC32());
		gfx.drawString(READY_STRING, GameDriver.NATIVE_SCREEN_WIDTH/2 - ResourceTools.fontMC32().getWidth(READY_STRING)/2, GameDriver.NATIVE_SCREEN_HEIGHT*0.8f);
	}

	@Override
	public void keyPressed(int key, char c)
	{
		if(key == 28)
		{
			getSM().setState(new MainMenuState(getSM(), getInput()));
		}
	}

	@Override
	public void keyReleased(int key, char c) { }
}
