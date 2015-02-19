/**
 * 
 */
package com.mycompany.clock;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * @author colin
 *
 */
public class ClockController extends WindowAdapter implements KeyListener{

	private ClockView view;
	
	/**
	 * Constructor
	 * 
	 * @param view
	 */
	public ClockController(ClockView view){
		this.view = view; 
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.WindowAdapter#windowClosing(java.awt.event.WindowEvent)
	 */
	public void windowClosing(WindowEvent e){
		System.exit(0);
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyPressed(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyPressed(KeyEvent evt) {
	    
		int keyCode = evt.getKeyCode();
	    switch (keyCode) {
	    case 37:
	    	view.seconds.transform(view.localRotation(6));
	    	break;
	    case 38 : // up cursor key 
	    	view.hours.transform(view.localRotation(6));
	    	break;
	    case 39:
	    	view.minutes.transform(view.localRotation(6));
	       
	          break;
	    case 40 : // down cursor key
	    	// do nothing
	    }
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyTyped(java.awt.event.KeyEvent)
	 */
	@Override
	public void keyTyped(KeyEvent evt){
		
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.awt.event.KeyListener#keyReleased(java.awt.event.KeyEvent)
	 */
	public void keyReleased(KeyEvent evt){
		
	}

}
