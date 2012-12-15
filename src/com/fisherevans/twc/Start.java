package com.fisherevans.twc;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;

import javax.swing.JFrame;

import org.lwjgl.LWJGLUtil;
import org.newdawn.slick.CanvasGameContainer;
import org.newdawn.slick.ScalableGame;
import org.newdawn.slick.SlickException;

public class Start
{
	public static boolean DEBUG = true; // Used to turn on or off debug printing throughout the game.
	public final String TITLE = "The Wayward Crown - Dev"; // The title of the game
	
	private static CanvasGameContainer _canvas; // The actual slick2d game
	private static ScalableGame _scale; // Holds the canvas in a scalable form
	private static JFrame _frame; // The frame holding the game
	private FrameActions _fa; // Window listener to gracefully close the game on "X"
	private boolean _fullscreen = false; // Keeps track of windowed ful screen mode.
	private int _xInset, _yInset; // Window instes used to resize window correctly.
	
	private int[][] _resos = { { 640, 360 }, { 1280, 720 }, { 1920, 1080 }, { 2560, 1440 } }; // Correct ratio resolutions - per pixel
	private int _curRes = 1; // ID of the current reccomended res being used.
	
	public static boolean running = true;
	
	/** Creates the game itself
	 * @throws SlickException
	 */
	public Start() throws SlickException
	{
		System.setProperty("org.lwjgl.librarypath", new File(new File(System.getProperty("user.dir"), "res/lib/lwjgl/native"), LWJGLUtil.getPlatformName()).getAbsolutePath());
		System.setProperty("net.java.games.input.librarypath", System.getProperty("org.lwjgl.librarypath"));
		
		_fa = new FrameActions();
		
		initCanvas();
		initFrame();
		initInsets();

		cycleReso();
		
		_frame.add(_canvas);
		_canvas.start();
		_canvas.requestFocus();
	}
	
	/** Resize window to next biggest reccomended res. If it is bigger than the screen, go full screen. If full screen, go to smallest size.
	 */
	public void cycleReso()
	{
		Dimension dim = _frame.getToolkit().getScreenSize();
		if(_resos[_curRes][0] >= dim.width || _resos[_curRes][1] >= dim.height)
		{
			_fullscreen = true;
			reInitFrame();
			_frame.setSize(dim.width, dim.height);
			_frame.setLocation(0,0);
			_curRes = -1;
		}
		else
		{
			if(_fullscreen)
			{
				_fullscreen = false;
				reInitFrame();
			}
			setInnerSize(_resos[_curRes][0], _resos[_curRes][1]);
		}
		_curRes++;
		if(_curRes >= _resos.length)
		{
			_curRes = 0;
		}
		_canvas.requestFocus();
	}
	
	/** Resizes the window
	 * @param x The width of the INNER window (the canvas, ignores insets)
	 * @param y The height of the INNER window (the canvas, ignores insets)
	 */
	public void setInnerSize(int x, int y)
	{
		x += _xInset;
		y += _yInset;
		Dimension dim = _frame.getToolkit().getScreenSize();
		_frame.setSize(x, y);
		_frame.setLocation(dim.width/2 - x/2, dim.height/2 - y/2);
	}
	
	/** Calculate the inset sizes of the jframe
	 */
	private void initInsets()
	{
		_xInset = _frame.getInsets().left + _frame.getInsets().right;
		_yInset = _frame.getInsets().top + _frame.getInsets().bottom;
	}
	
	/** create the slick2d canvas
	 * @throws SlickException
	 */
	private void initCanvas() throws SlickException
	{
		_scale = new ScalableGame(new GameDriver("The Wayward Crown - Beta", this), GameDriver.NATIVE_SCREEN_WIDTH, GameDriver.NATIVE_SCREEN_HEIGHT, true);
		_canvas = new CanvasGameContainer(_scale);
		_canvas.getContainer().setAlwaysRender(true);
		_canvas.getContainer().setShowFPS(DEBUG);
		//_canvas.getContainer().setTargetFrameRate(120);
		_canvas.getContainer().setVerbose(DEBUG);
		_canvas.getContainer().setMinimumLogicUpdateInterval(4);
	}
	
	/** Create the jframe to hold to slick2d canvas */
	private void initFrame()
	{
		_frame = new JFrame(TITLE);
		_frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		_frame.addWindowListener(_fa);
		if(_fullscreen)
		{
			_frame.setUndecorated(true);
		}
		_frame.setVisible(true);
		_frame.toFront();
	}
	
	/** Recreate the frame (used from switching between fullscreen and windowed */
	public void reInitFrame()
	{
		_frame.remove(_canvas);
		_frame.removeWindowListener(_fa);
		_frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		_frame.setVisible(false);
		_frame.dispose();
		initFrame();
		_frame.add(_canvas);
	}
	
	public static CanvasGameContainer getCanvas()
	{
		return _canvas;
	}

    public static void pullThePlug() {
            WindowEvent wev = new WindowEvent(_frame, WindowEvent.WINDOW_CLOSING);
            Toolkit.getDefaultToolkit().getSystemEventQueue().postEvent(wev);
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
