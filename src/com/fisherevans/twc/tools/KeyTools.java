package com.fisherevans.twc.tools;

import org.newdawn.slick.Input;

public class KeyTools
{
	public static final int[] UP = { 17, 200 },
							  LEFT = { 30, 203 },
							  RIGHT = { 32, 205 }, 
							  DOWN = { 31, 208 },
							  SELECT = { 57, 28 },
							  BACK = { 1 };
	
	public static float[] getMoveVector(Input in)
	{
		float[] vec = new float[] { 0, 0 };
		if(KeyTools.isLEFTDown(in))       { vec[0] -= 1; }
		else if(KeyTools.isRIGHTDown(in)) { vec[0] += 1; }
		else if(KeyTools.isUPDown(in))    { vec[1] -= 1; }
		else if(KeyTools.isDOWNDown(in))  { vec[1] += 1; }
		return vec;
	}
	
	public static boolean isUPDown(Input in)
	{
		return anyKeyDown(UP, in);
	}
	
	public static boolean isDOWNDown(Input in)
	{
		return anyKeyDown(DOWN, in);
	}
	
	public static boolean isLEFTDown(Input in)
	{
		return anyKeyDown(LEFT, in);
	}
	
	public static boolean isRIGHTDown(Input in)
	{
		return anyKeyDown(RIGHT, in);
	}
	
	public static boolean isMOVEDown(Input in)
	{
		return isLEFTDown(in) || isRIGHTDown(in) || isUPDown(in) || isDOWNDown(in);
	}
	
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
	
	private static boolean anyKeyDown(int[] keys, Input in)
	{
		for(int x = 0;x < keys.length;x++)
		{
			if(in.isKeyDown(keys[x]))
			{
				return true;
			}
		}
		return false;
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
