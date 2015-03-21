/**
 * 
 */
package com.mycompany.clock;

import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JComponent;

/**
 * @author colin
 *
 */
public class ScreenRefreshController {

	private Executor threadExecutor; 
	
	public ScreenRefreshController(){
		threadExecutor = Executors.newSingleThreadExecutor();
	}
	
	public void refreshScreen(JComponent clockViewPanel){
		threadExecutor.execute(new RefreshScreenTask(clockViewPanel));
	}
	
	
}
