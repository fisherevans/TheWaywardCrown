package com.fisherevans.twc.states.adventure.entities.controlers;

import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;
import com.fisherevans.twc.tools.KeyTools;

public class PlayerControler extends EntityControler
{
	private Input _input;
	public PlayerControler(Input input)
	{
		super();
		_input = input;
	}

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
		
		if(getEnt().getAS().getActions().isEmpty() && !getEnt().isMoving() && KeyTools.isMOVEDown(_input) && !getEnt().isInteracting()) // Not else so continued movement is smooth (doesn't miss a frame).
		{
			float[] moveVec = KeyTools.getMoveVector(_input);
			getEnt().setMoveAction(moveVec[0] + getEnt().getX(), moveVec[1] + getEnt().getY());
		}
	}

}
