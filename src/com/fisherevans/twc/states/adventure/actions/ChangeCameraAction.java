package com.fisherevans.twc.states.adventure.actions;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.entities.AdventureEntity;

public class ChangeCameraAction extends AdventureAction
{
	private AdventureEntity _ent;
	
	public ChangeCameraAction(AdventureState as, AdventureEntity ent)
	{
		super(as);
		_ent = ent;
	}
	
	public void startAction()
	{
		getAS().setCameraEntity(_ent);
		setComplete(true);
	}

	@Override
	public void updateAction()
	{
		if(!isHasStarted())
		{
			startAction();
			setHasStarted(true);
		}
	}
}
