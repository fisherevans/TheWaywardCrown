package com.fisherevans.twc;

import java.awt.Font;
import java.awt.FontFormatException;
import java.io.BufferedInputStream;
import java.io.IOException;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;

public class ResourceTools
{
	private static AngelCodeFont _mainFont = null;
	
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
	
	public static AngelCodeFont getMainFont()
	{
		if(_mainFont == null)
		{
			
			Image fontImage;
			try
			{
				fontImage = new Image("res/fonts/mainfont.png", false, Image.FILTER_NEAREST);
				_mainFont = new AngelCodeFont("res/fonts/mainfont.fnt", fontImage);
			}
			catch (SlickException e)
			{
				System.out.println("Failed to load the main font!");
				e.printStackTrace();
			}
		}
		return _mainFont;
	}
}
