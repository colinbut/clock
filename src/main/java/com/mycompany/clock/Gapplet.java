package com.mycompany.clock;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.TimeZone;
import javax.swing.event.*;
import javax.swing.*;

/**
 * MultiThread
 * 
 *
 * 
 */

/**
 * @author Colin
 *
 */
public class Gapplet extends JApplet implements Runnable{
	
	private ClockViewPanel canvas;
	private Drawing2D drawing;
	private TimeZone clockTimeZone; 
	private Calendar c;
	
	Shape2D seconds = new Shape2D(250, 250);
	Shape2D hours = new Shape2D(250, 250);
	Shape2D minutes = new Shape2D(250, 250);
	
	//Fields used for the animation stuff
	private long beforeTrans, afterTrans, beforeRender, afterRender;
	private long desiredFrameRate, transTime, renderTime, period;
	private long sleepTime, transformAndRenderTime;
	
	//Fields for Thread
	private Thread thr;
	private boolean stop = true;
	
	//Extras - testing
	private int secs = 0;
	private int mins = 0;
	private int hrs = 0;
	
	private float localX;
	private float localY;

    //swirl
	private double px = 100;
	private double py = 100;
	private double qx = 400;
	private double qy = 100;
	private double rx = 100;
	private double ry = 400;
	private double sx = 400;
	private double sy = 400;

	
	/**
	 * Constructor..
	 */
	public void init(){
		canvas = new ClockViewPanel();
		drawing = new Drawing2D();
		clockTimeZone = TimeZone.getDefault();//get the timezone
		c = Calendar.getInstance(clockTimeZone);
		getContentPane().add(canvas);
		initComponents();
		addKeyListener (new KeyListener()  {
			public void keyPressed(KeyEvent evt) {
			    int keyCode = evt.getKeyCode();
			    switch (keyCode) {
			    case 37:
			    	seconds.transform(localRotation(6));
			    	break;
			    case 38 : // up cursor key 
			    	hours.transform(localRotation(6));
			    	break;
			    case 39:
			    	minutes.transform(localRotation(6));
			       
			          break;
			    case 40 : // down cursor key
			    	
			    }
			}
			
			public void keyTyped(KeyEvent evt){
				
			}
			
			public void keyReleased(KeyEvent evt){
				
			}
		});
		//startAnim();
	}
	
	/*
	 * Makes the initial stuff
	 */
	public void initComponents(){
		
		drawClockBase();
		drawClockFigures();
		drawClockHands();
		calculateTime();
		Transformation trans = new Transformation();
		trans.translate(0, -40);
		drawing.transform(trans);
		localX = seconds.x();
		localY = seconds.y();
	}
	
	/*
	 * The numbers of the Numerals..
	 */
	public void drawClockFigures(){
		//12
		Shape2D twelve = new Shape2D(250,250);
		twelve.setShapeColor(Color.YELLOW);
		twelve.addLine(238, 70, 248, 90);
		twelve.addLine(248, 70, 238, 90);
		twelve.addLine(252, 70, 252, 90);
		twelve.addLine(257, 70, 257, 90);
		drawing.addShape(twelve);
		//3
		Shape2D three = new Shape2D(250, 250);
		three.setShapeColor(Color.YELLOW);
		three.addLine(410, 250, 430, 250);
		three.addLine(410, 240, 430, 240);
		three.addLine(410, 260, 430, 260);
		drawing.addShape(three);
		//6
		Shape2D six = new Shape2D(250, 250);
		six.setShapeColor(Color.YELLOW);
		six.addLine(242, 410, 242, 430);
		six.addLine(254, 410, 251, 430);
		six.addLine(254, 410, 257, 430);
		drawing.addShape(six);
		//9
		Shape2D nine = new Shape2D(250, 250);
		nine.setShapeColor(Color.YELLOW);
		nine.addLine(70, 260, 90, 260);
		nine.addLine(70, 240, 90, 250);
		nine.addLine(70, 250, 90, 240);
		drawing.addShape(nine);
		
	}
	
	/*
	 * The outline + the Square Swirl thats inside it
	 */
	public void drawClockBase(){
		canvas.setDrawing(drawing);
		//Outline
		Shape2D clockOutline = new Shape2D(250, 250);
		clockOutline.setShapeColor(Color.GREEN);
		clockOutline.addLine(60, 60, 440, 60);
		clockOutline.addLine(60, 60, 60, 440);
		clockOutline.addLine(60, 440, 440, 440);
		clockOutline.addLine(440, 60, 440, 440);
		drawing.addShape(clockOutline);
		//Base
		Shape2D clockBase = new Shape2D(250,250);
		clockBase.addLine((int)px, (int)py, (int)qx, (int)qy);
		clockBase.addLine((int)px, (int)py, (int)rx, (int)ry);
		clockBase.addLine((int)qx, (int)qy, (int)sx, (int)sy);
		clockBase.addLine((int)rx, (int)ry, (int)sx, (int)sy);
		
        double u = (10) / (qx - px);
		
		for(int i = 0; i < 120; i++){
			px = ((1 - u) * px) + (u * qx);
			py = ((1 - u) * py) + (u * qy);
			
			qx = ((1 - u) * qx) + (u * sx);
			qy = ((1 - u) * qy) + (u * sy);
			
			sx = ((1 - u) * sx) + (u * rx);
			sy = ((1 - u) * sy) + (u * ry);
			
			rx = ((1 - u) * rx) + (u * px);
			ry = ((1 - u) * ry) + (u * py);
			
			clockBase.addLine((int)px, (int)py, (int)qx, (int)qy);
			clockBase.addLine((int)qx, (int)qy, (int)sx, (int)sy);
			clockBase.setShapeColor(Color.RED);
			clockBase.addLine((int)sx, (int)sy, (int)rx, (int)ry);
			clockBase.addLine((int)rx, (int)ry, (int)px, (int)py);
		}
		drawing.addShape(clockBase);
		
	}
	
	/*
	 * Draws the clock hands
	 * - Hour, Minute & Seconds
	 */
	public void drawClockHands(){
		hours.addCircle(250, 250, 30, 30, 15);
		hours.addCurve(makeHourCurves());
		hours.addLine(260, 180, 240, 180);
		hours.addLine(260, 180, 250, 160);
		hours.addLine(240, 180, 250, 160);
		hours.setShapeColor(Color.BLUE);
		drawing.addShape(hours);
				
		minutes.addCircle(250, 250, 20, 20, 10);
		minutes.addCurve(makeHandCurves());
		triangleShape(minutes);
		minutes.setShapeColor(Color.GREEN);
		drawing.addShape(minutes);
				
		seconds.addCircle(250, 250, 10, 10, 5);
		triangleShape(seconds);
		seconds.addCurve(makeHandCurves());
		seconds.setShapeColor(Color.ORANGE);
		drawing.addShape(seconds);
	}
	
	/*
	 * Constructs an Apollo13 object shape
	 */
	private void triangleShape(Shape2D s){
    	s.addLine(260, 130, 240, 130);
		s.addLine(260, 130, 250, 110);
		s.addLine(240, 130, 250, 110);
	}
	
	private void calculateTime(){
		
		double minute = c.get(Calendar.MINUTE); //get min
		secs = c.get(Calendar.SECOND); //get secs
		double temp =  c.get(Calendar.HOUR); //get hour
		
		//calculate the actual angle
		double s = 6 * secs;
		double m = minute * 6;
		double h = ((temp/12) * 360) + (minute / 2);
			
		Tick(s, seconds);
		Tick(m, minutes);
		Tick(h, hours);
	}
	
	/**
	 * 
	 * @return curve
	 */
	private Bezier2D makeHandCurves(){
		Bezier2D curve = new Bezier2D();
		curve.addPoint(250, 250);
		curve.addPoint(230, 230);
		curve.addPoint(230, 210);
		curve.addPoint(250, 190);
		curve.addPoint(270, 170);
		curve.addPoint(270, 150);
		curve.addPoint(250, 130);
		return curve;
	}
	
	private Bezier2D makeHourCurves(){
		Bezier2D curve = new Bezier2D();
		curve.addPoint(250, 250);
		curve.addPoint(240, 240);
		curve.addPoint(240, 220);
		curve.addPoint(250, 210);
		curve.addPoint(270, 210);
		curve.addPoint(270, 200);
		curve.addPoint(250, 180);
		return curve;
	}
		
	/*
	 * Performs the localRotation..
	 */
	public Transformation localRotation(int degrees){
		float dx = localX;
		float dy = localY;
		Transformation transform1 = new Transformation();
		Transformation spin = new Transformation();
		Transformation translate1 = new Transformation();
		Transformation translate2 = new Transformation();
		
		translate1.translate(-dx, -dy);
		double angleInRadians = Math.toRadians(degrees);
		spin.rotate((float) -(angleInRadians));
		translate2.translate(dx, dy);
		
		transform1.transform(translate1);
		transform1.transform(spin);
		transform1.transform(translate2);
		
		return transform1;
	}
	
	/*
	 * 
	 */
	public void Tick(double d, Shape2D s){
		
		float dx = s.x();
		float dy = s.y();
				
		Transformation transform1 = new Transformation();
		Transformation spin = new Transformation();
		Transformation translate1 = new Transformation();
		Transformation translate2 = new Transformation();
		
		translate1.translate(-dx, -dy);
		double angleInRadians = Math.toRadians(d);
		spin.rotate((float) -(angleInRadians));
		translate2.translate(dx, dy);
		
		transform1.transform(translate1);
		transform1.transform(spin);
		transform1.transform(translate2);
		
		s.transform(transform1);
	}
	
	public void start(){
		startAnim();
	}
	
	/*
	 * Starts the Thread of this class
	 */
	public void startAnim(){
		System.out.println("Starting");
		stop = false;
		thr = new Thread(this);
		thr.start(); //starts the thread
		//and automatically calls run()
		canvas.startAnim();
	}
	
	/*
	 * Stops the total animation
	 */
	public void stopAnim(){
		System.out.println("Stopping");
		stop = true;
		thr.stop();//stops this thread
		canvas.stopAnim();
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 * The animation loop
	 */
	public void run(){
		    
		desiredFrameRate = 1;//In frames per second
		period = ((long) 1000.0)/desiredFrameRate;
		
		while(!stop){ //the animation loop
			
			beforeTrans = System.nanoTime();
			
			secs++;		
			seconds.transform(localRotation(6));
			if(secs == 60){
				secs = 0;
				mins++;
				minutes.transform(localRotation(6));
			}
			
			if(mins == 2){
				//increment hr
				mins = 0;
				hours.transform(localRotation(1));
			}
			
					
			afterTrans = System.nanoTime();
			
			transTime = afterTrans - beforeTrans;
			
			beforeRender = System.nanoTime();
			afterRender = System.nanoTime();
			renderTime = afterRender - beforeRender;
			
			transformAndRenderTime = transTime/ 100000 + renderTime/ 100000;
			sleepTime = period - (transformAndRenderTime);
			
			if(sleepTime <= 0){
				sleepTime = 5;
			}
			
			try{
			   Thread.sleep(sleepTime);
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}
	}
	
}

