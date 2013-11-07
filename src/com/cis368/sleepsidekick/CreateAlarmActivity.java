package com.cis368.sleepsidekick;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import android.app.ActionBar;
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TimePicker;

public class CreateAlarmActivity extends FragmentActivity {
	
	public static String date;
	
	private Button saveButton;

	private static Button dateButton;
	private Spinner soundSpinner, taskSpinner, snoozeSpinner;
	private EditText nameText;
	private Calendar calendar;
	private TimePicker timePicker;
	private CheckBox repeatCheckBox;

	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_alarm);
		getActionBar().setTitle("Create New Alarm");

		
		nameText = (EditText) findViewById(R.id.create_alarm_name);
		timePicker = (TimePicker) findViewById(R.id.create_alarm_time_picker);
		repeatCheckBox = (CheckBox) findViewById(R.id.create_alarm_repeat_checkbox);
		
		
		// Buttons
		saveButton = (Button) findViewById(R.id.create_alarm_button_save);
		saveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), MainActivity.class);
				Alarm alarm = new Alarm();
				alarm.setName(nameText.getText().toString());
				alarm.setDate(dateButton.getText().toString());
				int hour = timePicker.getCurrentHour();
				if (hour >= 12) {
					hour -= 12;
					alarm.setAm(false);
				}
				else
					alarm.setAm(true);
				alarm.setHour(timePicker.getCurrentHour().toString());
				alarm.setMinute(timePicker.getCurrentMinute().toString());
				alarm.setRepeat(repeatCheckBox.isChecked());
				alarm.setSnooze(snoozeSpinner.getSelectedItem().toString());
				alarm.setSnooze(taskSpinner.getSelectedItem().toString());
				alarm.setSnooze(soundSpinner.getSelectedItem().toString());
				i.putExtra("alarm", alarm);
				startActivity(i);
				finish(); // stops the current activity (prevents back button from bringing you back here)
			}
		});
		calendar = Calendar.getInstance();
		dateButton = (Button) findViewById(R.id.create_alarm_button_date);
		setCurrentDate(calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.YEAR));
		dateButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				showDatePickerDialog(v);
			}
		});

		
		// Spinners
		soundSpinner = (Spinner) findViewById(R.id.create_alarm_spinner_sound);
		List<String> list1 = new ArrayList<String>();
		list1.add("Music");
		list1.add("Nature");
		list1.add("White Noise");
		list1.add("None");
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list1);
		dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		soundSpinner.setAdapter(dataAdapter1);	
		
		taskSpinner = (Spinner) findViewById(R.id.create_alarm_spinner_task);
		List<String> list2 = new ArrayList<String>();
		list2.add("Math Problem");
		list2.add("Puzzle");
		list2.add("Activity");
		list2.add("None");
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list2);
		dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		taskSpinner.setAdapter(dataAdapter2);
		
		snoozeSpinner = (Spinner) findViewById(R.id.create_alarm_spinner_snooze);
		List<String> list3 = new ArrayList<String>();
		list3.add("None");
		list3.add("1 Minute");
		list3.add("2 Minutes");
		list3.add("3 Minutes");
		list3.add("5 Minutes");
		list3.add("10 Minutes");
		list3.add("15 Minutes");
		list3.add("30 Minutes");
		list3.add("1 Hour");
		ArrayAdapter<String> dataAdapter3 = new ArrayAdapter<String>(this,
			android.R.layout.simple_spinner_item, list3);
		dataAdapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		snoozeSpinner.setAdapter(dataAdapter3);
		
	}

	
	public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

		@Override
			public Dialog onCreateDialog(Bundle savedInstanceState) {
				// Use the current date as the default date in the picker
				final Calendar c = Calendar.getInstance();
				int year = c.get(Calendar.YEAR);
				int month = c.get(Calendar.MONTH);
				int day = c.get(Calendar.DAY_OF_MONTH);
			
			// Create a new instance of DatePickerDialog and return it
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
		CharSequence text = df.format(new Date(y-1900, m, d)).toString();
		dateButton.setText(text);
	}
	
}
