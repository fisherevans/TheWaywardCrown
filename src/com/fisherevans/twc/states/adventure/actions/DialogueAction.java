package com.fisherevans.twc.states.adventure.actions;

import com.fisherevans.twc.states.adventure.AdventureState;

public class DialogueAction extends AdventureAction
{
	private String _message;

	public DialogueAction(AdventureState as, String message)
	{
		super(as);
		_message = message;
	}

	@Override
	public void updateAction()
	{
		if(this.isHasStarted())
		{
			if(!getAS().isDialogueVisible())
			{
				setComplete(true);
			}
		}
		else
		{
			startAction();
			setHasStarted(true);
		}
	}

	@Override
	public void startAction()
	{
		getAS().setDialogueText(_message);
		getAS().setDialogueVisible(true);
	}

}
