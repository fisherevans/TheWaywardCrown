package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;

public class TeleportAction extends AdventureAction
{
	private String _entName;
	private int _x, _y;
	
	public TeleportAction(ActionManager am, String entName, int x, int y)
	{
		super(am);
		_entName = entName;
		_x = x;
		_y = y;
	}

	@Override
	public void initAction()
	{
		MovableEntity ent = ((MovableEntity)getAM().getAS().getEM().getEntity(_entName));
		ent.setX(_x);
		ent.setY(_y);
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
