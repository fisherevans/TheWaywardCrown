package com.fisherevans.twc.states.starting;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.ResourceTools;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;
import com.fisherevans.twc.states.mainmenu.MainMenuState;

public class StartingState extends State
{
	private float _scale = 0,
				  _ready = 0f;
	private Image titleScreen;
	private final String READY_STRING = "[ Press Enter ]";
	
	public StartingState(StateManager sm)
	{
		super(sm);
		titleScreen = ResourceTools.getImage("res/starting/images/title_screen.png");
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		if(_scale != 1)
		{
			_scale = _scale > 1 ? 1 : _scale + 0.0002f*delta;
		}
		else
		{
			_ready += 0.003f*delta;
			_ready = _ready > 360 ? _ready - 360 : _ready;
		}
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		titleScreen.setAlpha(_scale);
		gfx.drawImage(titleScreen, 0, 0);
		if(_scale >= 1)
		{
			float readyColor = ((float)Math.cos(_ready))*0.4f + 0.6f;
			gfx.setColor(new Color(readyColor, readyColor, readyColor));
			gfx.setFont(ResourceTools.getMainFont());
			gfx.drawString(READY_STRING, gc.getWidth()/2 - ResourceTools.getMainFont().getWidth(READY_STRING)/2, gc.getHeight()*0.8f);
		}
	}

	@Override
	public void keyPressed(int key, char c)
	{
		if(key == 28)
		{
			super.getSM().setState(new MainMenuState(super.getSM()));
		}
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
