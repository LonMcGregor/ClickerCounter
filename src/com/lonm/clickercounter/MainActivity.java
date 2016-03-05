package com.lonm.clickercounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	
    private TextSwitcher mSwitcher;
    private int currentCounter = 0;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        mSwitcher = (TextSwitcher) findViewById(R.id.switcher);
         mSwitcher.setFactory(mFactory);
 
        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
 
    }
 
    private ViewFactory mFactory = new ViewFactory() {
        @Override
        public View makeView() {
            TextView t = new TextView(MainActivity.this);
            t.setGravity(Gravity.CENTER_VERTICAL | Gravity.CENTER_HORIZONTAL);
            t.setTextSize(android.util.TypedValue.COMPLEX_UNIT_DIP, 100);
            return t;
        }
    };

	public void incrementCounter(View v){
		currentCounter++;
        mSwitcher.setText(String.valueOf(currentCounter));
	}
	public void decrementCounter(View v){
		currentCounter--;
        mSwitcher.setText(String.valueOf(currentCounter));
	}
	public void resetCounter(View v){
		currentCounter = 0;
        mSwitcher.setText(String.valueOf(currentCounter));
	}
}
