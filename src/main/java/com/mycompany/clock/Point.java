package com.mycompany.clock;

/**
 * 
 */

/**
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
	 * 
	 * @return
	 */
	public float getX(){
		return getElement(0,0);
	}
	
	/**
	 * 
	 * @return
	 */
	public float getY(){
		return getElement(1,0);
	}
	
	
}
