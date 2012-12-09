package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;


public class SetSpeedAction extends AdventureAction
{
	private String _entName;
	private float _speedScale;

	public SetSpeedAction(ActionManager am, String entName, float speedScale)
	{
		super(am);
		_entName = entName;
		_speedScale = speedScale;
	}

	@Override
	public void initAction()
	{
		MovableEntity ent = ((MovableEntity)getAM().getAS().getEM().getEntity(_entName));
		ent.setSpeedScale(_speedScale);
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
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key, char c)
	{
		
	}

}
