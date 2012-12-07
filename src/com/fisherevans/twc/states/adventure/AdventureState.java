package com.fisherevans.twc.states.adventure;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.Start;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;
import com.fisherevans.twc.states.adventure.actions.AdventureAction;
import com.fisherevans.twc.states.adventure.actions.ChangeCameraAction;
import com.fisherevans.twc.states.adventure.actions.DialogueAction;
import com.fisherevans.twc.states.adventure.entities.*;
import com.fisherevans.twc.tools.*;

public class AdventureState extends State
{
	private MapManager _mm;
	private EntityManager _em;
	private ActionManager _am;
	private DialogueManager _dm;

	/** Create the adventure state
	 * @param sm The state manager using this state
	 * @param input The slick2d input object affecting this state
	 */
	public AdventureState(StateManager sm, Input input)
	{
		super(sm, input);
		
		_mm = new MapManager(this);
		_am = new ActionManager(this);
		_em = new EntityManager(this);
		_dm = new DialogueManager(this);
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		_em.update(delta);
		_am.update(delta);
		_dm.update(delta);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		gfx.setFont(ResourceTools.font16());
		
		_mm.updateShift(_em.getCameraEntity());

		_mm.drawLayer(gfx, "bg"); // Background Layer
		_mm.drawLayer(gfx, "entbg"); // Entity Background Layer
		_em.render(gfx); //Draw Entities
		_mm.drawLayer(gfx, "entfg"); // Entity Foreground Layer
		_mm.drawLayer(gfx, "fg"); // Foreground Layer
		
		_am.render(gfx);
		_dm.render(gfx);
	}

	@Override
	public void keyPressed(int key, char c)
	{
		if(_dm.keyPressed(key, c)) { return; }
		if(_am.keyPressed(key, c)) { return; }
		if(_em.getPlayerEntity().getControler().keyPressed(key, c)) { return; }
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
	
	public DialogueManager getDM()
	{
		return _dm;
	}
}
