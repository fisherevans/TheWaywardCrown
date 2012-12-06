package com.fisherevans.twc.states.adventure.entities.controlers;

import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;
import com.fisherevans.twc.tools.KeyTools;

public class RandomControler extends EntityControler
{
	@Override
	public void update(int delta)
	{
		if(getEnt() == null)
		{
			return;
		}
		
		if(getEnt().isMoving())
		{
			getEnt().moveStep();
			getEnt().updateAnimation(delta);
		}
		
		if(!getEnt().isMoving() && !getEnt().isInteracting()) // Not else so continued movement is smooth (doesn't miss a frame).
		{
			if(Math.random() < 0.002f)
			{
				float[] moveVec = new float[] { 0, 0 };
				switch(((int) (Math.random()*4)) + 1)
				{
					case 1: moveVec[0] = 1; break;
					case 2: moveVec[0] = -1; break;
					case 3: moveVec[1] = 1; break;
					case 4: moveVec[1] = -1; break;
				}
				getEnt().setMoveAction(moveVec[0] + getEnt().getX(), moveVec[1] + getEnt().getY());
			}
		}
	}

}
