package app.main;

public final class AppOption {
	// current Game Type
	private static AppOption.Game gameType;
	public static enum Game {
		MISSLE_DODGE, PUZZLE_BUBBLE, SNOW_CRAFT
	}
	public static AppOption.Game getCurrentGameType(){
		return AppOption.gameType;
	}
	public static void setCurrentGameType(AppOption.Game currentGame){
		AppOption.gameType = currentGame;
	}
	// Dodge Options
	public static final class Dodge {
		public static final float PADDING_JOYSTIC 		= (float) 20;	// in Pixels
		public static final float PADDING_SCORETEXT		= (float) 20;	// in Pixels
		public static final float GUIDED_ASTEROID_SPEED	= (float) 2.5; 	// in Pixels
		public static final float ASTEROID_SPEED		= (float) 4; 	// in Pixels
		public static final float ASTEROID_ANGLE_RANGE	= (float) 20; 	// in Degrees
		public static final float ASTEROID_SAFETY_RANGE	= (float) 300; 	// in Pixels
		public static final float STAR_SPEED_RANGE 		= (float) 2;
		public static final float STAR_SPEED_MIN 		= (float) 1;
		public static final int NUMBER_OF_ASTEROID 		= 10;
		public static final int NUMBER_OF_STAR 			= 200;
		public static final int THREAD_INTERVAL 		= 50;
		public static final class Sensitivity {
			public static final float TOUCH_MIN			= (float) 0.02;
			public static final float TOUCH_MAX			= (float) 0.10;
			public static final float JOYSTIC_MIN		= (float) 0.05;
			public static final float JOYSTIC_MAX		= (float) 0.25;
			public static final float TILT_MIN			= (float) 0.5;
			public static final float TILT_MAX			= (float) 1.5;
			// Default is manually set to (min+max)/2
		}
	}
	public final class Bubble {
		
	}
	public final class SnowCraft {
		
	}
	
	private static float DEVICE_WIDTH;
	private static float DEVICE_HEIGHT;
	public static float getDeviceWidth(){ return DEVICE_WIDTH; }
	public static float getDeviceHeight(){ return DEVICE_HEIGHT; }
	// This Function must be called at the beginning of application
	public static final void setDeviceSize(float width, float height){
		DEVICE_WIDTH = width;
		DEVICE_HEIGHT = height;		
	}
}
