package com.fisherevans.twc.states.mainmenu.items;

import java.util.ArrayList;

import org.newdawn.slick.Color;
import org.newdawn.slick.Font;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.credits.CreditsState;
import com.fisherevans.twc.states.mainmenu.MainMenuState;
import com.fisherevans.twc.tools.ResourceTools;

public class CreditsItem extends MenuItem
{
	/** create an exit item. When used will close the program */
	public CreditsItem()
	{
		setColor(new Color(0.6f, 0.6f, 0.6f));
		setHighColor(new Color(1f, 1f, 1f));
		setText("Credits");
		setSelectable(true);
	}
	
	@Override
	public State action()
	{
		return new CreditsState(MainMenuState.getSM(), GameDriver.getInput());
	}
}
