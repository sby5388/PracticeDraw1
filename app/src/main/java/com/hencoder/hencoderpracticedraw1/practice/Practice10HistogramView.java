package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * @author Administrator
 */
public class Practice10HistogramView extends View {
    final private Paint paint = new Paint();

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图;

        canvas.drawColor(Color.GRAY);
        //
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);
        float startX = 100f;
        float startY = 50f;
        float stopX = startX;
        float stopY = startY + 320f;
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        startX = stopX;
        startY = stopY;
        stopX += 500f;
        canvas.drawLine(startX, startY, stopX, stopY, paint);
        //
        paint.setTextSize(30);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawText("直方图", (startX + stopX) / 2, startY + 100, paint);
        //
        paint.setColor(Color.GREEN);
        float[] heights = {5, 20, 20, 60, 100, 120, 50};
        final float width = 50f;
        final float empty = 15f;

        float left = startX;
        final float bottom = stopY;

        RectF rectF = new RectF();
        for (float height : heights) {
            rectF.left = left + empty;
            rectF.bottom = bottom;
            rectF.top = bottom - height * 2;
            rectF.right = rectF.left + width;
            left = rectF.right;
            canvas.drawRect(rectF, paint);
        }
        //
        String[] texts = {"Froyo", "GB", "ICS", "JB", "KitKat", "L", "M"};
        paint.setColor(Color.WHITE);
        paint.setTextSize(20f);
        float startTextX = startX + empty;
        float stopTextX = startTextX + width;
        for (String text : texts) {
//            canvas.drawText(text, (startTextX + stopTextX) / 2, startY + 20, paint);
            canvas.drawText(text, startTextX, startY + 20, paint);
            startTextX += (empty + width);
            stopTextX += (empty + width);
        }

    }
}
