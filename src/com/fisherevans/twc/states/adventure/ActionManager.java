package com.fisherevans.twc.states.adventure;

import java.util.ArrayList;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.actions.AdventureAction;

public class ActionManager
{
	private AdventureState _as;
	private ArrayList<AdventureAction> _actions;
	
	public ActionManager(AdventureState as)
	{
		_as = as;
		_actions = new ArrayList<>();
	}
	
	/** Adds a single action tot he action que
	 * @param aa the action to add
	 */
	public void addAction(AdventureAction aa)
	{
		_actions.add(aa);
	}
	
	/** Adds an arraylist of actions to the que
	 * @param aas the list to add
	 */
	public void addActions(ArrayList<AdventureAction> aas)
	{
		_actions.addAll(aas);
	}
	
	/** Updates the current action
	 * @param delta time delta between frames
	 */
	public void update(int delta)
	{
		if(!_actions.isEmpty())
		{
			AdventureAction action = _actions.get(0);
			if(action.hasStarted())
			{
				action.updateAction(delta);
			}
			else
			{
				action.start();
			}
			
			if(action.isComplete())
			{
				action.end();
				_actions.remove(0);
			}
		}
	}
	
	/** Allows the current action to render something
	 * @param gfx the slick2d graphics object
	 */
	public void render(Graphics gfx)
	{
		if(!_actions.isEmpty())
		{
			AdventureAction action = _actions.get(0);
			if(action.hasStarted())
			{
				action.render(gfx);
			}
		}
	}
	
	/** Allows the current action to take key input
	 * @param key key code
	 * @param c character of key
	 * @return return true if you want to block input after this
	 */
	public boolean keyPressed(int key, char c)
	{
		if(!_actions.isEmpty())
		{
			return _actions.get(0).keyPressed(key, c);
		}
		return false;
	}
	
	/**
	 * @return true if the current action is blocking input
	 */
	public boolean isBlockingInput()
	{
		if(!_actions.isEmpty())
		{
			return _actions.get(0).isBlockingInput();
		}
		return false;
	}
	
	public AdventureState getAS()
	{
		return _as;
	}
}
