package com.fisherevans.twc.states.mainmenu.items;

import java.awt.event.WindowFocusListener;
import java.awt.event.WindowListener;

import org.newdawn.slick.Color;

import com.fisherevans.twc.Start;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;

public class ExitItem extends MenuItem
{
	/** create an exit item. When used will close the program */
	public ExitItem()
	{
		setColor(new Color(0.6f, 0.6f, 0.6f));
		setHighColor(new Color(180, 60, 60));
		setText("Exit");
		setSelectable(true);
	}
	
	@Override
	public State action()
	{
		Start.pullThePlug();
		return null;
	}
}
