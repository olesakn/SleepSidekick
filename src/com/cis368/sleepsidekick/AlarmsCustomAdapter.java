package com.cis368.sleepsidekick;



import java.text.DecimalFormat;
import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class AlarmsCustomAdapter extends BaseAdapter {

	private ArrayList<Alarm> alarms;
	private Context context;

	AlarmsCustomAdapter (Context c, ArrayList<Alarm> a) {
		alarms = a;
		context = c;
	}
	
	@Override
	public int getCount() {
		return alarms.size();
	}

	@Override
	public Object getItem(int index) {
		return alarms.get(index);
	}

	@Override
	public long getItemId(int id) {
		return id;
	}

	@Override
	public View getView(int index, View v, ViewGroup parent) {

		if (v == null) {
			LayoutInflater vi = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(R.layout.list_row_alarm, null);
		}
		
		final Alarm alarm = alarms.get(index);

		TextView name = (TextView) v.findViewById(R.id.alarm_list_row_name);
		TextView time = (TextView) v.findViewById(R.id.alarm_list_row_time);
		CheckBox enabled = (CheckBox) v.findViewById(R.id.alarm_list_row_checkbox);
		String am_pm = " PM";
		if (alarm.isAm())
			am_pm = " AM";
		name.setText(alarm.getName());
		String hourStr = new DecimalFormat("00").format(Integer.parseInt(alarm.getHour()));
		String minStr = new DecimalFormat("00").format(Integer.parseInt(alarm.getMinute()));
		time.setText(hourStr + ":" + minStr + " " +  am_pm + "\t  " + alarm.getDays());
		enabled.setChecked(alarm.isEnabled());

		return v;
	}

}	

