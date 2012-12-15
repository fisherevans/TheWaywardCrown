package com.fisherevans.twc.states;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.tools.MathTools;
import com.fisherevans.twc.tools.ResourceTools;

public class FadeManager
{
	private State _state;
	private boolean _fadingOut = false;
	private float _fadeScale = 1;
	private Image _fadeImage;
	
	public FadeManager(State state)
	{
		_state = state;
		
		_fadeImage = ResourceTools.getImage("res/gui/blackout.png");
	}
	
	public void update(int delta)
	{
		//if(delta > 30)
		//	System.out.println(delta);
		if(_fadingOut)
		{
			_fadeScale += 0.002*delta;
		}
		else
		{
			_fadeScale -= 0.001*delta;
		}

		_fadeScale = MathTools.clamp(_fadeScale, 0, 1);
		//System.out.println(_fadeScale);
		_fadeImage.setAlpha(_fadeScale);
	}
	
	public void render(Graphics gfx)
	{
		gfx.drawImage(_fadeImage, 0, 0);
	}
	
	public void setFadeScale(float fadeScale)
	{
		_fadeScale = fadeScale;
	}
	
	public boolean isFadedOut()
	{
		return _fadeScale >= 1;
	}
	
	public boolean isFadedIn()
	{
		return _fadeScale <= 0;
	}
	
	public void fadeOut()
	{
		_fadingOut = true;
	}
	
	public void fadeIn()
	{
		_fadingOut = false;
	}

	public State getState()
	{
		return _state;
	}

	public void setState(State state)
	{
		_state = state;
	}
}
