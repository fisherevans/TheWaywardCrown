package com.fisherevans.twc.states.adventure;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.fisherevans.twc.control.KeyCodes;

public class PlayerEntity extends MovableEntity
{

	public PlayerEntity(float x, float y, Image image, AdventureState as, Input input)
	{
		super(x, y, image, as, input);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void update(int delta)
	{
		if(isMoving())
		{
			moveStep();
		}
		
		if(!isMoving() && KeyCodes.isMOVEDown(getInput())) // Not else so continued movement is smooth (doesn't miss a frame).
		{
			float[] moveVec = KeyCodes.getMoveVector(getInput());
			setMoveAction(moveVec[0] + getX(), moveVec[1] + getY());
		}
	}

}
