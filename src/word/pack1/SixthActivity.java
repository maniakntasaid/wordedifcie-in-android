package word.pack1;


import java.io.IOException;

import java.util.Locale;

import android.app.Activity;

import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;


public class SixthActivity extends Activity implements OnInitListener {

	ImageView image61;
	Button button62;
	Button button63;
	private TextToSpeech tts;
	private Button btnSpeak;
	Dbhandler myDbHelper;
	SQLiteDatabase db;
	String temp[];
	String word;
	TextView meaning;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act6);
		tts = new TextToSpeech(this, this);
		TextView title = (TextView) findViewById(R.id.text6);
		meaning = (TextView) findViewById(R.id.text5);
		Bundle b = getIntent().getExtras();
		word = b.getString("word");
		title.setText(word);
		System.out.println("vijay %%%%%%%%%%%  " + word);
		btnSpeak = (Button) findViewById(R.id.button1);

		this.myDbHelper = new Dbhandler(this);
		FetchingData();
		db = myDbHelper.getReadableDatabase();
		Cursor c = db.rawQuery(
				"select * from words where words='" + word + "'", null);
		c.moveToFirst();
		if (c != null) {
			do {
				int c1 = c.getColumnIndex("Des");
				String mean = c.getString(c1);

				meaning.setText(mean);

			} while (c.moveToNext());
		}
		btnSpeak.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View arg0) {
				speakOut();
			}

		});

	}

	@Override
	public void onDestroy() {
		// Don't forget to shutdown tts!
		if (tts != null) {
			tts.stop();
			tts.shutdown();
		}
		super.onDestroy();
	}

	private void FetchingData() {
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
	public void onInit(int status) {
		// TODO Auto-generated method stub
		if (status == TextToSpeech.SUCCESS) {

			int result = tts.setLanguage(Locale.US);

			if (result == TextToSpeech.LANG_MISSING_DATA
					|| result == TextToSpeech.LANG_NOT_SUPPORTED) {
				Log.e("TTS", "This Language is not supported");
			} else {
				btnSpeak.setEnabled(true);
				speakOut();
			}

		} else {
			Log.e("TTS", "Initilization Failed!");
		}

	}

	private void speakOut() {
		// TODO Auto-generated method stub
		String text = meaning.getText().toString();

		tts.speak(word + "MEANS :" + text, TextToSpeech.QUEUE_FLUSH, null);
	}

}
