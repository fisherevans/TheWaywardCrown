package com.fisherevans.twc.states;

public class TimeManager
{
	private long _time;
	private final int DAY_LENGTH = 24*1000;
	private long _lastTime;
	private StateManager _sm;
	
	public TimeManager(StateManager sm)
	{
		_sm = sm;
		_lastTime = System.currentTimeMillis();
		_time = (long) (DAY_LENGTH*0.8f);
	}
	
	public void update(int delta)
	{
		long thisTime = System.currentTimeMillis();
		_time += thisTime - _lastTime;
		_lastTime = thisTime;
		_time %= DAY_LENGTH;
		//System.out.println(_time);
	}
	
	public int getTime()
	{
		return (int) _time;
	}
	
	public float getTimeFloat()
	{
		return _time/((float)DAY_LENGTH);
	}
}
