package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.entities.AdventureEntity;
import com.fisherevans.twc.states.adventure.entities.MovableEntity;

public class SetCameraAction extends AdventureAction
{
	private String _entName;
	
	public SetCameraAction(ActionManager am, String entName)
	{
		super(am);
		_entName = entName;
	}

	@Override
	public void initAction()
	{
		MovableEntity ent = (MovableEntity) getAM().getAS().getEM().getEntity(_entName);
		getAM().getAS().getEM().setCameraEntity(ent);
		setComplete(true);
	}

	@Override
	public void reInitAction()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAction(int delta)
	{
		
	}

	@Override
	public void keyPressed(int key, char c)
	{
		
	}
}
