package com.mycompany.clock;

/**
 * 
 */

import java.util.ArrayList;
import java.util.List;
import java.awt.*;
/**
 * @author colin
 *
 */
public class Drawing2D extends Shape2D{
	
	private List<ClockComponent> shapes;
	
	/**
	 * Constructor
	 */
	public Drawing2D(){
		shapes = new ArrayList<ClockComponent>();
	}
	
	/**
	 * Adds a shape to the drawing
	 * 
	 * @param shape
	 */
	public void addShape(ClockComponent shape){
		shapes.add(shape);
	}
	
	/**
	 * Removes a shape from the drawing
	 * 
	 * @param shape
	 */
	public void removeShape(ClockComponent shape){
		shapes.remove(shape);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g){
		for(ClockComponent shape : shapes){
			shape.draw(g);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#transform(com.mycompany.clock.Transformation)
	 */
	@Override
	public void transform(Transformation trans){
		for(ClockComponent shape : shapes){
			shape.transform(trans);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String temp;
		String shapes2D = "";
		for(ClockComponent shape : shapes){
			shapes2D = shape.toString() + "\n" + shape.toString() + "\n";
		}
		temp = "Drawing2D(\n" + shapes2D + "\n" + ")endDrawing2D";
		return temp;
	}

}
