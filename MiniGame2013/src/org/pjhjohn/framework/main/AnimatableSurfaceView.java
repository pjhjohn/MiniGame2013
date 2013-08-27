package org.pjhjohn.framework.main;

import org.pjhjohn.framework.resource.AnimatableObj;
import org.pjhjohn.framework.resource.AnimatableObjContainer;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

public class AnimatableSurfaceView extends SurfaceView implements Callback {
	private AnimatableSurfaceViewThread thread;
	private AnimatableObjContainer container;
	private int backgroundColor;
	public AnimatableSurfaceView(Context context){
		super(context);
		this.container = new AnimatableObjContainer();
		this.backgroundColor = Color.BLACK;
		this.getHolder().addCallback(this);
	}
	public AnimatableSurfaceView(Context context, int backgroundColor){
		super(context);
		this.container = new AnimatableObjContainer();
		this.backgroundColor = backgroundColor;
		this.getHolder().addCallback(this);
	}
	
	public void update(){
		if(!container.isEmpty()) container.update();
	}
	
	public void draw(Canvas canvas){
		canvas.drawColor(backgroundColor);
		this.onDraw(canvas);
	}
	
	@Override
	public void onDraw(Canvas canvas){
		if(!container.isEmpty()) container.draw(canvas);
	}
	// Implement object registration.
	public void register(String key, AnimatableObj obj){
		this.container.add(key, obj);
	}
	public void unregisterAt(String key){
		this.container.remove(key);
	}
	public void unregisterAll(){
		this.container.clear();
	}

	// Implement SurfaceView.Callback
	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		thread = new AnimatableSurfaceViewThread(getHolder(), this);
		this.thread.setThreadEnable(true);
		this.thread.start();
	}
	@Override 
	public void surfaceChanged(SurfaceHolder holder, int arg1, int arg2, int arg3) {
		
	}
	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		boolean retry = true;
		this.thread.setThreadEnable(false);
		while(retry){
			try{
				this.thread.join();
				retry = false;
			} catch(InterruptedException e){
			}
		}
	}
}