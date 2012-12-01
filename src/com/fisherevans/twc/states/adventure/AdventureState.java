package com.fisherevans.twc.states.adventure;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import com.fisherevans.twc.MathTools;
import com.fisherevans.twc.control.KeyCodes;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;

public class AdventureState extends State
{
	private TiledMap _map;
	private int _mapW, _mapH, _mapTileSize;
	private float _pX, _pY;
	
	private boolean[] _moveKeys = { false, false, false, false, false }; // W, A, S, D, moving
	private float[] _moveVector = { 0f, 0f }; // X, Y
	private float _moveSpeed = 0.01f;

	public AdventureState(StateManager sm)
	{
		super(sm);
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
		
		_pX = 50;
		_pY = 50;
	}

	@Override
	public void update(GameContainer gc, int detla) throws SlickException
	{
		if(_moveKeys[4]) // Already moving
		{
			_moveVector[0] += _moveVector[0];
			_moveVector[1] += _moveVector[1];
			
			if(Math.abs(_moveVector[0]) > 1 || Math.abs(_moveVector[1]) > 1)
			{
				_pX += (int)_moveVector[0];
				_pY += (int)_moveVector[1];

				_pY = MathTools.clamp(_pY, 0, _mapH);
				_pX = MathTools.clamp(_pX, 0, _mapW);
				_moveKeys[4] = false;
			}
			
		}
		else if(_moveKeys[0] | _moveKeys[1] | _moveKeys[2] | _moveKeys[3]) // Start movement
		{
			_moveKeys[4] = true;
			_moveVector = genMoveVector(_moveKeys);
		}
		System.out.println("Player: " + _pX + ", " + _pY);
		
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		if(_moveKeys[4])
		{
			_map.render((int)(-_pX-_moveVector[0])*_mapTileSize, (int)(-_pY-_moveVector[1])*_mapTileSize, 0);
		}
		else
		{
			_map.render((int)(-_pX)*_mapTileSize, (int)(-_pY)*_mapTileSize, 0);
		}
		gfx.drawString("ADVENTURE TIME!!!!", 40, 40);
	}
	
	private float[] genMoveVector(boolean[] move)
	{
		float[] vec = { 0, 0 };
		if(move[0]) { vec[1] -= _moveSpeed; }
		if(move[1]) { vec[0] -= _moveSpeed; }
		if(move[2]) { vec[1] += _moveSpeed; }
		if(move[3]) { vec[0] += _moveSpeed; }
		return vec;
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
		if(KeyCodes.isUP(key))
		{
			_moveKeys[0] = true;
		}
		else if(KeyCodes.isDOWN(key))
		{
			_moveKeys[2] = true;
		}
		else if(KeyCodes.isLEFT(key))
		{
			_moveKeys[1] = true;
		}
		else if(KeyCodes.isRIGHT(key))
		{
			_moveKeys[3] = true;
		}
	}

	@Override
	public void keyReleased(int key, char c)
	{
		if(KeyCodes.isUP(key))
		{
			_moveKeys[0] = false;
		}
		else if(KeyCodes.isDOWN(key))
		{
			_moveKeys[2] = false;
		}
		else if(KeyCodes.isLEFT(key))
		{
			_moveKeys[1] = false;
		}
		else if(KeyCodes.isRIGHT(key))
		{
			_moveKeys[3] = false;
		}
	}

}
