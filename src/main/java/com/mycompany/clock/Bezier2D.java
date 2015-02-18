package com.mycompany.clock;

import java.awt.*;
import java.util.*;
/**
 * @author Colin
 *
 */
public class Bezier2D {
	
	private Vector<Point2D> points;
	private int numberOfPoints;
	private int segments;
	private float interval;
	private BasicStroke b;
	
	/*
	 * Constructor..
	 */
	public Bezier2D(){
		points = new Vector<Point2D>();
		numberOfPoints = 0;
		segments = 10;
		interval = (float)1/segments;
		b = new BasicStroke(3);
	}
	
	/*
	 * Add a point to the curve?
	 */
	public void addPoint(float x, float y){
		points.add(new Point2D(x, y));
		numberOfPoints++;
	}
	
	public int fact(int x){
		if(x == 0){
			return 1;
		}
		else{
			return (x * fact(x - 1));
		}
	}
	
	public int choice(int n, int i){
		return (fact(n)/ ((fact(i) * fact(n - i))));
		
	}
	
	public float bezier(int i, int n, float t){
		float bint = choice(n, i) * (float)Math.pow(t, i) *
		                (float)Math.pow((1 - t), n - i);
		return bint;
	}
	
	/*
	 * Draws the curve line
	 */
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		float xsrc = ((Point2D)points.get(0)).getX();
		float ysrc = ((Point2D)points.get(0)).getY();
		
		for(float t = 0; t <= 1.1; t+= interval){
			float xdest = 0;
			float ydest = 0;
			
			for(int i = 0; i < numberOfPoints; i++){
				float bint = bezier(i, numberOfPoints - 1, t);
				
				xdest += ((Point2D)points.get(i)).getX() * bint;
				ydest += ((Point2D)points.get(i)).getY() * bint;
			}
			//g2.setStroke(b);
			g2.drawLine((int)xsrc, (int)ysrc, (int)xdest, (int)ydest);
			xsrc = xdest;
			ysrc = ydest;
		}
	}
	
	public void transform(Transformation trans){
		for(int i = 0; i < numberOfPoints; i++){
			((Point2D)points.get(i)).transform(trans);
		}
	}

}
