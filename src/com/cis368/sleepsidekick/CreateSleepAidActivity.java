package com.cis368.sleepsidekick;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;

public class CreateSleepAidActivity extends Activity {


	protected void onCreate(Bundle savedInstanceState) {
	
	super.onCreate(savedInstanceState);
	setContentView(R.layout.create_sleep_aid);
	
	ActionBar actionBar = this.getActionBar();
	actionBar.setDisplayShowHomeEnabled(false);              
	actionBar.setDisplayShowTitleEnabled(false);
	
	}
}
