package com.zwt.necremote.db;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DButil {
    String TAG="DButil";
    Context context;
    public DButil(Context context){
        this.context=context;
    }


    //添加遥控器
    public void insertIR(String IRNAME, String IRKEY, String IRCODE){
        DatabaseHelper mySQLiteOpenHelper=DatabaseHelper.getInstance(context);
        SQLiteDatabase db = mySQLiteOpenHelper.getWritableDatabase();
        String sql="insert into NECIR(IRNAME,IRKEY,IRCODE) values('"+IRNAME+"','"+IRKEY+"','"+IRCODE+"')";
        db.execSQL(sql);
        Log.e(TAG, "insertIR: " );
    }

    //查询是否有这个遥控器
    public boolean insertIRjudge(String IRNAME){
        DatabaseHelper mySQLiteOpenHelper=DatabaseHelper.getInstance(context);
        SQLiteDatabase db = mySQLiteOpenHelper.getWritableDatabase();
        String sql="SELECT * FROM NECIR WHERE IRNAME="+IRNAME;
        Cursor cursor = db.rawQuery(sql, null);
        if (cursor==null){
            return false;
        }else {
            return true;
        }
    }

    //查询所有遥控器
    public String[] selectIRALL(){
        DatabaseHelper mySQLiteOpenHelper=DatabaseHelper.getInstance(context);
        SQLiteDatabase db = mySQLiteOpenHelper.getWritableDatabase();
        String sql="select distinct IRNAME from NECIR";
        Cursor cursor=db.rawQuery(sql,null);
        int IRallnumber=0;

        if (cursor!=null&&cursor.moveToFirst()){
            Log.e(TAG, "selectIRALL: " );
        }else{
            Log.e(TAG, "insertIR: null" );
            return null;
        }
        do {
            IRallnumber++;
        }while (cursor.moveToNext());
        Log.e(TAG, "selectIRALL: "+IRallnumber );
        String IRallname[]=new String[IRallnumber];
        cursor.moveToFirst();
        int i=0;
        do {
            String name=cursor.getString(cursor.getColumnIndex("IRNAME"));
            IRallname[i]=name;
            i++;
        }while (cursor.moveToNext());

        db.close();
        return IRallname;
    }
    //查询所有遥控器
    public Cursor selectIR(String IRNAME){
        DatabaseHelper mySQLiteOpenHelper=DatabaseHelper.getInstance(context);
        SQLiteDatabase db = mySQLiteOpenHelper.getWritableDatabase();
        String sql="SELECT * FROM NECIR WHERE IRNAME="+IRNAME;
        Cursor cursor=db.rawQuery(sql,null);
        int IRallnumber=0;
        if (cursor!=null&&cursor.moveToFirst()){
            Log.e(TAG, "selectIRALL: " );
        }else{
            Log.e(TAG, "insertIR: null" );
            return null;
        }
        db.close();
        return cursor;
    }

}
