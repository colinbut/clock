/**
 * 
 */
package com.mycompany.clock.animation;

import com.mycompany.clock.view.ClockView;

/**
 * A unit of task that performs the animation
 * 
 * @author colin
 *
 */
public class Animation implements Runnable{

	private long beforeTrans, afterTrans, beforeRender, afterRender;
	private long desiredFrameRate, transTime, renderTime, period;
	private long sleepTime, transformAndRenderTime;
	
	private boolean stop = false;
	
	private int secs = 0;
	private int mins = 0;
	
	
	private ClockView clockView = null;
	
	/**
	 * Constructor
	 * 
	 * @param gc the view for which the animation occurs on
	 */
	public Animation(ClockView gc){
		this.clockView = gc;
	}
	
	/*
	 * (non-Javadoc)
	 * @see java.lang.Runnable#run()
	 */
	@Override
	public void run() {
		
		desiredFrameRate = 1;//In frames per second
		period = ((long) 1000.0)/desiredFrameRate;
		
		while(!stop){ //the animation loop
			
			beforeTrans = System.nanoTime();
			
			secs++;		
			clockView.getSecondsHand().transform(clockView.localRotation(6));
			if(secs == 60){
				secs = 0;
				mins++;
				clockView.getMinutesHand().transform(clockView.localRotation(6));
			}
			
			if(mins == 2){
				//increment hr
				mins = 0;
				clockView.getHoursHand().transform(clockView.localRotation(1));
			}
			
					
			afterTrans = System.nanoTime();
			
			transTime = afterTrans - beforeTrans;
			
			beforeRender = System.nanoTime();
			//canvas.render();
			afterRender = System.nanoTime();
			renderTime = afterRender - beforeRender;
			
			transformAndRenderTime = transTime/ 100000 + renderTime/ 100000;
			sleepTime = period - (transformAndRenderTime);
			
			if(sleepTime <= 0){
				sleepTime = 5;
			}
			
			try{
			   Thread.sleep(sleepTime);
			}
			catch(Exception e){
				e.printStackTrace();
			}

		}
		
	}

}
