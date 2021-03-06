package com.fisherevans.twc.states.adventure;

import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.states.adventure.config.AdventureConfigLoader;
import com.fisherevans.twc.states.adventure.config.MapConfig;
import com.fisherevans.twc.states.adventure.entities.AdventureEntity;
import com.fisherevans.twc.tools.MathTools;

public class MapManager
{
	private AdventureState _as;
	private TiledMap _map; // The tiled map that is used with this adventure
	private int _mapW, _mapH, _mapTileSize; // Width and height of map and it's tiles
	
	private int _tsX, _tsY, _sX, _sY, _baseSX, _baseSY;
	
	public static final int TILES_WIDE = 22, TILES_HIGH = 14;
	
	public MapManager(AdventureState as)
	{
		_as = as;
	}
	
	public void initManager(AdventureConfigLoader config)
	{
		MapConfig mapConfig = config.getMapConfig();
		try
		{
			_map = new TiledMap(mapConfig.getTmx(), mapConfig.getTiles());
		}
		catch(Exception e)
		{
			System.out.println("Could not load test map - " + mapConfig.getTmx() + " - " + mapConfig.getTiles());
			e.printStackTrace();
		}
		
		_mapW = _map.getWidth();
		_mapH = _map.getHeight();
		_mapTileSize = _map.getTileHeight()*2;

		_baseSX = (int) ((-_map.getTileHeight() + GameDriver.NATIVE_SCREEN_H_WIDTH)*0.5f); 
		_baseSY = (int) ((-_map.getTileHeight() + GameDriver.NATIVE_SCREEN_H_HEIGHT)*0.5f);
		
		_as.getEM().setCameraEntity(mapConfig.getCamera());
		_as.getEM().setPlayerEntity(mapConfig.getPlayer());
		
		_as.getEM().setShifts(_map.getTileHeight());
	}
	
	public void updateShift(AdventureEntity camera)
	{
		_tsX = (int) (camera.getX() - 10); // half wide
		_tsY = (int) (camera.getY() - 6); // hald tall
		_sX = (int) ((_tsX-camera.getX())*_map.getTileHeight() + _baseSX);
		_sY = (int) ((_tsY-camera.getY())*_map.getTileHeight() + _baseSY);
	}

	/** Draw tiledmap layer on screen centered around shifts
	 * @param gfx slick2d graphics object
	 * @param xshift x center corrid
	 * @param yshift y center corrid
	 */
	public void drawLayer(Graphics gfx, int layer)
	{
		/* built in method
		gfx.scale(2, 2);
		_map.render(_sX, _sY, _tsX, _tsY, TILES_WIDE, TILES_HIGH, layer, false);
		gfx.scale(0.5f, 0.5f);
		*/
		//* Custom MEthod
		Image imgBuff;
		float sX2 = _sX*2;
		float sY2 = _sY*2;
		for(int x = Math.max(_tsX, 0);x < Math.min(_tsX+TILES_WIDE, _map.getWidth());x++)
		{
			for(int y = Math.max(_tsY, 0);y < Math.min(_tsY+TILES_HIGH, _map.getHeight());y++)
			{
				imgBuff = _map.getTileImage(x, y, layer);
				if(imgBuff != null)
				{
					imgBuff.draw(sX2+(x-_tsX)*_mapTileSize, sY2+(y-_tsY)*_mapTileSize, 2f);
				}
			}
		}
		//*/
	}
	
	public void drawLayer(Graphics gfx, String layer)
	{
		drawLayer(gfx, _map.getLayerIndex(layer));
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
