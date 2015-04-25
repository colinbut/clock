package com.mycompany.clock.graphics;

/**
 * 
 */
import java.awt.*;

/**
 * @author colin
 *
 */
public class Circle2D extends Shape2D{
	
	private Point point; // x,y coord
	private int width;
	private int height;
	private int offset;
	
	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param offset
	 */
	public Circle2D(float x, float y, int width, int height, int offset){
		point = new Point(x,y);
		this.width = width;
		this.height = height;
		//localOrigin = new Point2D(x - (width/2), y - (height/2));
		this.offset = offset;
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g){
		//g.drawOval((int)point.getX(), (int)point.getY(), width, height);
		g.fillOval((int)point.getX() - offset, (int)point.getY() - offset, width, height);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#transform(com.mycompany.clock.Transformation)
	 */
	@Override
	public void transform(Transformation trans){
		point.transform(trans);	
	}

}
