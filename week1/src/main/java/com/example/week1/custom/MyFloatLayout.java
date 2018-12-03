package com.example.week1.custom;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.week1.R;

import java.util.ArrayList;

public class MyFloatLayout extends LinearLayout {

    private final int mScreenWidth;
    private final int mScreenHeight;
    private String mColor;

    public MyFloatLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        //获取两种屏幕宽和高的方式
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        mScreenWidth = metrics.widthPixels;
        mScreenHeight = metrics.heightPixels;

        //设置这个布局垂直显示
        setOrientation(VERTICAL);

        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.CircleView);
        if (typedArray!=null){
            mColor = (String) typedArray.getText(R.styleable.CircleView_textColor);
        }
    }

    public void removeChildView(){
        //移除所有的子控件
            removeAllViews();
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
    }

    public void setData(ArrayList<String>data){
        LinearLayout linearLayout=getLin();
        for (int i = 0; i < data.size(); i++) {
            final String tmp = data.get(i);
            int numWidth=0;
            //得到第一行LinearLayout到底有多少子控件  方便计算宽度
            int childCount = linearLayout.getChildCount();
            //这个for循环只是计算一行LinearLayout的所有子控件的宽的和
            for (int j = 0; j <childCount ; j++) {
                //通过index得到每一个子控件
                TextView tv = (TextView) linearLayout.getChildAt(j);
                LayoutParams layoutParams= (LayoutParams) tv.getLayoutParams();
                int leftMargin = layoutParams.leftMargin;
                //测量这个tv的高和宽
                tv.measure(getMeasuredWidth(),getMeasuredHeight());
                numWidth+=tv.getMeasuredWidth()+leftMargin+tv.getPaddingLeft()+getPaddingRight();
            }
            
            TextView dataText=getText();
            dataText.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(getContext(),tmp,Toast.LENGTH_SHORT).show();
                }
            });
            //设置属性
            LayoutParams params = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
            params.leftMargin=15;
            params.topMargin=10;
            dataText.setLayoutParams(params);
            dataText.setText(tmp);
            dataText.measure(getMeasuredWidth(),getMeasuredHeight());
            int dataTextWidth=dataText.getMeasuredWidth()+dataText.getPaddingLeft()+dataText.getPaddingRight();
            //考虑到一个字符串很长 就直接超过整个屏幕的高了
            if (dataTextWidth>=mScreenWidth){
                String s = tmp.substring(0, 4);
                dataText.setText(s+"...");
                dataText.measure(getMeasuredWidth(), getMeasuredHeight());
                dataTextWidth = dataText.getMeasuredWidth();
            }
            if (mScreenWidth>=numWidth+dataTextWidth){
                linearLayout.addView(dataText);
            }else{
                //这里面对LinearLayout重新赋值  通过getLin换行
                linearLayout=getLin();
                linearLayout.addView(dataText);
            }
        }
    }


    //初始化子LinearLayout
    private LinearLayout getLin() {
        LinearLayout linearLayout = new LinearLayout(getContext());
        //LayoutParams 控制组件大小的一个工具类
        LayoutParams params=new LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.WRAP_CONTENT);
        linearLayout.setLayoutParams(params);
        //this文本对象
        this.addView(linearLayout);//只要重新添加View了自动换行了
        return linearLayout;
    }

    //初始化TextView
    private TextView getText() {
        TextView textView = new TextView(getContext());
        textView.setTextSize(20);
        textView.setTextColor(Color.parseColor(mColor));
        textView.setBackgroundResource(R.drawable.text_style);
        textView.setPadding(10,3,10,3);
        return textView;
    }
}
