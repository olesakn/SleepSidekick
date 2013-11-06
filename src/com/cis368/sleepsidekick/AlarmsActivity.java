package com.cis368.sleepsidekick;

import android.support.v4.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class AlarmsActivity extends Fragment {
	
	public static final String ARG_SECTION_NUMBER = "section_number";

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		final View rootView = inflater.inflate(R.layout.fragment_alarms, container, false);
		
		
		// Build GUI here
		
		
		Button createNewAlarm = (Button) rootView.findViewById(R.id.alarms_button_create_new_alarm);
		createNewAlarm.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				Intent i= new Intent(v.getContext(), CreateAlarmActivity.class);
				v.getContext().startActivity(i);
			}
		});
		
		return rootView;
	}
}
