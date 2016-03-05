package com.lonm.clickercounter;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextSwitcher;
import android.widget.TextView;
import android.widget.ViewSwitcher.ViewFactory;

public class MainActivity extends Activity {
	
    private TextSwitcher mSwitcher;
    private int currentCounter;
    private boolean allowVolumeDownToDecrement = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		currentCounter = 0;
		setContentView(R.layout.activity_main);
        mSwitcher = (TextSwitcher) findViewById(R.id.switcher);
         mSwitcher.setFactory(mFactory);
 
        Animation in = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_in_left);
        Animation out = AnimationUtils.loadAnimation(this,
                android.R.anim.slide_out_right);
        mSwitcher.setInAnimation(in);
        mSwitcher.setOutAnimation(out);
        mSwitcher.setText(String.valueOf(currentCounter));
    }
	
	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putInt("currentCounter", currentCounter);
		super.onSaveInstanceState(outState);
	};
	

	@Override
	protected void onRestoreInstanceState (Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState);
		if(savedInstanceState.containsKey("currentCounter")){
			currentCounter = savedInstanceState.getInt("currentCounter");
		} else {
			currentCounter = 0;
		}
        mSwitcher.setText(String.valueOf(currentCounter));
	};
 
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
	public void toggleVolumeDown(View v){
		if(allowVolumeDownToDecrement){
			allowVolumeDownToDecrement = false;
			Button b = (Button) findViewById(R.id.buttonVolDown);
			char [] newString = getString(R.string.descriptVolDownNotAllow).toCharArray();
			b.setText(newString, 0, newString.length);
		} else {
			allowVolumeDownToDecrement = true;
			Button b = (Button) findViewById(R.id.buttonVolDown);
			char [] newString = getString(R.string.descriptVolDownAllow).toCharArray();
			b.setText(newString, 0, newString.length);
		}
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event){
	    if (keyCode == KeyEvent.KEYCODE_VOLUME_UP){
	    	incrementCounter(null);
	    	return true;
	    } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN && allowVolumeDownToDecrement){
	    	decrementCounter(null);
	    	return true;
	    } else if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN){
	    	incrementCounter(null);
	    	return true;
	    }
	    return super.onKeyDown(keyCode, event);
	}
	
}
