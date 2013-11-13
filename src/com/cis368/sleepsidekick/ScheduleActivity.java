package com.cis368.sleepsidekick;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CalendarView;
import android.widget.CalendarView.OnDateChangeListener;
import android.widget.ListView;

public class ScheduleActivity extends Activity {
	
	private CalendarView calendar;
	private ListView listView;
	private ScheduleCustomAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.schedule);
		
		listView = (ListView) findViewById(R.id.schedule_list_view);
		listView.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				
			}
		});
		
		calendar = (CalendarView) findViewById(R.id.schedule_calendar_view);
		calendar.setOnDateChangeListener(new OnDateChangeListener() {
			public void onSelectedDayChange(CalendarView v, int y, int m, int d) {
				adapter = new ScheduleCustomAdapter(v.getContext(), getActivitiesForDate(y,m,d));
				listView.setAdapter(adapter);
				listView.refreshDrawableState();
				adapter.notifyDataSetChanged();
			}
		});
	}

	private ArrayList<Object> getActivitiesForDate(int month, int day, int year) {
		Calendar c = new GregorianCalendar();
        c.set(year, month, day);
        String dateStr = month + "/" + day + "/" + year;
        String dayOfWeek = getDayOfWeek(c.get(Calendar.DAY_OF_WEEK));
		
		ArrayList<Object> matches = new ArrayList<Object>();
		for (Alarm a: MainActivity.alarms) {
			if (a.getDays().contains(dayOfWeek))
				matches.add(a);
			if (a.getDate().equals(dateStr))
				matches.add(a);
		}
		for(SleepAid s : MainActivity.sleepAids) {
			if (s.getDays().contains(dayOfWeek))
				matches.add(s);
			if (s.getDate().equals(dateStr))
				matches.add(s);
		}
		return matches;
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
}
