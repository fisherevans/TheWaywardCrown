package com.fisherevans.twc.states.adventure.entities.controlers;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;

public abstract class EntityControler
{
	private MovableEntity _ent;
	
	public EntityControler()
	{
		_ent = null;
	}
	
	public abstract void update(int delta);
	
	public boolean keyPressed(int key, char c)
	{
		return false;
	}

	public MovableEntity getEnt()
	{
		return _ent;
	}

	public void setEnt(MovableEntity ent)
	{
		_ent = ent;
	}
}
