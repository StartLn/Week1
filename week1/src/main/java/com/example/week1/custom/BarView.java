package com.example.week1.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import com.example.week1.R;

public class BarView extends View {

    //用二维数组初始化柱状图的高度和颜色
    private int[][]rect_color_height = {{Color.RED,300},{Color.GRAY,400},{Color.GREEN,250},{Color.BLUE,600},{Color.YELLOW,330},{Color.BLACK,150}};
    private final float mSpace;
    private final float mWidth;
    private Paint mPaint;

    public BarView(Context context,AttributeSet attrs) {
        super(context, attrs);
        //初始化画笔
        initPaint();
        //引用自定义的属性
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        //设置柱状图的间隔
        mSpace = typedArray.getDimension(R.styleable.CircleView_rectSpace, 35);
        //设置柱状图的宽度
        mWidth = typedArray.getDimension(R.styleable.CircleView_rectWidth, 80);

        //清除typedArray里的资源
        typedArray.recycle();
    }

    /*
    * 画笔
    * */
    private void initPaint() {
        //定义一个画笔
        mPaint = new Paint();
        //设置画笔的锯齿效果
        mPaint.setAntiAlias(true);
        //设置画笔的颜色
        mPaint.setColor(Color.BLACK);
        //设置画笔线条的粗细
        mPaint.setStrokeWidth(4);
    }

    //开始绘制
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*
        * 开始绘制，
        * 1.绘制坐标轴  刻度
        * 2.绘制 条形图
        * */

        //1.绘制X轴  drawLine画线
        canvas.drawLine(50,getHeight()-50,getWidth()-50,getHeight()-50,mPaint);

        //绘制箭头
        canvas.drawLine(getWidth()-50,getHeight()-50,getWidth()-75,getHeight()-75,mPaint);
        canvas.drawLine(getWidth()-50,getHeight()-50,getWidth()-75,getHeight()-25,mPaint);

        //1.绘制Y轴  drawLine画线
        canvas.drawLine(50,getHeight()-50,50,50,mPaint);

        //绘制箭头
        canvas.drawLine(50,50,75,75,mPaint);
        canvas.drawLine(50,50,25,75,mPaint);

        //设置画笔字体大小
        mPaint.setTextSize(15);

        //绘制刻度   drawCircle画点圆
        canvas.drawCircle(50,getHeight()-350,5,mPaint);

        canvas.drawText("300",10,getHeight()-350,mPaint);

        canvas.drawCircle(50,getHeight()-650,5,mPaint);

        canvas.drawText("600",10,getHeight()-650,mPaint);

        //        画矩形，明确左上右下

//        第一个  左：50+1*间隔+0*柱宽      右：左+柱宽    下：整体高度-50   上：整体高度-50-柱高

//        第二个 左：50+2*间隔+1*柱宽

//        第三个  左：50+3*间隔+2*柱宽

        //重新定义一个画笔
        Paint rectPaint = new Paint();

        //通过for循环设置矩形
        for (int i = 0; i <rect_color_height.length; i++) {
            //找到4个点的位置
            int bottom = getHeight()-50;

            int top = getHeight()-50-rect_color_height[i][1];

            float left = 50+mSpace*(i+1)+mWidth*i;

            float right = left+mWidth;

            //设置柱状图的颜色
            rectPaint.setColor(rect_color_height[i][0]);
            //柱状图
            canvas.drawRect(left,top,right,bottom,rectPaint);
        }
    }
}
