package com.fisherevans.twc.states.adventure;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.ResourceTools;

public abstract class AdventureEntity
{
	private float _x, _y;
	private Image _image;
	private AdventureState _as;
	
	
	public AdventureEntity(float x, float y, Image image, AdventureState as)
	{
		if(image == null)
		{
			image = ResourceTools.getImage("res/sprites/default32x32.png");
		}
		_image = image;
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
	
	public int getOccuppiedX()
	{
		return (int)_x;
	}
	
	public int getOccuppiedY()
	{
		return (int)_y;
	}
	
	public Image getImage()
	{
		return _image;
	}
	
	public void setImage(Image image)
	{
		_image = image;
	}
	
	public AdventureState getAS()
	{
		return _as;
	}
}
