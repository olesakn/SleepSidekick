package com.cis368.sleepsidekick;

public class SleepAid {

	private String name, sound, date;
	private int[] days;
	private boolean repeat;
	
	
	
	public SleepAid() {
		name = "";
		sound = "";
		date = "";
		days = new int[7];
		repeat = false;
		
	}
	
	public SleepAid(String name,
					String date,
					String sound,
					int[] days,
					boolean repeat) {	
		this.name = name;
		this.date = date;
		this.sound = sound;
		this.days= days;
		this.repeat = repeat;
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

	public int[] getDays() {
		return days;
	}

	public void setDays(int[] days) {
		this.days = days;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}
	
	
}