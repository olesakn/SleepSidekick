package com.cis368.sleepsidekick;

import java.util.Random;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;

public class TaskMathProblemActivity extends Activity {

	private TextView top, bottom;
	private EditText answer;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.task_math_problem);
		
		top = (TextView) this.findViewById(R.id.task_math_top_num);
		bottom = (TextView) this.findViewById(R.id.task_math_bottom_num);
		
		setRandomNumbers();
		
		answer = (EditText) this.findViewById(R.id.task_math_answer);
		answer.setOnEditorActionListener(new EditText.OnEditorActionListener() {
			public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
				if (event != null && actionId == EditorInfo.IME_ACTION_DONE) {
					int t = Integer.parseInt(top.getText().toString());	
					int b = Integer.parseInt(bottom.getText().toString());	
					int a = Integer.parseInt(answer.getText().toString());	
					if (t + b == a) {
						Intent i = new Intent(v.getContext(), WakeUpActivity.class);
						startActivity(i);
						finish();
						return true;	
					}
					else {
						Toast.makeText(v.getContext(), "Incorrect Answer!", Toast.LENGTH_SHORT);
						answer.setText("");
					}
				}
				return false;
			}
		}); 
	}
	
	
	private void setRandomNumbers() {
		Random r = new Random();
		int topInt = r.nextInt(200) + 20;
		int bottomInt = r.nextInt(200) + 20;
		top.setText("" + topInt);
		bottom.setText("" + bottomInt);
	}
}
