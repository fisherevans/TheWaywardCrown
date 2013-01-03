package com.fisherevans.twc.states.credits;

import java.util.ArrayList;

import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import com.fisherevans.twc.GameDriver;
import com.fisherevans.twc.states.State;
import com.fisherevans.twc.states.StateManager;
import com.fisherevans.twc.states.mainmenu.MainMenuState;
import com.fisherevans.twc.tools.KeyTools;
import com.fisherevans.twc.tools.MathTools;
import com.fisherevans.twc.tools.ResourceTools;

public class CreditsState extends State
{
	private ArrayList<Line> _lines;
	
	private float _vertScroll = GameDriver.NATIVE_SCREEN_HEIGHT/2;
	private float _vertScrollV = 0.33f;
	private float _maxVert = 0;
	private boolean[] _keys = { false, false };
	
	private final int LINE_PAD = 8,
					  SIDE_PAD = 128,
					  BASE_RT_PAD = GameDriver.NATIVE_SCREEN_WIDTH - SIDE_PAD,
					  BASE_CTR_PAD = GameDriver.NATIVE_SCREEN_WIDTH/2;
	
	private final float MAX_AUTO_SCROLL = 0.05f,
						AUTO_SCROLL_ACC = 0.000025f;
	
	private boolean _leaving = false;
	
	public CreditsState(StateManager sm, Input input)
	{
		super(sm, input);
	}

	@Override
	public void load()
	{
		_lines = new ArrayList<>();
		createLines();
	}
	
	private void createLines()
	{
		Font small = ResourceTools.fontMC16();
		Font med = ResourceTools.fontFP32();
		Font big = ResourceTools.fontFP64();
		
		/* LINES */
		
		_lines.add(new Line(256));

		_lines.add(new Line("The Wayward Crown", Align.CENTER, big));
		_lines.add(new Line("and its awesome developers", Align.CENTER, small));
		
		_lines.add(new Line(400));

		_lines.add(new Line("Software Engineer", Align.LEFT, small));
		_lines.add(new Line("Fisher Evans", Align.LEFT, big));
		
		_lines.add(new Line(16));

		_lines.add(new Line("Story Writer", Align.RIGHT, small));
		_lines.add(new Line("Jack Evans", Align.RIGHT, big));

		_lines.add(new Line(16));

		_lines.add(new Line("Map Designer", Align.LEFT, small));
		_lines.add(new Line("Rachel First", Align.LEFT, big));

		_lines.add(new Line(16));

		_lines.add(new Line("Music Composer", Align.RIGHT, small));
		_lines.add(new Line("Måns Åberg", Align.RIGHT, big));
		
		_lines.add(new Line(16));

		_lines.add(new Line("Pixel Artist", Align.LEFT, small));
		_lines.add(new Line("Julie Feikens", Align.LEFT, big));
		
		_lines.add(new Line(390));

		_lines.add(new Line("Press [ Select ]", Align.CENTER, med));
		
		_lines.add(new Line(300));
		
		/* CALC TIME */
		
		for(Line line:_lines)
		{
			_maxVert += LINE_PAD;
			if(line.isLineBreak())
			{
				_maxVert += line.getBreakSize();
			}
			else
			{
				_maxVert += line.getFont().getHeight(line.getText());
			}
		}
		_maxVert -= GameDriver.NATIVE_SCREEN_HEIGHT;
	}

	@Override
	public void update(GameContainer gc, int delta) throws SlickException
	{
		if(_leaving && getSM().getFM().isFadedOut())
		{
			getSM().setState(new MainMenuState(getSM(), getInput()));
		}
		
		if(_keys[0])
		{
			_vertScroll += 0.3f*delta;
			_vertScrollV = MAX_AUTO_SCROLL;
		}
		else if(_keys[1])
		{
			_vertScroll -= 0.3f*delta;
		}
		else
		{
			_vertScrollV -= AUTO_SCROLL_ACC*delta;
			_vertScrollV = MathTools.clamp(_vertScrollV, -MAX_AUTO_SCROLL, MAX_AUTO_SCROLL);
			if(_vertScrollV < 0)
			{
				_vertScroll += _vertScrollV*delta;
			}
		}
		
		_vertScroll = MathTools.clamp(_vertScroll, -_maxVert, 0);
	}

	@Override
	public void render(GameContainer gc, Graphics gfx) throws SlickException
	{
		int top = 0;
		for(int x = 0;x < _lines.size();x++)
		{
			Line curLine = _lines.get(x);
			if(!curLine.isLineBreak())
			{
				Font curFont = curLine.getFont();
				gfx.setFont(curFont);
				
				switch(curLine.getAlign())
				{
					case LEFT:
						gfx.drawString(curLine.getText(), SIDE_PAD, top + _vertScroll);
						break;
					case CENTER:
						gfx.drawString(curLine.getText(), BASE_CTR_PAD - curFont.getWidth(curLine.getText())/2, top + _vertScroll);
						break;
					case RIGHT:
						gfx.drawString(curLine.getText(), BASE_RT_PAD - curFont.getWidth(curLine.getText()), top + _vertScroll);
						break;
				}
				
				top += LINE_PAD + curFont.getHeight(curLine.getText());
			}
			else
			{
				top += curLine.getBreakSize();
			}
		}
	}

	@Override
	public void keyPressed(int key, char c)
	{
		if(KeyTools.isUP(key))
		{
			_keys[0] = true;
		}
		else if(KeyTools.isDOWN(key))
		{
			_keys[1] = true;
		}
		
		if(KeyTools.isSELECT(key) && !_leaving)
		{
			_leaving = true;
			getSM().getFM().fadeOut();
		}
	}

	@Override
	public void keyReleased(int key, char c)
	{
		if(KeyTools.isUP(key))
		{
			_keys[0] = false;
		}
		else if(KeyTools.isDOWN(key))
		{
			_keys[1] = false;
		}
	}
	
	private class Line
	{
		private String _text;
		private Align _align;
		private Font _font;
		private boolean _lineBreak = false;
		private int _breakSize;

		public Line(String text, Align align, Font font)
		{
			_text = text;
			_align = align;
			_font = font;
		}
		
		public Line(int breakSize)
		{
			_lineBreak = true;
			_breakSize = breakSize;
		}

		public String getText()
		{
			return _text;
		}
		
		public Align getAlign()
		{
			return _align;
		}

		public Font getFont()
		{
			return _font;
		}

		public boolean isLineBreak()
		{
			return _lineBreak;
		}

		public int getBreakSize()
		{
			return _breakSize;
		}
	}
	
	private enum Align { LEFT, CENTER, RIGHT }

}
