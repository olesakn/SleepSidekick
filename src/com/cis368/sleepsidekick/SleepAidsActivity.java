package com.cis368.sleepsidekick;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
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
import android.widget.TextView;

public class SleepAidsActivity extends Fragment {
	
	public static final String ARG_SECTION_NUMBER = "section_number";
	private Button createButton;
	private ListView listView;
	private SleepAidsCustomAdapter adapter;
	private View rootView;
	private TextView noneCreated;
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_sleep_aids, container, false);

		noneCreated = (TextView) rootView.findViewById(R.id.sleep_aids_text_none_created);
		if (MainActivity.sleepAids.size() == 0)
			noneCreated.setText("No sleeps aids have been created");
		else
			noneCreated.setText("");
		
		// Create List View
		listView = (ListView) rootView.findViewById(R.id.sleep_aids_list_view);
		adapter = new SleepAidsCustomAdapter(rootView.getContext(), MainActivity.sleepAids);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> a, View view, int pos, long id) {
				SleepAid s = MainActivity.sleepAids.get(pos);
				s.setEnabled(!s.isEnabled());
				adapter.notifyDataSetChanged();
			}		
		});
		registerForContextMenu(listView);
		
		// Create Button
		createButton = (Button) rootView.findViewById(R.id.sleep_aids_button_create);
		createButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), CreateSleepAidActivity.class);
				v.getContext().startActivity(i);
				getActivity().finish();
			}
		});
		
		return rootView;
	}
	
	private void editSleepAid(int pos) {
		Intent i = new Intent(rootView.getContext(), CreateSleepAidActivity.class);
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
        menu.add(Menu.NONE, R.id.menu_alarm_customize_dates, Menu.NONE, "Customize Dates");
	}
	
	
	/****************************************************************
	 * On List Item Selected Context Menu
	 ****************************************************************/
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		AdapterContextMenuInfo info = (AdapterContextMenuInfo) item.getMenuInfo();
		if (item.getItemId() == R.id.menu_alarm_edit) {
			editSleepAid(info.position);
			return true;
		}
		else if (item.getItemId() == R.id.menu_alarm_delete) {
			MainActivity.sleepAids.remove(info.position);
			if (MainActivity.sleepAids.size() == 0)
				noneCreated.setText("No sleeps aids have been created");
			adapter.notifyDataSetChanged();
			return true;
		}
		else if (item.getItemId() == R.id.menu_alarm_customize_dates) {
			startActivity(new Intent(rootView.getContext(), ScheduleActivity.class));
			getActivity().finish();
			return true;
		}
		else
			return super.onContextItemSelected(item);
	}
}
