package com.cis368.sleepsidekick;

import java.util.ArrayList;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class AlarmsActivity extends Fragment {
	
	public static final String ARG_SECTION_NUMBER = "section_number";
	private ListView listView;
	private AlarmsCustomAdapter adapter;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_alarms, container, false);
		
		if (MainActivity.alarms.size() == 0)
			MainActivity.alarms.add(new Alarm("Class", "11/15/2013", "7", "30", 
						true, "Music", "Math Problem", "5 Minutes", true, new int[7]));
		
		// List View
		listView = (ListView) rootView.findViewById(R.id.alarms_list_view);
		adapter = new AlarmsCustomAdapter(rootView.getContext(), MainActivity.alarms);
		listView.setAdapter(adapter);
		//listView.setDivider(null);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				editAlarm(rootView.getContext(), pos);
			}		
		});
		registerForContextMenu(listView);

		// Save Button
		Button createNewAlarm = (Button) rootView.findViewById(R.id.alarms_button_create_new_alarm);
		createNewAlarm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i= new Intent(v.getContext(), CreateAlarmActivity.class);
				v.getContext().startActivity(i);
			}
		});
		
		return rootView;
	}

	private void editAlarm(Context c, int pos) {
		Intent i = new Intent(c, CreateAlarmActivity.class);
		i.putExtra("edit_position", pos);
		startActivity(i);
	}
}
