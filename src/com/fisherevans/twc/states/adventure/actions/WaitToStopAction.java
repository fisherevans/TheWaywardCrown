package com.fisherevans.twc.states.adventure.actions;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;

public class WaitToStopAction extends AdventureAction
{
	private String _entName;
	private MovableEntity _ent;
	private boolean _wasHalted;
	
	public WaitToStopAction(ActionManager am, String entName)
	{
		super(am);
		_entName = entName;
	}

	@Override
	public void initAction()
	{
		_ent = ((MovableEntity)getAM().getAS().getEM().getEntity(_entName));
		_wasHalted = _ent.isHalted();
		_ent.halt();
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
			if(!_wasHalted)
			{
				_ent.unhalt();
			}
		}
	}

	@Override
	public void keyPressed(int key, char c)
	{
		// TODO Auto-generated method stub
		
	}

}
