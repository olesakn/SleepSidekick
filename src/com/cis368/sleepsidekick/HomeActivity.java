package com.cis368.sleepsidekick;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeActivity extends Fragment implements OnClickListener {
	
	//public static final String ARG_SECTION_NUMBER = "section_number";
	private View rootView;
	private Button goToSleepButton, scheduleButton, statsButton;

	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_home, container, false);

		goToSleepButton = (Button) rootView.findViewById(R.id.home_button_go_to_sleep);
		goToSleepButton.setOnClickListener(this);
		
		scheduleButton = (Button) rootView.findViewById(R.id.home_button_schedule);
		scheduleButton.setOnClickListener(this);
		
		statsButton = (Button) rootView.findViewById(R.id.home_button_stats);
		statsButton.setOnClickListener(this);
		
		return rootView;
	}


	@Override
	public void onClick(View v) {
		
		if (v == goToSleepButton) {
			Intent i = new Intent(v.getContext(), SleepingActivity.class);
			startActivity(i);
			getActivity().finish();
		}
		
		if (v == scheduleButton) {
			Intent i = new Intent(v.getContext(), ScheduleActivity.class);
			startActivity(i);
			getActivity().finish();
		}
		
		if (v == statsButton) {
			Intent i = new Intent(v.getContext(), StatisticsActivity.class);
			startActivity(i);
			getActivity().finish();
			
		}
	}
}
