package com.fisherevans.twc.states.adventure.entities;

import java.util.ArrayList;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.tools.*;
import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.actions.AdventureAction;

public abstract class AdventureEntity
{
	private float _x, _y; // The x and y corrids fo the entity (in tile ids)
	private Image _image; // The image of the sprite
	private Image _icon;
	private EntityManager _em; // The state holding the entity
	private boolean _interacting = false; // True if entity is interacting with another entity
	private int _angle = 90; // 0 means they're facing right, 90 is down, so on.
	private int[] _drawOffset = { 0, 0 };
	private String _actions;
	private String _dispName;
	
	/** Create the entity.
	 * @param x The initital x pos
	 * @param y the inititial y pos
	 * @param image The initian sprite/image
	 * @param as the state holding this ent
	 */
	public AdventureEntity(EntityManager em)
	{
		_image = ResourceTools.getImage("res/sprites/test/default32x32.png");
		_x = 0;
		_y = 0;
		_em = em;
	}

	/** @return the entity manager holding this enttiy */
	public EntityManager getEM()
	{
		return _em;
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
	public int getOccupiedX()
	{
		return (int)_x;
	}

	/** The tile y corrid the entity is "occupying"
	 * @return the y corrid
	 */
	public int getOccupiedY()
	{
		return (int)_y;
	}
	
	/** Gets the entity's image
	 * @return The image
	 */
	public Image getImage()
	{
		_image.setRotation(getAngle());
		return _image;
	}
	
	/** set the entity's image
	 * @param image The new image
	 */
	public void setImage(Image image)
	{
		_image = image;
	}

	/** sets if plaer is interacting
	 *  @param interacting true if they are
	 */
	public void setInteracting(boolean interacting)
	{
		_interacting = interacting;
	}
	
	/** @return true if player is interacting with another entity */
	public boolean isInteracting()
	{
		return _interacting;
	}
	
	/** @return the angle the ent is facing in degrees */
	public int getAngle()
	{
		return _angle;
	}
	
	/** Sets he ents angle in degrees
	 * @param angle the new angle in degrees
	 */
	public void setAngle(int angle)
	{
		angle %= 360;
		_angle = angle;
	}
	
	public int[] getDrawOffset()
	{
		return _drawOffset;
	}
	
	public void setDrawOffset(int[] newOffset)
	{
		_drawOffset = newOffset;
	}

	public Image getIcon()
	{
		return _icon;
	}

	public void setIcon(Image icon)
	{
		_icon = icon;
	}

	public String getActions()
	{
		return _actions;
	}

	public void setActions(String actions)
	{
		_actions = actions;
	}

	public String getDispName()
	{
		return _dispName;
	}

	public void setDispName(String dispName)
	{
		_dispName = dispName;
	}
}
