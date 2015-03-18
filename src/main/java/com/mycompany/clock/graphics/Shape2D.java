/**
 * 
 */
package com.mycompany.clock.graphics;

import java.awt.Graphics;

/**
 * A 2D Shape
 * 
 * @author colin
 *
 */
public abstract class Shape2D {

	/**
	 * Draws the shape
	 * 
	 * @param g graphic object
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * Transforms the shape
	 * 
	 * @param t transformation object
	 */
	public abstract void transform(Transformation t);
}
