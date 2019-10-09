package com.by5388.demo.paint.canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;

/**
 * @author Administrator  on 2019/10/9.
 */
public class PieChartView extends View {
    public static final String TAG = PieChartView.class.getSimpleName();

    public interface PieData {
        String getName();

        float getValue();
    }

    private final Paint mPaint = new Paint();
    // TODO: 2019/10/9 如何让组件自适应 并自动放置到父容器的中间位置呢
    private final RectF mRectF = new RectF(100f, 100f, 500f, 500f);

    private List<PieData> mDataList = new ArrayList<>();


    public PieChartView(Context context) {
        super(context);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    public void setDataList(List<PieData> dataList) {
        mDataList = dataList;
        // TODO: 2019/10/9 需要在这里重新绘制UI
        invalidate();
    }

    private Exception mException = new Exception();

    @Override
    protected void onDraw(Canvas canvas) {
        //提示：建议不要在onDraw()中创建任何对象
        super.onDraw(canvas);
//        Log.e(TAG, "onDraw: ", mException);

        //todo 获取父容器的宽和高
        final int width = getWidth();
        final int height = getHeight();
        // TODO: 2019/10/9 设置成为一个圆形的,不知道有没有什么比较好的办法
        int radius = width;
        if (width > height) {
            radius = height;
        }
        final int padding = 16;
        radius = (int) (radius / 2) - padding;
//        mRectF.bottom = height - padding - Math.abs((int) (height - radius) / 2);
//        mRectF.top = padding + Math.abs((int) (height - radius) / 2);
//        mRectF.left = padding + Math.abs((int) (width - radius) / 2);
//        mRectF.right = width - padding - Math.abs((int) (width - radius) / 2);

        mRectF.bottom = (height / 2) + radius;
        mRectF.top = (height / 2) - radius;
        mRectF.left = (width / 2) + radius;
        mRectF.right = (width / 2) - radius;

        mPaint.setColor(Color.GREEN);
        mPaint.setAntiAlias(true);
//        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStyle(Paint.Style.FILL);
//        mPaint.setStrokeWidth(5f);

        canvas.drawArc(mRectF, 0f, 180, true, mPaint);


        if (true){
            return;
        }
        // TODO: 2019/10/9
        drawPie(canvas);
        mPaint.setColor(Color.RED);
        canvas.drawArc(mRectF, 0, 360f, false, mPaint);

    }

    private void drawPie(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        double sum = 0.0;
        for (PieData data : mDataList) {
            sum += data.getValue();
        }

        float startAngle = 0.0f;

        for (PieData data : mDataList) {
            final double next = data.getValue();
            Log.d(TAG, "drawPie: next = " + next);
            final float swapAngle = (float) (360 * (next / sum));
            Log.d(TAG, "drawPie: swapAngle = " + swapAngle);
            final int randomColor = getRandomColor();
            Log.d(TAG, "drawPie: randomColor = " + randomColor);
            mPaint.setColor(randomColor);
            canvas.drawArc(mRectF, startAngle, swapAngle, true, mPaint);
            startAngle += swapAngle;
        }

        // canvas.drawArc(mRectF, startAngle, 120f, true, mPaint);
        canvas.drawLine(100, 100, 300, 300, mPaint);
    }

    int temp = 0;

    /**
     * 生成随机颜色
     */
    private int getRandomColor() {
        switch (temp) {
            case 0:
                temp++;
                return Color.BLUE;
            case 1:
                temp++;
                return Color.BLACK;
            case 2:
                temp++;
                return Color.RED;
            case 3:
                temp++;
                return Color.GREEN;
            default:
                break;
        }

        final int red = (int) (Math.random() * 255);
        final int green = (int) (Math.random() * 255);
        final int blue = (int) (Math.random() * 255);
        final int color = 0xff000000 | red << 16 | green << 8 | blue;
        return color;
    }


    private void temp() {
        // TODO: 2019/10/9 如何获取父容器的大小
        // TODO: 2019/10/9 父容器的大小，又是由谁来确定的呢
    }
}
