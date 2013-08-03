package game.dodge.rank;

import game.dodge.main.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHeader;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.protocol.HTTP;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

public class RankingActivity extends Activity {
	ArrayList<String>	mNameArray = new ArrayList<String>();
	ArrayList<String>	mScoreArray = new ArrayList<String>();
	ArrayList<String> 	mCountryArray = new ArrayList<String>();	
	ArrayList<ListRowData> mListViewArray = new ArrayList<ListRowData>();
	CustomAdapter mCustomAdapter;

	ListView mListView;
	EditText mTextName;
	EditText mTextScore;
	EditText mTextCountry;
	//private int HowManyReCentLoc;	// for preference
	@Override
	public void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ranking_layout);
		findViewById(R.id.linearLayout).setBackgroundColor(Color.BLACK);
		mListView 	= (ListView)findViewById(R.id.listView);
		mTextName 	= (EditText)findViewById(R.id.name);
		mTextScore 	= (EditText)findViewById(R.id.score);
		mTextCountry= (EditText)findViewById(R.id.country);
		//mTextName.setBackgroundColor(Color.TRANSPARENT);
		mTextName.setTextColor(Color.WHITE);
		//mTextScore.setBackgroundColor(Color.TRANSPARENT);
		mTextScore.setTextColor(Color.WHITE);
		//mTextCountry.setBackgroundColor(Color.TRANSPARENT);
		mTextCountry.setTextColor(Color.WHITE);
		findViewById(R.id.button_read).setOnClickListener(onButtonClickListener);
		findViewById(R.id.button_send).setOnClickListener(onButtonClickListener);
		findViewById(R.id.button_clear).setOnClickListener(onButtonClickListener);
	}
	Button.OnClickListener onButtonClickListener = new Button.OnClickListener() {
		public void onClick(View v) {
			switch(v.getId()){
			case R.id.button_read : 
				String readHtml = readHtml();
				try {
					JSONArray jArray = new JSONArray(readHtml);
					JSONArraytoAdapter(jArray);
				} catch (JSONException e) {
					e.printStackTrace();
				} break;
			case R.id.button_send : sendJSON("http://superwoou.phps.kr/joonho.php", mTextName.getText().toString(), mTextScore.getText().toString(), mTextCountry.getText().toString());
			break;
			case R.id.button_clear : sendJSON("http://superwoou.phps.kr/joonho_clear.php", "", "", "");
			break;
			}
		}
	};
	private void JSONArraytoAdapter(JSONArray json){
		mCustomAdapter.clear();
		mCustomAdapter = new CustomAdapter(this,mListViewArray);
		mListView.setAdapter(mCustomAdapter);
		mNameArray.clear();
		mScoreArray.clear();
		mCountryArray.clear();
		for(int i=0; i<json.length(); i++){
			try {
				JSONObject obj = json.getJSONObject(i);
				mNameArray.add(obj.getString("name"));
				mScoreArray.add(obj.getString("score"));
				mCountryArray.add(obj.getString("country"));
				mCustomAdapter.add(new ListRowData(getApplicationContext(),mNameArray.get(i),mScoreArray.get(i),mCountryArray.get(i)));
			} catch (JSONException e) {
				e.printStackTrace();
			}
		}
	}
	
	public String readHtml() {
		StringBuilder builder = new StringBuilder();
		HttpClient client = new DefaultHttpClient();
		HttpGet httpGet = new HttpGet("http://superwoou.phps.kr/joonho_list.php");
		try {
			HttpResponse response = client.execute(httpGet);
			StatusLine statusLine = response.getStatusLine();
			int statusCode = statusLine.getStatusCode();
			if (statusCode == 200) {
				HttpEntity entity = response.getEntity();
				InputStream content = entity.getContent();
				BufferedReader reader = new BufferedReader(new InputStreamReader(content));
				String line;
				while ((line = reader.readLine()) != null) builder.append(line);
			} else Log.e(RankingActivity.class.toString(), "Failed to download file");
		} catch (ClientProtocolException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} return builder.toString();
	}
	protected void sendJSON(final String url, final String name, final String score, final String countryCode) {
		Thread t = new Thread() {
			public void run() {
				Looper.prepare(); //For Preparing Message Pool for the child Thread
				HttpClient client = new DefaultHttpClient();
				HttpConnectionParams.setConnectionTimeout(client.getParams(), 10000); //Timeout Limit
				HttpResponse response;
				JSONObject json = new JSONObject();
				try {
					HttpPost post = new HttpPost(url);
					json.put("name", name).put("score", score).put("country", countryCode);
					StringEntity se = new StringEntity( json.toString());  
					se.setContentType(new BasicHeader(HTTP.CONTENT_TYPE, "application/json"));
					post.setEntity(se);
					Log.i("JSON","Post Success");
					response = client.execute(post);
					/*Checking response */
					if(response!=null){
						Log.i("JSON","Resp Success");
						String resp = response.getEntity().getContent().toString(); //Get the data in the entity
						Log.i("HTTP Response",resp);
					}else Log.i("JSON","Resp Fail");

				} catch(Exception e) {
					Log.i("HTTPError",e.getCause() + "\n"+ e.getMessage());
					Toast.makeText(RankingActivity.this,"Cannot Estabilish Connection",Toast.LENGTH_SHORT).show();
				}
				Looper.loop(); //Loop in the message queue
			}
		};
		t.start();      
	}
	/*
	private void updateRecentLoc(){
		SharedPreferences prefLoc = getSharedPreferences("RescentLoc",  MODE_PRIVATE);
		SharedPreferences.Editor editor = prefLoc.edit();
		for(int i=0; i<mScoreArray.size(); i++) editor.putString(Integer.toString(i), mScoreArray.get(i));
		editor.putInt("HowMany", mScoreArray.size());
		editor.commit();

	}
	private void receiveRecentLoc(){
		SharedPreferences preference = getSharedPreferences("RescentLoc",  MODE_PRIVATE);
		HowManyReCentLoc = preference.getInt("HowMany", 0);
		for(int i=0; i<HowManyReCentLoc; i++){
			mScoreArray.add(preference.getString(Integer.toString(i), ""));
		}
	}
	private AdapterView.OnItemClickListener onListViewItemClickListener = new AdapterView.OnItemClickListener() {
		public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
		}
	};
	private AdapterView.OnItemLongClickListener onListViewItemLongClickListener = new AdapterView.OnItemLongClickListener() {
		public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
			// TODO Auto-generated method stub
			return false;
		}
	};*/	
}