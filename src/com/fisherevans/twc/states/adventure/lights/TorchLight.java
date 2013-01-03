package com.fisherevans.twc.states.adventure.lights;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;

public class TorchLight extends AdventureLight
{
	private Color _baseColor;
	private long _nextFlicker = 0;
	
	private final int FLICKER_DUR = 150;
	private final float FLICKER_DARKNESS = 0.1f;
	
	public TorchLight(String name)
	{
		super(0, 0, 64, name);
		_baseColor = new Color(1f, 0.9f, 0.5f);
		setColor(_baseColor);
	}

	@Override
	public void update(int delta)
	{
		if(System.currentTimeMillis() > _nextFlicker)
		{
			setColor(_baseColor.darker((float)Math.random()*FLICKER_DARKNESS));
			_nextFlicker = System.currentTimeMillis() + (long)(Math.random()*FLICKER_DUR);
		}
	}

	@Override
	public void render(Graphics gfx)
	{
		// TODO Auto-generated method stub
		
	}
	
}
