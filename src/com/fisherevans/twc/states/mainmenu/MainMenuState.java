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
	
	private boolean _leaving = false;
	private State _nextState;

	/** creates the menu statee
	 * @param sm the manager holding this menu
	 * @param input the slick2d player input object
	 */
	public MainMenuState(StateManager sm, Input input)
	{
		super(sm, input);
	}

	@Override
	public void load()
	{
		initMenuItems();
		_menuPos = 0;
	}
	
	/** Init the menu list and populate the items within
	 */
	private void initMenuItems()
	{
		_menuItems = new ArrayList<MenuItem>();
		_menuItems.add(new AdventureDemoItem());
		_menuItems.add(new SimpleTextItem("-----------", false));
		_menuItems.add(new CreditsItem());
		_menuItems.add(new ExitItem());
		
		if(_menuItems.isEmpty())
		{
			_menuItems.add(new SimpleTextItem("Empty!!!", true));
		}
	}
	
	/** Move select menu item down the list - skips un-selectable items*/
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

	/** Move select menu item up the list - skips un-selectable items */
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
		if(_leaving && getSM().getFM().isFadedOut())
		{
			getSM().setState(_nextState);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.setFont(ResourceTools.fontMC32());
		
		for(int listId = 0;listId < _menuItems.size();listId++)
		{
			Color color = _menuPos == listId ? _menuItems.get(listId).getHighColor() : _menuItems.get(listId).getColor();
			String text = _menuPos == listId ? "> " + _menuItems.get(listId).getText() + " <" : _menuItems.get(listId).getText();
			int x = GameDriver.NATIVE_SCREEN_WIDTH/2 - ResourceTools.fontMC32().getWidth(text)/2;
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
		else if(KeyTools.isSELECT(key) && !_leaving)
		{
			State actionState = _menuItems.get(_menuPos).action();
			if(actionState != null)
			{
				_leaving = true;
				getSM().getFM().fadeOut();
				_nextState = actionState;
			}
		}
		else if(KeyTools.isBACK(key))
		{
			getSM().setState(new StartingState(getSM(), getInput()));
		}
	}

	@Override
	public void keyReleased(int key, char c) { }
}
