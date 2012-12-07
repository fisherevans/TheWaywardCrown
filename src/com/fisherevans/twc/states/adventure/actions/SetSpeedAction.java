package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.ActionManager;
import com.fisherevans.twc.states.adventure.entities.MovableEntity;


public class SetSpeedAction extends AdventureAction
{
	private MovableEntity _ent;
	private float _speedScale;

	public SetSpeedAction(ActionManager am, boolean blockInput, MovableEntity ent, float speedScale)
	{
		super(am, blockInput);
		_ent = ent;
		_speedScale = speedScale;
	}

	@Override
	public void initAction()
	{
		_ent.setSpeedScale(_speedScale);
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
	public void render(Graphics gfx)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean keyPressed(int key, char c)
	{
		// TODO Auto-generated method stub
		return false;
	}

}
