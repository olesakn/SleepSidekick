package com.cis368.sleepsidekick;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Alarm implements Serializable {

	private static final long serialVersionUID = 1L;
	private String 	name, date, hour, minute, sound, task, snooze;
	private boolean repeat, am, enabled;
	private ArrayList<String> days;
	
	public Alarm() {
		this.name = "";
		this.date = "1/1/2013";
		this.hour = "12";
		this.minute = "0";
		this.sound = "";
		this.task = "";
		this.snooze = "";
		this.repeat = false;
		this.am = true;
		this.days = new ArrayList<String>();
		this.enabled = true;
	}
	
	public Alarm(String name, String date, String hour, String minute, boolean am, 
				String sound, String task, String snooze, boolean repeat, ArrayList<String> days) {
		this.name = name;
		this.date = date;
		this.hour = hour;
		this.minute = minute;
		this.sound = sound;
		this.task = task;
		this.snooze = snooze;
		this.repeat = repeat;
		this.am = am;
		this.days = days;
		this.enabled = true;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getHour() {
		return hour;
	}

	public void setHour(String hour) {
		this.hour = hour;
	}

	public String getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = minute;
	}

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		this.sound = sound;
	}

	public String getTask() {
		return task;
	}

	public void setTask(String task) {
		this.task = task;
	}

	public String getSnooze() {
		return snooze;
	}

	public void setSnooze(String snooze) {
		this.snooze = snooze;
	}

	public boolean isRepeat() {
		return repeat;
	}

	public void setRepeat(boolean repeat) {
		this.repeat = repeat;
	}

	public boolean isAm() {
		return am;
	}

	public void setAm(boolean am) {
		this.am = am;
	}

	public String getDays() {
		if (days.size() > 0)
			return days.toString();
		else {
			return this.date;
		}
	}

	public void setDays(ArrayList<String> days) {
		this.days = days;
	}

	public boolean isEnabled() {
		return enabled;
	}
	
	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
}
