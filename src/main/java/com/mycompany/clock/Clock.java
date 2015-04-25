package com.mycompany.clock;

import com.mycompany.clock.view.ClockView;

/**
 * Clock Application
 * 
 * @author colin
 *
 */
public class Clock {
	
	/**
	 * Main application
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		
		ClockView start = new ClockView();
		
		// starts the animation
		start.startAnim();
	}

}
