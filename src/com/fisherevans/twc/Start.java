package com.fisherevans.twc;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class Start extends JFrame
{
	private CanvasGameContainer _canvas;
	private int _xInset, _yInset;
	private static boolean _debug = true;
	
	public Start() throws SlickException
	{
		super("Chaotic Harmony");
		
		initCanvas();
		initFrame();
		initInsets();

		setInnerSize(720, 480);
		
		add(_canvas);
		_canvas.start();
		_canvas.requestFocus();
		
	}
	
	public void setInnerSize(int x, int y)
	{
		x += _xInset;
		y += _yInset;
		Dimension dim = getToolkit().getScreenSize();
		setSize(x, y);
		setLocation(dim.width/2 - x/2, dim.height/2 - y/2);
	}
	
	private void initInsets()
	{
		_xInset = getInsets().left + getInsets().right;
		_yInset = getInsets().top + getInsets().bottom;
		debug("X Inset: " + _xInset + " - Y Inset: " + _yInset);
	}
	
	private void initCanvas() throws SlickException
	{
		_canvas = new CanvasGameContainer(new ScalableGame(new GameDriver("Chaotic Harmony"),720,480, true));
		_canvas.getContainer().setAlwaysRender(true);
		_canvas.getContainer().setShowFPS(_debug);
		_canvas.getContainer().setTargetFrameRate(60);
		_canvas.getContainer().setVerbose(_debug);
	}
	
	private void initFrame()
	{
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		addWindowListener(new FrameActions());
		toFront();
		setState(this.NORMAL);
		setVisible(true);
	}
	
	public static void debug(String msg)
	{
		if(_debug)
		{
			System.out.println("[DEBUG] " + msg);
		}
	}
	
	public static void main(String[] args)
	{
		try
		{
			new Start();
		}
		catch (SlickException e)
		{
			System.out.println("Failed to create the game object");
			e.printStackTrace();
		}
	}
	
	/* Source code gotten from http://slick.javaunlimited.net/viewtopic.php?f=3&t=4543&p=26351#p26351 */
	public class FrameActions implements WindowListener {
		@Override public void windowActivated  (WindowEvent arg0) { }
		@Override public void windowClosed     (WindowEvent arg0) { System.exit(0); }
		@Override public void windowClosing    (WindowEvent arg0) { _canvas.setEnabled(false); _canvas.dispose(); }
		@Override public void windowDeactivated(WindowEvent arg0) { }
		@Override public void windowDeiconified(WindowEvent arg0) { }
		@Override public void windowIconified  (WindowEvent arg0) { }
		@Override public void windowOpened     (WindowEvent arg0) { }
	}
}
