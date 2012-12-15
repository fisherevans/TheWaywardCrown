package com.fisherevans.twc;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;

/** The main driver for the games. Hold all of the system managers and acts as the main game loop.
 * @author Fisher
 *
 */
public class GameDriver extends BasicGame
{
	public static final int
	NATIVE_SCREEN_WIDTH = 1280,
	NATIVE_SCREEN_HEIGHT = 720, // The NATIVE resolution of the game. This never changes,  but the window can scale and keep the ratio.
	NATIVE_SCREEN_H_WIDTH = NATIVE_SCREEN_WIDTH/2,
	NATIVE_SCREEN_H_HEIGHT = NATIVE_SCREEN_HEIGHT/2; // The NATIVE resolution of the game. This never changes,  but the window can scale and keep the ratio.
	
	
	private static Input _input; // Play input object
	private StateManager _sm; // The state Manager
	private Start _frame; // The JFrame that holds this drive
	private GlobalKeyListener _gkl; // A key listener used for global event s(such as full screen)
	
	/** Initiaites the game driver
	 * @param title The title of the game (window title)
	 * @param start The "Frame" holding it
	 */
	public GameDriver(String title, Start start)
	{
		super(title);
		_frame = start;
	}
	
	/**
	 *  @return The input object
	 */
	public static Input getInput()
	{
		return _input;
	}
	
	
	@Override
	public void init(GameContainer gc) throws SlickException
	{
		_gkl = new GlobalKeyListener(this);
		_input = gc.getInput();
		_sm = new StateManager(this);
		GL11.glEnable(GL11.GL_BLEND);
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
	
	/** Sets the correct listeners for the current Input object. (when changing states)
	 * @param newInputTarget
	 */
	public void setInputTarget(State newInputTarget)
	{
		_input.removeAllListeners();
		_input.addKeyListener(_gkl);
		_input.addKeyListener(newInputTarget);
	}
	
	/**
	 * @return Gets the JFrame holding the driver.
	 */
	public Start getFrame()
	{
		return _frame;
	}
}