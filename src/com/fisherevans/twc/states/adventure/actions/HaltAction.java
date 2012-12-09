package com.fisherevans.twc.states.adventure.actions;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;

public class HaltAction extends AdventureAction
{
	private String _entName;
	private boolean _halt;
	
	public HaltAction(ActionManager am, String entName, boolean halt)
	{
		super(am);
		_entName = entName;
		_halt = halt;
	}

	@Override
	public void initAction()
	{
		MovableEntity ent = ((MovableEntity)getAM().getAS().getEM().getEntity(_entName));
		if(_halt)
			ent.halt();
		else
			ent.unhalt();

		setComplete(true);
	}

	@Override
	public void reInitAction()
	{
	}

	@Override
	public void updateAction(int delta)
	{
	}

	@Override
	public void keyPressed(int key, char c)
	{
		// TODO Auto-generated method stub
		
	}

}
