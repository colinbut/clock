package com.mycompany.clock;

import java.awt.*;

import javax.swing.*;
/**
 * @author colin
 *
 */
public class ClockViewPanel extends JPanel{
	
	private static final long serialVersionUID = 7846676666316763109L;
	private ClockDrawing2D myDrawing;
	
	private ScreenRefreshController screenRefreshController;
		
	/**
	 * Constructor
	 */
	public ClockViewPanel(){
		myDrawing = new ClockDrawing2D();
		setBackground(Color.BLACK);
		
		screenRefreshController = new ScreenRefreshController();
		
	}
	
	/**
	 * Sets the Drawing to this View
	 * 
	 * @param drawing The Drawing to be set
	 */
	public void setDrawing(ClockDrawing2D drawing){
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
		screenRefreshController.refreshScreen(this);
	}
	
	/**
	 * Returns the 2D drawing that is part of this view
	 * 
	 * @return the 2D drawing
	 */
	public ClockDrawing2D get2DDrawing(){
		return myDrawing;
	}


}
