package com.reiser.slideback;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;

import com.reiser.slideback.interfaces.SlideBackCallBack;
import com.reiser.slideback.view.SlideBackView;

/**
 * Created by reiserx on 2019/1/5.
 * desc : 测滑辅助类
 */
public class SlideBackHelper {


    private Activity mActivity;
    private SlideBackCallBack mCallBack;

    private float maxSlideWidth;

    private SlideBackView mSlideBackView;

    @SuppressLint("ClickableViewAccessibility")
    public void register(Activity activity, final SlideBackCallBack callBack) {
        this.mActivity = activity;
        this.mCallBack = callBack;
        DisplayMetrics dm = activity.getResources().getDisplayMetrics();
        float screenWidth = dm.widthPixels;
        float screenHeight = dm.heightPixels;
        maxSlideWidth = screenWidth / 13;
        mSlideBackView = new SlideBackView(activity);
        mSlideBackView.setBackGroundHeight(screenHeight / 3);
        mSlideBackView.setMaxSlideWidth(maxSlideWidth);
        FrameLayout container = (FrameLayout) activity.getWindow().getDecorView();
        container.addView(mSlideBackView);

        container.setOnTouchListener(new View.OnTouchListener() {
            private boolean isSideSlide = false;
            private float downX = 0;

            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                switch (motionEvent.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        downX = motionEvent.getRawX();
                        if (downX < maxSlideWidth) {
                            isSideSlide = true;
                        }
                        break;
                    case MotionEvent.ACTION_MOVE:
                        if (isSideSlide) {
                            float moveX = motionEvent.getRawX() - downX; // 获取X轴位移距离
                            if (Math.abs(moveX) <= maxSlideWidth * 4) {
                                // 如果位移距离在可拉动距离内，更新SlideBackIconView的当前拉动距离并重绘
                                mSlideBackView.updateControlPoint(Math.abs(moveX) / 4);
                            }
                            setBackViewY(mSlideBackView, (int) (motionEvent.getRawY()));
                        }
                        break;
                    case MotionEvent.ACTION_UP:
                        if (isSideSlide) {
                            isSideSlide = false;
                            mSlideBackView.updateControlPoint(0);
                            if (motionEvent.getRawX() >= maxSlideWidth * 4 && callBack != null) {
                                callBack.onSlideBack();
                            }
                        }
                        break;
                }
                return isSideSlide;
            }
        });
    }


    public void unRegister() {
        mActivity = null;
        mCallBack = null;
        mSlideBackView = null;
    }


    private void setBackViewY(SlideBackView view, int position) {
        int topMargin = (int) (position - (view.getBackGroundHeight() / 2));
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(view.getLayoutParams());
        layoutParams.topMargin = topMargin;
        view.setLayoutParams(layoutParams);
    }
}
