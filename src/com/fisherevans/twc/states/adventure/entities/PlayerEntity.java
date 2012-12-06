package com.fisherevans.twc.states.adventure.entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.actions.AdventureAction;
import com.fisherevans.twc.states.adventure.entities.controlers.*;
import com.fisherevans.twc.tools.KeyTools;

public class PlayerEntity extends MovableEntity
{
	private Input _input; // the slick 2d player input object
	
	/** create the entity
	 * @param x init x pos
	 * @param y init y pos
	 * @param image ini imahe
	 * @param as adventure state holding the entty
	 * @param input slick2d user input onject
	 */
	public PlayerEntity(float x, float y, Image image, AdventureState as, Input input)
	{
		super(x, y, image, new PlayerControler(input), as);
		_input = input;
		setSpeedScale(1);
	}
	
	@Override
	public void update(int delta)
	{
		getControler().update(delta);
	}
	
	public void tryToInteract()
	{
		int[] intVec = { 0, 0 };
		switch(getAngle())
		{
			case 0: intVec[0] = 1; break;
			case 90: intVec[1] = 1; break;
			case 180: intVec[0] = -1; break;
			case 270: intVec[1] = -1; break;
			default: return;
		}
		
		AdventureEntity ent = getAS().getEntityIn(getOccupiedX() + intVec[0], getOccupiedY() + intVec[1], this);
		
		if(ent == null)
		{
			return;
		}
		
		ent.getInteraction();
		
		//setInteracting(true);
		//ent.setInteracting(true);
	}
	
	public void getInteraction()
	{ }

}
