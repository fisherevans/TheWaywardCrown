package com.fisherevans.twc.states.adventure.triggers;

import java.util.Arrays;

import org.newdawn.slick.geom.Rectangle;

import com.fisherevans.twc.states.adventure.entities.AdventureEntity;

public class AdventureTrigger
{
	private int _x1, _y1, _x2, _y2;
	private String _actions;
	private String[] _effects;
	private TriggerManager _tm;
	private int _timesMax, _times;
	
	public AdventureTrigger(TriggerManager tm)
	{
		_tm = tm;
		_times = 0;
	}
	
	public void checkTrigger(AdventureEntity ent)
	{
		if(_times > 0 && _times < _timesMax)
		{
			return;
		}
		
		if(!Arrays.asList(_effects).contains(ent.getName()))
		{
			return;
		}
		
		float px = ent.getX();
		float py = ent.getY();
		if(px >= _x1 && px <= _x2 && py >= _y1 && py <= _y2)
		{
			_tm.getAs().getAM().addActionQueue(_actions);
			_times++;
		}
	}
	
	public int getX1()
	{
		return _x1;
	}
	public void setX1(int x1)
	{
		_x1 = x1;
	}
	public int getX2()
	{
		return _x2;
	}
	public void setX2(int x2)
	{
		_x2 = x2;
	}
	public int getY1()
	{
		return _y1;
	}
	public void setY1(int y1)
	{
		_y1 = y1;
	}
	public int getY2()
	{
		return _y2;
	}
	public void setY2(int y2)
	{
		_y2 = y2;
	}
	public String getActions()
	{
		return _actions;
	}
	public void setActions(String actions)
	{
		_actions = actions;
	}

	public int getTimes()
	{
		return _times;
	}

	public void setTimes(int times)
	{
		_times = times;
	}

	public String[] getEffects()
	{
		return _effects;
	}

	public void setEffects(String[] effects)
	{
		_effects = effects;
	}
}
