package com.fisherevans.twc.states.adventure.config;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

import com.fisherevans.twc.states.adventure.AdventureState;
import com.fisherevans.twc.states.adventure.actions.*;
import com.fisherevans.twc.states.adventure.config.EntityConfig.ENTTYPE;

public class AdventureConfigLoader
{
	private enum LOADPART { MAP, ENTITIES, ACTIONS };
	
	private AdventureState _as;
	
	private File _file;
	private Scanner _reader;
	
	private LOADPART _curLoadPart;
	
	private MapConfig _mapConfig;
	
	private ArrayList<EntityConfig> _entityConfigs;
	private int _entityIndex;
	
	private HashMap _queueHash;
	private String _queueKey;
	
	public AdventureConfigLoader(AdventureState as, String location)
	{
		_as = as;
		
		_mapConfig = new MapConfig();
		_entityConfigs = new ArrayList<>();
		_entityIndex = -1;
		
		_queueHash = new HashMap();
		_queueKey = "default";
		
		_file = new File(location);
		_curLoadPart = LOADPART.MAP;
		try
		{
			_reader = new Scanner(_file);
			
			String buffer = "";
			while(_reader.hasNextLine())
			{
				buffer = _reader.nextLine();
				buffer = buffer.replaceAll("#.*", "").replaceAll("\\s+", " ").replaceAll("^\\s+", ""); // Get rid of comments and extra spaces and leading spaces.
				if(!buffer.matches("^ *$"))
				{
					if(checkLoadPartSwitch(buffer));
					else
					{
						String[] line = buffer.split(" ");
						switch(_curLoadPart)
						{
							case MAP:
								processMapLine(line);
								break;
							case ENTITIES:
								processEntitiesLine(line);
								break;
							case ACTIONS:
								processActionsLine(line);
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
				case "ENTITIES":
					_curLoadPart = LOADPART.ENTITIES;
					break;
				case "ACTIONS":
					_curLoadPart = LOADPART.ACTIONS;
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
			case "FACE":
				switch(line[1])
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
						_entityConfigs.get(_entityIndex).setAngle(Integer.parseInt(line[1]));
				}
				break;
			case "TYPE":
				switch(line[1])
				{
					case "movable":
						_entityConfigs.get(_entityIndex).setType(ENTTYPE.MOVABLE);
						break;
				}
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
				((ActionQueue)_queueHash.get(_queueKey)).addAction(new HaltAction(_as.getAM(), line[1], true));
				break;
			case "UNHALT":
				((ActionQueue)_queueHash.get(_queueKey)).addAction(new HaltAction(_as.getAM(), line[1], false));
				break;
			case "WAIT":
				((ActionQueue)_queueHash.get(_queueKey)).addAction(new WaitAction(_as.getAM(), Integer.parseInt(line[1])));
				break;
			case "SETCAMERA":
				((ActionQueue)_queueHash.get(_queueKey)).addAction(new SetCameraAction(_as.getAM(), line[1]));
				break;
			case "SETSPEED":
				((ActionQueue)_queueHash.get(_queueKey)).addAction(new SetSpeedAction(_as.getAM(), line[1], Float.parseFloat(line[2])));
				break;
			case "MOVE":
				((ActionQueue)_queueHash.get(_queueKey)).addAction(new SetCameraAction(_as.getAM(), line[1]));
				break;
			case "TELEPORT":
				((ActionQueue)_queueHash.get(_queueKey)).addAction(new TeleportAction(_as.getAM(), line[1], Integer.parseInt(line[2]), Integer.parseInt(line[3])));
				break;
			case "WAITTOSTOP":
				((ActionQueue)_queueHash.get(_queueKey)).addAction(new WaitToStopAction(_as.getAM(), line[1]));
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

	public MapConfig getMapConfig()
	{
		return _mapConfig;
	}
	
	public HashMap getQueueHash()
	{
		return _queueHash;
	}
}
