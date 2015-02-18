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
public class Shape2D {
	
	//Lists of shapes
	private List<Line2D> lines;
	private List<Circle2D> circles;
	private List<Bezier2D> curves;
	private Color color;
	private Point2D localOrigin;
	
	/**
	 * Constructor
	 * 
	 * @param x
	 * @param y
	 */
	public Shape2D(float x, float y){
		lines = new ArrayList<Line2D>();
		circles = new ArrayList<Circle2D>();
		curves = new ArrayList<Bezier2D>();
		localOrigin = new Point2D(x,y);
		color = Color.BLACK;
	}
	
	/**
	 * 
	 * @return
	 */
	public List<Line2D> getLineList(){
		return lines;
	}
		
	/**
	 * Adds the line to the shape
	 * @param line The line
	 */
	public void addLine(Line2D line){
		lines.add(line);
	}
	
	/**
	 * Adds 4 floating point values that would be used to construct
	 * a line. A line object is then instantiated
	 * 
	 * @param x1
	 * @param x2
	 * @param y1
	 * @param y2
	 */
	public void addLine(float x1, float x2, float y1, float y2){
		Line2D line = new Line2D(x1, x2, y1, y2);
		lines.add(line);
	}
	
	/**
	 * Adds a circle to the shapes
	 * 
	 * @param x
	 * @param y
	 * @param width
	 * @param height
	 * @param offset
	 */
	public void addCircle(float x, float y, int width, int height, int offset){
		Circle2D circle = new Circle2D(x, y, width, height, offset);
		circles.add(circle);
	}
	
	/**
	 * 
	 * @param curve
	 */
	public void addCurve(Bezier2D curve){
		curves.add(curve);
	}
	
	/**
	 * Draws the shapes
	 * @param g
	 */
	public void draw(Graphics g){
		for(Line2D line : lines){
			g.setColor(color);
			line.draw(g);
		}
		
		for(Circle2D circle : circles){
			g.setColor(color);
			circle.draw(g);
		}
		
		for(Bezier2D curve : curves){
			g.setColor(color);
			curve.draw(g);
		}
		
		
	}
	
	/**
	 * 
	 * @param c
	 */
	public void setShapeColor(Color c){
		color = c;
	}
	
	/**
	 * 
	 * @param trans
	 */
	public void transform(Transformation trans){
		for(Line2D line : lines){
			line.transform(trans);
		}
		
		for(Circle2D circle : circles){
			circle.transform(trans);
		}
		
		for(Bezier2D curve : curves){
			curve.transform(trans);
		}
		localOrigin.transform(trans);
	}
	
	/**
	 * 
	 * @return
	 */
	public float x(){
		return localOrigin.getX();
	}
	
	/**
	 * 
	 * @return
	 */
	public float y(){
		return localOrigin.getY();
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String temp;
		String lines2D = "";
		for(Line2D line : lines){
			lines2D =  line.toString() + "\n" + line.toString() + "\n";
		}
		temp = "Shape2D(\n" + lines2D + "\n" + ")endShape2D";
		return temp;
	}

}
