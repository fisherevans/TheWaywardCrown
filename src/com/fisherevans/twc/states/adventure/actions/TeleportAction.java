package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.ActionManager;
import com.fisherevans.twc.states.adventure.entities.MovableEntity;

public class TeleportAction extends AdventureAction
{
	private MovableEntity _ent;
	private int[] _pos;
	
	public TeleportAction(ActionManager am, boolean blockInput, MovableEntity ent, int[] pos)
	{
		super(am, blockInput);
		_ent = ent;
		_pos = pos;
	}

	@Override
	public void initAction()
	{
		_ent.setX(_pos[0]);
		_ent.setY(_pos[1]);
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
