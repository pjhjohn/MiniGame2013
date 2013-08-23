package org.pjhjohn.framework.animatable;

import java.util.ArrayList;
import java.util.Hashtable;

import android.graphics.Canvas;
import android.util.Log;

public class AnimatableObjContainer extends AnimatableObj{
	private Hashtable<String, AnimatableObj> container;
	private ArrayList<String> key_mapper;
	public AnimatableObjContainer() {
		super();
		Log.i("AnimatableObjContainer", "Constructor");
		this.container = new Hashtable<String, AnimatableObj>();
		this.key_mapper = new ArrayList<String>();
	}
	
	public AnimatableObj add(String key, AnimatableObj object){
		Log.i("AnimatableObjContainer", "Add" + key);
		AnimatableObj removed_obj = null;
		boolean key_existance = false;
		for(int i = 0; i < key_mapper.size(); i++) if(key.compareTo(key_mapper.get(i))==0) {
			key_existance = true;
			break;
		}
		if(key_existance) {
			removed_obj = container.get(key);
			container.put(key, object);
			return removed_obj;
		} else {
			key_mapper.add(key);
			container.put(key,  object);
			return null;
		}
	}
	public void clear(){
		this.key_mapper.clear();
		this.container.clear();
	}
	public boolean isEmpty(){
		return key_mapper.isEmpty();
	}
	public AnimatableObj remove(String key){
		AnimatableObj removed_obj = null;
		boolean key_existance = false;
		int key_index = -1;
		for(int i = 0; i < key_mapper.size(); i++) if(key.compareTo(key_mapper.get(i))==0) {
			key_existance = true;
			key_index = i;
			break;
		}
		if(key_existance) {
			removed_obj = container.get(key);
			container.remove(key);
			key_mapper.remove(key_index);
		} return removed_obj;
	}
	public int size(){
		return key_mapper.size();
	}
	@Override
	public String toString(){
		String str = "[";
		for(int i = 0; i < key_mapper.size(); i++) str += (container.get(key_mapper.get(i)).toString() + ", "); 
		if(key_mapper.size()>=1) str = str.substring(0, str.length()-2);
		return str += "]";
	}

	@Override
	public void update() {
		AnimatableObj element = null;
		for(int i = 0; i < key_mapper.size(); i++) {
			element = container.get(key_mapper.get(i));
			element.update();
		}
	}
	@Override
	public void draw(Canvas canvas){
//		AnimatableObj element = null;
		for(int i = 0; i < key_mapper.size(); i++) {
			Log.i("AnimatableContainer", "Before Drawing #"+i);
			container.get(key_mapper.get(i)).draw(canvas);
			Log.i("AnimatableContainer", "After Drawing #"+i);
		}
	}
}
