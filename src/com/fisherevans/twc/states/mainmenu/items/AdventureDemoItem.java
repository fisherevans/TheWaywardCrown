package com.fisherevans.twc.states.mainmenu.items;

import org.newdawn.slick.Color;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.mainmenu.MainMenuState;

public class AdventureDemoItem extends MenuItem
{
	/** Creates the adventure demo item - Loads a temp map for testing purposes */
	public AdventureDemoItem()
	{
		setColor(new Color(0.6f, 0.6f, 0.6f));
		setHighColor(new Color(1f, 1f, 1f));
		setText("Adventure Demo");
		setSelectable(true);
	}
	
	@Override
	public State action()
	{
		return new AdventureState(MainMenuState.getSM(), GameDriver.getInput(), "res/configs/forest_test.ldr");
	}
}
