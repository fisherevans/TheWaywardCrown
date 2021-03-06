package com.fisherevans.twc.states.adventure.actions;

import java.util.ArrayList;
import java.util.HashMap;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.config.AdventureConfigLoader;

public class ActionManager
{
	private AdventureState _as;
	private ArrayList<ActionQueue> _queues;
	private HashMap _queueHash;
	private ActionQueue _curQueue;
	
	public ActionManager(AdventureState as)
	{
		_as = as;
	}
	
	public void initManager(AdventureConfigLoader config)
	{
		_queues = new ArrayList<>();
		_queueHash = config.getQueueHash();
		System.out.println("Loaded " + _queueHash.size() + " Action Queues.");
	}
	
	/** Adds a single action tot he action que
	 * @param aa the action to add
	 */
	public int addActionQueue(ActionQueue queue)
	{
		_queues.add(queue);
		return _queues.size()-1;
	}
	
	/** Adds a single action tot he action que
	 * @param aa the action to add
	 */
	public int addActionQueue(String queue)
	{
		if(queue == null)
			return -1;
		
		System.out.println("Loading Action Queue: " + queue);
		
		ArrayList<AdventureAction> newActions = new ArrayList<>();
		newActions.addAll(((ActionQueue)_queueHash.get(queue)).getActions());
		ActionQueue newQueue = new ActionQueue(this, newActions);
		
		_queues.add(newQueue);
		return _queues.size()-1;
	}

	public ActionQueue getActionQueue(String queue)
	{
		if(queue == null)
			return null;
		
		System.out.println("Loading Action Queue: " + queue);
		
		ArrayList<AdventureAction> newActions = new ArrayList<>();
		newActions.addAll(((ActionQueue)_queueHash.get(queue)).getActions());
		ActionQueue newQueue = new ActionQueue(this, newActions);
		
		return newQueue;
	}
	
	/** Updates the current action
	 * @param delta time delta between frames
	 */
	public void update(int delta)
	{
		if(!_queues.isEmpty())
		{
			for(int x = 0;x < _queues.size();)
			{
				_curQueue = _queues.get(x);
				_curQueue.update(delta);
				
				if(_curQueue.getActions().isEmpty())
					_queues.remove(x);
				else
					x++;
			}
		}
	}
	
	/** Allows the current action to render something
	 * @param gfx the slick2d graphics object
	 */
	public void render(Graphics gfx)
	{
		
	}
	
	/** Allows the current action to take key input
	 * @param key key code
	 * @param c character of key
	 * @return return true if you want to block input after this
	 */
	public void keyPressed(int key, char c)
	{
		if(!_queues.isEmpty())
		{
			for(ActionQueue queue:_queues)
			{
				queue.keyPressed(key, c);
			}
		}
	}
	
	public AdventureState getAS()
	{
		return _as;
	}
	
	public ActionQueue getCurrentQueue()
	{
		return _curQueue;
	}
}
