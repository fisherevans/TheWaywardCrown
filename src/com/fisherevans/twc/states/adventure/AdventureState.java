package com.fisherevans.twc.states.adventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.Start;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;
import com.fisherevans.twc.states.adventure.entities.*;
import com.fisherevans.twc.tools.*;

public class AdventureState extends State
{
	private TiledMap _map; // The tiled map that is used with this adventure
	private int _mapW, _mapH, _mapTileSize; // Width and height of map and it's tiles
	private ArrayList<AdventureEntity> _ents; // The entities currently in the map.
	private PlayerEntity _pent; // The player's entity.

	/** Create the adventure state
	 * @param sm The state manager using this state
	 * @param input The slick2d input object affecting this state
	 */
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
		_mapTileSize = _map.getTileHeight()*2;
		
		_ents = new ArrayList<AdventureEntity>();
		_pent = new PlayerEntity(4, 4, ResourceTools.getImage("res/sprites/test/char.png"), this, getInput());

		_ents.add(_pent);

		_ents.add(new NPCEntity(5, 5, ResourceTools.getImage("res/sprites/test/char2.png"), this));
		_ents.add(new NPCEntity(6, 6, ResourceTools.getImage("res/sprites/test/char3.png"), this));
		//_ents.add(new NPCEntity(54, 40, ResourceTools.getImage("res/sprites/test/char.png"), this));
		//_ents.add(new NPCEntity(50, 37, ResourceTools.getImage("res/sprites/test/char.png"), this));
		//_ents.add(new NPCEntity(53, 40, ResourceTools.getImage("res/sprites/test/char.png"), this));
	}
	
	/** @return the TiledMap object of the map loaded for this state */
	public TiledMap getMap()
	{
		return _map;
	}
	
	/** @return the width/height of the map tiles (in pixels) */
	public int getTileSize()
	{
		return _mapTileSize;
	}
	
	/** @return the number of tiles hight the map is */
	public int getMapH()
	{
		return _mapH;
	}
	
	/** @return the number of tiles wide the map is. */
	public int getMapW()
	{
		return _mapW;
	}
	
	/** Checks to see if the tile is taken up by an entity other than the one passed
	 * @param x The tiledmap tile x corrid
	 * @param y the tiledmap tile y corrid
	 * @param ent The entity to ignore in testing
	 * @return true if available o move into, false if blocked by another entity.
	 */
	public boolean isEntityIn(int x, int y, AdventureEntity ent)
	{
		for(AdventureEntity tent:_ents)
		{
			if(tent.getOccupiedX() == x && tent.getOccupiedY() == y)
			{
				return true;
			}
		}
		return false;
	}
	
	/** Checks to see if the tile is taken up by an entity other than the one passed, then returns that entity
	 * @param x The tiledmap tile x corrid
	 * @param y the tiledmap tile y corrid
	 * @param ent The entity to ignore in testing
	 * @return the entity if there, null if nothing.
	 */
	public AdventureEntity getEntityIn(int x, int y, AdventureEntity ent)
	{
		for(AdventureEntity tent:_ents)
		{
			if(tent.getOccupiedX() == x && tent.getOccupiedY() == y)
			{
				return tent;
			}
		}
		return null;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		for(AdventureEntity ent:_ents)
		{
			ent.update(delta);
		}
		
		Collections.sort(_ents, new EntityYSorter());
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.setFont(ResourceTools.font16());
		
		float xshift = -_pent.getX()*_mapTileSize + GameDriver.NATIVE_SCREEN_WIDTH/2 - _mapTileSize/2;
		float yshift = -_pent.getY()*_mapTileSize + GameDriver.NATIVE_SCREEN_HEIGHT/2 - _mapTileSize/2;

		drawLayer(gfx, xshift, yshift, 0); // Background Layer
		drawLayer(gfx, xshift, yshift, 1); // Entity Layer
		drawEntities(gfx, xshift, yshift); //Draw Entities
		drawLayer(gfx, xshift, yshift, 2); // Foreground Layer
	}
	
	/** Draw entites on screen centered around shifts
	 * @param gfx slick2d graphics object
	 * @param xshift x center corrid
	 * @param yshift y center corrid
	 */
	private void drawEntities(Graphics gfx, float xshift, float yshift)
	{
		int entId = 0;
		for(AdventureEntity ent:_ents)
		{
			gfx.drawImage(ent.getImage(),
					ent.getDrawOffset()[0]+ent.getX()*_mapTileSize+xshift,
					ent.getDrawOffset()[1]+ent.getY()*_mapTileSize+yshift);
			if(Start.DEBUG)
			{
				gfx.drawString("[" + ent.getOccupiedX() + ", " + ent.getOccupiedY() + "] > " + ent.toString().replaceAll(".*\\.", ""), 40, 40 + 40*entId);
			}
			entId++;
		}
	}

	/** Draw tiledmap layer on screen centered around shifts
	 * @param gfx slick2d graphics object
	 * @param xshift x center corrid
	 * @param yshift y center corrid
	 */
	private void drawLayer(Graphics gfx, float xshift, float yshift, int layer)
	{
		gfx.scale(2, 2);
		_map.render((int)+xshift/2, (int)+yshift/2, layer);
		gfx.scale(0.5f, 0.5f);
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
		if(KeyTools.isSELECT(key) && !_pent.isMoving())
		{
			_pent.tryToInteract();
		}
	}

	@Override
	public void keyReleased(int key, char c)
	{
		
	}
	
	public class EntityYSorter implements Comparator<AdventureEntity> {
	    @Override
	    public int compare(AdventureEntity o1, AdventureEntity o2)
	    {
	        return (int) ((o1.getY()+o1.getDrawOffset()[1]) - (o2.getY()+o2.getDrawOffset()[1]));
	    }
	}
}
