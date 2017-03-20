package word.pack1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class FourthActivity extends Activity {
	ImageView home;
	ImageView mike;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.act4);
		home = (ImageView) findViewById(R.id.home4);
		mike = (ImageView) findViewById(R.id.mike4);
		home.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Intent it = new Intent(FourthActivity.this,
						Wordedifice.class);
				startActivity(it);
				finish();
				return false;
			}
		});
		mike.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Intent it = new Intent(FourthActivity.this, FifthActivity.class);
				startActivity(it);
				finish();
				return false;
			}
		});

	}

}
