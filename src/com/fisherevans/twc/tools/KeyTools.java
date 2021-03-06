package com.fisherevans.twc.tools;

import org.newdawn.slick.Input;

/**
 * @author Fisher
 *
 */
public class KeyTools
{
	/** Arrays of key codes which correspond with pressing up */
	public static final int[] UP = { 17, 200 };
	/** Arrays of key codes which correspond with pressing left */
	public static final int[] LEFT = { 30, 203 };
	/** Arrays of key codes which correspond with pressing right */
	public static final int[] RIGHT = { 32, 205 }; 
	/** Arrays of key codes which correspond with pressing down */
	public static final int[] DOWN = { 31, 208 };
	/** Arrays of key codes which correspond with pressing select */
	public static final int[] SELECT = { 57, 28 };
	/** Arrays of key codes which correspond with pressing back */
	public static final int[] BACK = { 1 };
	
	/** Creates a movement vector (perp to an axis) based on key downs
	 * @param in slick2d player input object
	 * @return the vectore based on movement keys
	 */
	public static float[] getMoveVector(Input in)
	{
		float[] vec = new float[] { 0, 0 };
		if(KeyTools.isLEFTDown(in))       { vec[0] -= 1; }
		else if(KeyTools.isRIGHTDown(in)) { vec[0] += 1; }
		else if(KeyTools.isUPDown(in))    { vec[1] -= 1; }
		else if(KeyTools.isDOWNDown(in))  { vec[1] += 1; }
		return vec;
	}
	
	public static int getMoveAngle(Input in)
	{
		int angle = -1;
		if(KeyTools.isLEFTDown(in))       { angle = 180; }
		else if(KeyTools.isRIGHTDown(in)) { angle = 0; }
		else if(KeyTools.isUPDown(in))    { angle = 270; }
		else if(KeyTools.isDOWNDown(in))  { angle = 90; }
		return angle;
	}
	
	/**
	 * @param in slick2d player input object
	 * @return true if an up key is pressed down
	 */
	public static boolean isUPDown(Input in)
	{
		return anyKeyDown(UP, in);
	}

	/**
	 * @param in slick2d player input object
	 * @return true if an down key is pressed down
	 */
	public static boolean isDOWNDown(Input in)
	{
		return anyKeyDown(DOWN, in);
	}

	/**
	 * @param in slick2d player input object
	 * @return true if an left key is pressed down
	 */
	public static boolean isLEFTDown(Input in)
	{
		return anyKeyDown(LEFT, in);
	}

	/**
	 * @param in slick2d player input object
	 * @return true if an right key is pressed down
	 */
	public static boolean isRIGHTDown(Input in)
	{
		return anyKeyDown(RIGHT, in);
	}

	/**
	 * @param in slick2d player input object
	 * @return true if any movement key is pressed down
	 */
	public static boolean isMOVEDown(Input in)
	{
		return isLEFTDown(in) || isRIGHTDown(in) || isUPDown(in) || isDOWNDown(in);
	}
	
	/** tests to see if key matches list of up keys
	 * @param key key to test
	 * @return true if it does match
	 */
	public static boolean isUP(int key)
	{
		return contains(UP, key);
	}

	/** tests to see if key matches list of down keys
	 * @param key key to test
	 * @return true if it does match
	 */
	public static boolean isDOWN(int key)
	{
		return contains(DOWN, key);
	}

	/** tests to see if key matches list of left keys
	 * @param key key to test
	 * @return true if it does match
	 */
	public static boolean isLEFT(int key)
	{
		return contains(LEFT, key);
	}

	/** tests to see if key matches list of right keys
	 * @param key key to test
	 * @return true if it does match
	 */
	public static boolean isRIGHT(int key)
	{
		return contains(RIGHT, key);
	}
	/** tests to see if key matches list of select keys
	 * @param key key to test
	 * @return true if it does match
	 */
	
	public static boolean isSELECT(int key)
	{
		return contains(SELECT, key);
	}

	/** tests to see if key matches list of back keys
	 * @param key key to test
	 * @return true if it does match
	 */
	public static boolean isBACK(int key)
	{
		return contains(BACK, key);
	}

	/** checks to see if any key code is current pressed in array
	 * @param arrays of keys to test
	 * @param in slick2d playe rinput object
	 * @return true if one or more key is pressed
	 */
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
	
	/** simple int array contain check
	 * @param keys keys to look through
	 * @param key key to test
	 * @return if key is in keys, returns true
	 */
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
