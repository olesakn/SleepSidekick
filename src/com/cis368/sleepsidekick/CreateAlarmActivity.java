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
import android.widget.CompoundButton;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.TimePicker;
import android.widget.ToggleButton;

public class CreateAlarmActivity extends FragmentActivity {
	
	public static String date;
	private Button saveButton;
	private static Button dateButton;
	private Spinner soundSpinner, taskSpinner, snoozeSpinner;
	private EditText nameText;
	private TimePicker timePicker;
	private CheckBox repeatCheckBox;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.create_alarm);

		nameText = (EditText) findViewById(R.id.create_alarm_name);
		timePicker = (TimePicker) findViewById(R.id.create_alarm_time_picker);
		repeatCheckBox = (CheckBox) findViewById(R.id.create_alarm_repeat_checkbox);
		saveButton = (Button) findViewById(R.id.create_alarm_button_save);
		dateButton = (Button) findViewById(R.id.create_alarm_button_date);
		dateButton.setText("Set Date");
		soundSpinner = (Spinner) findViewById(R.id.create_alarm_spinner_sound);
		taskSpinner = (Spinner) findViewById(R.id.create_alarm_spinner_task);
		snoozeSpinner = (Spinner) findViewById(R.id.create_alarm_spinner_snooze);
		initializeGUIComponents();
		
		
		int edit_position = getIntent().getIntExtra("edit_position", -1);
		if (edit_position >= 0) {
			Alarm a = MainActivity.alarms.get(edit_position);
			nameText.setText(a.getName());
			dateButton.setText(a.getDate());
			repeatCheckBox.setChecked(a.isRepeat());
			int hour = Integer.parseInt(a.getHour());
			if (a.isAm()) {
				if (hour == 12)
					timePicker.setCurrentHour(0);
			}
			else
				timePicker.setCurrentHour(hour+12);
			
			timePicker.setCurrentMinute(Integer.parseInt(a.getMinute()));
			String days = a.getDays();
			
			if (a.isRepeat()) {
				if (days.equals("[Everyday]"))
					days = "Su,M,Tu,W,Th,F,Sa";
				if (days.equals("[Weekdays]"))
					days = "M,Tu,W,Th,F";
				if (days.equals("[Weekends]"))
					days = "Su,Sa";
				if (days.contains("Su"))
					((ToggleButton) findViewById(R.id.create_alarm_toggle_Su)).setChecked(true);
				if (days.contains("M"))
					((ToggleButton) findViewById(R.id.create_alarm_toggle_M)).setChecked(true);
				if (days.contains("Tu"))
					((ToggleButton) findViewById(R.id.create_alarm_toggle_Tu)).setChecked(true);
				if (days.contains("W"))
					((ToggleButton) findViewById(R.id.create_alarm_toggle_W)).setChecked(true);
				if (days.contains("Th"))
					((ToggleButton) findViewById(R.id.create_alarm_toggle_Th)).setChecked(true);
				if (days.contains("F"))
					((ToggleButton) findViewById(R.id.create_alarm_toggle_F)).setChecked(true);
				if (days.contains("Sa"))
					((ToggleButton) findViewById(R.id.create_alarm_toggle_Sa)).setChecked(true);
			}
		}
	}

	
	private void initializeGUIComponents() {

		// SAVE BUTTON LISTENER
		saveButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Alarm alarm = new Alarm();
				alarm.setName(nameText.getText().toString());
				if (nameText.getText().length() == 0)
					alarm.setName("Alarm");
				alarm.setDate(dateButton.getText().toString());
				if (dateButton.getText().equals("Set Date")) {
					SimpleDateFormat df = new SimpleDateFormat("MM/dd/yyyy");
					Calendar c = Calendar.getInstance();
					String d = df.format(new Date(c.get(Calendar.YEAR)-1900, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)));
					alarm.setDate(d);
				} 
				int hour = timePicker.getCurrentHour();
				alarm.setAm(true);
				if (hour == 12)
					alarm.setAm(false);
				if (hour == 0)
					hour = 12;
				if (hour > 12) {
					hour -= 12;
					alarm.setAm(false);
				}	
				alarm.setHour(hour + "");
				alarm.setMinute(timePicker.getCurrentMinute().toString());
				alarm.setRepeat(repeatCheckBox.isChecked());
				alarm.setSnooze(snoozeSpinner.getSelectedItem().toString());
				alarm.setSnooze(taskSpinner.getSelectedItem().toString());
				alarm.setSnooze(soundSpinner.getSelectedItem().toString());
				alarm.setDays(getRepeatedDays());
				
				int edit_position = getIntent().getIntExtra("edit_position", -1);
				if (edit_position >= 0)
					MainActivity.alarms.set(edit_position, alarm);
				else
					MainActivity.alarms.add(alarm);

				//finish();
				onBackPressed(); // return to alarms screen
			}
		});
		
		// CHANGE DATE BUTTON LISTENER
		dateButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				repeatCheckBox.setChecked(false);
				toggleDaysEnabled(false, false);
				showDatePickerDialog(v);
			}
		});



		// SPINNER LISTS
		List<String> list1 = new ArrayList<String>();
		list1.add("Default");
		list1.add("Music");
		list1.add("Nature");
		list1.add("White Noise");
		ArrayAdapter<String> dataAdapter1 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list1);
		dataAdapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		soundSpinner.setAdapter(dataAdapter1);	

		List<String> list2 = new ArrayList<String>();
		list2.add("None");
		list2.add("Math Problem");
		list2.add("Puzzle");
		list2.add("Activity");
		ArrayAdapter<String> dataAdapter2 = new ArrayAdapter<String>(this,
				android.R.layout.simple_spinner_item, list2);
		dataAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		taskSpinner.setAdapter(dataAdapter2);

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
		
		// Repeat Check Box
		repeatCheckBox.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(CompoundButton cb, boolean checked) {
				if (checked) {
					toggleDaysEnabled(true, false);
					dateButton.setText("Set Date");
				}
				else {
					toggleDaysEnabled(false, false);
					Calendar c = Calendar.getInstance();
					setCurrentDate(c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH), c.get(Calendar.YEAR));
				}
			}
		});
		toggleDaysEnabled(false, false);
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
	
	/*********************************************************************
	 * Get String representation of the repeated days
	 ********************************************************************/
	private ArrayList<String> getRepeatedDays() {
		ArrayList<String> days = new ArrayList<String>();
		if (repeatCheckBox.isChecked()) {
			int count = 0;
			if (((ToggleButton)findViewById(R.id.create_alarm_toggle_Su)).isChecked()) {
				days.add("Su");
				count += 20;
			}
			if (((ToggleButton)findViewById(R.id.create_alarm_toggle_M)).isChecked()) {
				days.add("M");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_alarm_toggle_Tu)).isChecked()) {
				days.add("Tu");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_alarm_toggle_W)).isChecked()) {
				days.add("W");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_alarm_toggle_Th)).isChecked()) {
				days.add("Th");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_alarm_toggle_F)).isChecked()) {
				days.add("F");
				count++;
			}
			if (((ToggleButton)findViewById(R.id.create_alarm_toggle_Sa)).isChecked()) {
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
	
	private void toggleDaysEnabled(boolean value, boolean checked) {
		((ToggleButton) findViewById(R.id.create_alarm_toggle_Su)).setEnabled(value);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_Su)).setChecked(checked);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_M)).setEnabled(value);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_M)).setChecked(checked);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_Tu)).setEnabled(value);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_Tu)).setChecked(checked);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_W)).setEnabled(value);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_W)).setChecked(checked);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_Th)).setEnabled(value);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_Th)).setChecked(checked);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_F)).setEnabled(value);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_F)).setChecked(checked);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_Sa)).setEnabled(value);
		((ToggleButton) findViewById(R.id.create_alarm_toggle_Sa)).setChecked(checked);
	}


	@Override
	public void onBackPressed() {
		Intent i = new Intent(this, MainActivity.class);
		i.putExtra("tab", 2);
		startActivity(i);
		finish(); // removes this activity from memory
	}
	
	
	
}
