package com.example.week1.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.RotateAnimation;

import java.util.Random;

public class CustomCorona extends View implements View.OnClickListener{

    private Context mContext;
    //初始化数据，字符串数组   轮盘颜色数组
    private String[] contents=new String[]{"美 女", "女 神", "热 舞", "丰 满", "性 感", "知 性"};
    public int[] colors = new int[]{Color.parseColor("#8EE5EE"), Color.parseColor("#FFD700"), Color.parseColor("#FFD39B"), Color.parseColor("#FF8247"), Color.parseColor("#FF34B3"), Color.parseColor("#F0E68C")};
    //中心显示文字
    public String centerText="start";
    private final Paint mPaint;
    private int mWidth;
    private RotateAnimation rotateAnimation;
    private float newdul;
    public CustomCorona(Context context, AttributeSet attrs) {
        super(context, attrs);

        this.mContext=context;

        /*自定义一个画笔*/
        mPaint = new Paint();

        //本布局中的点击事件监听
        setOnClickListener(this);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //设置画笔的颜色
        mPaint.setColor(Color.GREEN);
        //画圆
        canvas.drawCircle(mWidth / 2, mWidth / 2, mWidth / 2, mPaint);
        RectF rectF = new RectF(0, 0, mWidth, mWidth);
        //再次定义画笔
        mPaint.setStyle(Paint.Style.FILL);
        //设置弧和弧的颜色
        for (int i = 0; i < colors.length; i++) {
            //设置弧的颜色为我们数组中定义的颜色
            mPaint.setColor(colors[i]);
            //弧度设置为i * 60
            int startjd = i * 60;
            canvas.drawArc(rectF, startjd, 60, true, mPaint);
        }

        //再次定义一下画笔
        mPaint.setColor(Color.WHITE);
        mPaint.setTextSize(30);
        //设置数据
        for (int i = 0; i <contents.length ; i++) {
            int startjd=i*60;
            Path path = new Path();
            //文字位置 rectF ，startjd 所占的角度  ， 60  文字所占大小
            path.addArc(rectF,startjd,60);
            //文字水平和垂直方向的偏移量
            canvas.drawTextOnPath(contents[i],path,60,60,mPaint);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
            setMeasuredDimension(600,600);
        //得到测量过后的宽和高      注意这是得到测量过后的宽和高使用getMeasuredWidth()方法
            mWidth=getMeasuredWidth();
    }

    @Override
    public void onClick(View v) {
        //Random定义一个随机数
        Random mRandom=new Random();
        //mextInt 定义随机数取0-1000之间
        int dul = mRandom.nextInt(1000);
        rotateAnimation = new RotateAnimation(newdul, dul+1000, mWidth / 2, mWidth / 2);
        rotateAnimation.setFillAfter(true);
        rotateAnimation.setDuration(3000);
        startAnimation(rotateAnimation);
        newdul=dul%360+1000;
    }
}
