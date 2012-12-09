package com.fisherevans.twc.states.adventure.entities;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.Start;
import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.actions.AdventureAction;
import com.fisherevans.twc.states.adventure.actions.SetCameraAction;
import com.fisherevans.twc.states.adventure.actions.DialogueAction;
import com.fisherevans.twc.states.adventure.actions.MoveAction;
import com.fisherevans.twc.states.adventure.actions.SetSpeedAction;
import com.fisherevans.twc.states.adventure.actions.TeleportAction;
import com.fisherevans.twc.states.adventure.actions.WaitAction;
import com.fisherevans.twc.states.adventure.config.AdventureConfigLoader;
import com.fisherevans.twc.states.adventure.config.EntityConfig;
import com.fisherevans.twc.states.adventure.entities.controllers.NullController;
import com.fisherevans.twc.states.adventure.entities.controllers.PlayerController;
import com.fisherevans.twc.states.adventure.entities.controllers.RandomController;
import com.fisherevans.twc.tools.ResourceTools;

public class EntityManager
{
	private AdventureState _as;
	private ArrayList<AdventureEntity> _ents; // The entities currently in the map.
	private HashMap _entsHash;
	private AdventureEntity _cameraEntity; // The player's entity.
	private MovableEntity _playerEntity; // The player's entity.
	private float _baseXShift, _baseYShift;
	
	public EntityManager(AdventureState as)
	{
		_as = as;
	}
	
	public void initManager(AdventureConfigLoader config)
	{
		_ents = new ArrayList<>();
		_entsHash = new HashMap();
		
		for(EntityConfig conf:config.getEntityConfigs())
		{
			addEntity(conf);
		}
		System.out.println("Loaded " + _entsHash.size() + " Entities.");
	}
	
	public void setShifts(int tileSize)
	{
		_baseXShift = GameDriver.NATIVE_SCREEN_H_WIDTH - tileSize;
		_baseYShift = GameDriver.NATIVE_SCREEN_H_HEIGHT - tileSize;
	}
	
	private void addEntity(EntityConfig conf)
	{
		if(conf.isBadConfig())
		{
			System.out.println(conf.getName() + " is a bad config item!");
		}
		else
		{
			switch(conf.getType())
			{
				case MOVABLE:
				{
					MovableEntity tempEnt = new MovableEntity(this);
					tempEnt.setAnimation(ResourceTools.getImage(conf.getSprite()));
					tempEnt.setIcon(ResourceTools.getImage(conf.getIcon()));
					tempEnt.setX(conf.getX());
					tempEnt.setY(conf.getY());
					tempEnt.setAngle(conf.getAngle());
					tempEnt.setSpeedScale(conf.getSpeed());
					tempEnt.setDispName(conf.getDispName());
					tempEnt.setActions(conf.getActions());
					switch(conf.getController())
					{
						case "player":
							tempEnt.setControler(new PlayerController(tempEnt, _as.getInput()));
							break;
						case "random":
							tempEnt.setControler(new RandomController(tempEnt));
							break;
						default:
							tempEnt.setControler(new NullController(tempEnt));
							break;
					}
					_entsHash.put(conf.getName(), tempEnt);
					_ents.add(tempEnt);
				}
			}
			
		}
	}
	
	/** Updates all entities
	 * @param delta time delta between frames
	 */
	public void update(int delta)
	{
		for(AdventureEntity ent:_ents)
		{
			ent.update(delta);
		}
	}
	
	/** Draw entites on screen centered around shifts
	 * @param gfx slick2d graphics object
	 * @param xshift x center corrid
	 * @param yshift y center corrid
	 */
	public void render(Graphics gfx)
	{
		Collections.sort(_ents, new EntityYSorter());

		float xShift = _baseXShift - _cameraEntity.getX()*getAS().getMM().getMapTileSize();
		float yShift = _baseYShift - _cameraEntity.getY()*getAS().getMM().getMapTileSize();
		
		int entId = 0;
		for(AdventureEntity ent:_ents)
		{
			gfx.drawImage(ent.getImage(),
					ent.getDrawOffset()[0]+ent.getX()*_as.getMM().getMapTileSize()+xShift,
					ent.getDrawOffset()[1]+ent.getY()*_as.getMM().getMapTileSize()+yShift);
			if(Start.DEBUG)
			{
				gfx.drawString("[" + ent.getOccupiedX() + ", " + ent.getOccupiedY() + "] > " + ent.toString().replaceAll(".*\\.", ""), 40, 40 + 40*entId);
			}
			entId++;
		}
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
	
	public AdventureEntity getEntity(String name)
	{
		return (AdventureEntity) _entsHash.get(name);
	}

	public AdventureEntity getCameraEntity()
	{
		return _cameraEntity;
	}

	public void setCameraEntity(AdventureEntity cameraEntity)
	{
		_cameraEntity = cameraEntity;
	}

	public void setCameraEntity(String cameraEntity)
	{
		_cameraEntity = (AdventureEntity) _entsHash.get(cameraEntity);
	}

	public MovableEntity getPlayerEntity()
	{
		return _playerEntity;
	}

	public void setPlayerEntity(MovableEntity playerEntity)
	{
		_playerEntity = playerEntity;
	}

	public void setPlayerEntity(String playerEntity)
	{
		_playerEntity = (MovableEntity) _entsHash.get(playerEntity);
	}
	
	public AdventureState getAS()
	{
		return _as;
	}
	
	/** Sorter used to sort entities by their Y value - render's closer one after.
	 * @author Fisher
	 *
	 */
	public class EntityYSorter implements Comparator<AdventureEntity> {
	    @Override
	    public int compare(AdventureEntity o1, AdventureEntity o2)
	    {
	    	int ret = 0;
	    	if(o1.getY()+o1.getDrawOffset()[1] > o2.getY()+o2.getDrawOffset()[1])
	    		ret = 1;
	    	else if(o1.getY()+o1.getDrawOffset()[1] < o2.getY()+o2.getDrawOffset()[1])
	    		ret = -1;
	    	else if(o1.getX()+o1.getDrawOffset()[1] > o2.getX()+o2.getDrawOffset()[1])
	    		ret = 1;
	        return ret;
	    }
	}
}
