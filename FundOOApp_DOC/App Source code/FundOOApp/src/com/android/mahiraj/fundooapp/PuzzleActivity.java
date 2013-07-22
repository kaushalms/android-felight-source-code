package com.android.mahiraj.fundooapp;
/**
 * @author Mahesh Rajput
 *
 */
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

public class PuzzleActivity extends Activity {
	TextView ques;
	TextView ans;
	ImageView share_puzzle;
	FundOOApplication app;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.puzzle_layout);
		app = (FundOOApplication) getApplication();
		getResourceIDs();
		Intent intent = getIntent();
		String puzzle = (String) intent.getCharSequenceExtra("puzzle");
		ques.setText(puzzle);

		int pos = intent.getIntExtra("position", 0);
		List<String> ansList = app.getPuzzleAns();
		final String answer = ansList.get(pos);
		ans.setText(answer);

		share_puzzle.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				Intent intent = new Intent(Intent.ACTION_VIEW);
				intent.setType("vnd.android-dir/mms-sms");
				intent.putExtra("sms_body", answer);
				startActivity(intent);

			}
		});

	}

	private void getResourceIDs() {
		ques = (TextView) findViewById(R.id.question);
		ans = (TextView) findViewById(R.id.answer);
		share_puzzle = (ImageView) findViewById(R.id.share_puzzle);

	}

}
