package com.crust87.centercropvideoviewsample.widget;

import android.content.Context;
import android.util.AttributeSet;

import com.crust87.centercropvideoview.widget.CenterCropVideoView;

/**
 * Created by mabi on 2016. 1. 15..
 */
public class SquareCenterCropVideoView extends CenterCropVideoView {

    public SquareCenterCropVideoView(Context context) {
        super(context);
    }

    public SquareCenterCropVideoView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public SquareCenterCropVideoView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    protected void onMeasure(final int widthMeasureSpec, final int heightMeasureSpec) {
        int width = MeasureSpec.getSize(widthMeasureSpec);
        setMeasuredDimension(width, width);
    }
}
