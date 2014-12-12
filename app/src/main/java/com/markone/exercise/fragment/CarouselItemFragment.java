package com.markone.exercise.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.markone.exercise.activity.SliderActivity;
import com.markone.exercise.R;

/**
 * Created by jschnall on 12/11/14.
 */
public class CarouselItemFragment extends Fragment {
    public static final int OVAL = 0;
    public static final int TRIANGLE = 1;
    public static final int RECTANGLE = 2;

    private static final String ARG_SHAPE = "shape";

    private int mShape;

    private View mLayout;
    private ImageView mShapeView;

    public static CarouselItemFragment newInstance(int shape) {
        CarouselItemFragment fragment = new CarouselItemFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_SHAPE, shape);
        fragment.setArguments(args);
        return fragment;
    }

    public CarouselItemFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setHasOptionsMenu(true);

        Bundle bundle = getArguments();
        if (bundle != null) {
            mShape = bundle.getInt(ARG_SHAPE);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mLayout = (ViewGroup) inflater.inflate(R.layout.fragment_carousel_item, container, false);

        mShapeView = (ImageView) mLayout.findViewById(R.id.shape);
        switch (mShape) {
            case OVAL:
                mShapeView.setImageResource(R.drawable.circle_fill);
                break;
            case TRIANGLE:
                mShapeView.setImageResource(R.drawable.triangle_fill);
                break;
            case RECTANGLE:
                mShapeView.setImageResource(R.drawable.rect_fill);
                break;

        }
        mShapeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent launchIntent = new Intent(getActivity(), SliderActivity.class);
                launchIntent.putExtra(SliderActivity.EXTRA_SHAPE, mShape);
                startActivity(launchIntent);
            }
        });

        return mLayout;
    }
}
