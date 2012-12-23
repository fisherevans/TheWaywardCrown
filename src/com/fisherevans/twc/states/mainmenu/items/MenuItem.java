package com.fisherevans.twc.states.mainmenu.items;

import org.newdawn.slick.Color;

import com.fisherevans.twc.states.State;

public abstract class MenuItem
{
	private Color _color, _highColor; // color of the item normally and when selected.
	private String _text; // The text of the item
	private boolean _selectable; // If you can select the object
	
	/** Method to call when the user selects the menu item */
	public abstract State action();
	
	/** Sets the text of the item
	 * @param text the new text
	 */
	public void setText(String text)
	{
		_text = text;
	}
	
	/** sets the normal color of the item
	 * @param color the new color
	 */
	public void setColor(Color color)
	{
		_color = color;
	}

	/** sets the selected color of the item
	 * @param color the new color
	 */
	public void setHighColor(Color highColor)
	{
		_highColor = highColor;
	}
	
	/** Sets whether the item can be selected in the menu
	 * @param selectable true for yes, false for no
	 */
	public void setSelectable(boolean selectable)
	{
		_selectable = selectable;
	}
	
	/** @return the text of the item */
	public String getText()
	{
		return _text;
	}

	/** @return the color of the item normally */
	public Color getColor()
	{
		return _color;
	}
	
	/** @return the color of the item when it's selected */
	public Color getHighColor()
	{
		return _highColor;
	}
	
	/** @return true if the item can be selected, false if not. */
	public boolean isSelectable()
	{
		return _selectable;
	}
}
