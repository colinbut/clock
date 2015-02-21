/**
 * 
 */
package com.mycompany.clock;

import java.awt.Graphics;

/**
 * @author colin
 *
 */
public abstract class Shape2D {

	/**
	 * 
	 * @param g
	 */
	public abstract void draw(Graphics g);
	
	/**
	 * 
	 * @param t
	 */
	public abstract void transform(Transformation t);
}
