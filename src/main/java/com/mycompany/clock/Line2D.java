package com.mycompany.clock;

import java.awt.*;

/**
 * A 2D Line
 * 
 * A line has 2 points in 2D space. Start and End with a horizontal 
 * connection between the 2 points.
 * 
 * @author colin
 *
 */
public class Line2D extends Shape2D{
	
	private Point src;
	private Point dest;
	
	/**
	 * Constructor
	 * 
	 * @param x1 x coordinate of 1st Point
	 * @param x2 x coordinate of 2nd Point
	 * @param y1 y coordinate of 1st Point
	 * @param y2 y coordinate of 2nd Point
	 */
	public Line2D(float x1, float x2, float y1, float y2){
		src = new Point(x1, x2);
		dest = new Point(y1, y2);
	}
	
	/**
	 * Gets the start point of a line
	 * 
	 * @return
	 */
	public Point getSrc(){
		return src;
	}
	
	/**
	 * Gets the end point of a line
	 * 
	 * @return
	 */
	public Point getDest(){
		return dest;
	}
	

	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		//g2.setStroke(new BasicStroke(3));
		
		g2.drawLine((int)src.getX(), (int)src.getY(), 
				(int)dest.getX(), (int)dest.getY());
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#transform(com.mycompany.clock.Transformation)
	 */
	@Override
	public void transform(Transformation trans){
		src.transform(trans);
		dest.transform(trans);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String temp;
		temp = "Line2D(\n" + "src: " + src.toString() + "\n" + 
		       "dest: " + dest.toString() + "\n" + ")endLine2d";
		return temp;
	}

}
