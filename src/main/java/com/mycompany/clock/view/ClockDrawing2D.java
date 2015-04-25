package com.mycompany.clock.view;

import java.util.ArrayList;
import java.util.List;
import java.awt.*;

import com.mycompany.clock.graphics.Shape2D;
import com.mycompany.clock.graphics.Transformation;
/**
 * 
 * Represents a 2D drawing which composes of numerous
 * 2D shapes
 * 
 * @author colin
 *
 */
public class ClockDrawing2D extends Shape2D{
	
	private List<ClockHand> shapes;
	
	/**
	 * Constructor
	 */
	public ClockDrawing2D(){
		shapes = new ArrayList<ClockHand>();
	}
	
	/**
	 * Adds a shape to the drawing
	 * 
	 * @param shape
	 */
	public void addShape(ClockHand shape){
		shapes.add(shape);
	}
	
	/**
	 * Removes a shape from the drawing
	 * 
	 * @param shape
	 */
	public void removeShape(ClockHand shape){
		shapes.remove(shape);
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#draw(java.awt.Graphics)
	 */
	@Override
	public void draw(Graphics g){
		for(ClockHand shape : shapes){
			shape.draw(g);
		}
	}
	
	/*
	 * (non-Javadoc)
	 * @see com.mycompany.clock.Shape2D#transform(com.mycompany.clock.Transformation)
	 */
	@Override
	public void transform(Transformation trans){
		for(ClockHand shape : shapes){
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
		for(ClockHand shape : shapes){
			shapes2D = shape.toString() + "\n" + shape.toString() + "\n";
		}
		temp = "Drawing2D(\n" + shapes2D + "\n" + ")endDrawing2D";
		return temp;
	}

}
