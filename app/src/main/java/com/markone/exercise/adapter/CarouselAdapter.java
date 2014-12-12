package com.markone.exercise.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.markone.exercise.fragment.CarouselItemFragment;

/**
 * Created by jschnall on 12/11/14.
 */
public class CarouselAdapter extends FragmentPagerAdapter {
    private static final int PAGE_COUNT = 3;
    public CarouselAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return CarouselItemFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return PAGE_COUNT;
    }

}
