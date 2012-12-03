package com.fisherevans.twc.states.mainmenu.items;

import org.newdawn.slick.Color;

public class SimpleTextItem extends MenuItem
{
	/** Creates a menu item that is just text and has no action .
	 * @param text Text of the item
	 * @param selectable If it can be selected or not.
	 */
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
