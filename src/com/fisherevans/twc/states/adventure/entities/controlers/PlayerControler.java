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
		// Avoid null pointer exceptions....
		if(getEnt() == null)
		{
			return;
		}
		
		// If the player is moving, step the animation and interpolation.
		if(getEnt().isMoving())
		{
			getEnt().moveStep();
			getEnt().updateAnimation(delta);
		}
		
		// Not and else if because the move step can change "isMoving()"
		if(!getEnt().getEM().getAS().getAM().isBlockingInput() // Not if there's dialogue;
				&& !getEnt().isMoving() // If we're not already moving.
				&& KeyTools.isMOVEDown(_input) // Only if we're pressing a move key
				&& !getEnt().isInteracting() // Not if we're interacting
		)
		{
			float[] moveVec = KeyTools.getMoveVector(_input);
			getEnt().setMoveAction(moveVec[0] + getEnt().getX(), moveVec[1] + getEnt().getY());
		}
	}
	
	public boolean keyPressed(int key, char c)
	{
		if(KeyTools.isSELECT(key) && !getEnt().isMoving())
		{
			getEnt().interact();
		}
		
		return false;
	}

}
