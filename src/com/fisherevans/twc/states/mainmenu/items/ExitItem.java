package com.fisherevans.twc.states.mainmenu.items;

import org.newdawn.slick.Color;

import com.fisherevans.twc.Start;
import com.fisherevans.twc.states.StateManager;

public class ExitItem extends MenuItem
{
	public ExitItem()
	{
		setColor(new Color(0.6f, 0.6f, 0.6f));
		setHighColor(new Color(1f, 0f, 0f));
		setText("Exit");
		setSelectable(true);
	}
	
	@Override
	public void action()
	{
		
	}
}
