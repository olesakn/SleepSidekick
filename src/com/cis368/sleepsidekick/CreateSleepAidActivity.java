package com.cis368.sleepsidekick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.SeekBar;
import android.widget.Spinner;
import android.widget.TextView;

public class CreateSleepAidActivity extends Activity {

	TextView name;
	Button dateButton, saveButton;
	Spinner soundSpinner;
	SeekBar volumeBar, brightnessBar;
	CheckBox repeat;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_sleep_aid);
	
		name = (TextView) this.findViewById(R.id.create_sleep_aid_name);
		dateButton = (Button) this.findViewById(R.id.create_sleep_aid_button_date);
		saveButton = (Button) this.findViewById(R.id.create_sleep_aid_button_save);
		soundSpinner = (Spinner) this.findViewById(R.id.create_sleep_aid_spinner_sound);
		volumeBar = (SeekBar) this.findViewById(R.id.create_sleep_aid_seekbar_volume);
		brightnessBar = (SeekBar) this.findViewById(R.id.create_sleep_aid_seekbar_brightness);
		repeat = (CheckBox) this.findViewById(R.id.create_sleep_aid_checkbox_repeat);
		
		
		saveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SleepAid x = new SleepAid();
				x.setDate(dateButton.getText().toString());
				x.setName(name.getText().toString());
				x.setRepeat(repeat.isChecked());
				MainActivity.sleepAids.add(x);
				
				Intent i = new Intent(v.getContext(), MainActivity.class);
				i.putExtra("tab", 2);
				startActivity(i);
				finish(); // removes this activity from memory
			}
		});
	}
}
