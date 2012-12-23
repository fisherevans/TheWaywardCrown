package com.fisherevans.twc.states.adventure.actions;

public class InsertAction extends AdventureAction
{
	private String _actionQueue;
	
	public InsertAction(ActionManager am, String actionQueue)
	{
		super(am);
		_actionQueue = actionQueue;
	}

	@Override
	public void initAction()
	{
		ActionQueue thisQueue = getAM().getCurrentQueue();
		ActionQueue insert = getAM().getActionQueue(_actionQueue);
		
		if(insert != null)
		{
			thisQueue.getActions().addAll(1, insert.getActions());
		}
		else
		{
			System.out.println("The queue: " + _actionQueue + " does not exist...");
		}
		
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
		// TODO Auto-generated method stub
		
	}

}
