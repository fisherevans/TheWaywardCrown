package com.fisherevans.twc.states.adventure.actions;

import java.util.ArrayList;

import com.fisherevans.twc.states.adventure.AdventureState;

public class ActionQueue
{
	private ArrayList<AdventureAction> _actions;
	private ActionManager _am;

	public ActionQueue(ActionManager am)
	{
		_am = am;
		_actions = new ArrayList<>();
	}
	
	public ActionQueue(ActionManager am, ArrayList<AdventureAction> actions)
	{
		_am = am;
		_actions = actions;
	}
	
	public ArrayList<AdventureAction> getActions()
	{
		return _actions;
	}
	
	public void addAction(AdventureAction action)
	{
		_actions.add(action);
	}
	
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

	/** Allows the current action to take key input
	 * @param key key code
	 * @param c character of key
	 * @return return true if you want to block input after this
	 */
	public void keyPressed(int key, char c)
	{
		if(!_actions.isEmpty())
		{
			_actions.get(0).keyPressed(key, c);
		}
	}
}
