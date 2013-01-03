package com.fisherevans.twc.states.adventure.entities;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.entities.controllers.EntityController;

/**
 * @author Fisher
 *
 */
public class AnimatedEntity extends AdventureEntity
{
	private Animation _anUp , _anDown, _anLeft, _anRight;
	private int _frameDuration;

	/** create the entity
	 * @param x init x pos
	 * @param y init y pos
	 * @param image ini imahe
	 * @param as adventure state holding the entty
	 */
	public AnimatedEntity(EntityManager em)
	{
		super(em);

		setDrawOffset(new float[] { 0, -64 });
	}
	
	public void setAnimation(Image image, int frameDuration)
	{
		int w = image.getWidth();
		int frames = w/64;
		_frameDuration = frameDuration;
		
		_anUp = new Animation(true);
		_anDown = new Animation(true);
		_anLeft = new Animation(true);
		_anRight = new Animation(true);

		for(int animX = 0;animX < 64*frames/4;animX += 64)
		{
			_anUp.addFrame(image.getSubImage(animX, 0, 64, image.getHeight()), _frameDuration);
		}
		for(int animX = 64*frames/4;animX < 64*frames/4*2;animX += 64)
		{
			_anDown.addFrame(image.getSubImage(animX, 0, 64, image.getHeight()), _frameDuration);
		}
		for(int animX = 64*frames/4*2;animX < 64*frames/4*3;animX += 64)
		{
			_anLeft.addFrame(image.getSubImage(animX, 0, 64, image.getHeight()), _frameDuration);
		}
		for(int animX = 64*frames/4*3;animX < 64*frames/4*4;animX += 64)
		{
			_anRight.addFrame(image.getSubImage(animX, 0, 64, image.getHeight()), _frameDuration);
		}
	}

	@Override
	public void update(int delta)
	{
		updateAnimation(delta);
	}
	
	/** Steps throug animation
	 * @param delta time delta since last update
	 */
	public void updateAnimation(int delta)
	{
		switch(getAngle())
		{
			case 0: _anRight.update(delta); break;
			case 90: _anDown.update(delta); break;
			case 180: _anLeft.update(delta); break;
			case 270: _anUp.update(delta); break;
		}
	}
	
	@Override
	public Image getImage()
	{
		Animation anim;
		switch(getAngle())
		{
			case 0: anim = _anRight; break;
			case 90: anim = _anDown; break;
			case 180: anim = _anLeft; break;
			case 270: anim = _anUp; break;
			default: anim = _anDown; break;
		}
		return anim.getCurrentFrame();
	}
}
