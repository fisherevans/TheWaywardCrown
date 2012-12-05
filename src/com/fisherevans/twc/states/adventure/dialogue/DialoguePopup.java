package com.fisherevans.twc.states.adventure.dialogue;

import org.newdawn.slick.Image;

import com.fisherevans.twc.tools.ResourceTools;

public class DialoguePopup
{
	private String _text;
	private Image _icon;
	private boolean _leftOrient;
	
	public DialoguePopup(String text, Image icon, boolean leftOrient)
	{
		_text = text;
		_leftOrient = leftOrient;
		
		if(icon == null)
		{
			icon = ResourceTools.getImage("res/sprites/test/default64x64.png");
		}

		_icon = icon;
	}
	
	public String getText()
	{
		return _text;
	}

	public void setText(String text)
	{
		_text = text;
	}

	public Image getIcon()
	{
		return _icon;
	}

	public void setIcon(Image icon)
	{
		_icon = icon;
	}

	public boolean isLeftOrient()
	{
		return _leftOrient;
	}

	public void setLeftOrient(boolean leftOrient)
	{
		_leftOrient = leftOrient;
	}

}
