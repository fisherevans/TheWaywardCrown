package com.fisherevans.twc.states.adventure.actions;

import com.fisherevans.twc.states.adventure.AdventureState;

public class SetAdventureStateAction extends AdventureAction
{
	private String _ldr;

	public SetAdventureStateAction(ActionManager am, String ldr)
	{
		super(am);
		_ldr = ldr;
	}

	@Override
	public void initAction()
	{
		setComplete(true);
		getAM().getAS().getSM().setState(new AdventureState(getAM().getAS().getSM(), getAM().getAS().getInput(), _ldr));
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
		// TODO Auto-generated method stub
		
	}

}
