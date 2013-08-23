package org.pjhjohn.framework.manager;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {
	private static SoundManager singleton = new SoundManager();
	public static SoundManager getInstance(){ return singleton; }
	private SoundManager(){}
	
	private SoundPool	 soundPool;
	private AudioManager audioManager;
	private Context		 context;
	private HashMap<Integer,Integer>	soundPoolMap;
	private int			 streamType;
	
	public static final int PLAY_SINGLE = 0;
	public static final int PLAY_LOOP = -1;
	
	public void init(Context context){
		streamType = AudioManager.STREAM_MUSIC;
		soundPool = new SoundPool(4, streamType, 0);
		soundPoolMap = new HashMap<Integer,Integer>();
		audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		this.context = context;
	}
	public void addSound(int index, int soundID){
		soundPoolMap.put(index, soundPool.load(context, soundID, 1));
	}
	
	public void play(int index, int playType){
		float volume = audioManager.getStreamVolume(streamType) / audioManager.getStreamMaxVolume(streamType);
		soundPool.play((Integer)soundPoolMap.get(index), volume, volume, 1, playType, 1f);
	}
}
