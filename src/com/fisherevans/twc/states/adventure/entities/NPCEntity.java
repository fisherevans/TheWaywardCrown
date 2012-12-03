package com.fisherevans.twc.states.adventure.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.tools.KeyTools;

public class NPCEntity extends MovableEntity
{

	/** create the entity
	 * @param x init x pos
	 * @param y init y pos
	 * @param image ini imahe
	 * @param as adventure state holding the entty
	 */
	public NPCEntity(float x, float y, Image image, AdventureState as)
	{
		super(x, y, image, as);
		setSpeedScale(0.333f);
	}
	
	@Override
	public void update(int delta)
	{
		if(isMoving())
		{
			moveStep();
		}
		
		if(!isMoving()) // Not else so continued movement is smooth (doesn't miss a frame).
		{
			if(Math.random() < 0.002f)
			{
				int rand = ((int) (Math.random()*4)) + 1;
				//System.out.println(rand);
				float[] moveVec = new float[] { 0, 0 };
				switch(rand)
				{
					case 1: moveVec[0] = 1; break;
					case 2: moveVec[0] = -1; break;
					case 3: moveVec[1] = 1; break;
					case 4: moveVec[1] = -1; break;
				}
				setMoveAction(moveVec[0] + getX(), moveVec[1] + getY());
			}
		}
	}

}
