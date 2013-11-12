package com.cis368.sleepsidekick;

import java.util.ArrayList;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

public class SleepAidsCustomAdapter extends BaseAdapter {

	private ArrayList<SleepAid> sleepAids;
	private Context context;

	SleepAidsCustomAdapter (Context c, ArrayList<SleepAid> a) {
		sleepAids = a;
		context = c;
	}
	
	@Override
	public int getCount() {
		return sleepAids.size();
	}

	@Override
	public Object getItem(int index) {
		return sleepAids.get(index);
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
		CheckBox repeat = (CheckBox) v.findViewById(R.id.sleep_aid_list_row_checkbox);
		

		SleepAid s = MainActivity.sleepAids.get(index);
		
		name.setText(s.getName());
		other.setText(s.getSound() + "\t   " + s.getDays());
		repeat.setChecked(s.isEnabled());
		
		return v;
	}
}
