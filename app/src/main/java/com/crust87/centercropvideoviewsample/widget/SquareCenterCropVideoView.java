/*
 * Android-CenterCropVideoView
 * https://github.com/crust87/Android-FFmpegExecutor
 *
 * Mabi
 * crust87@gmail.com
 * last modify 2016-01-15
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
