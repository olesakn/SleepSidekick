package com.cis368.sleepsidekick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SleepingActivity extends Activity implements OnClickListener {

	Button disableSA, disableAlarm;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleeping);
	
		
		disableSA = (Button) findViewById(R.id.sleeping_button_sleep_air);
		disableSA.setOnClickListener(this);
		
		disableAlarm = (Button) findViewById(R.id.sleeping_button_alarm);
		disableAlarm.setOnClickListener(this);
		
		
		
		// this is for testing purposes
		// switches to "alarm going off" after 10 seconds
		try {
			Thread.sleep(10000);
		} catch (InterruptedException e) {}
		startActivity(new Intent(this, TaskMathProblemActivity.class));
		finish();
	}

	public void onClick(View v) {
		if (v == disableSA){
			
		}
		if (v == disableAlarm) {
			Intent i = new Intent(this, MainActivity.class);
			
		}
	}
}
