package com.fisherevans.twc.states.adventure.actions;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;
import com.fisherevans.twc.tools.KeyTools;

public class MoveAction extends AdventureAction
{
	private String _entName;
	MovableEntity _ent;
	private int _angle, _distance;

	public MoveAction(ActionManager am, String entName, int angle, int distance)
	{
		super(am);
		_entName = entName;
		_angle = angle;
		_distance = distance;
	}

	@Override
	public void initAction()
	{
		_ent = ((MovableEntity)getAM().getAS().getEM().getEntity(_entName));
		if(!_ent.isMoving()) // If we're not already moving.
		{
			//System.out.println("Moving " + _distance + " unites at " + _angle + " degres");
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
