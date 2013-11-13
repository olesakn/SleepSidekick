package com.cis368.sleepsidekick;

import java.util.ArrayList;

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
			other.setText(alarm.getHour() + ":" + alarm.getMinute() + am_pm);
			enabled.setChecked(alarm.isEnabled());
			
		} catch (Exception e) {
			try {
				SleepAid s = MainActivity.sleepAids.get(index);
				name.setText(s.getName());
				other.setText(s.getSound());
				enabled.setChecked(s.isEnabled());
			} catch (Exception f) {}
		}
		
		return v;
	}

}
