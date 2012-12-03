package com.fisherevans.twc.tools;

public class MathTools
{
	public static float clamp(float x, float min, float max)
	{
		x = x < min ? min : x;
		x = x > max ? max : x;
		return x;
	}
}
