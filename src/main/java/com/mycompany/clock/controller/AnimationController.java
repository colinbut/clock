/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.clock.controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import com.mycompany.clock.animation.Animation;
import com.mycompany.clock.view.ClockView;

/**
 * A controller that is responsible for the animation's 
 * thread management/submission/execution
 * 
 * @author colin
 *
 */
public class AnimationController {

	private Animation animation;
	private Executor threadExecutor = null;
	
	/**
	 * Constructor
	 * 
	 * @param clockView the view
	 */
	public AnimationController(ClockView clockView){
		animation = new Animation(clockView);
		threadExecutor = Executors.newSingleThreadExecutor();
	}
	
	/**
	 * Animates 
	 */
	public void animate(){
		threadExecutor.execute(animation);
	}
}
