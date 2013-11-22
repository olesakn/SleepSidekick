package com.cis368.sleepsidekick;

import java.text.DecimalFormat;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class HomeActivity extends Fragment implements OnClickListener {
	
	private View rootView;
	private Button goToSleepButton, scheduleButton, statsButton;
	private TextView info; 
	
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		rootView = inflater.inflate(R.layout.fragment_home, container, false);

		goToSleepButton = (Button) rootView.findViewById(R.id.home_button_go_to_sleep);
		goToSleepButton.setOnClickListener(this);
		
		scheduleButton = (Button) rootView.findViewById(R.id.home_button_schedule);
		scheduleButton.setOnClickListener(this);
		
		statsButton = (Button) rootView.findViewById(R.id.home_button_stats);
		statsButton.setOnClickListener(this);
		
		info = (TextView) rootView.findViewById(R.id.home_text_view_information);
		if (MainActivity.alarms.size() > 0) {
			Alarm a = MainActivity.alarms.get(0);
			String am_pm = "PM";
			if (a.isAm())
				am_pm = "AM";
			info.setText("Alarm set for " + a.getHour() + ":" + a.getMinute() + " " + am_pm + 
					   "\nThursday, February 21\n16 hours, 26 mins from now");
			
		}
		else
			info.setText("No alarms currently set\n\n ");
		
		
		return rootView;
	}


	@Override
	public void onClick(View v) {
		
		if (v == goToSleepButton) {
			if (MainActivity.alarms.size() > 0 && MainActivity.alarms.size() > 0) {
				Intent i = new Intent(v.getContext(), SleepingActivity.class);
				startActivity(i);
				getActivity().finish();
			}
			else
				Toast.makeText(v.getContext(), "No alarms or sleep aids are set!", Toast.LENGTH_LONG).show();
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
