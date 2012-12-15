package com.fisherevans.twc.states.adventure.actions;

import com.fisherevans.twc.states.adventure.entities.AdventureEntity;

public class FaceAction extends AdventureAction
{
	private String _entName, _toFaceName;
	private int _angle;
	
	public FaceAction(ActionManager am, String entName, String toFaceName)
	{
		super(am);
		_entName = entName;
		_toFaceName = toFaceName;
	}
	
	public FaceAction(ActionManager am, String entName, int angle)
	{
		super(am);
		_entName = entName;
		_angle = angle;
	}

	@Override
	public void initAction()
	{
		if(_toFaceName == null)
		{
			getAM().getAS().getEM().getEntity(_entName).setAngle(_angle);
		}
		else
		{
			AdventureEntity ent = getAM().getAS().getEM().getEntity(_entName);
			AdventureEntity targ = getAM().getAS().getEM().getEntity(_toFaceName);
			int dx = ent.getOccupiedX() - targ.getOccupiedX();
			int dy = ent.getOccupiedY() - targ.getOccupiedY();
			
			if(Math.abs(dx) > Math.abs(dy))
			{
				if(dx < 0)
					ent.setAngle(0);
				else
					ent.setAngle(180);
			}
			else
			{
				if(dy < 0)
					ent.setAngle(90);
				else
					ent.setAngle(270);
			}
		}
		
		setComplete(true);
	}

	@Override
	public void reInitAction()
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateAction(int delta)
	{
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(int key, char c)
	{
		// TODO Auto-generated method stub
		
	}
}
