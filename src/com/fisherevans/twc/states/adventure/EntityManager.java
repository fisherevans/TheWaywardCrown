package com.fisherevans.twc.states.adventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.newdawn.slick.Graphics;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.Start;
import com.fisherevans.twc.states.adventure.actions.AdventureAction;
import com.fisherevans.twc.states.adventure.actions.ChangeCameraAction;
import com.fisherevans.twc.states.adventure.actions.DialogueAction;
import com.fisherevans.twc.states.adventure.actions.MoveAction;
import com.fisherevans.twc.states.adventure.actions.SetSpeedAction;
import com.fisherevans.twc.states.adventure.actions.TeleportAction;
import com.fisherevans.twc.states.adventure.actions.WaitAction;
import com.fisherevans.twc.states.adventure.entities.AdventureEntity;
import com.fisherevans.twc.states.adventure.entities.NPCEntity;
import com.fisherevans.twc.states.adventure.entities.PlayerEntity;
import com.fisherevans.twc.tools.ResourceTools;

public class EntityManager
{
	private AdventureState _as;
	private ArrayList<AdventureEntity> _ents; // The entities currently in the map.
	private AdventureEntity _cameraEntity; // The player's entity.
	private PlayerEntity _playerEntity; // The player's entity.
	private float _baseXShift, _baseYShift;
	
	public EntityManager(AdventureState as)
	{
		_as = as;

		_baseXShift = GameDriver.NATIVE_SCREEN_H_WIDTH -  getAS().getMM().getMapTileSize()/2;
		_baseYShift = GameDriver.NATIVE_SCREEN_H_HEIGHT - getAS().getMM().getMapTileSize()/2;
		
		_ents = new ArrayList<AdventureEntity>();
		_playerEntity = new PlayerEntity(4, 4, ResourceTools.getImage("res/sprites/test/char.png"), this, _as.getInput());
		setCameraEntity(_playerEntity);

		_ents.add(_playerEntity);
		
		NPCEntity npc = new NPCEntity(5, 7, ResourceTools.getImage("res/sprites/test/char2.png"), this);
		ArrayList<AdventureAction> actions = new ArrayList<>();
		actions.add(new DialogueAction(_as.getAM(), "Why hello! I'm a Non-Player-Controled testing unit! Hit Space :)", ResourceTools.getImage("res/sprites/test/default64x64.png"), true));
		actions.add(new ChangeCameraAction(_as.getAM(), npc));
		actions.add(new DialogueAction(_as.getAM(), "Just now, the camera changed to me. Pretty neat, right? I will walk " +
				"you through the currently implemented actions! Next, I'll have you sit there for 5 seconds following me.",
				ResourceTools.getImage("res/sprites/test/default64x64.png"), true));
		actions.add(new WaitAction(_as.getAM(), 5000, true));
		actions.add(new DialogueAction(_as.getAM(), "Wasn't that fun?! Well, I'll let you get back to walking around. " +
				"But in 10 seconds, I'll be back to yammer iny our ear!",
				ResourceTools.getImage("res/sprites/test/default64x64.png"), true));
		actions.add(new ChangeCameraAction(_as.getAM(), _playerEntity));
		actions.add(new WaitAction(_as.getAM(), 10000, false));
		actions.add(new ChangeCameraAction(_as.getAM(), npc));
		actions.add(new DialogueAction(_as.getAM(), "And now the camera is back to me! You'll also notice that my ugly " +
				"face icon is on the right now. It was on the left before... Now, DANCE MONKEY!",
				ResourceTools.getImage("res/sprites/test/default64x64.png"), false));
		actions.add(new SetSpeedAction(_as.getAM(), true, _playerEntity, 0.333f));
		actions.add(new MoveAction(_as.getAM(), true, _playerEntity, new int[] { 1, 0 }));
		actions.add(new MoveAction(_as.getAM(), true, _playerEntity, new int[] { 0, -1 }));
		actions.add(new MoveAction(_as.getAM(), true, _playerEntity, new int[] { -1, 0 }));
		actions.add(new MoveAction(_as.getAM(), true, _playerEntity, new int[] { 0, 1 }));
		actions.add(new MoveAction(_as.getAM(), true, _playerEntity, new int[] { -1, 0 }));
		actions.add(new MoveAction(_as.getAM(), true, _playerEntity, new int[] { 1, 0 }));
		actions.add(new MoveAction(_as.getAM(), true, _playerEntity, new int[] { 0, -1 }));
		actions.add(new MoveAction(_as.getAM(), true, _playerEntity, new int[] { 0, 1 }));
		actions.add(new SetSpeedAction(_as.getAM(), true, _playerEntity, 1));
		actions.add(new DialogueAction(_as.getAM(), "And now the face is back on the left! Now I'll teleport you to the " +
				"courtyard and let you be on your way.",
				ResourceTools.getImage("res/sprites/test/default64x64.png"), true));
		actions.add(new ChangeCameraAction(_as.getAM(), _playerEntity));
		actions.add(new TeleportAction(_as.getAM(), true, _playerEntity, new int[] { 41, 15 } ));
		npc.setActions(actions);
		
		_ents.add(npc);
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

	public AdventureEntity getCameraEntity()
	{
		return _cameraEntity;
	}

	public void setCameraEntity(AdventureEntity cameraEntity)
	{
		_cameraEntity = cameraEntity;
	}

	public PlayerEntity getPlayerEntity()
	{
		return _playerEntity;
	}

	public void setPlayerEntity(PlayerEntity playerEntity)
	{
		_playerEntity = playerEntity;
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
	        return (int) ((o1.getY()+o1.getDrawOffset()[1]) - (o2.getY()+o2.getDrawOffset()[1]));
	    }
	}
}
