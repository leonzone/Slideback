package com.reiser.slideback.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by reiserx on 2018/12/25.
 * desc :侧滑显示的 View
 */
public class SlideBackView extends View {
    private Path mbackgroundPath;
    private Path mArrowPath;
    private Paint mbackgroundPaint;
    private Paint mArrowPaint;

    private float mSlideWidth = 0;
    private float mMaxSlideWidth = 0;
    private float mArrowSize = 20;

    private int mBackGroundColor = Color.BLACK;
    private float mBackGroundHeight = 0;

    public SlideBackView(Context context) {
        this(context, null);
    }

    public SlideBackView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SlideBackView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mbackgroundPath = new Path();
        mArrowPath = new Path();

        mbackgroundPaint = new Paint();
        mbackgroundPaint.setAntiAlias(true);
        mbackgroundPaint.setStyle(Paint.Style.FILL_AND_STROKE);
        mbackgroundPaint.setColor(mBackGroundColor);
        mbackgroundPaint.setStrokeWidth(1);

        mArrowPaint = new Paint();
        mArrowPaint.setAntiAlias(true);
        mArrowPaint.setStyle(Paint.Style.STROKE);
        mArrowPaint.setStrokeWidth(4);
        mArrowPaint.setStrokeCap(Paint.Cap.ROUND);
        mArrowPaint.setStrokeJoin(Paint.Join.ROUND);
        setAlpha(0);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //draw background
        mbackgroundPath.reset();
        mbackgroundPath.moveTo(0, 0);
        mbackgroundPath.cubicTo(0, mBackGroundHeight * 5 / 16, mSlideWidth, mBackGroundHeight * 3 / 8, mSlideWidth, mBackGroundHeight / 2);
        mbackgroundPath.cubicTo(mSlideWidth, mBackGroundHeight * 5 / 8, 0, mBackGroundHeight * 11 / 16, 0, mBackGroundHeight);
        canvas.drawPath(mbackgroundPath, mbackgroundPaint);

        //draw arrow
        float arrowZoom = mSlideWidth / mMaxSlideWidth;
        mArrowPath.reset();
        if (arrowZoom < 0.75f) {
            mArrowPaint.setColor(Color.argb((int) (255 * 0.75), 255, 255, 255));
            mArrowPath.moveTo(mSlideWidth / 2, mBackGroundHeight / 2 - (arrowZoom * mArrowSize));
            mArrowPath.lineTo(mSlideWidth / 2, mBackGroundHeight / 2);
            mArrowPath.lineTo(mSlideWidth / 2, mBackGroundHeight / 2 + (arrowZoom * mArrowSize));
        } else {
            double angle = Math.toRadians((arrowZoom - 0.75f) * 180);
            float r = mArrowSize * 0.75f;
            mArrowPaint.setColor(Color.argb((int) (255 * arrowZoom), 255, 255, 255));
            mArrowPath.moveTo((float) (mSlideWidth / 2 + Math.sin(angle) * r), (float) (mBackGroundHeight / 2 - Math.cos(angle) * r));
            mArrowPath.lineTo(mSlideWidth / 2, mBackGroundHeight / 2);
            mArrowPath.lineTo((float) (mSlideWidth / 2 + Math.sin(angle) * r), (float) (mBackGroundHeight / 2 + Math.cos(angle) * r));
        }
        canvas.drawPath(mArrowPath, mArrowPaint);
        setAlpha(mSlideWidth / mMaxSlideWidth - 0.2f);

    }


    public void updateControlPoint(float controlX) {
        this.mSlideWidth = controlX;
        invalidate();
    }


    public float getMaxSlideWidth() {
        return mMaxSlideWidth;
    }

    public void setMaxSlideWidth(float mMaxSlideWidth) {
        this.mMaxSlideWidth = mMaxSlideWidth;
    }

    public float getBackGroundHeight() {
        return mBackGroundHeight;
    }

    public void setBackGroundHeight(float mBackGroundHeight) {
        this.mBackGroundHeight = mBackGroundHeight;
    }
}
