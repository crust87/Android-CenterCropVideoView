package com.crust87.centercropvideoviewsample.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;

// 정사각 ImageView
public class SquareCenterCropImageView extends ImageView {
	
	public SquareCenterCropImageView(Context context) {
		super(context);
		setScaleType(ScaleType.CENTER_CROP);
	}

	public SquareCenterCropImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setScaleType(ScaleType.CENTER_CROP);
	}

	public SquareCenterCropImageView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		setScaleType(ScaleType.CENTER_CROP);
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int width = MeasureSpec.getSize(widthMeasureSpec);
		setMeasuredDimension(width, width);
	}
}
