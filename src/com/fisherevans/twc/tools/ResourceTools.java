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
	private static AngelCodeFont _fontMC16 = null, _fontMC32 = null; // font sizes available for use
	private static AngelCodeFont _fontPM16 = null, _fontPM32 = null, _fontPM64 = null;
	private static AngelCodeFont _fontPMB16 = null, _fontPMB32 = null, _fontPMB64 = null;
	private static AngelCodeFont _fontFP32 = null, _fontFP64 = null;
	
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
	public static AngelCodeFont fontMC16()
	{
		if(_fontMC16 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/mc16.png", false, Image.FILTER_NEAREST);
				_fontMC16 = new AngelCodeFont("res/fonts/mc16.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the mc16 font!");
				e.printStackTrace();
			}
		}
		return _fontMC16;
	}

	/** @return default size 32 font. */
	public static AngelCodeFont fontMC32()
	{
		if(_fontMC32 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/mc32.png", false, Image.FILTER_NEAREST);
				_fontMC32 = new AngelCodeFont("res/fonts/mc32.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the mc32 font!");
				e.printStackTrace();
			}
		}
		return _fontMC32;
	}
	
	/** @return default size 16 font. */
	public static AngelCodeFont fontPM16()
	{
		if(_fontPM16 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/pm16.png", false, Image.FILTER_NEAREST);
				_fontPM16 = new AngelCodeFont("res/fonts/pm16.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the pm16 font!");
				e.printStackTrace();
			}
		}
		return _fontPM16;
	}

	/** @return default size 32 font. */
	public static AngelCodeFont fontPM32()
	{
		if(_fontPM32 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/pm32.png", false, Image.FILTER_NEAREST);
				_fontPM32 = new AngelCodeFont("res/fonts/pm32.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the pm32 font!");
				e.printStackTrace();
			}
		}
		return _fontPM32;
	}

	/** @return default size 32 font. */
	public static AngelCodeFont fontFP32()
	{
		if(_fontFP32 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/fp32.png", false, Image.FILTER_NEAREST);
				_fontFP32 = new AngelCodeFont("res/fonts/fp32.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the fp32 font!");
				e.printStackTrace();
			}
		}
		return _fontFP32;
	}

	/** @return default size 32 font. */
	public static AngelCodeFont fontFP64()
	{
		if(_fontFP64 == null)
		{
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/fp64.png", false, Image.FILTER_NEAREST);
				_fontFP64 = new AngelCodeFont("res/fonts/fp64.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the fp64 font!");
				e.printStackTrace();
			}
		}
		return _fontFP64;
	}
}
