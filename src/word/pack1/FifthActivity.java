package word.pack1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.RecognizerIntent;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.widget.TextView.BufferType;

public class FifthActivity extends Activity {

	Dbhandler myDbHelper;
	String displayedwords = null;
	String userword = null, sword;
	private static final int REQUESTCODE_VOICE = 0;
	ImageView mike, home, image4;
	SQLiteDatabase db;
	EditText word, syword;
	ArrayList<String> al = new ArrayList<String>();
	int i;
	Button show;
	Button cancel, first;
	
	private ArrayList<String> matches;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act5);
		mike = (ImageView) findViewById(R.id.mike5);
		home = (ImageView) findViewById(R.id.home5);
		show = (Button) findViewById(R.id.record5);
		first = (Button) findViewById(R.id.button1);
		image4 = (ImageView) findViewById(R.id.imageView1);
		word = (EditText) findViewById(R.id.scorevalue5);
		syword = (EditText) findViewById(R.id.scorevalue4);
		mike.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				startVoiceRecognitionActivity();
				PackageManager pm = getPackageManager();
				List<ResolveInfo> activities = pm
						.queryIntentActivities(new Intent(
								RecognizerIntent.ACTION_RECOGNIZE_SPEECH), 0);
				if (activities.size() == 0) {
					show.setEnabled(false);
				}
				return false;
			}
		});
		home.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Intent it = new Intent(FifthActivity.this,
						Wordedifice.class);
				startActivity(it);
				return false;
			}
		});
		syword.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent it = new Intent(FifthActivity.this, SixthActivity.class);
				it.putExtra("word", sword);
				startActivity(it);
			}
		});
		show.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userword = word.getText().toString();

				if (displayedwords == null)
					displayedwords = "'" + userword + "'";
				else
					displayedwords = displayedwords + ",'" + userword + "'";

				i = userword.length();
				//Toast.makeText(getApplicationContext(), userword, 200).show();
				String us = userword.substring(i - 1, i);
				//Toast.makeText(getApplicationContext(), us, 90).show();
				FifthActivity.this.myDbHelper = new Dbhandler(
						FifthActivity.this);
				FetchingData();
				System.out
						.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
				db = myDbHelper.getReadableDatabase();
				System.out.println(displayedwords);
				sword = FifthActivity.this.myDbHelper.GetNextWord(db, us,
						displayedwords);
				if (sword == "") {
					syword.setText(sword);
					Toast.makeText(getApplicationContext(), "No Word",Toast.LENGTH_SHORT)
							.show();
				}

				else {
					syword.setText(sword);
					Toast.makeText(getApplicationContext(), sword,Toast.LENGTH_SHORT).show();
					displayedwords = displayedwords + ",'" + sword + "'";
				}
			}
		});
		first.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				userword = word.getText().toString();

				if (displayedwords == null)
					displayedwords = "'" + userword + "'";
				else
					displayedwords = displayedwords + ",'" + userword + "'";

				i = userword.length();
				//Toast.makeText(getApplicationContext(), userword, 200).show();
				userword = userword.trim();
				String us = userword.substring(0, 1);
				//Toast.makeText(getApplicationContext(), us, 90).show();
				FifthActivity.this.myDbHelper = new Dbhandler(
						FifthActivity.this);
				FetchingData();
				System.out
						.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
				db = myDbHelper.getReadableDatabase();
				System.out.println(displayedwords);
				sword = FifthActivity.this.myDbHelper.GetNextWord(db, us,
						displayedwords);
				if (sword == "") {
					syword.setText(sword);
					Toast.makeText(getApplicationContext(), "No Word",Toast.LENGTH_SHORT)
							.show();
				}

				else {
					syword.setText(sword);
					Toast.makeText(getApplicationContext(), sword,Toast.LENGTH_SHORT).show();
					displayedwords = displayedwords + ",'" + sword + "'";
				}
			}
		});
		image4.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				moveTaskToBack(true);
				return false;
			}
		});
		Button bt = (Button) findViewById(R.id.button2);
		bt.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				userword = word.getText().toString();

				if (displayedwords == null)
					displayedwords = "'" + userword + "'";
				else
					displayedwords = displayedwords + ",'" + userword + "'";

				i = userword.length();
				FifthActivity.this.myDbHelper = new Dbhandler(
						FifthActivity.this);
				FetchingData();
				System.out
						.println(" $$$$$$$$$$$$$$$$$$$$$$$ fetchdata completed @@@@@@@@@@@@@@@@@@@@@");
				db = myDbHelper.getReadableDatabase();
				System.out.println(displayedwords);
				sword = FifthActivity.this.myDbHelper.GetWord(db, userword,
						displayedwords);
				if (sword.contentEquals(userword)) {

					displayedwords = displayedwords + ",'" + userword + "'";
					Intent it = new Intent(FifthActivity.this,
							SixthActivity.class);
					it.putExtra("word", userword);
					startActivity(it);

				}

				else {
					Toast.makeText(getApplicationContext(),"Sorry No Data Found",Toast.LENGTH_SHORT).show();

				}
			}

		});

	}

	protected void FetchingData() {
		// TODO Auto-generated method stub
		try {

			myDbHelper.onCreateDataBase();

		} catch (IOException ioe) {

			throw new Error("Unable to create database");

		}
		try {

			myDbHelper.openDataBase();
			db = myDbHelper.getWritableDatabase();
			System.out.println("executed");

		} catch (SQLException sqle) {

			throw sqle;

		}
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {

		if (requestCode == 10 && resultCode == 20) {
			ArrayList<String> al2 = data.getStringArrayListExtra("ARRAYLIST");
			this.al = al2;
		}

		if (requestCode == REQUESTCODE_VOICE && resultCode == RESULT_OK) {
			matches = data
					.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);

			if (matches != null) {
				word.setText(matches.get(0), BufferType.EDITABLE);
			}
		}
	}

	protected void startVoiceRecognitionActivity() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
		intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
				RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
		intent.putExtra(RecognizerIntent.EXTRA_PROMPT,
				"Voice recognition Demo...");
		startActivityForResult(intent, REQUESTCODE_VOICE);
	}

}
