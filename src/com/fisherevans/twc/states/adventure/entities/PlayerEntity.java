package com.fisherevans.twc.states.adventure.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.tools.KeyTools;

public class PlayerEntity extends MovableEntity
{
	private Input _input; // the slick 2d player input object
	private boolean _interacting = false; // True if player is interacting ith an entity
	
	/** create the entity
	 * @param x init x pos
	 * @param y init y pos
	 * @param image ini imahe
	 * @param as adventure state holding the entty
	 * @param input slick2d user input onject
	 */
	public PlayerEntity(float x, float y, Image image, AdventureState as, Input input)
	{
		super(x, y, image, as);
		_input = input;
		setSpeedScale(1);
	}
	
	@Override
	public void update(int delta)
	{
		if(isMoving())
		{
			moveStep();
		}
		
		if(!isMoving() && KeyTools.isMOVEDown(_input) && !_interacting) // Not else so continued movement is smooth (doesn't miss a frame).
		{
			float[] moveVec = KeyTools.getMoveVector(_input);
			setMoveAction(moveVec[0] + getX(), moveVec[1] + getY());
		}
	}

	/** sets if plaer is interacting
	 *  @param interacting true if they are
	 */
	public void setInteracting(boolean interacting)
	{
		_interacting = interacting;
	}
	
	/** @return true if player is interacting with another entity */
	public boolean getInteracting()
	{
		return _interacting;
	}

}
