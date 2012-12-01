package com.fisherevans.twc.control;

public class KeyCodes
{
	public static final int[] UP = { 17, 200 },
							  LEFT = { 30, 203 },
							  RIGHT = { 32, 205 }, 
							  DOWN = { 31, 208 },
							  SELECT = { 57, 28 },
							  BACK = { 1 };
	
	public static boolean isUP(int key)
	{
		return contains(UP, key);
	}
	
	public static boolean isDOWN(int key)
	{
		return contains(DOWN, key);
	}
	
	public static boolean isLEFT(int key)
	{
		return contains(LEFT, key);
	}
	
	public static boolean isRIGHT(int key)
	{
		return contains(RIGHT, key);
	}
	
	public static boolean isSELECT(int key)
	{
		return contains(SELECT, key);
	}
	
	public static boolean isBACK(int key)
	{
		return contains(BACK, key);
	}
	
	private static boolean contains(int[] keys, int key)
	{
		for(int x = 0;x < keys.length;x++)
		{
			if(keys[x] == key)
			{
				return true;
			}
		}
		return false;
	}
}
