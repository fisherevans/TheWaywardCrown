package com.fisherevans.twc.states.adventure.entities;

import org.newdawn.slick.Image;

import com.fisherevans.twc.states.adventure.AdventureState;

public abstract class MovableEntity extends AdventureEntity
{
	private float _xb, _yb, _xa, _ya; // posa and posb corrids for interpolation
	private boolean _moving = false; // true if in a moving phase
	private long _startTime; // Start time of movement
	
	public static final float MOVE_TIME = 200; // Move time (in ms) per tile
	private float _speedScale = 1; // Speed scale

	/** create the entity
	 * @param x init x pos
	 * @param y init y pos
	 * @param image ini imahe
	 * @param as adventure state holding the entty
	 */
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
	
	/** TEsts to see if there isn't a tile in the way of the move
	 * @param xb the x value of the map pos
	 * @param yb the y value of the map pos
	 * @return true if nothing's in the way. false if there is.
	 */
	public boolean isGoodMove(float xb, float yb)
	{
		int xbTile = (int)xb;
		int ybTile = (int)yb;
		
		if(getAS().getMap().getTileId(xbTile, ybTile, 3) == 258) { return false; }
		if(getAS().isEntityIn((int)xb, (int)yb, this)) { return false; }
		
		return true;
	}
	
	/** While moving - interpolates through movestep. 
	 */
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
	
	/** creates the move action
	 * @param xb x pos to move to
	 * @param yb y pos to move to.
	 */
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
	
	/** @return the x value the entity is "occupying" */
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

	/** @return the y value the entity is "occupying" */
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
	

	/** @return true if the entity is currently moving */
	public boolean isMoving()
	{
		return _moving;
	}
	
	/** Scales the movement of the object
	 * @param scale How fast/slow to move
	 */
	public void setSpeedScale(float scale)
	{
		_speedScale = scale;
	}
	
	/** @return the speed scale of the entitys movement. */
	public float getSpeedScale()
	{
		return _speedScale;
	}
}
