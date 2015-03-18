package com.mycompany.clock;

import java.awt.*;
import java.util.Calendar;
import java.util.TimeZone;

import javax.swing.*;

import com.mycompany.clock.graphics.Bezier2D;
import com.mycompany.clock.graphics.Transformation;


/**
 * This represents the "View" of a MVC 
 * 
 * This consists of the application window frame
 * 
 * It is also where the animation occurs. Currently it only starts the animation
 * and animates indefinitely. It has no capabilities of stopping the animation.
 * 
 * 
 * @author colin
 *
 */
public class ClockView extends JFrame{
	
	private static final long serialVersionUID = 4931306666298591588L;
	private ClockViewPanel canvas;
	private ClockDrawing2D drawing;
	private TimeZone clockTimeZone; 
	private Calendar c;
	
	private ClockComponent seconds;
	private ClockComponent hours;
	private ClockComponent minutes;
	
	private int secs = 0;

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
	
	private ClockController controller;
	private AnimationController animationController;
	
	
	/**
	 * Constructor
	 */
	public ClockView(){
		
		controller = new ClockController(this);
		animationController = new AnimationController(this);
			
		canvas = new ClockViewPanel();
		drawing = new ClockDrawing2D();
		
		seconds = new ClockComponent(250, 250);
		hours = new ClockComponent(250, 250);
		minutes = new ClockComponent(250, 250);
		
		clockTimeZone = TimeZone.getDefault();//get the timezone
		c = Calendar.getInstance(clockTimeZone);
		
		initComponents();
		
		addKeyListener(controller);	
		addWindowListener(controller);
	}
	
	/**
	 * Makes the initial stuff
	 */
	public void initComponents(){
		
		setTitle("Clock");
		setLocation(250, 200);
		setPreferredSize(new Dimension(500,460));
		
		drawClockBase();
		drawClockFigures();
		drawClockHands();
		calculateTime();
		
		Transformation trans = new Transformation();
		trans.translate(0, -40);
		drawing.transform(trans);
		localX = seconds.x();
		localY = seconds.y();
	
		pack();
		getContentPane().add(canvas);
		
		setVisible(true);
		
	}
	
	/**
	 * The numbers of the Numerals
	 */
	private void drawClockFigures(){
		//12
		ClockComponent twelve = new ClockComponent(250,250);
		twelve.setShapeColor(Color.YELLOW);
		twelve.addLine(238, 70, 248, 90);
		twelve.addLine(248, 70, 238, 90);
		twelve.addLine(252, 70, 252, 90);
		twelve.addLine(257, 70, 257, 90);
		drawing.addShape(twelve);
		//3
		ClockComponent three = new ClockComponent(250, 250);
		three.setShapeColor(Color.YELLOW);
		three.addLine(410, 250, 430, 250);
		three.addLine(410, 240, 430, 240);
		three.addLine(410, 260, 430, 260);
		drawing.addShape(three);
		//6
		ClockComponent six = new ClockComponent(250, 250);
		six.setShapeColor(Color.YELLOW);
		six.addLine(242, 410, 242, 430);
		six.addLine(254, 410, 251, 430);
		six.addLine(254, 410, 257, 430);
		drawing.addShape(six);
		//9
		ClockComponent nine = new ClockComponent(250, 250);
		nine.setShapeColor(Color.YELLOW);
		nine.addLine(70, 260, 90, 260);
		nine.addLine(70, 240, 90, 250);
		nine.addLine(70, 250, 90, 240);
		drawing.addShape(nine);
		
	}
	
	/**
	 * The outline + the Square Swirl thats inside it
	 */
	private void drawClockBase(){
		canvas.setDrawing(drawing);
		//Outline
		ClockComponent clockOutline = new ClockComponent(250, 250);
		clockOutline.setShapeColor(Color.GREEN);
		clockOutline.addLine(60, 60, 440, 60);
		clockOutline.addLine(60, 60, 60, 440);
		clockOutline.addLine(60, 440, 440, 440);
		clockOutline.addLine(440, 60, 440, 440);
		drawing.addShape(clockOutline);
		//Base
		ClockComponent clockBase = new ClockComponent(250,250);
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
	
	/**
	 * Draws the clock hands
	 * - Hour, Minute & Seconds
	 */
	private void drawClockHands(){
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
	private void triangleShape(ClockComponent s){
    	s.addLine(260, 130, 240, 130);
		s.addLine(260, 130, 250, 110);
		s.addLine(240, 130, 250, 110);
	}
	
	/*
	 * Calculates the time
	 */
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
	
	/*
	 * Constructs the hand curves
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
	
	/*
	 * Constructs the hour curves
	 */
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
		
	/**
	 * Performs the localRotation
	 */
	 Transformation localRotation(int degrees){
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
	 * Moves the clock hands
	 */
	private void Tick(double d, ClockComponent s){
		
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
	
	/**
	 * Starts the animation
	 */
	public void startAnim(){
		System.out.println("Starting");
		animationController.animate();
		canvas.startAnim();
	}
	
	/**
	 * Gets the seconds hand drawing shape
	 * 
	 * @return seconds hand clock component
	 */
	public ClockComponent getSecondsHand(){
		return seconds;
	}
	
	/**
	 * Gets the minutes hand drawing shape
	 * 
	 * @return minutes hand clock component
	 */
	public ClockComponent getMinutesHand(){
		return minutes;
	}
	
	/**
	 * Gets the hours hand drawing shape
	 * 
	 * @return hours hand clock component
	 */
	public ClockComponent getHoursHand(){
		return hours;
	}
	


	
}
