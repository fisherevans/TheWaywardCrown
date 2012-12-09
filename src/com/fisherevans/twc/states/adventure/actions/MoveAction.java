package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;
import com.fisherevans.twc.tools.KeyTools;

public class MoveAction extends AdventureAction
{
	private MovableEntity _ent;
	private int _angle, _distance;

	public MoveAction(ActionManager am, String ent, int angle, int distance)
	{
		super(am);
		_ent = ((MovableEntity)getAM().getAS().getEM().getEntity(ent));
		_angle = angle;
		_distance = distance;
	}

	@Override
	public void initAction()
	{
		if(_ent.isMoving()) // If we're not already moving.
		{
			_ent.setMoveAction(_angle, _distance);
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
		if(!_ent.isMoving())
		{
			setComplete(true);
		}
	}

	@Override
	public void keyPressed(int key, char c)
	{
		
	}

}
