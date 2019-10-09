package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {
    final private Paint paint = new Paint();

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // TODO: 2019/10/9 temp
        if (true) {
            drawArc(canvas);
        }

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(2f);

        RectF rectF = new RectF(200f, 100f, 450f, 300f);


        canvas.drawArc(rectF, -170f, 45f, false, paint);
        //
        paint.setStyle(Paint.Style.FILL);
        canvas.drawArc(rectF, -120f, 110f, true, paint);
        //
        canvas.drawArc(rectF, 15f, 155f, false, paint);
    }


    private void drawArc(Canvas canvas) {
        // TODO: 2019/10/9 一个饼状图
        final Paint paint = new Paint();
        paint.setStyle(Paint.Style.FILL);
        final RectF rectF = new RectF(300f, 200f, 600f, 400f);

        paint.setColor(Color.RED);
        canvas.drawArc(rectF, -90f, 120f, true, paint);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF, 30f, 120f, true, paint);
        paint.setColor(Color.GREEN);
        canvas.drawArc(rectF, 150f, 120f, true, paint);
    }


    // TODO: 2019/10/9 绘制一个并不连接圆心的扇形

    private void tempArc(Canvas canvas) {

    }

}
