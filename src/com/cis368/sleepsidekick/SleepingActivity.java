package com.cis368.sleepsidekick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class SleepingActivity extends Activity implements OnClickListener {

	Button disableSA, disableAlarm;
	
	private Handler handler = new Handler() {
		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				startActivity(new Intent(getApplicationContext(), TaskMathProblemActivity.class));
				finish();
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleeping);
	
		
		disableSA = (Button) findViewById(R.id.sleeping_button_sleep_air);
		disableSA.setOnClickListener(this);
		
		disableAlarm = (Button) findViewById(R.id.sleeping_button_alarm);
		disableAlarm.setOnClickListener(this);
		
		// this is for testing purposes
		// switches to "alarm going off" after 10 seconds
		Message msg = new Message();
        msg.what = 0;
        handler.sendMessageDelayed(msg, 10000);
	}

	

	public void onClick(View v) {
		if (v == disableSA){
			
		}
		if (v == disableAlarm) {
			Intent i = new Intent(this, MainActivity.class);
			
		}
	}
}
