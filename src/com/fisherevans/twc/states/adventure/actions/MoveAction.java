package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.ActionManager;
import com.fisherevans.twc.states.adventure.entities.MovableEntity;
import com.fisherevans.twc.tools.KeyTools;

public class MoveAction extends AdventureAction
{
	private MovableEntity _ent;
	private int[] _moveVec;

	public MoveAction(ActionManager am, boolean blockInput, MovableEntity ent, int[] moveVec)
	{
		super(am, blockInput);
		_ent = ent;
		_moveVec = moveVec;
	}

	@Override
	public void initAction()
	{
		if(!_ent.isMoving()) // If we're not already moving.
		{
			_ent.setMoveAction(_moveVec[0] + _ent.getX(), _moveVec[1] + _ent.getY());
		}
	}

	@Override
	public void reInitAction()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAction(int delta)
	{
		_ent.getControler().update(delta);
		if(!_ent.isMoving())
		{
			setComplete(true);
		}
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
