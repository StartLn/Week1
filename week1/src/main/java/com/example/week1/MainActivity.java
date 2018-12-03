package com.example.week1;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.week1.adapter.MyFragmentAdapter;
import com.example.week1.fragment.AFragment;
import com.example.week1.fragment.BFragment;
import com.example.week1.fragment.CFragment;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    private ArrayList<Fragment>mFragments=new ArrayList<>();
    private ArrayList<String>mTitle=new ArrayList<>();
    private TabLayout mTablayout;
    private ViewPager mViewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //初始化视图
        initData();
        initView();
    }

    private void initData() {
        mFragments.add(new AFragment());
        mFragments.add(new BFragment());
        mFragments.add(new CFragment());
        mTitle.add("抽奖");
        mTitle.add("统计图");
        mTitle.add("热门搜索");
    }

    private void initView() {
        mTablayout = findViewById(R.id.Tab_Layout);
        mViewpager = findViewById(R.id.View_Page);
        mViewpager.setAdapter(new MyFragmentAdapter(getSupportFragmentManager(),mFragments,mTitle));
        mTablayout.setupWithViewPager(mViewpager);
    }
}
