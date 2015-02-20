package com.mycompany.clock;

import java.awt.*;

import javax.swing.*;
/**
 * @author colin
 *
 */
public class ClockViewPanel extends JPanel{
	
	private static final long serialVersionUID = 7846676666316763109L;
	Drawing2D myDrawing;
	
	private Thread thr;
		
	/**
	 * Constructor
	 */
	public ClockViewPanel(){
		myDrawing = new Drawing2D();
		setBackground(Color.BLACK);
		thr = new Thread(new ScreenRefresher(this));
	}
	
	/**
	 * Sets the Drawing to this View
	 * 
	 * @param drawing The Drawing to be set
	 */
	public void setDrawing(Drawing2D drawing){
		myDrawing = drawing;
	}
	
	/*
	 * (non-Javadoc)
	 * @see javax.swing.JComponent#paintComponent(java.awt.Graphics)
	 */
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		myDrawing.draw(g);
	}
	
	/**
	 * Starts the thread that does the rendering (Erasing + Drawing)
	 */
	public void startAnim(){
		thr.start();
	}
	
	/**
	 * Stops this rendering thread
	 * 
	 * This method is not used in this application
	 */
	@Deprecated
	public void stopAnim(){
		thr.stop();
	}
	


}
