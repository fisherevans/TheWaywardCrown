package com.fisherevans.twc.tools;

public class MathTools
{
	/** returns a clamped number
	 * @param x number in question
	 * @param min min vlue to return
	 * @param max max number to return
	 * @return returns x clamped to the min and max
	 */
	public static float clamp(float x, float min, float max)
	{
		x = x < min ? min : x;
		x = x > max ? max : x;
		return x;
	}
}
