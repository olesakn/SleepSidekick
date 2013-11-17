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
		
		snooze = (Button) findViewById(R.id.wake_up_button_snooze);
		snooze.setOnClickListener(new OnClickListener(){
			public void onClick(View v) {
				startActivity(new Intent(v.getContext(), SleepingActivity.class));
				finish();
			}
		});
		awake = (Button) findViewById(R.id.wake_up_button_wake_up);
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