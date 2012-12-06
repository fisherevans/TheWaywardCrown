package com.fisherevans.twc.states.adventure.entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.actions.AdventureAction;
import com.fisherevans.twc.states.adventure.actions.ChangeCameraAction;
import com.fisherevans.twc.states.adventure.actions.DialogueAction;
import com.fisherevans.twc.states.adventure.actions.WaitAction;
import com.fisherevans.twc.states.adventure.entities.controlers.RandomControler;
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
		super(x, y, image, new RandomControler(), as);
		setSpeedScale(0.5f);
	}

	public void getInteraction()
	{
		ArrayList<AdventureAction> actions = new ArrayList<AdventureAction>();

		getAS().addAction(new ChangeCameraAction(getAS(), this));
		getAS().addAction(new DialogueAction(getAS(), "You have mail!"));
		getAS().addAction(new ChangeCameraAction(getAS(), getAS().getCameraEntity()));
		
		getAS().addActions(actions);
	}
}
