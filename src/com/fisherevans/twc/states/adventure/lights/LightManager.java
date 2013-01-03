package com.fisherevans.twc.states.adventure.lights;

import java.util.ArrayList;
import java.util.HashMap;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL14;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.svg.Gradient;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.config.AdventureConfigLoader;
import com.fisherevans.twc.tools.ResourceTools;

import external.chronocide.shaders.Shader;

public class LightManager
{
	
	private AdventureState _as;
	private Color[] _timeColors = new Color[4];
	
	private Color _curTimeColor;
	private Image _lightMap;
	private Image _lightImage;
	private Image _whitePixel;
	private Gradient _timeGradient;
	
	private Shader _ltShdr;
	
	public static final int LIGHT_SIZE = 96*2;
	
	private ArrayList<AdventureLight> _lights;
	private HashMap _lightsHash;
	
	public LightManager(AdventureState as)
	{
		_as = as;
		
		try { _lightMap = new Image(GameDriver.NATIVE_SCREEN_WIDTH, GameDriver.NATIVE_SCREEN_HEIGHT); }
		catch (SlickException e) { e.printStackTrace(); }
		
		_lightImage = ResourceTools.getImage("res/sprites/lighting/spot_light.png");
		_whitePixel = ResourceTools.getImage("res/sprites/lighting/base_lighting.png");
		_timeGradient = new Gradient("Time", false);
		
		_lights = new ArrayList<>();
		_lightsHash = new HashMap();
	}
	
	public void initManager(AdventureConfigLoader config)
	{
		_timeColors = config.getLightConfig().getTimeLights();

		_timeGradient.addStep(0, _timeColors[3]);
		_timeGradient.addStep(0.19f, _timeColors[3]);
		_timeGradient.addStep(0.26f, _timeColors[0]);
		_timeGradient.addStep(0.41f, _timeColors[1]);
		_timeGradient.addStep(0.65f, _timeColors[1]);
		_timeGradient.addStep(0.79f, _timeColors[2]);
		_timeGradient.addStep(0.92f, _timeColors[3]);
		_timeGradient.addStep(1f, _timeColors[3]);

		_curTimeColor = _timeGradient.getColorAt(_as.getSM().getTimeM().getTimeFloat());
		
		for(AdventureLight light:config.getLightConfig().getLights())
		{
			_lights.add(light);
			_lightsHash.put(light.getName(), light);
		}
		System.out.println("Loaded " + _lights.size() + " Lights.");
		
		try
		{
			_ltShdr = Shader.makeShader("res/shaders/highLight.vert", "res/shaders/highLight.frag");
		}
		catch (SlickException e)
		{
			System.out.println("Could not load light shadrs.");
			e.printStackTrace();
		}
		
	}
	
	public void update(GameContainer gc, int delta)
	{
		_curTimeColor = _timeGradient.getColorAt(_as.getSM().getTimeM().getTimeFloat());
		
		for(AdventureLight light:_lights)
		{
			light.update(delta);
		}
		/*
		if(Start.width != _lightMap.getWidth() || Start.height != _lightMap.getHeight())
		{
			try
			{
				_lightMap = new Image(Start.width, Start.height);
			}
			catch (SlickException e)
			{
				e.printStackTrace();
			}
		}*/
	}
	
	public void render(Graphics gfx)
	{
		try
		{
			Graphics lg = _lightMap.getGraphics();
			lg.drawImage(_whitePixel, 0, 0, _lightMap.getWidth(), _lightMap.getHeight(), 0, 0, 1, 1, _curTimeColor);
			//_ltShdr.startShader();
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
			for(AdventureLight light:_lights)
			{
				int offset = (int) ((light.getSize()/32f)*16);
				lg.drawImage(_lightImage.getScaledCopy(light.getScale()), _as.getScreenX(light.getX())-offset, _as.getScreenY(light.getY())-offset, light.getColor());
			}
			//Shader.forceFixedShader();
			//lg.drawImage(_spotLight.getScaledCopy(4), GameDriver.NATIVE_SCREEN_H_WIDTH-256, GameDriver.NATIVE_SCREEN_H_HEIGHT-256 - 32);
			lg.setDrawMode(Graphics.MODE_NORMAL);
			lg.flush();
		}
		catch (SlickException e) { e.printStackTrace(); }

		GL11.glBlendFunc(GL11.GL_DST_COLOR, GL11.GL_ZERO);
		_lightMap.draw(0, 0);
		gfx.setDrawMode(Graphics.MODE_NORMAL);
		
		gfx.setFont(ResourceTools.fontMC16());
		gfx.drawString(String.format("Hour: %d", (int)(_as.getSM().getTimeM().getTimeFloat()*24)), 100, 10);
	}
	
	public void setLight(int index, Color color)
	{
		_timeColors[index] = color;
	}
	
	public Color getLight(int index)
	{
		return _timeColors[index];
	}
}
