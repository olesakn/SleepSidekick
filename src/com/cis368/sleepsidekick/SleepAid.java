package com.cis368.sleepsidekick;

import java.util.ArrayList;

public class SleepAid {

	private String name, sound, date;
	private ArrayList<String> days;
	private boolean repeat, enabled;
	
	
	
	public SleepAid() {
		name = "";
		sound = "";
		date = "";
		days = new ArrayList<String>();
		repeat = false;
		enabled = true;
		
	}
	
	public SleepAid(String name,
					String date,
					String sound,
					ArrayList<String> days,
					boolean repeat) {	
		this.name = name;
		this.date = date;
		this.sound = sound;
		this.days= days;
		this.repeat = repeat;
		this.enabled= true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getDays() {
		if (days.size() > 0)
			return days.toString();
		else
			return this.date;
	}

	public void setDays(ArrayList<String> days) {
		this.days = days;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
	
}