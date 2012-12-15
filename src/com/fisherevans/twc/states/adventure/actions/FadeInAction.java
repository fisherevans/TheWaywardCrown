package com.fisherevans.twc.states.adventure.actions;

public class FadeInAction extends AdventureAction
{

	public FadeInAction(ActionManager am)
	{
		super(am);
	}

	@Override
	public void initAction()
	{
		getAM().getAS().getSM().getFM().fadeIn();
	}

	@Override
	public void reInitAction()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAction(int delta)
	{
		if(getAM().getAS().getSM().getFM().isFadedIn())
		{
			setComplete(true);
		}
	}

	@Override
	public void keyPressed(int key, char c)
	{
		// TODO Auto-generated method stub
		
	}

}
