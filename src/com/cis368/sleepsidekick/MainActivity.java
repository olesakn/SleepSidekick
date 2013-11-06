package com.cis368.sleepsidekick;
import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.app.ActionBar.Tab;
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
	

	/*********************************************** 
	 * On Create
	 **********************************************/
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home);
		
		final ActionBar actionBar = getActionBar();

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


	public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// I don't know if we need this one or not
	}


	public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
		// Don't use this one
	}

	/*******************************************************
	 * Sections Page Adapter
	 * returns a fragment corresponding to one of the tabs
	 ******************************************************/
	public class SectionsPagerAdapter extends FragmentPagerAdapter {

		public SectionsPagerAdapter(FragmentManager fm) {
			super(fm);
		}

		public Fragment getItem(int position) {
			// getItem is called to instantiate the fragment for the given page.
			// Return a SectionFragment with the page number as its lone argument.
			Fragment fragment = new HomeActivity();
			Bundle args = new Bundle();
			args.putInt(HomeActivity.ARG_SECTION_NUMBER, position + 1);
			fragment.setArguments(args);
			return fragment;
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
}
