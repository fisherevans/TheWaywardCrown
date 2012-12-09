package com.fisherevans.twc.states.adventure.config;

public class MapConfig
{
	private String _tmx, _tiles, _player, _camera;

	public String getPlayer()
	{
		return _player;
	}

	public void setPlayer(String player)
	{
		_player = player;
	}

	public String getCamera()
	{
		return _camera;
	}

	public void setCamera(String camera)
	{
		_camera = camera;
	}

	public String getTmx()
	{
		return _tmx;
	}

	public void setTmx(String tmx)
	{
		_tmx = tmx;
	}

	public String getTiles()
	{
		return _tiles;
	}

	public void setTiles(String tiles)
	{
		_tiles = tiles;
	}
}
