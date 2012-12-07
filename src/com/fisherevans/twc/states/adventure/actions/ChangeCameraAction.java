package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.ActionManager;
import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.entities.AdventureEntity;

public class ChangeCameraAction extends AdventureAction
{
	private AdventureEntity _ent;
	
	public ChangeCameraAction(ActionManager am, AdventureEntity ent)
	{
		super(am, false);
		_ent = ent;
	}

	@Override
	public void initAction()
	{
		getAM().getAS().getEM().setCameraEntity(_ent);
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
	public void render(Graphics gfx)
	{
		
	}

	@Override
	public boolean keyPressed(int key, char c)
	{
		return isBlockingInput();
	}
}
