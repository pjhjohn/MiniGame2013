package org.pjhjohn.framework.manager;

import org.pjhjohn.framework.controller.IController;
import org.pjhjohn.framework.view.AGameView;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.hardware.SensorManager;

public class AppManager {
	// Resource & Bitmap & Device Width/Height & dp<->px
	private static Resources resource;
	public static void setResources(Resources r) { AppManager.resource = r;	}
	public static Resources getResources(){ return AppManager.resource;		}
	public static Bitmap getBitmap(int id){ return BitmapFactory.decodeResource(resource, id);	}
	public static float getDeviceWidth(){	return resource.getDisplayMetrics().widthPixels;	}
	public static float getDeviceHeight(){	return resource.getDisplayMetrics().heightPixels;	}
	public static float getDeviceDensity(){	return resource.getDisplayMetrics().density;		}
	public static int dp2px(float dp){	return (int)(dp * resource.getDisplayMetrics().density);}
	public static float px2dp(int px){	return px / resource.getDisplayMetrics().density;		}	
	// Context
	private static Context context;	
	public static void setContext(Context c) { AppManager.context = c;	}
	public static Context getContext(){	return AppManager.context;		}
	
	// Application Flow Part	
	public static boolean 	soundEnable;
	public static final int MAX_FPS = 30;	
	public static final int MAX_FRAME_SKIPS = 5;
	// AGameView
	private static AGameView gameView;
	public static void setGameView(AGameView v) { AppManager.gameView = v;	}
	public static AGameView getGameView(){ return AppManager.gameView;		}

	// State
	private static IState state;
	public static void setState(IState s) {	AppManager.state = s;	}
	public static IState getState(){ return AppManager.state;		}
		
	// Controller
	private static IController controller;
	public static void setController(IController c) { AppManager.controller = c;	}
	public static IController getController(){ return AppManager.controller;		}
		
	// AGameViewThread Enable
	private static boolean 		 threadEnable;
	public static boolean isGameThreadActive() { return AppManager.threadEnable;	}
	public static void setThreadFlag(boolean e) { AppManager.threadEnable = e;		}

	// SensorManager
	private static SensorManager sensor;
	public static void setSensorManager(SensorManager s) { AppManager.sensor = s;	}
	public static SensorManager getSensorManager(){	return AppManager.sensor;	 	}
}
