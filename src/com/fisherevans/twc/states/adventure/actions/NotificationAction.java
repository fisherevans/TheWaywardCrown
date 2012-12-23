package com.fisherevans.twc.states.adventure.actions;

import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.DialogueManager;
import com.fisherevans.twc.states.adventure.entities.AdventureEntity;
import com.fisherevans.twc.tools.ResourceTools;

public class NotificationAction extends AdventureAction
{
	private String _baseMessage;

	public NotificationAction(ActionManager am, String baseMessage)
	{
		super(am);
		_baseMessage = baseMessage;
	}

	@Override
	public void initAction()
	{
		getAM().getAS().getDM().addNotification(_baseMessage);
	}

	@Override
	public void reInitAction()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAction(int delta)
	{
		if(!getAM().getAS().getDM().isShowing())
		{
			setComplete(true);
		}
	}

	@Override
	public void keyPressed(int key, char c)
	{
		
	}
}
