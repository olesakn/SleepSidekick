package com.cis368.sleepsidekick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ToggleButton;

public class CreateSleepAidActivity extends FragmentActivity implements OnSeekBarChangeListener {

	private TextView name;
	private static Button dateButton;
	private Button saveButton;
	private Spinner soundSpinner;
	private SeekBar volumeBar, brightnessBar;
	private CheckBox repeatCheckBox;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_sleep_aid);
	
		name = (TextView) this.findViewById(R.id.create_sleep_aid_name);
		dateButton = (Button) this.findViewById(R.id.create_sleep_aid_button_date);
		saveButton = (Button) this.findViewById(R.id.create_sleep_aid_button_save);
		soundSpinner = (Spinner) this.findViewById(R.id.create_sleep_aid_spinner_sound);
		volumeBar = (SeekBar) this.findViewById(R.id.create_sleep_aid_seekbar_volume);
		brightnessBar = (SeekBar) this.findViewById(R.id.create_sleep_aid_seekbar_brightness);
		repeatCheckBox = (CheckBox) this.findViewById(R.id.create_sleep_aid_checkbox_repeat);
		initializeGUIComponents();

		int edit_position = getIntent().getIntExtra("edit_position", -1);
		if (edit_position >= 0) {	
			SleepAid s = MainActivity.sleepAids.get(edit_position);
			name.setText(s.getName());
			dateButton.setText(s.getDate());
			repeatCheckBox.setChecked(s.isRepeat());
			String days = s.getDays();
			if (s.isRepeat()) {
				if (days.equals("[Everyday]"))
					days = "Su,M,Tu,W,Th,F,Sa";
				if (days.equals("[Weekdays]"))
					days = "M,Tu,W,Th,F";
				if (days.equals("[Weekends]"))
					days = "Su,Sa";
				if (days.contains("Su"))
					((ToggleButton) findViewById(R.id.create_sleep_aid_toggle_Su)).setChecked(true);
				if (days.contains("M"))
					((ToggleButton) findViewById(R.id.create_sleep_aid_toggle_M)).setChecked(true);
				if (days.contains("Tu"))
					((ToggleButton) findViewById(R.id.create_sleep_aid_toggle_Tu)).setChecked(true);
				if (days.contains("W"))
					((ToggleButton) findViewById(R.id.create_sleep_aid_toggle_W)).setChecked(true);
				if (days.contains("Th"))
					((ToggleButton) findViewById(R.id.create_sleep_aid_toggle_Th)).setChecked(true);
				if (days.contains("F"))
					((ToggleButton) findViewById(R.id.create_sleep_aid_toggle_F)).setChecked(true);
				if (days.contains("Sa"))
					((ToggleButton) findViewById(R.id.create_sleep_aid_toggle_Sa)).setChecked(true);
			}
		}
	}

	
	/********************************************
	 * Initialize GUI Components
	 *******************************************/
	private void initializeGUIComponents() {
		
		// Buttons
		saveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				SleepAid x = new SleepAid();
				x.setDate(dateButton.getText().toString());
				x.setName(name.getText().toString());
				if (x.getName().length() == 0)
					x.setName("Sleep Aid");
				x.setRepeat(repeatCheckBox.isChecked());
				x.setSound(soundSpinner.getSelectedItem().toString());
				x.setDays(getRepeatedDays());
				
				int edit_position = getIntent().getIntExtra("edit_position", -1);
				if (edit_position >= 0)
					MainActivity.sleepAids.set(edit_position, x);
				else
				MainActivity.sleepAids.add(x);
				onBackPressed(); // go back to sleep aids screen
			}
		});
		dateButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDatePickerDialog(v);
			}
		});

		// Spinners
		List<String> list = new ArrayList<String>();
		list.add("None");
		list.add("Music");
		list.add("Nature");
		list.add("White Noise");
		ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list);
		dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		soundSpinner.setAdapter(dataAdapter);
		
		// Seek Bars
		volumeBar.setOnSeekBarChangeListener(this);
		volumeBar.setProgress(50);
		brightnessBar.setOnSeekBarChangeListener(this);
		brightnessBar.setProgress(50);

	}

	/***************************************************
	 * On Seek Bar Changed Methods
	 **************************************************/
	public void onStartTrackingTouch(SeekBar sb) {}
	public void onStopTrackingTouch(SeekBar sb) {}
	public void onProgressChanged(SeekBar sb, int progress, boolean fromUser) {
		if (sb == volumeBar) {
			TextView volumeText = (TextView) findViewById(R.id.create_sleep_aid_text_volume);
			volumeText.setText("Volume - " + progress + "%");
		}
		if (sb == brightnessBar) {
			TextView brightnessText = (TextView) findViewById(R.id.create_sleep_aid_text_brightness);
			brightnessText.setText("Brightness - " + progress + "%");
		}
	}
	
	
	/***************************************
	 * Date Dialog Picker
	 **************************************/
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
		public Dialog onCreateDialog(Bundle savedInstanceState) {
			// Use the current date as the default date in the picker
			final Calendar c = Calendar.getInstance();
			int year = c.get(Calendar.YEAR);
			int month = c.get(Calendar.MONTH);
			int day = c.get(Calendar.DAY_OF_MONTH);
			return new DatePickerDialog(getActivity(), this, year, month, day);
		}
			
		public void onDateSet(DatePicker view, int year, int month, int day) {
			setCurrentDate(month, day, year); 
		}
	}
	

	public void showDatePickerDialog(View v) {
	    DialogFragment newFragment = new DatePickerFragment();
	    newFragment.show(getSupportFragmentManager(), "datePicker");
	}


	/*********************************************************************
	 * Set Current Date
	 ********************************************************************/
	private static void setCurrentDate(int m, int d, int y) {
		SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		dateButton.setText(df.format(new Date(y-1900, m, d)));
	}


	@Override
	public void onBackPressed() {
		Intent i = new Intent(this, MainActivity.class);
		i.putExtra("tab", 0);
		startActivity(i);
		finish(); // removes this activity from memory
	}
	
	
	/*********************************************************************
	 * Get String representation of the repeated days
	 ********************************************************************/
	private ArrayList<String> getRepeatedDays() {
		ArrayList<String> days = new ArrayList<String>();
		if (repeatCheckBox.isChecked()) {
			int count = 0;
			if (((ToggleButton)findViewById(R.id.create_sleep_aid_toggle_Su)).isChecked()) {
				days.add("Su");
				count += 20;
			}
			if (((ToggleButton)findViewById(R.id.create_sleep_aid_toggle_M)).isChecked()) {
				days.add("M");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_sleep_aid_toggle_Tu)).isChecked()) {
				days.add("Tu");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_sleep_aid_toggle_W)).isChecked()) {
				days.add("W");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_sleep_aid_toggle_Th)).isChecked()) {
				days.add("Th");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_sleep_aid_toggle_F)).isChecked()) {
				days.add("F");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_sleep_aid_toggle_Sa)).isChecked()) {
				days.add("Sa");
				count += 20;
			}
			if (count == 45 ) {
				days.clear();
				days.add("Everyday");
			}
			if (count == 5) {
				days.clear();
				days.add("Weekdays");
			}
			if (count == 40) {
				days.clear();
				days.add("Weekends");
			}
		}
		return days;
	}
	
	
	
}
