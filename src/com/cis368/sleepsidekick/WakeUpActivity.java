package com.cis368.sleepsidekick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class WakeUpActivity extends Activity {

	Button awake, snooze; 

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.wake_up);
		
		snooze = new Button(this);
		snooze.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(), SleepingActivity.class));
				finish();
			}
		});
		awake = new Button(this);
		awake.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), MainActivity.class);
				i.putExtra("tab", 1);
				startActivity(i);
				finish();
			}
		});
	}
}