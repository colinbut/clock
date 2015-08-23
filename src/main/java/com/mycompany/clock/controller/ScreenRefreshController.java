/*
 * |-------------------------------------------------
 * | Copyright © 2008 Colin But. All rights reserved. 
 * |-------------------------------------------------
 */
package com.mycompany.clock.controller;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JComponent;

import com.mycompany.clock.animation.RefreshScreenTask;

/**
 * Controller for screen refresh
 * 
 * @author colin
 *
 */
public class ScreenRefreshController {

	private Executor threadExecutor; 
	
	/**
	 * Constructor
	 */
	public ScreenRefreshController(){
		threadExecutor = Executors.newSingleThreadExecutor();
	}
	
	/**
	 * Refreshes the screen
	 * 
	 * @param clockViewPanel
	 */
	public void refreshScreen(JComponent clockViewPanel){
		threadExecutor.execute(new RefreshScreenTask(clockViewPanel));
	}
	
	
}
