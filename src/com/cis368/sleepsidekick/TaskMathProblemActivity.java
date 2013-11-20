package com.cis368.sleepsidekick;

import java.util.Random;

import junit.framework.Test;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.media.MediaPlayer.OnCompletionListener;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnKeyListener;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class TaskMathProblemActivity extends Activity {

	private TextView top, bottom;
	private EditText answer;
	private MediaPlayer mp;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_math_problem);
		
		top = (TextView) this.findViewById(R.id.task_math_top_num);
		bottom = (TextView) this.findViewById(R.id.task_math_bottom_num);
		answer = (EditText) this.findViewById(R.id.task_math_answer);
		setRandomNumbers();
		
		mp = MediaPlayer.create(this, R.raw.alarm_tone);
        mp.setOnCompletionListener(new OnCompletionListener() {
            public void onCompletion(MediaPlayer mp) {
                mp.start();
            }
        });   
        mp.start();
		
		answer.setOnKeyListener(new OnKeyListener() {
			public boolean onKey(View v, int keyCode, KeyEvent event) {
				if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_ENTER) {
					try {
						int t = Integer.parseInt(top.getText().toString());	
						int b = Integer.parseInt(bottom.getText().toString());	
						int a = Integer.parseInt(answer.getText().toString());	
						if (t + b == a) {
							mp.release();
							Intent i = new Intent(v.getContext(), WakeUpActivity.class);
							startActivity(i);
							finish();
							return false;	
						} else {
							Toast.makeText(v.getContext(), "Incorrect Answer!", Toast.LENGTH_SHORT).show();
							answer.setText(""); 
							answer.requestFocus();
							return true;
						}
					} catch (Exception e) {}
				}
				return false;
			}
		}); 
	}
	
	
	private void showKeyboard() {
		InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
	}
	
	@Override
	public void onBackPressed() {
		// do nothing (don't let user turn off alarm)
	}



	private void setRandomNumbers() {
		Random r = new Random();
		int topInt = r.nextInt(200) + 20;
		int bottomInt = r.nextInt(200) + 20;
		top.setText("" + topInt);
		bottom.setText("" + bottomInt);
	}
}
