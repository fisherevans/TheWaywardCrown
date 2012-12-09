package com.fisherevans.twc.states.adventure.actions;

import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.DialogueManager;
import com.fisherevans.twc.tools.ResourceTools;

public class DialogueAction extends AdventureAction
{
	private String _baseMessage;
	private Image _icon;
	private boolean _leftAlign;

	public DialogueAction(ActionManager am, String baseMessage, Image icon, boolean leftAlign)
	{
		super(am);
		_baseMessage = baseMessage;
		_leftAlign = leftAlign;
		_icon = icon;
	}

	@Override
	public void initAction()
	{
		//getAM().getAS().getDM().addDialogue(_baseMessage, _leftAlign, _icon);
	}

	@Override
	public void reInitAction()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAction(int delta)
	{
		/*if(!getAM().getAS().getDM().isShowing())
		{
			setComplete(true);
		}*/
	}

	@Override
	public void keyPressed(int key, char c)
	{
		
	}
}
