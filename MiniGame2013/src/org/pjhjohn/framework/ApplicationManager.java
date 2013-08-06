package org.pjhjohn.framework;

import org.pjhjohn.framework.controller.IController;
import org.pjhjohn.framework.state.IState;

import android.app.AlertDialog;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;

// Resources SensorManager Context IController
public class ApplicationManager {
	private static Resources 		resource;
	private static SensorManager 	sensor;
	private static Context 			context;
	private static IController 		controller;
	private static IState			state;
	private static AlertDialog 		dialog;
	private static boolean 			threadState;
	public static boolean 		soundEnable;
	
	public static void setResources(Resources res_in){
		ApplicationManager.resource = res_in;
	}
	public static Resources getResources(){
		return resource;
	}
	public static Bitmap getBitmap(int id){
		return BitmapFactory.decodeResource(resource, id);
	}
	
	public static void setSensorManager(SensorManager _sensor){
		ApplicationManager.sensor = _sensor;
	}
	public static SensorManager getSensorManager(){
		return sensor;
	}
	
	public static void setContext(Context _context){
		ApplicationManager.context = _context;
	}
	public static Context getContext(){
		return context;
	}
	
	public static void setController(IController controller_in){
		ApplicationManager.controller = controller_in;
	}
	public static IController getController(){
		return controller;
	}
	
	public static void setAlertDialog(AlertDialog dialog) {
		ApplicationManager.dialog = dialog;
	}
	public static AlertDialog getAlertDialog() {
		return dialog;
	}
	
	public static boolean isThreadActive() {
		return threadState;
	}
	public static void setThreadFlag(boolean threadState) {
		ApplicationManager.threadState = threadState;
	}
	public static IState getState() {
		return state;
	}
	public static void setState(IState state) {
		ApplicationManager.state = state;
	}
}
