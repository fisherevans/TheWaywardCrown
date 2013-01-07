package com.fisherevans.twc.states.adventure.config;

import java.io.BufferedReader;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import org.newdawn.slick.Color;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.actions.*;
import com.fisherevans.twc.states.adventure.config.EntityConfig.ENTTYPE;
import com.fisherevans.twc.states.adventure.lights.TorchLight;
import com.fisherevans.twc.states.adventure.triggers.AdventureTrigger;
import com.fisherevans.twc.tools.PerlParser;

public class AdventureConfigLoader
{
	private enum LOADPART { MAP, LIGHTS, ENTITIES, ACTIONS, TRIGGERS };
	
	private AdventureState _as;
	
	private File _file;
	private BufferedReader _ldrInput;
	
	private LOADPART _curLoadPart;
	
	private MapConfig _mapConfig;
	
	private LightConfig _lightConfig;
	private int _lightIndex;
	
	private ArrayList<EntityConfig> _entityConfigs;
	private int _entityIndex;
	
	private ArrayList<AdventureTrigger> _triggerConfigs;
	private int _triggerIndex;
	
	private HashMap _queueHash;
	private String _queueKey;
	
	public AdventureConfigLoader(AdventureState as, String location)
	{
		_as = as;
		
		_mapConfig = new MapConfig();
		_lightConfig = new LightConfig();
		_entityConfigs = new ArrayList<>();
		_entityIndex = -1;
		_triggerConfigs = new ArrayList<>();
		_triggerIndex = -1;
		
		_queueHash = new HashMap();
		_queueKey = "default";
		
		_file = new File(location);
		_curLoadPart = LOADPART.MAP;
		try
		{
			_ldrInput = PerlParser.parseLDR(location);
			
			String buffer = "";
			while((buffer = _ldrInput.readLine()) != null)
			{
				buffer = buffer.replaceAll("#.*", "").replaceAll("\\s+", " ").replaceAll("^\\s+", ""); // Get rid of comments and extra spaces and leading spaces.
				if(!buffer.matches("^ *$"))
				{
					//System.out.println(buffer);
					if(checkLoadPartSwitch(buffer));
					else
					{
						String[] line = buffer.split(" ");
						switch(_curLoadPart)
						{
							case MAP:
								processMapLine(line);
								break;
							case LIGHTS:
								processLightsLine(line);
								break;
							case ENTITIES:
								processEntitiesLine(line);
								break;
							case ACTIONS:
								processActionsLine(line);
								break;
							case TRIGGERS:
								processTriggersLine(line);
								break;
						}
					}
				}
			}
		}
		catch(Exception e)
		{
			System.out.println("There was an error loading the map config: " + location);
			e.printStackTrace();
		}
	}
	
	private boolean checkLoadPartSwitch(String line)
	{
		boolean matches = false;
		if(line.startsWith(":"))
		{
			matches = true;
			line = line.replaceAll(":", "");
			switch(line)
			{
				case "MAP":
					_curLoadPart = LOADPART.MAP;
					break;
				case "LIGHTS":
					_curLoadPart = LOADPART.LIGHTS;
					break;
				case "ENTITIES":
					_curLoadPart = LOADPART.ENTITIES;
					break;
				case "ACTIONS":
					_curLoadPart = LOADPART.ACTIONS;
					break;
				case "TRIGGERS":
					_curLoadPart = LOADPART.TRIGGERS;
					break;
			}
		}
		return matches;
	}
	
	private void processMapLine(String[] line)
	{
		switch(line[0])
		{
			case "TMX":
				_mapConfig.setTmx(line[1]);
				break;
			case "TILES":
				_mapConfig.setTiles(line[1]);
				break;
			case "CAMERA":
				_mapConfig.setCamera(line[1]);
				break;
			case "PLAYER":
				_mapConfig.setPlayer(line[1]);
				break;
			case "ALL":
				_mapConfig.setTmx(line[1]);
				_mapConfig.setTiles(line[2]);
				_mapConfig.setCamera(line[3]);
				_mapConfig.setPlayer(line[4]);
		}
	}
	
	private void processLightsLine(String[] line)
	{
		switch(line[0])
		{
			case "DAWN":
				_lightConfig.setTimeLight(0, new Color(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3])));
				break;
			case "DAY":
				_lightConfig.setTimeLight(1, new Color(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3])));
				break;
			case "DUSK":
				_lightConfig.setTimeLight(2, new Color(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3])));
				break;
			case "NIGHT":
				_lightConfig.setTimeLight(3, new Color(Integer.parseInt(line[1]), Integer.parseInt(line[2]), Integer.parseInt(line[3])));
				break;
			case "NEW":
				switch(line[1])
				{
					case "TORCH":
						_lightIndex = _lightConfig.addLight(new TorchLight(line[2]));
						//System.out.println("One Liner?");
						if(line.length > 3)
						{
							//System.out.println("Yes, One Liner");
							_lightConfig.getLight(_lightIndex).setX(Integer.parseInt(line[3]));
							_lightConfig.getLight(_lightIndex).setY(Integer.parseInt(line[4]));
							_lightConfig.getLight(_lightIndex).setSize(Integer.parseInt(line[5]));
						}
						break;
				}
				break;
			case "X":
				_lightConfig.getLight(_lightIndex).setX(Integer.parseInt(line[1]));
				break;
			case "Y":
				_lightConfig.getLight(_lightIndex).setY(Integer.parseInt(line[1]));
				break;
			case "SIZE":
				_lightConfig.getLight(_lightIndex).setSize(Integer.parseInt(line[1]));
				break;
		}
	}
	
	private void processEntitiesLine(String[] line)
	{
		switch(line[0])
		{
			case "NEW":
				_entityConfigs.add(new EntityConfig(line[1]));
				_entityIndex++;
				break;
			case "SPRITE":
				_entityConfigs.get(_entityIndex).setSprite(line[1]);
				break;
			case "ICON":
				_entityConfigs.get(_entityIndex).setIcon(line[1]);
				break;
			case "X":
				_entityConfigs.get(_entityIndex).setX(Float.parseFloat(line[1]));
				break;
			case "Y":
				_entityConfigs.get(_entityIndex).setY(Float.parseFloat(line[1]));
				break;
			case "ACTIONS":
				_entityConfigs.get(_entityIndex).setActions(line[1]);
				break;
			case "CONTROLER":
				_entityConfigs.get(_entityIndex).setController(line[1]);
				break;
			case "NAME":
				_entityConfigs.get(_entityIndex).setDispName(getStringFrom(line, 1));
				break;
			case "SPEED":
				_entityConfigs.get(_entityIndex).setSpeed(Float.parseFloat(line[1]));
				break;
			case "ANIMDUR":
				_entityConfigs.get(_entityIndex).setAnimDur(Integer.parseInt(line[1]));
				break;
			case "FACE":
				setEntityFace(line[1]);
				break;
			case "TYPE":
				setEntityType(line[1]);
				break;
			case "IMAGES":
				_entityConfigs.get(_entityIndex).setSprite(line[1]);
				_entityConfigs.get(_entityIndex).setIcon(line[2]);
				break;
			case "POSITION":
				_entityConfigs.get(_entityIndex).setX(Float.parseFloat(line[1]));
				_entityConfigs.get(_entityIndex).setY(Float.parseFloat(line[2]));
				setEntityFace(line[3]);
				break;
			case "CHARACTER":
				setEntityType(line[1]);
				_entityConfigs.get(_entityIndex).setActions(line[2]);
				_entityConfigs.get(_entityIndex).setDispName(getStringFrom(line, 3));
				break;
			case "MOVEMENT":
				_entityConfigs.get(_entityIndex).setController(line[1]);
				_entityConfigs.get(_entityIndex).setSpeed(Float.parseFloat(line[2]));
				break;
		}
	}
	
	private void processActionsLine(String[] line)
	{
		switch(line[0])
		{
			case "NEW":
				_queueKey = line[1];
				ActionQueue queue = new ActionQueue(_as.getAM());
				_queueHash.put(line[1], queue);
				break;
			case "HALT":
				addAction(new HaltAction(_as.getAM(), line[1], true));
				break;
			case "UNHALT":
				addAction(new HaltAction(_as.getAM(), line[1], false));
				break;
			case "WAIT":
				addAction(new WaitAction(_as.getAM(), Integer.parseInt(line[1])));
				break;
			case "SETCAMERA":
				addAction(new SetCameraAction(_as.getAM(), line[1]));
				break;
			case "SETSPEED":
				addAction(new SetSpeedAction(_as.getAM(), line[1], Float.parseFloat(line[2])));
				break;
			case "MOVE":
				int angle = 0;
				switch(line[2])
				{
					case "N": angle = 90; break;
					case "E": angle = 0; break;
					case "S": angle = 270; break;
					case "W": angle = 180; break;
				}
				addAction(new MoveAction(_as.getAM(), line[1], angle, Integer.parseInt(line[3])));
				break;
			case "TELEPORT":
				addAction(new TeleportAction(_as.getAM(), line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3])));
				break;
			case "WAITTOSTOP":
				addAction(new WaitToStopAction(_as.getAM(), line[1]));
				break;
			case "SETADVENTURE":
				addAction(new SetAdventureStateAction(_as.getAM(), line[1]));
				addAction(new FadeInAction(_as.getAM()));
				break;
			case "DIALOGUE":
				addAction(new DialogueAction(_as.getAM(), getStringFrom(line, 3), line[2], line[1].equals("left")));
				break;
			case "NOTIFICATION":
				addAction(new NotificationAction(_as.getAM(), getStringFrom(line, 1)));
				break;
			case "FADEOUT":
				addAction(new FadeOutAction(_as.getAM()));
				break;
			case "FADEIN":
				addAction(new FadeInAction(_as.getAM()));
				break;
			case "FACE":
				switch(line[2])
				{
					case "N": addAction(new FaceAction(_as.getAM(), line[1], 270)); break;
					case "S": addAction(new FaceAction(_as.getAM(), line[1], 90)); break;
					case "E": addAction(new FaceAction(_as.getAM(), line[1], 0)); break;
					case "W": addAction(new FaceAction(_as.getAM(), line[1], 180)); break;
					default: addAction(new FaceAction(_as.getAM(), line[1], line[2])); break; 
				}
				break;
			case "INSERT":
				addAction(new InsertAction(_as.getAM(), line[1]));
				break;
		};
	}
	
	private void addAction(AdventureAction action)
	{
		((ActionQueue)_queueHash.get(_queueKey)).addAction(action);
	}
	
	private void processTriggersLine(String[] line)
	{
		switch(line[0])
		{
			case "NEW":
				_triggerConfigs.add(new AdventureTrigger(_as.getTM()));
				_triggerIndex++;
				break;
			case "X":
				_triggerConfigs.get(_triggerIndex).setX1(Integer.parseInt(line[1]));
				_triggerConfigs.get(_triggerIndex).setX2(Integer.parseInt(line[1]));
				break;
			case "Y":
				_triggerConfigs.get(_triggerIndex).setY1(Integer.parseInt(line[1]));
				_triggerConfigs.get(_triggerIndex).setY2(Integer.parseInt(line[1]));
				break;
			case "X1":
				_triggerConfigs.get(_triggerIndex).setX1(Integer.parseInt(line[1]));
				break;
			case "X2":
				_triggerConfigs.get(_triggerIndex).setX2(Integer.parseInt(line[1]));
				break;
			case "Y1":
				_triggerConfigs.get(_triggerIndex).setY1(Integer.parseInt(line[1]));
				break;
			case "Y2":
				_triggerConfigs.get(_triggerIndex).setY2(Integer.parseInt(line[1]));
				break;
			case "ACTIONS":
				_triggerConfigs.get(_triggerIndex).setActions(line[1]);
				break;
			case "TIMES":
				_triggerConfigs.get(_triggerIndex).setTimes(Integer.parseInt(line[1]));
				break;
			case "EFFECTS":
				_triggerConfigs.get(_triggerIndex).setEffects(getStringFrom(line, 1).split(" "));
				break;
		};
	}
	
	private String getString(String[] line, int start, int end)
	{
		String buffer = "";
		for(int x = start;x <= end;x++)
		{
			buffer += line[x] + " ";
		}
		buffer = buffer.replaceAll(" +$", "");
		
		return buffer;
	}
	
	private String getStringFrom(String[] line, int start)
	{
		return getString(line, start, line.length-1);
	}

	public ArrayList<EntityConfig> getEntityConfigs()
	{
		return _entityConfigs;
	}

	public ArrayList<AdventureTrigger> getTriggers()
	{
		return _triggerConfigs;
	}

	public MapConfig getMapConfig()
	{
		return _mapConfig;
	}
	
	public HashMap getQueueHash()
	{
		return _queueHash;
	}
	
	public LightConfig getLightConfig()
	{
		return _lightConfig;
	}
	
	private void setEntityFace(String line)
	{
		switch(line)
		{
			case "N":
				_entityConfigs.get(_entityIndex).setAngle(270);
				break;
			case "S":
				_entityConfigs.get(_entityIndex).setAngle(90);
				break;
			case "W":
				_entityConfigs.get(_entityIndex).setAngle(180);
				break;
			case "E":
				_entityConfigs.get(_entityIndex).setAngle(0);
				break;
			default:
				_entityConfigs.get(_entityIndex).setAngle(Integer.parseInt(line));
		}
	}
	
	private void setEntityType(String line)
	{
		switch(line)
		{
			case "movable":
				_entityConfigs.get(_entityIndex).setType(ENTTYPE.MOVABLE);
				break;
			case "animated":
				_entityConfigs.get(_entityIndex).setType(ENTTYPE.ANIMATED);
				break;
		}
	}
}
