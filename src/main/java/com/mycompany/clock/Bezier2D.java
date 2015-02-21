package com.mycompany.clock;

import java.awt.*;
import java.util.*;
/**
 * @author colin
 *
 */
public class Bezier2D extends Shape2D{
	
	private Vector<Point> points;
	private int numberOfPoints;
	private int segments;
	private float interval;
	private BasicStroke b;
	
	/**
	 * Constructor
	 */
	public Bezier2D(){
		points = new Vector<Point>();
		numberOfPoints = 0;
		segments = 10;
		interval = (float)1/segments;
		b = new BasicStroke(3);
	}
	
	/**
	 * Add a point to the curve
	 */
	public void addPoint(float x, float y){
		points.add(new Point(x, y));
		numberOfPoints++;
	}
	
	/**
	 * 
	 * @param x
	 * @return
	 */
	public int fact(int x){
		if(x == 0){
			return 1;
		}
		else{
			return (x * fact(x - 1));
		}
	}
	
	/**
	 * 
	 * @param n
	 * @param i
	 * @return
	 */
	public int choice(int n, int i){
		return (fact(n)/ ((fact(i) * fact(n - i))));
	}
	
	/**
	 * 
	 * @param i
	 * @param n
	 * @param t
	 * @return
	 */
	public float bezier(int i, int n, float t){
		float bint = choice(n, i) * (float)Math.pow(t, i) *
		                (float)Math.pow((1 - t), n - i);
		return bint;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		float xsrc = ((Point)points.get(0)).getX();
		float ysrc = ((Point)points.get(0)).getY();
		
		for(float t = 0; t <= 1.1; t+= interval){
			float xdest = 0;
			float ydest = 0;
			
			for(int i = 0; i < numberOfPoints; i++){
				float bint = bezier(i, numberOfPoints - 1, t);
				
				xdest += ((Point)points.get(i)).getX() * bint;
				ydest += ((Point)points.get(i)).getY() * bint;
			}
			//g2.setStroke(b);
			g2.drawLine((int)xsrc, (int)ysrc, (int)xdest, (int)ydest);
			xsrc = xdest;
			ysrc = ydest;
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#transform(com.mycompany.clock.Transformation)
	 */
	@Override
	public void transform(Transformation trans){
		for(int i = 0; i < numberOfPoints; i++){
			((Point)points.get(i)).transform(trans);
		}
	}

}
