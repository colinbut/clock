package com.mycompany.clock;

/**
 * 
 */
import java.awt.*;
/**
 * @author Admin
 *
 */
public class Line2D {
	
	private Point2D src;
	private Point2D dest;
	
	public Line2D(float x1, float x2, float y1, float y2){
		src = new Point2D(x1, x2);
		dest = new Point2D(y1, y2);
	}
	
	public Point2D getSrc(){
		return src;
	}
	
	public Point2D getDest(){
		return dest;
	}
	
	public void printLine(){
		System.out.println("(" + src.getX() + "," + src.getY() + ")\n" +
				"(" + dest.getX() + "," + dest.getY() + ")");
	}
	
	public void draw(Graphics g){
		Graphics2D g2 = (Graphics2D)g;
		//g2.setStroke(new BasicStroke(3));
		
		g2.drawLine((int)src.getX(), (int)src.getY(), 
				(int)dest.getX(), (int)dest.getY());
	}
	
	public void transform(Transformation trans){
		src.transform(trans);
		dest.transform(trans);
	}
	
	
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(){
		String temp;
		temp = "Line2D(\n" + "src: " + src.toString() + "\n" + 
		       "dest: " + dest.toString() + "\n" + ")endLine2d";
		return temp;
	}

}
