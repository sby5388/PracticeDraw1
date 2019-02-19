package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {
    final private Paint paint = new Paint();

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLACK);
        Path path = new Path();
        float radius = 100f;
        float left = 100;
        float top = 100;
        float right = left + radius;
        float bottom = top + radius;
        RectF rectF = new RectF(left, top, right, bottom);
        path.addArc(rectF, 180f, 180f);
        rectF.left = rectF.right;
        rectF.right = rectF.left + radius;
        path.addArc(rectF, 180f, 180f);

        path.lineTo(rectF.left, rectF.bottom + radius);
//        path.close();
        path.lineTo(left, (bottom + top) / 2);
        canvas.drawPath(path, paint);
    }
}
