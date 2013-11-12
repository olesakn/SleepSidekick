package com.cis368.sleepsidekick;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class SleepAidsActivity extends Fragment {
	
	public static final String ARG_SECTION_NUMBER = "section_number";
	private Button createButton;
	private ListView listView;
	private SleepAidsCustomAdapter adapter;
	

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_sleep_aids, container, false);

		// Build GUI here

		createButton = (Button) rootView.findViewById(R.id.sleep_aids_button_create);
		createButton.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i = new Intent(v.getContext(), CreateSleepAidActivity.class);
				v.getContext().startActivity(i);
			}
		});
		
		listView = (ListView) rootView.findViewById(R.id.sleep_aids_list_view);
		adapter = new SleepAidsCustomAdapter(rootView.getContext(), MainActivity.sleepAids);
		listView.setAdapter(adapter);
		listView.setDivider(null);
		listView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> adapter, View view, int pos, long id) {
				editSleepAid(view.getContext(), pos);
			}		
		});
		
		return rootView;
	}
	
	private void editSleepAid(Context context, int pos) {
		// TODO
	}
}
