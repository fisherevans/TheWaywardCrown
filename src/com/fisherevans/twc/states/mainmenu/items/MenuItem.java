package com.fisherevans.twc.states.mainmenu.items;

import org.newdawn.slick.Color;

public abstract class MenuItem
{
	private Color _color, _highColor;
	private String _text;
	private boolean _selectable;
	
	public abstract void action();
	
	public void setText(String text)
	{
		_text = text;
	}
	
	public void setColor(Color color)
	{
		_color = color;
	}
	
	public void setHighColor(Color highColor)
	{
		_highColor = highColor;
	}
	
	public void setSelectable(boolean selectable)
	{
		_selectable = selectable;
	}
	
	public String getText()
	{
		return _text;
	}

	public Color getColor()
	{
		return _color;
	}

	public Color getHighColor()
	{
		return _highColor;
	}
	
	public boolean isSelectable()
	{
		return _selectable;
	}
}
