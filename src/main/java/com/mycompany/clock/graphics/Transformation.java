package com.mycompany.clock.graphics;


/**
 * 
 */

/**
 * @author colin
 *
 */
public class Transformation extends Matrix{

	/**
	 * Constructor
	 */
	public Transformation(){
		super(3,3,0);
		matrix[0][0] = 1;
		matrix[1][1] = 1;
		matrix[2][2] = 1;
	}
	
	/**
	 * 
	 * @param x
	 * @param y
	 */
	public void translate(float x, float y){
		matrix[0][2] = x;
		matrix[1][2] = y;
	}
	
	/**
	 * 
	 * @param angle
	 */
	public void rotate(float angle){
		 matrix[0][0] = (float)Math.cos(angle);
		 matrix[1][0] = -(float)Math.sin(angle);
		 matrix[0][1] = (float) Math.sin(angle);
		 matrix[1][1] = (float) Math.cos(angle);
	}
	
	/**
	 * 
	 * @param xscale
	 * @param yscale
	 */
	public void scale(float xscale, float yscale){
		//TODO: not yet implemented
		
	}
}
