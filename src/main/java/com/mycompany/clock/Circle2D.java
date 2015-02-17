package com.mycompany.clock;

/**
 * 
 */
import java.awt.*;
/**
 * @author Colin
 *
 */
public class Circle2D {
	
	private Point2D point; //x pos
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
		point = new Point2D(x,y);
		this.width = width;
		this.height = height;
		//localOrigin = new Point2D(x - (width/2), y - (height/2));
		this.offset = offset;
	}
	
	public void draw(Graphics g){
		//g.drawOval((int)point.getX(), (int)point.getY(), width, height);
		g.fillOval((int)point.getX() - offset, (int)point.getY() - offset, width, height);
	}
	
	public void transform(Transformation trans){
		point.transform(trans);
		
	}

}
