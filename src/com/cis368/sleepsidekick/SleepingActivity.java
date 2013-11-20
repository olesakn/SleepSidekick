package com.cis368.sleepsidekick;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class SleepingActivity extends Activity implements OnClickListener {

	Button disableSA, disableAlarm;
	MediaPlayer mp;
	boolean screenStillActive;
	TextView SAInfo, alarmInfo;
	
	private Handler handler = new Handler() {
		/* (non-Javadoc)
		 * @see android.os.Handler#handleMessage(android.os.Message)
		 */
		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0: 
				if (screenStillActive) {
					mp.release();
					startActivity(new Intent(getApplicationContext(), TaskMathProblemActivity.class));
					finish();
				}
				break;
			}
			super.handleMessage(msg);
		}
	};
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.sleeping);
		screenStillActive = true;
		SAInfo = (TextView) findViewById(R.id.sleeping_text_sleep_aid_info);
		alarmInfo = (TextView) findViewById(R.id.sleeping_text_alarm_info);
		
		disableSA = (Button) findViewById(R.id.sleeping_button_sleep_air);
		disableSA.setOnClickListener(this);
		
		disableAlarm = (Button) findViewById(R.id.sleeping_button_alarm2);
		disableAlarm.setOnClickListener(this);


		mp = MediaPlayer.create(this, R.raw.sleeping_tone);
		mp.setOnCompletionListener(new OnCompletionListener() {
			public void onCompletion(MediaPlayer mp) {
				mp.release();
			}
		});  
		
		Message msg = new Message();
        msg.what = 0;
		if ((getIntent().getBooleanExtra("snooze", false))) {
			SAInfo.setText("");
			disableSA.setVisibility(View.GONE);
			alarmInfo.setText("Snoozing for 5 more minutes");
	        handler.sendMessageDelayed(msg, 10000);
		}
		else {
			mp.start();
	        handler.sendMessageDelayed(msg, 20000);
		}
	
		// this is for testing purposes
		// makes to alarm "go off" after 10 seconds
		
	}

	
	public void onClick(View v) {
		if (v == disableSA){
			mp.release();
			disableSA.setText("Sleep aid is muted");
		}
		if (v == disableAlarm) {
			onBackPressed();
		}
	}


	@Override
	public void onBackPressed() {
		screenStillActive = false;
		mp.release();
		Intent i = new Intent(this, MainActivity.class);
		i.putExtra("tab", 1);
		startActivity(i);
		finish(); // removes this activity from memory
		
	}
	
	
}
