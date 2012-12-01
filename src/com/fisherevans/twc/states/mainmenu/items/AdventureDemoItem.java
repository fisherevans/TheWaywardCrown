package com.fisherevans.twc.states.mainmenu.items;

import org.newdawn.slick.Color;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.mainmenu.MainMenuState;

public class AdventureDemoItem extends MenuItem
{
	public AdventureDemoItem()
	{
		setColor(new Color(0.6f, 0.6f, 0.6f));
		setHighColor(new Color(1f, 1f, 1f));
		setText("Adventure Demo");
		setSelectable(true);
	}
	
	@Override
	public void action()
	{
		MainMenuState.getSM().setState(new AdventureState(MainMenuState.getSM()));
	}
}
