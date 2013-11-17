package com.cis368.sleepsidekick;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.NavUtils;
import android.support.v4.view.ViewPager;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class MainActivity extends FragmentActivity implements ActionBar.TabListener {
	
	/** Instance Variables */
	SectionsPagerAdapter mSectionsPagerAdapter;
	ViewPager viewPager;
	public static ArrayList<Alarm> alarms;
	public static ArrayList<SleepAid> sleepAids;
	

	/*********************************************** 
	 * On Create
	 **********************************************/
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		final ActionBar actionBar = getActionBar();
		actionBar.setDisplayShowHomeEnabled(false);              
		actionBar.setDisplayShowTitleEnabled(false);
		
		
		// Create the adapter that will return a fragment for each of the three
		// primary sections of the app.
		mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

		// Set up the ViewPager with the sections adapter.
		viewPager = (ViewPager) findViewById(R.id.pager);
		viewPager.setAdapter(mSectionsPagerAdapter);

		// Select corresponding tab when swiping 
		viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
			public void onPageSelected(int position) {
				actionBar.setSelectedNavigationItem(position);
			}
		});
		

		// Create tabs
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		actionBar.addTab(actionBar.newTab().setText("Sleep Aids").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Home").setTabListener(this));
		actionBar.addTab(actionBar.newTab().setText("Alarms").setTabListener(this));
		
		if (alarms == null)
			alarms = new ArrayList<Alarm>();
		if (sleepAids == null)
			sleepAids = new ArrayList<SleepAid>();
		Intent i = this.getIntent();
		int tab = i.getIntExtra("tab", 1);
		viewPager.setCurrentItem(tab);
	}

	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu
		// This also adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}


	public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		viewPager.setCurrentItem(tab.getPosition());
	}
	
	// Don't need to use these
	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction){}
	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {}

	/*******************************************************
	 * Sections Page Adapter
	 * returns a fragment corresponding to one of the tabs
	 ******************************************************/
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}
		
		public Fragment getItem(int position) {
			switch(position) {
				case 0:
					return new SleepAidsActivity();
				case 1:
					return new HomeActivity();
				case 2:
					return new AlarmsActivity();
				default:
					return new Fragment();
			}
		}

		public int getCount() {
			return 3;
		}

		public CharSequence getPageTitle(int position) {
			if (position == 0) 
				return "Sleep Aids";
			if (position == 1)
				return "Home";
			if (position == 2)
				return "Alarms";
			
			return null;
		}
	}

	@Override
	public void onBackPressed() {
		if (viewPager.getCurrentItem() == 1)
			finish();
		else
			viewPager.setCurrentItem(1);
	}
	
	
}
