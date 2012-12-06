package com.fisherevans.twc.states.adventure.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.entities.controlers.EntityControler;

/**
 * @author Fisher
 *
 */
public abstract class MovableEntity extends AdventureEntity
{
	/** Time in milliseconds to move from tile to tile */
	public static final int MOVE_TIME = 200;
	/** Time in milliseconds between each frame of the anumation. */
	public static final int ANIM_DUR = (int) (MOVE_TIME/1.66);
	
	private float _xb, _yb, _xa, _ya; // posa and posb corrids for interpolation
	private boolean _moving = false; // true if in a moving phase
	private long _startTime; // Start time of movement
	private Animation _anUp , _anDown, _anLeft, _anRight;
	private EntityControler _controler;

	private float _speedScale = 1; // Speed scale

	/** create the entity
	 * @param x init x pos
	 * @param y init y pos
	 * @param image ini imahe
	 * @param as adventure state holding the entty
	 */
	public MovableEntity(float x, float y, Image image, EntityControler controler, AdventureState as)
	{
		super(x, y, image, as);
		
		_controler = controler;
		_controler.setEnt(this);

		_anUp = new Animation(true);
		_anDown = new Animation(true);
		_anLeft = new Animation(true);
		_anRight = new Animation(true);

		for(int animX = 0;animX < 192;animX += 64)
			{ _anUp.addFrame(image.getSubImage(animX, 0, 64, image.getHeight()), ANIM_DUR); }
			_anUp.addFrame(image.getSubImage(64, 0, 64, image.getHeight()), ANIM_DUR);
		for(int animX = 192;animX < 384;animX += 64)
			{ _anDown.addFrame(image.getSubImage(animX, 0, 64, image.getHeight()), ANIM_DUR); }
			_anDown.addFrame(image.getSubImage(256, 0, 64, image.getHeight()), ANIM_DUR);
		for(int animX = 384;animX < 576;animX += 64)
			{ _anLeft.addFrame(image.getSubImage(animX, 0, 64, image.getHeight()), ANIM_DUR); }
			_anLeft.addFrame(image.getSubImage(448, 0, 64, image.getHeight()), ANIM_DUR);
		for(int animX = 576;animX < 768;animX += 64)
			{ _anRight.addFrame(image.getSubImage(animX, 0, 64, image.getHeight()), ANIM_DUR); }
			_anRight.addFrame(image.getSubImage(640, 0, 64, image.getHeight()), ANIM_DUR);

		setDrawOffset(new int[] { 0, -64 });
	}

	@Override
	public void update(int delta)
	{
		getControler().update(delta);
	}
	
	/** Steps throug animation
	 * @param delta time delta since last update
	 */
	public void updateAnimation(int delta)
	{
		delta *= getSpeedScale();
		if(isMoving())
		{
			switch(getAngle())
			{
				case 0: _anRight.update(delta); break;
				case 90: _anDown.update(delta); break;
				case 180: _anLeft.update(delta); break;
				case 270: _anUp.update(delta); break;
			}
		}
	}
	
	@Override
	public Image getImage()
	{
		Animation anim;
		switch(getAngle())
		{
			case 0: anim = _anRight; break;
			case 90: anim = _anDown; break;
			case 180: anim = _anLeft; break;
			case 270: anim = _anUp; break;
			default: anim = _anDown; break;
		}
		if(isMoving())
			return anim.getCurrentFrame();
		else
			return anim.getImage(1);
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
		
		if(xbTile < 0 || ybTile < 0)
		{
			return false;
		}
		
		//System.out.println(getAS().getMap().getTileId(xbTile, ybTile, getAS().getMap().getLayerIndex("col")s));
		
		if(getAS().getMap().getTileId(xbTile, ybTile, getAS().getMap().getLayerIndex("col")) == 1026) { return false; }
		if(getAS().isEntityIn((int)xb, (int)yb, this)) { return false; }
		
		return true;
	}
	
	/** While moving - interpolates through movestep. 
	 */
	public void moveStep()
	{
		float t = (((float)(System.currentTimeMillis() - _startTime))/(MOVE_TIME))*_speedScale;
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
		int[] moveVec = { (int)(xb-getX()), (int)(yb-getY()) };

		if(moveVec[0] == 1 && moveVec[1] == 0)
			setAngle(0);
		else if(moveVec[0] == -1 && moveVec[1] == 0)
			setAngle(180);
		else if(moveVec[0] == 0 && moveVec[1] == 1)
			setAngle(90);
		else if(moveVec[0] == 0 && moveVec[1] == -1)
			setAngle(270);
		
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
	public int getOccupiedY()
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

	public EntityControler getControler()
	{
		return _controler;
	}

	public void setControler(EntityControler controler)
	{
		_controler = controler;
	}
}
