/**
 * 
 */
package com.mycompany.clock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JComponent;

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
