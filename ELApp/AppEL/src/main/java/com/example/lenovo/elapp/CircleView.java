package com.example.lenovo.elapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by hxh on 2018/5/25.
 */

public class CircleView extends View implements Runnable {

    //    起始旋转角度
    private static int ROTATE = -2;
    private Paint mPaint, sweepPaint, miniPaint;
    //    扇形的线条粗度
    private static final int STROKE_WIDTH = 10;
    //    默认尺寸
    private static final int DEFAULT_SIZE = 60;
    //    大圆偏移量，防止小圆在0,90,180,270度时被遮挡只显示半个
    private static final int CIRCLE_OFF_SET = 20;
    private int width;
    private int height;
    //    大圆半径
    private int radius;
    private String text = "开始";
    //    大圆字体偏移量
    private float textOffSet;
    private TextPaint textPaint, miniTextPaint;
    private RectF rectF;
    private float sweepAngle = 0;
    private boolean isStop = false;

    public CircleView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        大圆画笔
        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        mPaint.setStyle(Paint.Style.FILL);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
        mPaint.setStrokeCap(Paint.Cap.ROUND);
        mPaint.setColor(getResources().getColor(R.color.colorAccent));

//        弧线画笔
        sweepPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        sweepPaint.setStyle(Paint.Style.STROKE);
        sweepPaint.setStrokeJoin(Paint.Join.ROUND);
        sweepPaint.setStrokeCap(Paint.Cap.ROUND);
        sweepPaint.setColor(0xffffffff);
        sweepPaint.setStrokeWidth(STROKE_WIDTH);

//        小圆画笔
        miniPaint = new Paint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG);
        miniPaint.setStyle(Paint.Style.FILL);
        miniPaint.setStrokeJoin(Paint.Join.ROUND);
        miniPaint.setStrokeCap(Paint.Cap.ROUND);
        miniPaint.setColor(0xffffffff);

//      抗锯齿，防抖动，去亚像素
        textPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        textPaint.setColor(Color.WHITE);
        textPaint.setTextAlign(Paint.Align.CENTER);

//        小圆文本画笔
        miniTextPaint = new TextPaint(Paint.ANTI_ALIAS_FLAG | Paint.DITHER_FLAG | Paint.SUBPIXEL_TEXT_FLAG);
        miniTextPaint.setColor(Color.BLUE);
        miniTextPaint.setTextAlign(Paint.Align.CENTER);
        miniTextPaint.setTextSize(15);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureHeight(int heightMeasureSpec) {

        int height = 0;
        int mode = MeasureSpec.getMode(heightMeasureSpec);
        int size = MeasureSpec.getSize(heightMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            height = size;
        } else {
            height = DEFAULT_SIZE;
            if (mode == MeasureSpec.AT_MOST) {
                height = Math.min(height, size);
            }
        }
        return height;
    }

    private int measureWidth(int widthMeasureSpec) {
        int width = 0;
        int mode = MeasureSpec.getMode(widthMeasureSpec);
        int size = MeasureSpec.getSize(widthMeasureSpec);
        if (mode == MeasureSpec.EXACTLY) {
            width = size;
        } else {
            width = DEFAULT_SIZE;
            if (mode == MeasureSpec.AT_MOST) {
                width = Math.min(width, size);
            }
        }

        return width;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        width = w;
        height = h;
        radius = w / 2;

        calcMeasure(width / 5f);

        rectF = new RectF(STROKE_WIDTH + CIRCLE_OFF_SET, STROKE_WIDTH + CIRCLE_OFF_SET, width - STROKE_WIDTH - CIRCLE_OFF_SET, height - STROKE_WIDTH - CIRCLE_OFF_SET);

        Log.d("aa", "onSizeChanged...");
    }

    private void calcMeasure(float textSize) {
        if (text != null) {
            textPaint.setTextSize(textSize);
            textOffSet = (textPaint.ascent() + textPaint.descent()) / 2;
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        背景色 测试位图显示区域
        canvas.drawColor(0xff808080);
//        实体圆
        canvas.drawCircle(width / 2, height / 2, radius - STROKE_WIDTH - CIRCLE_OFF_SET, mPaint);
//        中间文本
        canvas.drawText(text, width / 2, height / 2 + Math.abs(textOffSet), textPaint);
//        横线测试基准线
        canvas.drawLine(STROKE_WIDTH, height / 2, width - STROKE_WIDTH, height / 2, textPaint);
//        竖线测试基准线
        canvas.drawLine(width / 2, STROKE_WIDTH, width / 2, height - STROKE_WIDTH, textPaint);
//        划过的弧度
        canvas.drawArc(rectF, -90, sweepAngle, false, sweepPaint);

        canvas.save();
        canvas.translate(width / 2, height / 2);

        if (ROTATE + 2 <= 360) {
            canvas.rotate(ROTATE += 2);
            canvas.drawCircle(0, -height / 2 + STROKE_WIDTH + CIRCLE_OFF_SET, 20, miniPaint);
            drawMiniText(0, -height / 2 + STROKE_WIDTH + CIRCLE_OFF_SET, canvas, ROTATE);
        }
        canvas.restore();
    }

    private void drawMiniText(int miniX, int miniY, Canvas canvas, int rotate) {

        canvas.save();
        canvas.translate(miniX, miniY);
        canvas.rotate(-rotate);
        canvas.drawText(text, 0, Math.abs((miniTextPaint.descent() + miniTextPaint.ascent()) / 2), miniTextPaint);
        canvas.restore();
    }

    @Override
    public void run() {
        try {
            while (!isStop) {
                Thread.sleep(50);
                sweepAngle += 2;
                if (sweepAngle == 360) {
                    sweepAngle = 0;
                    ROTATE = -2;
                    text = "开始";
                    postInvalidate();
                    break;
                }
                text = ((int) sweepAngle * 100 / 360) + "%";
                postInvalidate();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}