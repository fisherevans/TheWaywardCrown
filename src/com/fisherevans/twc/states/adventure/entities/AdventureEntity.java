package com.fisherevans.twc.states.adventure.entities;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.tools.*;
import com.fisherevans.twc.states.adventure.AdventureState;

public abstract class AdventureEntity
{
	private float _x, _y; // The x and y corrids fo the entity (in tile ids)
	private Image _image; // The image of the sprite
	private AdventureState _as; // The state holding the entity
	
	/** Create the entity.
	 * @param x The initital x pos
	 * @param y the inititial y pos
	 * @param image The initian sprite/image
	 * @param as the state holding this ent
	 */
	public AdventureEntity(float x, float y, Image image, AdventureState as)
	{
		if(image == null)
		{
			image = ResourceTools.getImage("res/sprites/default32x32.png");
		}
		_image = image;
		_x = x;
		_y = y;
		_as = as;
	}
	
	/** Updates the entity's pos
	 * @param delta The time delta;
	 */
	public abstract void update(int delta);
	
	/** @return the x position to draw */
	public float getX()
	{
		return _x;
	}
	
	/** @return the y position to draw */
	public float getY()
	{
		return _y;
	}
	
	/** set the x value
	 *  @param x the new x value
	 */
	public void setX(float x)
	{
		_x = x;
	}
	
	/** set the y value
	 * @param y the new y value
	 */
	public void setY(float y)
	{
		_y = y;
	}
	
	/** The tile x corrid the entity is "occupying"
	 * @return the x corrid
	 */
	public int getOccuppiedX()
	{
		return (int)_x;
	}

	/** The tile y corrid the entity is "occupying"
	 * @return the y corrid
	 */
	public int getOccuppiedY()
	{
		return (int)_y;
	}
	
	/** Gets the entity's image
	 * @return The image
	 */
	public Image getImage()
	{
		return _image;
	}
	
	/** set the entity's image
	 * @param image The new image
	 */
	public void setImage(Image image)
	{
		_image = image;
	}
	
	/** @return the adventure state holding this enttiy */
	public AdventureState getAS()
	{
		return _as;
	}
}
