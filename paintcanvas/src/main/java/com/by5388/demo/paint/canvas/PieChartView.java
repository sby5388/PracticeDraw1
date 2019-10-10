package com.by5388.demo.paint.canvas;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
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
    private final RectF mRectF = new RectF(100f, 100f, 300f, 300f);

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

    @Override
    protected void onDraw(Canvas canvas) {
        //提示：建议不要在onDraw()中创建任何对象
        super.onDraw(canvas);

        //todo 获取父容器的宽和高
        final int width = getWidth();
        final int height = getHeight();
        // TODO: 2019/10/9 设置成为一个圆形的,不知道有没有什么比较好的办法
        int radius = width;
        if (width > height) {
            radius = height;
        }
        final int padding = 30;
        radius = (radius / 2) - padding;

        // TODO: 2019/10/10 一直没有绘制出来：原因是 left 跟 right的值 弄错了
        mRectF.bottom = (height / 2) + radius;
        mRectF.top = (height / 2) - radius;
        mRectF.left = (width / 2) - radius;
        mRectF.right = (width / 2) + radius;

        mPaint.setColor(getRandomColor());
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5f);
        canvas.drawArc(mRectF, 0f, 360f, true, mPaint);
        // TODO: 2019/10/9
        drawPie(canvas);
    }

    private void drawPie(Canvas canvas) {
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeWidth(10);
        double sum = 0.0;
        for (PieData data : mDataList) {
            sum += data.getValue();
        }

        float startAngle = 180.0f;

        for (PieData data : mDataList) {
            final double next = data.getValue();
            Log.d(TAG, "drawPie: next = " + next);
            final float swapAngle = (float) (360 * (next / sum));
            Log.d(TAG, "drawPie: swapAngle = " + swapAngle);
            final int randomColor = getRandomColor();
            mPaint.setColor(randomColor);
            canvas.drawArc(mRectF, startAngle, swapAngle, true, mPaint);
            startAngle += swapAngle;
        }
    }

    /**
     * 生成随机颜色
     * 函数来自ApiDemo
     */
    private static int getRandomColor() {
        final int red = (int) (Math.random() * 255);
        final int green = (int) (Math.random() * 255);
        final int blue = (int) (Math.random() * 255);
        final int color = 0xff000000 | red << 16 | green << 8 | blue;
        return color;
    }


    float mAngle = 90f;

    private void startCustomAnimation() {
        // TODO: 2019/10/10 自定义的动画 参考 https://blog.csdn.net/qq_40881680/article/details/
        //旋转90度
        final ObjectAnimator animator = ObjectAnimator.ofFloat(this, "rotation", mAngle);
        animator.setDuration(300);
        animator.start();
        mAngle += 90f;
        // TODO: 2019/10/10 如何实现 再次点击时 继续旋转度数呢

    }

    public void startAnimator() {
        post()
    }

    public void stopAnimator(){

    }


}
