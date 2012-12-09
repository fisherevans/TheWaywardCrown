package com.fisherevans.twc.states.adventure.entities.controllers;

import com.fisherevans.twc.states.adventure.entities.MovableEntity;

public abstract class EntityController
{
	private MovableEntity _ent;
	
	public EntityController(MovableEntity ent)
	{
		_ent = ent;
	}
	
	public abstract void update(int delta);
	
	public void keyPressed(int key, char c)
	{
		
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
