package com.fisherevans.twc.states.adventure;

import java.util.ArrayList;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.MathTools;
import com.fisherevans.twc.control.KeyCodes;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;

public class AdventureState extends State
{
	private TiledMap _map;
	private int _mapW, _mapH, _mapTileSize;
	private ArrayList<AdventureEntity> _ents;
	private PlayerEntity _pent;

	public AdventureState(StateManager sm, Input input)
	{
		super(sm, input);
		
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
		_mapTileSize = _map.getTileHeight();
		
		_ents = new ArrayList<AdventureEntity>();
		_pent = new PlayerEntity(50, 40, null, this, getInput());
		
		_ents.add(_pent);
	}
	
	public TiledMap getMap()
	{
		return _map;
	}
	
	public int getTileSize()
	{
		return _mapTileSize;
	}
	
	public int getMapH()
	{
		return _mapH;
	}
	
	public int getMapW()
	{
		return _mapW;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		for(AdventureEntity ent:_ents)
		{
			ent.update(delta);
		}
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		float xshift = -_pent.getX()*_mapTileSize + GameDriver.NATIVE_SCREEN_WIDTH/2 - _mapTileSize/2;
		float yshift = -_pent.getY()*_mapTileSize + GameDriver.NATIVE_SCREEN_HEIGHT/2 - _mapTileSize/2;
		
		_map.render((int)+xshift, (int)+yshift, 0);
		
		for(AdventureEntity ent:_ents)
		{
			gfx.drawImage(ent.getSprite(), ent.getX()*_mapTileSize+xshift, ent.getY()*_mapTileSize+yshift);
		}
		
		gfx.drawString("ADVENTURE TIME!!!!", 40, 40);
	}

	@Override
	public void mouseClicked(int button, int x, int y, int clickCount)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseDragged(int oldx, int oldy, int newx, int newy)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(int oldx, int oldy, int newx, int newy)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(int button, int x, int y)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(int button, int x, int y)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseWheelMoved(int change)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key, char c)
	{
		
	}

	@Override
	public void keyReleased(int key, char c)
	{
		
	}
}
