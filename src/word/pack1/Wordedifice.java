package word.pack1;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.ImageView;

public class Wordedifice extends Activity {

	ImageView image1;
	ImageView image2;

	ImageView image4;

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		image1 = (ImageView) findViewById(R.id.playicon);
		image2 = (ImageView) findViewById(R.id.instructions1);

		image4 = (ImageView) findViewById(R.id.exit1);
		image1.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Wordedifice.this,
						FourthActivity.class);
				startActivity(it);
				return false;
			}
		});

		image2.setOnTouchListener(new OnTouchListener() {

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				Intent it = new Intent(Wordedifice.this,
						InstructionActivity.class);
				startActivity(it);
				return false;
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

	}

}