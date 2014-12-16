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

    private static final int WIDTH = 50;
    private static final int HEIGHT = 100;

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

        int segmentHeight = 0;
        switch (mShape) {
            case CarouselItemFragment.OVAL: {
                segmentHeight = prefs.getInt(Settings.OVAL_VALUE, 50);
                mSeekBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_oval));
                break;
            }
            case CarouselItemFragment.TRIANGLE: {
                segmentHeight = prefs.getInt(Settings.TRIANGLE_VALUE, 50);
                mSeekBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_triangle));
                break;
            }
            case CarouselItemFragment.RECTANGLE: {
                segmentHeight = prefs.getInt(Settings.RECT_VALUE, 50);
                mSeekBar.setProgressDrawable(getResources().getDrawable(R.drawable.progress_rect));
                break;
            }
        }
        mSeekBar.setProgress(segmentHeight);
        double percent = calcPercent(segmentHeight);
        mTextView.setText(getString(R.string.percent, percent));

        mSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                double percent = calcPercent(i);
                mTextView.setText(getString(R.string.percent, percent));
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


    private double calcPercent(int segmentHeight) {
        switch (mShape) {
            case CarouselItemFragment.OVAL: {
                // http://www.had2know.com/academics/ellipse-segment-tank-volume-calculator.html
                // (AB/4)[arccos(1 - 2h/A) - (1 - 2h/A)sqrt(4h/A - 4h^2/A^2)]
                // where arccos is in radians, B is width, A is height, and h is segment height
                double area = (int) (Math.PI / 4 * WIDTH * HEIGHT);
                double segmentArea = (HEIGHT * WIDTH / 4) *
                        (Math.acos(1d - 2d * segmentHeight / HEIGHT) - (1d - 2d * segmentHeight / HEIGHT) *
                                Math.sqrt(4d * segmentHeight / HEIGHT - 4d * segmentHeight * segmentHeight / (HEIGHT * HEIGHT)));

                return segmentArea / area * 100;
            }
            case CarouselItemFragment.TRIANGLE: {
                // w * h / 2
                double area = WIDTH * HEIGHT / 2; // 2500
                double segmentWidth = segmentHeight / 2; // Will always be same 2:1 ratio
                double segmentArea = segmentWidth * segmentHeight / 2;

                return segmentArea / area * 100;
            }
            case CarouselItemFragment.RECTANGLE: {
                // w * h (Would be more strictly correct to account for rounded corners, but would need to know their radius)
                double area = WIDTH * HEIGHT; // 5000
                double segmentArea = WIDTH * segmentHeight;

                return segmentArea / area * 100;
            }
        }

        return 0;
    }
}
