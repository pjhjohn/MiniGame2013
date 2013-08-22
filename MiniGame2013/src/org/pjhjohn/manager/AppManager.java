package org.pjhjohn.manager;

import org.pjhjohn.framework.controller.IController;
import org.pjhjohn.framework.state.IState;
import org.pjhjohn.framework.view.AGameView;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;

public class AppManager {
	// Application Resource Part
	private static Resources 	 resource;
	private static Context 		 context;
	private static AlertDialog 	 dialog;
	
	public static void setResources(Resources res_in){
		AppManager.resource = res_in;
	}
	public static Resources getResources(){
		return resource;
	}
	public static Bitmap getBitmap(int id){
		return BitmapFactory.decodeResource(resource, id);
	}
	
	public static void setContext(Context _context){
		AppManager.context = _context;
	}
	public static Context getContext(){
		return context;
	}
	
	public static void setAlertDialog(AlertDialog dialog) {
		AppManager.dialog = dialog;
	}
	public static AlertDialog getAlertDialog() {
		return dialog;
	}
	
	// Application Flow Part
	private static AGameView	 gameView;
	private static SensorManager sensor;
	private static IController 	 controller;
	private static IState		 state;
	private static boolean 		 threadState;
	public static boolean 		 soundEnable;
	
	public static void setGameView(AGameView gameView){
		AppManager.gameView = gameView;
	}
	public static AGameView getGameView(){
		return AppManager.gameView;
	}
	
	public static void setSensorManager(SensorManager _sensor){
		AppManager.sensor = _sensor;
	}
	public static SensorManager getSensorManager(){
		return sensor;
	}
	
	public static void setController(IController controller_in){
		AppManager.controller = controller_in;
	}
	public static IController getController(){
		return controller;
	}
	
	public static boolean isGameThreadActive() {
		return threadState;
	}
	public static void setThreadFlag(boolean threadState) {
		AppManager.threadState = threadState;
	}
	public static IState getState() {
		return state;
	}
	public static void setState(IState state) {
		AppManager.state = state;
	}
	
	//Device Information
	private static float DEVICE_WIDTH = 0;
	private static float DEVICE_HEIGHT = 0;
	private static float DEVICE_DENSITY = 0;
	public static float getDeviceWidth(){ return DEVICE_WIDTH; }
	public static float getDeviceHeight(){ return DEVICE_HEIGHT; }
	public static float getDeviceDensity(){ return DEVICE_DENSITY; }
	// This Function must be called at the beginning of application
	public static final void setDeviceSize(float width, float height){
		DEVICE_WIDTH = width;
		DEVICE_HEIGHT = height;		
	}
	public static void setDeviceDensity(Context context){
		DEVICE_DENSITY = context.getResources().getDisplayMetrics().density;
	}
	public static float dp2px(float dp){
		return dp * DEVICE_DENSITY;
	}
	public static float px2dp(float px){
		return px / DEVICE_DENSITY;
	}
}
