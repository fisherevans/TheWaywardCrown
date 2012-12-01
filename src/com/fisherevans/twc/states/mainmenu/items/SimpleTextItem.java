package com.fisherevans.twc.states.mainmenu.items;

import org.newdawn.slick.Color;

public class SimpleTextItem extends MenuItem
{
	public SimpleTextItem(String text, boolean selectable)
	{
		setColor(new Color(0.6f, 0.6f, 0.6f));
		setHighColor(new Color(1f, 0.5f, 0f));
		setText(text);
		setSelectable(selectable);
	}
	
	@Override
	public void action()
	{
		
	}
}
