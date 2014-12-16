package com.markone.exercise.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.markone.exercise.R;
import com.markone.exercise.fragment.CarouselItemFragment;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;

/**
 * Created by jschnall on 12/11/14.
 */
public class CarouselAdapter2 extends BaseAdapter {
    Context mContext;
    private int[] images = {R.drawable.circle_fill, R.drawable.triangle_fill, R.drawable.rect_fill};

    public CarouselAdapter2(Context context) {
        mContext = context;
    }

    @Override
    public int getCount() {
        return images.length;
    }

    @Override
    public Integer getItem(int i) {
        return images[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup viewGroup) {
        ImageView imageView= new ImageView(mContext);
        imageView.setImageResource(images[position]);
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        imageView.setPadding(16, 16, 16, 16);
        imageView.setBackgroundResource(R.drawable.bg_rect);
        return imageView;
    }}
