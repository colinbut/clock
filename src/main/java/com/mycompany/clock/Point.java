package com.mycompany.clock;


/**
 * A Point in 2D space
 * 
 * has x,y coordinates
 * 
 * @author Colin
 *
 */
public class Point extends Matrix{
	
	
	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Point(float x, float y){
		super(3,1,1);
		matrix[0][0] = x;
		matrix[1][0] = y;
	}
	
	/**
	 * Gets the x coordinate
	 * 
	 * @return x coordinate in float value
	 */
	public float getX(){
		return getElement(0,0);
	}
	
	/**
	 * Gets the y coordinate
	 * 
	 * @return y coordinate in float value
	 */
	public float getY(){
		return getElement(1,0);
	}
	
	
}
