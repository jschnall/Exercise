package com.markone.exercise.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.ActivityOptionsCompat;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.markone.exercise.R;
import com.markone.exercise.activity.SliderActivity;

import at.technikum.mti.fancycoverflow.FancyCoverFlow;
import at.technikum.mti.fancycoverflow.FancyCoverFlowAdapter;

/**
 * Created by jschnall on 12/16/14.
 */
public class CoverFlowAdapter extends FancyCoverFlowAdapter {
    Activity mContext;

    private int[] images = {R.drawable.circle_fill, R.drawable.triangle_fill, R.drawable.rect_fill};

    public CoverFlowAdapter(Activity context) {
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
    public View getCoverFlowItem(final int i, View reuseableView, ViewGroup viewGroup) {
        ImageView imageView = null;

        if (reuseableView != null) {
            imageView = (ImageView) reuseableView;
        } else {
            imageView = new ImageView(viewGroup.getContext());
            imageView.setScaleType(ImageView.ScaleType.CENTER_INSIDE);
            imageView.setLayoutParams(new FancyCoverFlow.LayoutParams(300, 400));

        }

        imageView.setImageResource(this.getItem(i));
        imageView.setBackgroundResource(R.drawable.bg_rect);
        return imageView;

    }
}
