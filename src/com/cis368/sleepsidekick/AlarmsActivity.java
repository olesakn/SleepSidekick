package com.cis368.sleepsidekick;

import java.util.ArrayList;
import java.util.Arrays;
import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.AdapterView.OnItemClickListener;

public class AlarmsActivity extends Fragment {
	
	public static final String ARG_SECTION_NUMBER = "section_number";
	private ListView listView;
	private AlarmsCustomAdapter adapter;
	View rootView;

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_alarms, container, false);
		
		
		if (MainActivity.alarms.size() == 0)
			MainActivity.alarms.add(new Alarm("Class", "11/15/2013", "7", "30", 
						true, "Music", "Math Problem", "5 Minutes", true, new ArrayList<String>() {{add("M"); add("W"); add("F");}}));
		
		// List View
		listView = (ListView) rootView.findViewById(R.id.alarms_list_view);
		adapter = new AlarmsCustomAdapter(getActivity(), MainActivity.alarms);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View view, int pos, long id) {
				Alarm alarm = MainActivity.alarms.get(pos);
				alarm.setEnabled(!alarm.isEnabled());
				adapter.notifyDataSetChanged();
				//editAlarm(pos);
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

	private void editAlarm(int pos) {
		Intent i = new Intent(rootView.getContext(), CreateAlarmActivity.class);
		i.putExtra("edit_position", pos);
		startActivity(i);
		getActivity().finish();
	}
	
	/****************************************************************
	 * Create Context Menu
	 ****************************************************************/
	public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);            
		menu.add(Menu.NONE, R.id.menu_alarm_edit, Menu.NONE, "Edit");
        menu.add(Menu.NONE, R.id.menu_alarm_delete, Menu.NONE, "Delete");
	}
	
	
	/****************************************************************
	 * On List Item Selected Context Menu
	 ****************************************************************/
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		if (item.getItemId() == R.id.menu_alarm_edit) {
			Intent i = new Intent(rootView.getContext(), CreateAlarmActivity.class);
			i.putExtra("edit_position", info.position);
			startActivity(i);
			getActivity().finish();
			return true;
		}
		else if (item.getItemId() == R.id.menu_alarm_delete) {
			MainActivity.alarms.remove(info.position);
			return true;
		}
		else
			return super.onContextItemSelected(item);
	}
	
}
