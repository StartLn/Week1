package com.example.week1.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;

import java.util.ArrayList;

public class MyDao {
    private Context mContext;
    private final MyOpenHelper helper;
    private final SQLiteDatabase mData;

    public MyDao(Context mContext) {
        this.mContext = mContext;
        helper = new MyOpenHelper(mContext);
        mData = helper.getWritableDatabase();
    }

    public void insertSqlite(String name) {
        ContentValues values = new ContentValues();
        values.put("name",name);
        mData.insert("liu",null,values);
        //Toast.makeText(mContext,"插入成功",Toast.LENGTH_SHORT).show();
    }

    public ArrayList<String> selectName() {
        ArrayList<String>list  = new ArrayList<>();
        Cursor cursor = mData.query("liu", null, null, null, null, null, null, null);
        while (cursor.moveToNext()){
            String name = cursor.getString(cursor.getColumnIndex("name"));
            list.add(name);
        }
        return list;
    }

    public void delete() {
        mData.execSQL("delete from liu");
    }
}
