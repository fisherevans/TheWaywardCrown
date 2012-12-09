package com.fisherevans.twc.states.adventure.config;

public class EntityConfig
{
	public enum ENTTYPE { MOVABLE };
	private String _name, _sprite, _icon, _controller, _actions, _dispName;
	private float _x = 0, _y = 0, _speed = 1;
	private int _angle = 90;
	private ENTTYPE _type = ENTTYPE.MOVABLE;
	
	public EntityConfig(String name)
	{
		_name = name;
	}
	
	public boolean isBadConfig()
	{
		//System.out.printf("%s, %s, %s, %s, %s, %s, %f, %f, %d, %f\n", _name,_dispName,_sprite,_icon,_controller,_actions,_x,_y,_angle,_speed);
		return _name == null || _dispName == null || _sprite == null || _icon == null || _controller == null || _x < 0 || _y < 0 || _angle < 0 || _speed < 0;
	}
	
	public String getActions()
	{
		return _actions;
	}
	public void setActions(String actions)
	{
		_actions = actions;
	}
	
	public String getName()
	{
		return _name;
	}
	public void setName(String name)
	{
		_name = name;
	}
	
	public String getSprite()
	{
		return _sprite;
	}
	public void setSprite(String sprite)
	{
		_sprite = sprite;
	}
	public String getIcon()
	{
		return _icon;
	}
	public void setIcon(String icon)
	{
		_icon = icon;
	}
	public String getController()
	{
		return _controller;
	}
	public void setController(String controler)
	{
		_controller = controler;
	}
	public float getX()
	{
		return _x;
	}
	public void setX(float x)
	{
		_x = x;
	}
	public float getY()
	{
		return _y;
	}
	public void setY(float y)
	{
		_y = y;
	}
	public int getAngle()
	{
		return _angle;
	}
	public void setAngle(int angle)
	{
		_angle = angle;
	}
	public ENTTYPE getType()
	{
		return _type;
	}
	public void setType(ENTTYPE type)
	{
		_type = type;
	}

	public float getSpeed()
	{
		return _speed;
	}

	public void setSpeed(float speed)
	{
		_speed = speed;
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
