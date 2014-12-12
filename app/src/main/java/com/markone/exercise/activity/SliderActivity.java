package com.markone.exercise.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.SeekBar;

import com.markone.exercise.fragment.CarouselItemFragment;
import com.markone.exercise.R;


public class SliderActivity extends Activity {
    public static final String EXTRA_SHAPE = "shape";

    private int mShape;

    private SeekBar mSeekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_slider);

        handleIntent();

        mSeekBar = (SeekBar) findViewById(R.id.shape);
        switch (mShape) {
            case CarouselItemFragment.OVAL:
                mSeekBar.setProgressDrawable(getDrawable(R.drawable.progress_oval));
                break;
            case CarouselItemFragment.TRIANGLE:
                mSeekBar.setProgressDrawable(getDrawable(R.drawable.progress_triangle));
                break;
            case CarouselItemFragment.RECTANGLE:
                mSeekBar.setProgressDrawable(getDrawable(R.drawable.progress_rect));
                break;

        }

        //TODO load value from shared prefs
        //mSeekBar.setValue
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
