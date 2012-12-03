package com.fisherevans.twc.states.mainmenu;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.tools.*;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;
import com.fisherevans.twc.states.mainmenu.items.*;
import com.fisherevans.twc.states.starting.StartingState;
import com.fisherevans.twc.tools.KeyTools;

public class MainMenuState extends State
{
	private ArrayList<MenuItem> _menuItems;
	private int _menuPos;

	public MainMenuState(StateManager sm, Input input)
	{
		super(sm, input);
		initMenuItems();
		_menuPos = 0;
	}
	
	private void initMenuItems()
	{
		_menuItems = new ArrayList<MenuItem>();

		_menuItems.add(new SimpleTextItem("Resume", true));
		_menuItems.add(new SimpleTextItem("Load", true));
		_menuItems.add(new SimpleTextItem("New", true));
		_menuItems.add(new SimpleTextItem("-----------", false));
		_menuItems.add(new AdventureDemoItem());
		_menuItems.add(new SimpleTextItem("-----------", false));
		_menuItems.add(new ExitItem());
		
		if(_menuItems.isEmpty())
		{
			_menuItems.add(new SimpleTextItem("Empty!!!", true));
		}
	}
	
	private void moveDownMenu()
	{
		int init = _menuPos++;
		while(_menuPos < _menuItems.size() && !_menuItems.get(_menuPos).isSelectable())
		{
			_menuPos++;
		}
		if(_menuPos >= _menuItems.size())
		{
			_menuPos = init;
		}
	}
	
	private void moveUpMenu()
	{
		int init = _menuPos--;
		while(_menuPos >= 0 && !_menuItems.get(_menuPos).isSelectable())
		{
			_menuPos--;
		}
		if(_menuPos < 0)
		{
			_menuPos = init;
		}
	}

	@Override
	public void update(GameContainer gc, int detla) throws SlickException
	{
		
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.setFont(ResourceTools.font40());
		
		for(int listId = 0;listId < _menuItems.size();listId++)
		{
			Color color = _menuPos == listId ? _menuItems.get(listId).getHighColor() : _menuItems.get(listId).getColor();
			String text = _menuPos == listId ? "> " + _menuItems.get(listId).getText() + " <" : _menuItems.get(listId).getText();
			int x = GameDriver.NATIVE_SCREEN_WIDTH/2 - ResourceTools.font40().getWidth(text)/2;
			int y = 56 + 56*listId;
			gfx.setColor(color);
			gfx.drawString(text, x, y);
		}
	}

	@Override
	public void keyPressed(int key, char c)
	{
		if(KeyTools.isUP(key))
		{
			moveUpMenu();
		}
		else if(KeyTools.isDOWN(key))
		{
			moveDownMenu();
		}
		else if(KeyTools.isSELECT(key) || KeyTools.isRIGHT(key))
		{
			_menuItems.get(_menuPos).action();
		}
		else if(KeyTools.isBACK(key))
		{
			getSM().setState(new StartingState(getSM(), getInput()));
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
