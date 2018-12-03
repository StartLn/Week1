package com.example.week1.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.week1.R;
import com.example.week1.custom.HeaderView;
import com.example.week1.custom.MyFloatLayout;
import com.example.week1.dao.MyDao;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class CFragment extends Fragment implements View.OnClickListener{
    private String[] data = {"流感", "咳嗽", "过敏", "发烧", "感冒", "湿疹", "便秘", "痔疮", "协和", "鼻炎", "失眠", "痛风", "上火", "脚气", "抑郁症", "性欲", "乳腺增生", "头晕", "腰痛"};
    private MyFloatLayout MyFloat_Layout;
    private MyFloatLayout mHistoryLayout;
    private HeaderView mHeadView;
    private MyDao mDao;
    private ArrayList<String> mList = new ArrayList<>();
    private ArrayList<String> mHistory = new ArrayList<>();
    private TextView mDelete;
    
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v=inflater.inflate(R.layout.fragment_c,null);
        
        mDao = new MyDao(getActivity());
        mHistory = mDao.selectName();
        initData();
        initView(v);
        if (!mHistory.isEmpty()) {
            mHistoryLayout.setData(mHistory);
        }
        return v;
    }

    private void initData() {
        mList.clear();
        for (int i = 0; i < data.length; i++) {
            mList.add(data[i]);
        }
    }

    private void initView(View v) {
        mDelete = v.findViewById(R.id.Delete_Text);
        mDelete.setOnClickListener(this);
        mDelete.setOnClickListener(this);
        MyFloat_Layout = v.findViewById(R.id.MyFloat_Layout);
        MyFloat_Layout.setData(mList);
        mHistoryLayout =v.findViewById(R.id.MyFloat_Layout_History);
        mHeadView = v.findViewById(R.id.Header_View);
        mHeadView.getmCancle().setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.Cancel_Text:
                String name = mHeadView.getEditStr().trim();
                mDao.insertSqlite(mHeadView.getEditStr().trim());
                //自己封装了一个方法删除子控件
                mHistoryLayout.removeChildView();
                mHistory.add(name);
                mHistoryLayout.setData(mHistory);
                break;
            case R.id.Delete_Text:
                mDao.delete();
                mHistory.clear();
                mHistoryLayout.removeChildView();
                break;
        }
    }
}
