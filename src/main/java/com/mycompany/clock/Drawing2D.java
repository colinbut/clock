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
public class Drawing2D {
	
	private List<Shape2D> shapes;
	
	public Drawing2D(){
		shapes = new ArrayList<Shape2D>();
	}
	
	public void addShape(Shape2D shape){
		shapes.add(shape);
	}
	
	public void removeShape(Shape2D shape){
		shapes.remove(shape);
	}
	
	public void draw(Graphics g){
		for(Shape2D shape : shapes){
			shape.draw(g);
		}
	}
	
	public void transform(Transformation trans){
		for(Shape2D shape : shapes){
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
		for(Shape2D shape : shapes){
			shapes2D = shape.toString() + "\n" + shape.toString() + "\n";
		}
		temp = "Drawing2D(\n" + shapes2D + "\n" + ")endDrawing2D";
		return temp;
	}

}
