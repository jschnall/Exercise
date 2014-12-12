package com.markone.exercise.activity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;
import android.widget.TextView;

import com.markone.exercise.Settings;
import com.markone.exercise.fragment.CarouselItemFragment;
import com.markone.exercise.R;


public class SliderActivity extends Activity {
    public static final String EXTRA_SHAPE = "shape";

    private int mShape;

    private TextView mTextView;
    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        handleIntent();

        SharedPreferences prefs = Settings.getSharedPreferences(this);

        mTextView = (TextView) findViewById(R.id.value);
        mSeekBar = (SeekBar) findViewById(R.id.shape);

        int percent = 0;
        switch (mShape) {
            case CarouselItemFragment.OVAL: {
                percent = prefs.getInt(Settings.OVAL_VALUE, 50);
                mSeekBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_oval));
                break;
            }
            case CarouselItemFragment.TRIANGLE: {
                percent = prefs.getInt(Settings.TRIANGLE_VALUE, 50);
                mSeekBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_triangle));
                break;
            }
            case CarouselItemFragment.RECTANGLE: {
                percent = prefs.getInt(Settings.RECT_VALUE, 50);
                mSeekBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_rect));
                break;
            }
        }

        mTextView.setText(getString(R.string.percent, percent));
        mSeekBar.setProgress(percent);

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mTextView.setText(getString(R.string.percent, i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences prefs = Settings.getSharedPreferences(this);
        switch (mShape) {
            case CarouselItemFragment.OVAL: {
                prefs.edit().putInt(Settings.OVAL_VALUE, mSeekBar.getProgress()).commit();
                break;
            }
            case CarouselItemFragment.TRIANGLE: {
                prefs.edit().putInt(Settings.TRIANGLE_VALUE, mSeekBar.getProgress()).commit();
                break;
            }
            case CarouselItemFragment.RECTANGLE: {
                prefs.edit().putInt(Settings.RECT_VALUE, mSeekBar.getProgress()).commit();
                break;
            }

        }
    }

    private void handleIntent() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        if (bundle != null) {
            mShape = bundle.getInt(EXTRA_SHAPE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        //int id = item.getItemId();

        return super.onOptionsItemSelected(item);
    }
}
