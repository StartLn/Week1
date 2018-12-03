package com.example.week1.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import com.example.week1.R;

public class HeaderView extends LinearLayout {

    private final EditText mEditText;
    private final Button mCancle;

    public HeaderView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //1.导入布局
        LayoutInflater.from(context).inflate(R.layout.header_view,this);
        //2.寻找控件
        //输入框
        mEditText = findViewById(R.id.Search_Edit);
        //按钮
        mCancle = findViewById(R.id.Cancel_Text);
    }

    /*自定义一个方法用来获取输入框中的数据*/
    public String getEditStr(){
        return mEditText.getText().toString();
    }
    /*自定义一个方法用来获取按钮*/
    public Button getmCancle() {
        return mCancle;
    }
}
