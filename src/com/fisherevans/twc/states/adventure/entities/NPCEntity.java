package com.fisherevans.twc.states.adventure.entities;

import java.util.ArrayList;

import org.newdawn.slick.Image;
import org.newdawn.slick.Input;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.EntityManager;
import com.fisherevans.twc.states.adventure.actions.AdventureAction;
import com.fisherevans.twc.states.adventure.actions.ChangeCameraAction;
import com.fisherevans.twc.states.adventure.actions.DialogueAction;
import com.fisherevans.twc.states.adventure.actions.WaitAction;
import com.fisherevans.twc.states.adventure.entities.controlers.NullControler;
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
	public NPCEntity(float x, float y, Image image, EntityManager em)
	{
		super(x, y, image, new RandomControler(), em);
		setSpeedScale(0.5f);
	}
}
