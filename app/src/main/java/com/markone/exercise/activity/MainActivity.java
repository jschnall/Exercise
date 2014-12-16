package com.markone.exercise.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.markone.exercise.adapter.CarouselAdapter;
import com.markone.exercise.R;
import com.markone.exercise.adapter.CarouselAdapter2;
import com.markone.exercise.adapter.CoverFlowAdapter;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;

import com.touchmenotapps.carousel.simple.HorizontalCarouselLayout;
import com.touchmenotapps.carousel.simple.HorizontalCarouselLayout.CarouselInterface;
import com.touchmenotapps.carousel.simple.HorizontalCarouselStyle;

public class MainActivity extends FragmentActivity {
    //private ViewPager mPager;
    //private PagerAdapter mPagerAdapter;

    private HorizontalCarouselStyle mStyle;
    private HorizontalCarouselLayout mCarousel;
    private CarouselAdapter2 mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_main_two);
        //mAdapter = new CarouselAdapter2(this);
        //mCarousel = (HorizontalCarouselLayout) findViewById(R.id.carousel_layout);
        //mStyle = new HorizontalCarouselStyle(this, HorizontalCarouselStyle.NO_STYLE);
        //mCarousel.setStyle(mStyle);
        //mCarousel.setAdapter(mAdapter);

        //mCarousel.setOnCarouselViewChangedListener(new CarouselInterface() {
        //    @Override
        //    public void onItemChangedListener(View v, int position) {
        //    }
        //});

        setContentView(R.layout.activity_main_coverflow);
        FancyCoverFlow fancyCoverFlow = (FancyCoverFlow) findViewById(R.id.coverflow);
        fancyCoverFlow.setAdapter(new CoverFlowAdapter(this));
        fancyCoverFlow.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(MainActivity.this, SliderActivity.class);
                intent.putExtra(SliderActivity.EXTRA_SHAPE, i);

                String transitionName = getString(R.string.transition_shape);
                ActivityOptionsCompat options =
                        ActivityOptionsCompat.makeSceneTransitionAnimation(MainActivity.this,
                                view,   // The view which starts the transition
                                transitionName    // The transitionName of the view we’re transitioning to
                        );
                ActivityCompat.startActivity(MainActivity.this, intent, options.toBundle());
            }
        });

        //setContentView(R.layout.activity_main);
        //mPager = (ViewPager) findViewById(R.id.pager);
        //mPagerAdapter = new CarouselAdapter(getSupportFragmentManager());
        //mPager.setAdapter(mPagerAdapter);
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
