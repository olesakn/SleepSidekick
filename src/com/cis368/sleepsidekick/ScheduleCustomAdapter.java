package com.cis368.sleepsidekick;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class ScheduleCustomAdapter extends BaseAdapter {
	
	private Context context;
	ArrayList<Object> data;

	ScheduleCustomAdapter(Context c, ArrayList<Object> d) {
		context = c;
		data = d;
	}
	
	@Override
	public int getCount() {
		return data.size();
	}

	@Override
	public Object getItem(int index) {
		return data.get(index);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int index, View v, ViewGroup parent) {
		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_row_sleep_aid, null);
		}
		
		TextView name = (TextView) v.findViewById(R.id.sleep_aid_list_row_name);
		TextView other = (TextView) v.findViewById(R.id.sleep_aid_list_row_sound_and_days);
		CheckBox enabled = (CheckBox) v.findViewById(R.id.sleep_aid_list_row_checkbox);
		
		try {
			Alarm alarm = (Alarm) data.get(index);
			
			String am_pm = " PM";
			if (alarm.isAm())
				am_pm = " AM";
			name.setText(alarm.getName());
			String hourStr = new DecimalFormat("00").format(Integer.parseInt(alarm.getHour()));
			String minStr = new DecimalFormat("00").format(Integer.parseInt(alarm.getMinute()));
			other.setText(hourStr + ":" + minStr + " " + am_pm + "\t  " + alarm.getDays());
			Calendar c = ScheduleActivity.calendar;
			String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date(c.get(Calendar.YEAR)-1900, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)));
			if (alarm.isEnabled()) {
				if (alarm.getDisabledDates().contains(date))
					enabled.setChecked(false);
				else
					enabled.setChecked(true);
			}
			else
				enabled.setChecked(false);
			
		} catch (Exception e) {
			try {
				SleepAid s = (SleepAid) data.get(index);
				name.setText(s.getName());
				other.setText(s.getSound() + "\t  " + s.getDays());
				Calendar c = ScheduleActivity.calendar;
				String date = new SimpleDateFormat("MM/dd/yyyy").format(new Date(c.get(Calendar.YEAR)-1900, c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH)));
				if (s.isEnabled()) {
					if (s.getDisabledDates().contains(date))
						enabled.setChecked(false);
					else
						enabled.setChecked(true);
				}
				else
					enabled.setChecked(false);
			} catch (Exception f) {}
		}	
		return v;
	}
}