package com.fisherevans.twc.states.adventure.triggers;

import java.util.ArrayList;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.config.AdventureConfigLoader;
import com.fisherevans.twc.states.adventure.entities.MovableEntity;

public class TriggerManager
{
	private AdventureState _as;
	private ArrayList<AdventureTrigger> _triggers;
	
	public TriggerManager(AdventureState as)
	{
		_as = as;
		_triggers = new ArrayList<>();
	}
	
	public void initManager(AdventureConfigLoader config)
	{
		_triggers = config.getTriggers();
		System.out.println("Loaded " + _triggers.size() + " Triggers.");
	}
	
	public void checkTrigger(MovableEntity ent)
	{
		for(AdventureTrigger trigger:_triggers)
		{
			trigger.checkTrigger(ent);
		}
	}
	
	public void update(int delta)
	{
		
	}

	public AdventureState getAS()
	{
		return _as;
	}

	public void setAs(AdventureState as)
	{
		_as = as;
	}

	public ArrayList<AdventureTrigger> getTriggers()
	{
		return _triggers;
	}

	public void setTriggers(ArrayList<AdventureTrigger> triggers)
	{
		_triggers = triggers;
	}
}
