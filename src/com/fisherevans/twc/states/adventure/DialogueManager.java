package com.fisherevans.twc.states.adventure;

import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.tools.KeyTools;
import com.fisherevans.twc.tools.ResourceTools;

public class DialogueManager
{
	private ArrayList<Dialogue> _dialogues;
	private boolean _printing, _fast, _complete, _visible, _leftAlign;
	private Image _icon, _bg;
	private Font _font;
	private AdventureState _as;
	
	private final int PRINT_SPEED = 80;
	private final float FAST_SCALE = 3;
	private final int MAX_PRINT_WIDTH = 1000;
	private final int TEXT_SIDE = 210;
	private final int TEXT_1_TOP = 619;
	private final int TEXT_2_TOP = 663;
	private final int IMG_SIDE = 40;
	private final int IMG_TOP = 596;
	private final int BG_TOP = 596;
	
	public DialogueManager(AdventureState as)
	{
		_as = as;
		_complete = true;
		_visible = false;
		_bg = ResourceTools.getImage("res/gui/dialogue.png");
		_dialogues = new ArrayList<>();
		_font = ResourceTools.fontFP32();
	}
	
	/** Updates the dialogue
	 * @param delta time delta bewteen frames
	 */
	public void update(int delta)
	{
		
	}
	
	/** Renders the current dialogue
	 * @param gfx slick2d graphics
	 */
	public void render(Graphics gfx)
	{
		if(!_dialogues.isEmpty())
		{
			gfx.setFont(_font);
			gfx.drawImage(_bg, 0, BG_TOP);
			Dialogue d = _dialogues.get(0);
			int iconLeft = 0, text0Left = 0, text1Left = 0;

			if(d.isLeftAlign())
			{
				iconLeft = IMG_SIDE; text0Left = TEXT_SIDE; text1Left = TEXT_SIDE;
			}
			else
			{
				iconLeft = GameDriver.NATIVE_SCREEN_WIDTH-d.getIcon().getWidth()-IMG_SIDE;
				text0Left = GameDriver.NATIVE_SCREEN_WIDTH-TEXT_SIDE-_font.getWidth(d.getMessage()[0]);
				text1Left = GameDriver.NATIVE_SCREEN_WIDTH-TEXT_SIDE-_font.getWidth(d.getMessage()[1]);
			}
			
			gfx.drawImage(d.getIcon(), iconLeft, IMG_TOP);
			gfx.drawString(d.getMessage()[0], text0Left, TEXT_1_TOP);
			gfx.drawString(d.getMessage()[1], text1Left, TEXT_2_TOP);
		}
	}
	
	/** Handles key input
	 * @param key key code
	 * @param c key character
	 * @return true if you want to interrupt input
	 */
	public void keyPressed(int key, char c)
	{
		if(!_dialogues.isEmpty())
		{
			if(_dialogues.get(0).keyPressed(key, c));
			else if(KeyTools.isSELECT(key)) { _dialogues.remove(0); }
		}
	}
	
	public void addDialogue(String message, boolean leftAlign, Image icon)
	{
		ArrayList<String> messages = new ArrayList<>();
		
		String[] words = message.split(" +");
		String buffer = "";
		
		for(int index = 0;index < words.length;index++)
		{
			if(_font.getWidth(buffer + words[index]) >= MAX_PRINT_WIDTH)
			{
				messages.add(buffer);
				buffer = "";
			}
			buffer += words[index] + " ";
		}
		messages.add(buffer);
		
		for(int index = 0;index < messages.size();index += 2)
		{
			String[] dualBuffer = { messages.get(index), "" };
			if(index + 1 < messages.size())
			{
				dualBuffer[1] = messages.get(index + 1);
			}
			_dialogues.add(new Dialogue(dualBuffer, icon, leftAlign));
		}
	}
	
	public boolean isShowing()
	{
		return !_dialogues.isEmpty();
	}
	
	private class Dialogue
	{
		private String[] _messages;
		private Image _icon;
		private boolean _leftAlign;
		
		public Dialogue(String[] messages, Image icon, boolean leftAlign)
		{
			_messages = messages;
			_icon = icon;
			_leftAlign = leftAlign;
		}

		public String[] getMessage()
		{
			return _messages;
		}

		public void setMessage(String[] messages)
		{
			_messages = messages;
		}

		public Image getIcon()
		{
			return _icon;
		}

		public void setIcon(Image icon)
		{
			_icon = icon;
		}
		
		public boolean isLeftAlign()
		{
			return _leftAlign;
		}
		
		private void setLeftAlign(boolean leftAlign)
		{
			_leftAlign = leftAlign;
		}
		
		public boolean keyPressed(int key, char c)
		{
			return false;
		}
	}
}
