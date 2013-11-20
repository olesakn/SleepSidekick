package com.cis368.sleepsidekick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class StatisticsActivity extends Activity {


	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.statistics);
	}
	
	@Override
	public void onBackPressed() {
		Intent i = new Intent(this, MainActivity.class);
		i.putExtra("tab", 1);
		startActivity(i);
		finish(); // removes this activity from memory
	}

}
