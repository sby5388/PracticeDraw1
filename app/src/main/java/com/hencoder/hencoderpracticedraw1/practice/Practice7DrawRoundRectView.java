package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice7DrawRoundRectView extends View {
    final private Paint paint = new Paint();

    public Practice7DrawRoundRectView(Context context) {
        super(context);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice7DrawRoundRectView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawRoundRect() 方法画圆角矩形
        RectF rectF = new RectF(220f, 200f, 500, 350f);
        float cx = 20f;
        float cy = cx;
        paint.setColor(Color.BLACK);
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);

        canvas.drawRoundRect(rectF, cx, cy, paint);
        // TODO: 2019/10/9 temp
        drawRoundRect(canvas);
    }

    private void drawRoundRect(Canvas canvas) {
        final RectF rectF = new RectF();
        rectF.left = 100f;
        rectF.right = 300f;
        rectF.top = 100f;
        rectF.bottom = 240f;
        // TODO: 2019/10/9 参考自 https://www.jianshu.com/p/c050bee691d3
//        float rx：生成圆角的椭圆的X轴半径
//       float ry：生成圆角的椭圆的Y轴半径
        final float rx = 20f;
        final float ry = 20f;
        final Paint paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10f);
        paint.setAntiAlias(true);
        canvas.drawRoundRect(rectF, rx, ry, paint);
    }
}
