/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.clock.animation;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.JComponent;

import com.mycompany.clock.view.ClockViewPanel;

/**
 * A class that is responsible for refreshing the screen
 * 
 * Makes use of Double Buffering drawing technique
 * 
 * @author colin
 *
 */
public class RefreshScreenTask implements Runnable{

	// uses for Double Buffering
	private Image offScreenBuffer = null;
	private Graphics offScreenGraphics;
	private Dimension d, oldD;
	private boolean stop = false;
	
	private JComponent clockViewPanel = null;
	
	/**
	 * Constructor
	 * 
	 * @param cvp
	 */
	public RefreshScreenTask(JComponent cvp){
		clockViewPanel = cvp;
	}
	
	/**
	 * 
	 * Renders the screen for animation.
	 * Animation causes redraw so need to refresh the initial background 
	 * scene
	 */
	public void render(){
		//Get the graphics object 1st
		Graphics g = clockViewPanel.getGraphics();
		d = clockViewPanel.getSize();
		
		if(offScreenBuffer == null || oldD.width != d.width ||
				oldD.height != d.height){
			oldD = d;
			
			offScreenBuffer = clockViewPanel.createImage(d.width, d.height);
			offScreenGraphics = (Graphics2D)offScreenBuffer.getGraphics();
		}
		else{
			offScreenGraphics.setColor(Color.BLACK);
			offScreenGraphics.fillRect(0, 0, d.width, d.height);
			offScreenGraphics.setColor(Color.WHITE);
			((ClockViewPanel) clockViewPanel).get2DDrawing().draw(offScreenGraphics);
			
			try{
				if((g != null)){
					g.drawImage(offScreenBuffer, 0, 0, clockViewPanel);
					Toolkit.getDefaultToolkit().sync();
					g.dispose();
				}
			}
			catch(Exception ev){
				ev.printStackTrace();
			}
		}
		
		
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	public void run(){
		try{
			//for(;;){//infinite loop when called
			while(!stop){	
			    render(); 
				Thread.sleep(20);
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	
}
