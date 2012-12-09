package com.fisherevans.twc.states.adventure.entities.controllers;

import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;
import com.fisherevans.twc.tools.KeyTools;

public class RandomController extends EntityController
{
	public RandomController(MovableEntity ent)
	{
		super(ent);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(int delta)
	{		
		if(!getEnt().isMoving() && !getEnt().isInteracting()) // Not else so continued movement is smooth (doesn't miss a frame).
		{
			if(Math.random()*delta < 0.3f)
			{
				int angle = 0;
				switch(((int) (Math.random()*4)) + 1)
				{
					case 1: angle = 0; break;
					case 2: angle = 90; break;
					case 3: angle = 180; break;
					case 4: angle = 270; break;
				}
				getEnt().setMoveAction(angle, 1);
			}
		}
	}

}
