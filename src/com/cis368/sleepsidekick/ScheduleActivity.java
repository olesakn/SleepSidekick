package com.cis368.sleepsidekick;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ListView;
import android.widget.TextView;

public class ScheduleActivity extends Activity {
	
	private CalendarView calendarView;
	private ListView listView;
	private ScheduleCustomAdapter adapter;
	private ArrayList<Object> activities;
	private Calendar calendar;
	private TextView noActivitiesToday;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule);

		listView = (ListView) findViewById(R.id.schedule_list_view);
		calendar = Calendar.getInstance();
		activities = getActivitiesForDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH),calendar.get(Calendar.DAY_OF_MONTH));
		noActivitiesToday = ((TextView) findViewById(R.id.schedule__text_no_tasks_today));
		noActivitiesToday.setText("");
		if (activities.size() == 0)
			noActivitiesToday.setText("No activities on this date");
		adapter = new ScheduleCustomAdapter(this, activities);
		listView.setAdapter(adapter);
		listView.refreshDrawableState();
		adapter.notifyDataSetChanged();
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View v, int pos,long arg3) {
				try {
					Alarm alarm = (Alarm) activities.get(pos);
					alarm.setEnabled(!alarm.isEnabled());
					adapter.notifyDataSetChanged();
					
				} catch (Exception e) { try {
						SleepAid sa = (SleepAid) activities.get(pos);
						sa.setEnabled(!sa.isEnabled());
						adapter.notifyDataSetChanged();
				}catch (Exception f){}}
			}
		});
		
		calendarView = (CalendarView) findViewById(R.id.schedule_calendar_view);
		calendarView.setOnDateChangeListener(new OnDateChangeListener() {
			public void onSelectedDayChange(CalendarView v, int y, int m, int d) {
				activities = getActivitiesForDate(y,m,d);
				adapter = new ScheduleCustomAdapter(v.getContext(), activities);
				listView.setAdapter(adapter);
				listView.refreshDrawableState();
				adapter.notifyDataSetChanged();
			}
		});
	}

	private ArrayList<Object> getActivitiesForDate(int year, int month, int day) {
		Calendar c = new GregorianCalendar();
        c.set(year, month, day);
        String dateStr = month + "/" + day + "/" + year;
        String dayOfWeek = getDayOfWeek(c.get(Calendar.DAY_OF_WEEK));
		
		ArrayList<Object> matches = new ArrayList<Object>();
		for (Alarm a: MainActivity.alarms) {
			if (convertToDaysString(a.getDays()).contains(dayOfWeek))
				matches.add(a);
			if (a.getDate().equals(dateStr))
				matches.add(a);
		}
		
		for(SleepAid s : MainActivity.sleepAids) {
			if (convertToDaysString(s.getDays()).contains(dayOfWeek))
				matches.add(s);
			if (s.getDate().equals(dateStr))
				matches.add(s);
		}
		return matches;
	}
	
	private String convertToDaysString(String str) {
		if (str.equals("[Weekends]"))
			return "[Su, Sa]";
		else if (str.equals("[Weekdays]"))
			return "[M, Tu, W, Th, F]";
		else if (str.equals("[Everyday]"))
			return "[Su, M, Tu, W, Th, F, Sa]";
		else
			return str;
	}
	
	private String getDayOfWeek(int var) {
		switch(var) {
		case Calendar.SUNDAY:
			return "Su";
		case Calendar.MONDAY:
			return "M";
		case Calendar.TUESDAY:
			return "Tu";
		case Calendar.WEDNESDAY:
			return "W";
		case Calendar.THURSDAY:
			return "Th";
		case Calendar.FRIDAY:
			return "F";
		case Calendar.SATURDAY:
			return "Sa";
		default:
			return "";
		}
	}

	@Override
	public void onBackPressed() {
		Intent i = new Intent(this, MainActivity.class);
		i.putExtra("tab", 1);
		startActivity(i);
		finish(); // removes this activity from memory
	}
	
	
}
