package org.pjhjohn.framework.rank;

import game.main.R;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

class CustomAdapter extends ArrayAdapter<ListRowData>{
	private LayoutInflater myInflater;
	public CustomAdapter(Context context,ArrayList<ListRowData> object){
		super(context,0,object);
		myInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public View getView(int position, View v, ViewGroup parent){
		View view = null;
		view = (v==null)? myInflater.inflate(R.layout.customview,null) : v;
		final ListRowData data = this.getItem(position);
		if(data!=null){
			((TextView)view.findViewById(R.id.cName)).setTextColor(Color.rgb(0xff, 0xad, 0x33));
			((TextView)view.findViewById(R.id.cName)).setTextSize(20);
			((TextView)view.findViewById(R.id.cName)).setTypeface(null, Typeface.ITALIC);
			((TextView)view.findViewById(R.id.cName)).setText(data.getName());
			
			((TextView)view.findViewById(R.id.cScore)).setTextColor(Color.rgb(0xff, 0xad, 0x33));
			((TextView)view.findViewById(R.id.cScore)).setTextSize(20);
			((TextView)view.findViewById(R.id.cScore)).setTypeface(null, Typeface.ITALIC);
			((TextView)view.findViewById(R.id.cScore)).setText(data.getScore());
			
			((TextView)view.findViewById(R.id.cCountry)).setTextColor(Color.rgb(0xff, 0xad, 0x33));
			((TextView)view.findViewById(R.id.cCountry)).setTextSize(20);
			((TextView)view.findViewById(R.id.cCountry)).setTypeface(null, Typeface.ITALIC);
			((TextView)view.findViewById(R.id.cCountry)).setText(data.getCountry());
		} return view;
	}
}