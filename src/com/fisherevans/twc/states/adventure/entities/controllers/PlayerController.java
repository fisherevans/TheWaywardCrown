package com.fisherevans.twc.states.adventure.entities.controllers;

import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;
import com.fisherevans.twc.tools.KeyTools;

public class PlayerController extends EntityController
{
	private Input _input;
	
	public PlayerController(MovableEntity ent, Input input)
	{
		super(ent);
		_input = input;
	}

	@Override
	public void update(int delta)
	{
		// Not and else if because the move step can change "isMoving()"
		if(!getEnt().isMoving() // If we're not already moving.
				&& KeyTools.isMOVEDown(_input) // Only if we're pressing a move key
				&& !getEnt().isInteracting() // Not if we're interacting
		)
		{
			int angle = KeyTools.getMoveAngle(_input);
			if(angle >= 0)
				getEnt().setMoveAction(angle, 1);
		}
	}
	
	public void keyPressed(int key, char c)
	{
		if(KeyTools.isSELECT(key) && !getEnt().isMoving() && !getEnt().isHalted())
		{
			getEnt().interact();
		}
	}

}
