package com.fisherevans.twc.states.adventure.entities;

import org.newdawn.slick.Image;

import com.fisherevans.twc.states.adventure.AdventureState;

public abstract class MovableEntity extends AdventureEntity
{
	private float _xb, _yb, _xa, _ya;
	private boolean _moving = false;
	private long _startTime;
	
	public static final float MOVE_TIME = 200;
	private float _speedScale = 1;
	
	public MovableEntity(float x, float y, Image image, AdventureState as)
	{
		super(x, y, image, as);
	}

	@Override
	public void update(int delta)
	{
		if(_moving)
		{
			moveStep();
		}
	}
	
	public boolean isGoodMove(float xb, float yb)
	{
		int xbTile = (int)xb;
		int ybTile = (int)yb;
		
		if(getAS().getMap().getTileId(xbTile, ybTile, 1) == 258) { return false; }
		if(getAS().isEntityIn((int)xb, (int)yb, this)) { return false; }
		
		return true;
	}
	
	public void moveStep()
	{
		float t = ((float)(System.currentTimeMillis() - _startTime))/(MOVE_TIME/_speedScale);
		setX(_xa + ((_xb - _xa) * t));
		setY(_ya + ((_yb - _ya) * t));
		
		if(t >= 1)
		{
			setX(_xb);
			setY(_yb);
			_moving = false;
		}
	}
	
	public void setMoveAction(float xb, float yb)
	{
		if(isGoodMove(xb, yb))
		{
			_xa = getX();
			_ya = getY();
			_xb = xb;
			_yb = yb;
			_moving = true;
			_startTime = System.currentTimeMillis();
		}
	}
	
	public int getOccupiedX()
	{
		if(_moving)
		{
			return (int)_xb;
		}
		else
		{
			return (int)getX();
		}
	}
	
	public int getOccuppiedY()
	{
		if(_moving)
		{
			return (int)_yb;
		}
		else
		{
			return (int)getY();
		}
	}
	
	public boolean isMoving()
	{
		return _moving;
	}
	
	public void setSpeedScale(float scale)
	{
		_speedScale = scale;
	}
	
	public float getSpeedScale()
	{
		return _speedScale;
	}
}
