package org.pjhjohn.framework;

import java.util.HashMap;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

public class SoundManager {
	private static SoundManager singleton = new SoundManager();
	public static SoundManager getInstance(){ return singleton; }
	private SoundManager(){}
	
	private SoundPool	 m_soundPool;
	private AudioManager m_audioManager;
	private Context		 m_activity;
	private HashMap<Integer,Integer>	m_soundPoolMap;
	private int			 m_streamType;
	
	public static final int PLAY_SINGLE = 0;
	public static final int PLAY_LOOP = -1;
	
	public void init(Context context){
		m_streamType = AudioManager.STREAM_MUSIC;
		m_soundPool = new SoundPool(4, m_streamType, 0);
		m_soundPoolMap = new HashMap<Integer,Integer>();
		m_audioManager = (AudioManager)context.getSystemService(Context.AUDIO_SERVICE);
		m_activity = context;
	}
	public void addSound(int index, int soundID){
		m_soundPoolMap.put(index, m_soundPool.load(m_activity, soundID, 1));
	}
	
	public void play(int index, int playType){
		float volume = m_audioManager.getStreamVolume(m_streamType) / m_audioManager.getStreamMaxVolume(m_streamType);
		m_soundPool.play((Integer)m_soundPoolMap.get(index), volume, volume, 1, playType, 1f);
	}
}
