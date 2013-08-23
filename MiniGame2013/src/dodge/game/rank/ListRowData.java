package dodge.game.rank;

import android.content.Context;

class ListRowData {
	private String name;
	private String score;
	private String country;
	public ListRowData(Context context, String name, String score, String country){
		this.name = name;
		this.score = score;
		this.country = country;
	}
	public String getName(){
		return this.name;
	}
	public String getScore(){
		return this.score;
	}
	public String getCountry(){
		return this.country;
	}
}