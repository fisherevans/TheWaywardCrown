package com.fisherevans.twc.states.adventure.lights;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public abstract class AdventureLight
{
	private float _x, _y;
	private float _size, _scale;
	private Color _color;
	private String _name;
	
	public AdventureLight(float x, float y, float size, String name)
	{
		_x = x;
		_y = y;
		_size = size;
		_scale = _size/LightManager.LIGHT_SIZE;
		_color = new Color(1f, 1f, 1f);
		_name = name;
	}
	
	public abstract void update(int delta);
	public abstract void render(Graphics gfx);

	public float getX()
	{
		return _x;
	}

	public void setX(float x)
	{
		_x = x;
	}

	public float getY()
	{
		return _y;
	}

	public void setY(float y)
	{
		_y = y;
	}

	public float getSize()
	{
		return _size;
	}

	public void setSize(float size)
	{
		_size = size;
		_scale = _size/LightManager.LIGHT_SIZE;
	}

	public Color getColor()
	{
		return _color;
	}

	public void setColor(Color color)
	{
		_color = color;
	}
	
	public float getScale()
	{
		return _scale;
	}
	
	public String getName()
	{
		return _name;
	}
	
	public Color getLightColor(Color daylight)
	{
		return new Color(Math.max(_color.r, daylight.r), 
				Math.max(_color.g, daylight.g), 
				Math.max(_color.b, daylight.b));
	}
}
