package com.fisherevans.twc.states.adventure;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.tiled.TiledMap;

public class MapManager
{
	private AdventureState _as;
	private TiledMap _map; // The tiled map that is used with this adventure
	private int _mapW, _mapH, _mapTileSize; // Width and height of map and it's tiles
	
	public MapManager(AdventureState as)
	{
		_as = as;
		
		try
		{
			_map = new TiledMap("res/maps/test/test.tmx", "res/maps/test");
		}
		catch(Exception e)
		{
			System.out.println("Could not load test map");
			e.printStackTrace();
		}
		
		_mapW = _map.getWidth();
		_mapH = _map.getHeight();
		_mapTileSize = _map.getTileHeight()*2;
	}

	/** Draw tiledmap layer on screen centered around shifts
	 * @param gfx slick2d graphics object
	 * @param xshift x center corrid
	 * @param yshift y center corrid
	 */
	public void drawLayer(Graphics gfx, float xshift, float yshift, int layer)
	{
		gfx.scale(2, 2);
		_map.render((int)+xshift/2, (int)+yshift/2, layer);
		gfx.scale(0.5f, 0.5f);
	}
	
	public AdventureState getAS()
	{
		return _as;
	}

	public TiledMap getMap()
	{
		return _map;
	}

	public void setMap(TiledMap map)
	{
		_map = map;
	}

	public int getMapW()
	{
		return _mapW;
	}

	public void setMapW(int mapW)
	{
		_mapW = mapW;
	}

	public int getMapH()
	{
		return _mapH;
	}

	public void setMapH(int mapH)
	{
		_mapH = mapH;
	}

	public int getMapTileSize()
	{
		return _mapTileSize;
	}

	public void setMapTileSize(int mapTileSize)
	{
		_mapTileSize = mapTileSize;
	}
}
