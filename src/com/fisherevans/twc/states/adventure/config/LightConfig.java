package com.fisherevans.twc.states.adventure.config;

import java.util.ArrayList;

import org.newdawn.slick.Color;

import com.fisherevans.twc.states.adventure.lights.AdventureLight;

public class LightConfig
{
	private Color[] _timeLights = new Color[4];
	private ArrayList<AdventureLight> _lights;
	
	public void setTimeLight(int index, Color color)
	{
		_timeLights[index] = color;
		_lights = new ArrayList<>();
	}
	
	public Color[] getTimeLights()
	{
		return _timeLights;
	}
	
	public int addLight(AdventureLight light)
	{
		_lights.add(light);
		return _lights.size()-1;
	}
	
	public AdventureLight getLight(int id)
	{
		return _lights.get(id);
	}
	
	public ArrayList<AdventureLight> getLights()
	{
		return _lights;
	}
}
