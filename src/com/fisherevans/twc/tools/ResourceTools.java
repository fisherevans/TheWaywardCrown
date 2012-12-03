package com.fisherevans.twc.tools;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.IOException;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class ResourceTools
{
	private static AngelCodeFont _font16 = null, _font24 = null, _font32 = null, _font40 = null, _font48 = null; // font sizes available for use
	
	/** creates a correctly scaled image
	 * @param res location of image to load
	 * @return The scaled image with nearest filter
	 */
	public static Image getImage(String res)
	{
		try
		{
			Image newImage = new Image(res);
			newImage.setFilter(Image.FILTER_NEAREST);
			newImage = newImage.getScaledCopy(2);
			return newImage;
		}
		catch (SlickException e)
		{
			System.out.println("Could not load image: " + res + "!\n");
			e.printStackTrace();
		}
		return null;
	}
	
	/** @return default size 16 font. */
	public static AngelCodeFont font16()
	{
		if(_font16 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/size16.png", false, Image.FILTER_NEAREST);
				_font16 = new AngelCodeFont("res/fonts/size16.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the size16 font!");
				e.printStackTrace();
			}
		}
		return _font16;
	}

	/** @return default size 24 font. */
	public static AngelCodeFont font24()
	{
		if(_font24 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/size24.png", false, Image.FILTER_NEAREST);
				_font24 = new AngelCodeFont("res/fonts/size24.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the size24 font!");
				e.printStackTrace();
			}
		}
		return _font24;
	}

	/** @return default size 32 font. */
	public static AngelCodeFont font32()
	{
		if(_font32 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/size32.png", false, Image.FILTER_NEAREST);
				_font32 = new AngelCodeFont("res/fonts/size32.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the size32 font!");
				e.printStackTrace();
			}
		}
		return _font32;
	}

	/** @return default size 40 font. */
	public static AngelCodeFont font40()
	{
		if(_font40 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/size40.png", false, Image.FILTER_NEAREST);
				_font40 = new AngelCodeFont("res/fonts/size40.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the size40 font!");
				e.printStackTrace();
			}
		}
		return _font40;
	}

	/** @return default size 48 font. */
	public static AngelCodeFont font48()
	{
		if(_font48 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/size48.png", false, Image.FILTER_NEAREST);
				_font48 = new AngelCodeFont("res/fonts/size48.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the size48 font!");
				e.printStackTrace();
			}
		}
		return _font48;
	}
}
