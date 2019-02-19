package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice11PieChartView extends View {
    final private Paint paint = new Paint();

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画饼图
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);

        float radius = 360f;
        float left = 100f;
        float top = 100f;
        float right = left + radius;
        float bottom = top + radius;
        RectF rectF = new RectF(left, top, right, bottom);
        //
        paint.setColor(Color.RED);
        canvas.drawArc(rectF, 180f, 130f, true, paint);
        //
        float move = 10f;
        rectF.right += move;
        rectF.bottom += move;
        rectF.left += move;
        rectF.top += move;
        paint.setColor(Color.YELLOW);
        canvas.drawArc(rectF, -45f, 40f, true, paint);
        //
        paint.setColor(Color.WHITE);
        canvas.drawArc(rectF, 0f, 10f, true, paint);
        //
        paint.setColor(Color.GRAY);
        canvas.drawArc(rectF, 15f, 10f, true, paint);
        //
        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 30f, 50f, true, paint);
        //
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF, 85f, 95f, true, paint);
    }
}
