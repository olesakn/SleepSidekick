package com.cis368.sleepsidekick;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class HomeActivity extends Fragment {

	public static final String ARG_SECTION_NUMBER = "section_number";

	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_home, container, false);

		
		// Build GUI here
		final Button goToSleepButton = (Button) rootView.findViewById(R.id.home_button_go_to_sleep);
		goToSleepButton.setOnClickListener(new OnClickListener() {
			public void onClick(View arg0) {
				goToSleepButton.setText("Corey is a loser");
				
				
				
			}
		});
		return rootView;
	}
}
