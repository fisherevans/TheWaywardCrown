package com.fisherevans.twc.states.adventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.lwjgl.opengl.GL11;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.Start;
import com.fisherevans.twc.states.FadeManager;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;
import com.fisherevans.twc.states.adventure.actions.ActionManager;
import com.fisherevans.twc.states.adventure.actions.AdventureAction;
import com.fisherevans.twc.states.adventure.actions.SetCameraAction;
import com.fisherevans.twc.states.adventure.actions.DialogueAction;
import com.fisherevans.twc.states.adventure.config.AdventureConfigLoader;
import com.fisherevans.twc.states.adventure.entities.*;
import com.fisherevans.twc.states.adventure.lights.LightManager;
import com.fisherevans.twc.states.adventure.triggers.TriggerManager;
import com.fisherevans.twc.states.pausemenu.PauseMenuState;
import com.fisherevans.twc.tools.*;

public class AdventureState extends State
{
	private String _ldr;
	
	private AdventureConfigLoader _config;
	private MapManager _mm;
	private EntityManager _em;
	private ActionManager _am;
	private TriggerManager _tm;
	private DialogueManager _dm;
	private LightManager _lm;

	/** Create the adventure state
	 * @param sm The state manager using this state
	 * @param input The slick2d input object affecting this state
	 */
	public AdventureState(StateManager sm, Input input, String ldr)
	{
		super(sm, input);
		DBHandler.setSaveName("res/saves/save001.sqlite");
		DBHandler.openDB();
		_ldr = ldr;
	}

	@Override
	public void load()
	{
		_em = new EntityManager(this);
		_mm = new MapManager(this);
		_am = new ActionManager(this);
		_tm = new TriggerManager(this);
		_dm = new DialogueManager(this);
		_lm = new LightManager(this);
		
		_config = new AdventureConfigLoader(this, _ldr);

		_em.initManager(_config);
		_mm.initManager(_config);
		_am.initManager(_config);
		_tm.initManager(_config);
		_lm.initManager(_config);
		
		//System.exit(0);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		_am.update(delta);
		_tm.update(delta);
		_dm.update(delta);
		_lm.update(gc, delta);
		_em.update(delta);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.setFont(ResourceTools.fontMC16());
		
		_mm.updateShift(_em.getCameraEntity());

		_mm.drawLayer(gfx, "bg"); // Background Layer
		_mm.drawLayer(gfx, "entbg"); // Entity Background Layer
		_em.render(gfx); //Draw Entities
		_mm.drawLayer(gfx, "entfg"); // Entity Foreground Layer
		_mm.drawLayer(gfx, "fg"); // Foreground Layer
		
		_lm.render(gfx);

		_dm.render(gfx);
	}

	@Override
	public void keyPressed(int key, char c)
	{
		_dm.keyPressed(key, c);
		//if(_am.keyPressed(key, c)) { return; }
		_em.getPlayerEntity().getControler().keyPressed(key, c);
		
		if(KeyTools.isBACK(key))
		{
			getSM().setStateQuick(new PauseMenuState(getSM(), getInput(), this));
		}
	}

	@Override
	public void keyReleased(int key, char c)
	{
		
	}

	public MapManager getMM()
	{
		return _mm;
	}
	
	public ActionManager getAM()
	{
		return _am;
	}
	
	public EntityManager getEM()
	{
		return _em;
	}
	
	public TriggerManager getTM()
	{
		return _tm;
	}
	
	public DialogueManager getDM()
	{
		return _dm;
	}
	
	public float getScreenX(float x)
	{
		return (x-_em.getCameraEntity().getX())*_mm.getMapTileSize() + GameDriver.NATIVE_SCREEN_H_WIDTH;
	}
	
	public float getScreenY(float y)
	{
		return (y-_em.getCameraEntity().getY())*_mm.getMapTileSize() + GameDriver.NATIVE_SCREEN_H_HEIGHT;
	}
}
