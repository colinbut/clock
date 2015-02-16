package com.mycompany.clock;

import java.awt.*;

import javax.swing.*;
/**
 * @author Colin
 *
 */
public class GView extends JPanel implements Runnable{
	
	private Drawing2D myDrawing;
	//Uses for Double Buffering + later Threads
	private Image offScreenBuffer = null;
	private Graphics offScreenGraphics;
	private Dimension d, oldD;
	//Use for Multi-Threading
	private Thread thr;
	private boolean stop = false;
		
	/**
	 * Constructor..
	 */
	public GView(){
		myDrawing = new Drawing2D();
		setBackground(Color.BLACK);
		thr = new Thread(this);
	}
	
	/*
	 * Sets the Drawing to this View
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
		stop = false;
		thr.start();
	}
	
	/**
	 * Stops this rendering thread
	 */
	public void stopAnim(){
		stop = false;
		thr.stop();
	}
	
	/**
	 * Renders the Animation...   ?
	 */
	public void render(){
		//Get the graphics object 1st
		Graphics g = getGraphics();
		d = getSize();
		
		if(offScreenBuffer == null || oldD.width != d.width ||
				oldD.height != d.height){
			oldD = d;
			
			offScreenBuffer = createImage(d.width, d.height);
			offScreenGraphics = (Graphics2D)offScreenBuffer.getGraphics();
		}
		else{
			offScreenGraphics.setColor(Color.BLACK);
			offScreenGraphics.fillRect(0, 0, d.width, d.height);
			offScreenGraphics.setColor(Color.WHITE);
			myDrawing.draw(offScreenGraphics);
			
			try{
				if((g != null)){
					g.drawImage(offScreenBuffer, 0, 0, this);
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
