package com.jackwang.colortracklayout.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;

import com.jackwang.colortracklayout.R;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatTextView;

/**
 * 姓名: YaYaG
 * 时间： 2020-06-03
 * 描述：
 */
public class ClipTextView extends AppCompatTextView {
    private Paint mOriginPaint;
    private Paint mChangePaint;
    private int mChangeColor;
    private int mOriginColor;
    private float currentProgress = 0.0f;
    private Direction mDirection = Direction.LEFT_TO_RIGHT;

    public enum Direction {
        LEFT_TO_RIGHT, RIGHT_TO_LEFT
    }

    public ClipTextView(Context context) {
        this(context, null);
    }

    public ClipTextView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipTextView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (attrs != null) {
            TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.ClipTextView);

            mChangeColor = typedArray.getColor(R.styleable.ClipTextView_changeColor, Color.RED);
            mOriginColor = typedArray.getColor(R.styleable.ClipTextView_originColor, Color.BLACK);
        }
        mOriginPaint = paint(mOriginColor);
        mChangePaint = paint(mChangeColor);
    }


    private Paint paint(int color) {
        Paint paint = new Paint();
        paint.setTextSize(getTextSize());
        paint.setColor(color);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        drawText(canvas);
    }


    private void drawText(Canvas canvas) {
        if (getText() == null) {
            return;
        }
        int currentWidth = (int) (currentProgress * getWidth());
        canvas.save();
        Rect rect = new Rect(0, 0, currentWidth, getHeight());
        canvas.clipRect(rect);

        Paint.FontMetricsInt fontMetricsInt = mChangePaint.getFontMetricsInt();
        int dy = (fontMetricsInt.bottom - fontMetricsInt.top) / 2 - fontMetricsInt.bottom;
        int baseLine = getHeight() / 2 + dy;

        canvas.drawText(getText().toString(), 0, baseLine, mDirection == Direction.RIGHT_TO_LEFT ?
                mOriginPaint : mChangePaint);
        canvas.restore();

        canvas.save();
        //右部分的颜色
        Rect originReact =
                new Rect((int) (currentProgress * getWidth()),
                        0, getWidth(), getHeight());
        canvas.clipRect(originReact);
        canvas.drawText(getText().toString(), 0, baseLine,
                mDirection == Direction.RIGHT_TO_LEFT ?
                        mChangePaint : mOriginPaint);
        canvas.restore();
    }

    public synchronized void setCurrentProgress(float currentProgress) {
        this.currentProgress = currentProgress;
        invalidate();
    }

    public void setChangeColor(int changeColor) {
        mChangeColor = changeColor;
        mChangePaint.setColor(changeColor);
    }

    public void setOriginColor(int originColor) {
        mOriginColor = originColor;
        mOriginPaint.setColor(originColor);
    }

    public float getCurrentProgress() {
        return currentProgress;
    }

    public void setDirection(Direction direction) {
        mDirection = direction;
    }
}
