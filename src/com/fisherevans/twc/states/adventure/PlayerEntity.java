package com.fisherevans.twc.states.adventure;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.fisherevans.twc.control.KeyCodes;

public class PlayerEntity extends AdventureEntity
{
	private Input _input;
	private float _xb, _yb, _xa, _ya;
	private boolean _moving = false;
	private long _startTime;
	
	private final float MOVE_TIME = 200;
	
	public PlayerEntity(float x, float y, Image image, AdventureState as, Input input)
	{
		super(x, y, image, as);
		_input = input;
	}

	@Override
	public void update(int delta)
	{
		if(_moving)
		{
			step();
			if(System.currentTimeMillis() - _startTime >= MOVE_TIME)
			{
				setX(_xb);
				setY(_yb);
				_moving = false;
			}
		}
		if(!_moving)
		{
			if(KeyCodes.isMOVEDown(_input))
			{
				_xa = getX();
				_ya = getY();
				_xb = getX();
				_yb = getY();
				
				if(KeyCodes.isLEFTDown(_input)) { _xb -= 1; }
				else if(KeyCodes.isRIGHTDown(_input)) { _xb += 1; }
				else if(KeyCodes.isUPDown(_input)) { _yb -= 1; }
				else if(KeyCodes.isDOWNDown(_input)) { _yb += 1; }
				
				if(isGoodMove(_xb, _yb))
				{
					_moving = true;
					_startTime = System.currentTimeMillis();
				}
			}
		}
	}
	
	public boolean isGoodMove(float xb, float yb)
	{
		int xbTile = (int)xb;
		int ybTile = (int)yb;
		
		if(getAS().getMap().getTileId(xbTile, ybTile, 1) == 258) { return false; }
		
		return true;
	}
	
	private void step()
	{
		float t = ((float)(System.currentTimeMillis() - _startTime))/MOVE_TIME;
		setX(_xa + ((_xb - _xa) * t));
		setY(_ya + ((_yb - _ya) * t));
	}
}
