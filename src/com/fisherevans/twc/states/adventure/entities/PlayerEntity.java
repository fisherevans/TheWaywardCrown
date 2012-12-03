package com.fisherevans.twc.states.adventure.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.tools.KeyTools;

public class PlayerEntity extends MovableEntity
{
	private Input _input;
	
	public PlayerEntity(float x, float y, Image image, AdventureState as, Input input)
	{
		super(x, y, image, as);
		_input = input;
	}
	
	@Override
	public void update(int delta)
	{
		if(isMoving())
		{
			moveStep();
		}
		
		if(!isMoving() && KeyTools.isMOVEDown(_input)) // Not else so continued movement is smooth (doesn't miss a frame).
		{
			float[] moveVec = KeyTools.getMoveVector(_input);
			setMoveAction(moveVec[0] + getX(), moveVec[1] + getY());
		}
	}

}
