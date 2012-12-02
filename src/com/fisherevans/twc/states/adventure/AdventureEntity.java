package com.fisherevans.twc.states.adventure;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.ResourceTools;

public abstract class AdventureEntity
{
	private float _x, _y;
	private Image _sprite;
	private AdventureState _as;
	
	public AdventureEntity(float x, float y, Image image, AdventureState as)
	{
		if(image == null)
		{
			image = ResourceTools.getImage("res/sprites/default32x32.png");
		}
		_sprite = image;
		_x = x;
		_y = y;
		_as = as;
	}
	
	public abstract void update(int delta);
	
	public float getX()
	{
		return _x;
	}
	
	public float getY()
	{
		return _y;
	}
	
	public void setX(float x)
	{
		_x = x;
	}
	
	public void setY(float y)
	{
		_y = y;
	}
	
	public Image getSprite()
	{
		return _sprite;
	}
	
	public void setSprite(Image image)
	{
		_sprite = image;
	}
	
	public AdventureState getAS()
	{
		return _as;
	}
}
