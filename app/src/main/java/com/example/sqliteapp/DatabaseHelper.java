package com.example.sqliteapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static  final  String DATABASE_NAME="student.db";
    public static  final  String TABLE_NAME="student_table";
    public static  final  String COL_1="ID";
    public static  final  String COL_2="NAME";
    public static  final  String COL_3="SURNAME";
    public static  final  String COL_4="MARKS";



    public DatabaseHelper(@Nullable Context context) {
            super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
    db.execSQL("create table "+TABLE_NAME+"(ID INTEGER primary key autoincrement,NAME TEXT,SURNAME TEXT ,MARKS INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name,String surname,String marks) // this method is used to store data in table
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put(COL_2,name);
        contentValues.put(COL_3,surname);
        contentValues.put(COL_4,marks);
        long result= db.insert(TABLE_NAME,null,contentValues);
        if(result==-1)
            return false;
        else
            return true;
    }
    public Cursor getAllData() //This interface provides random read-write access to the result set returned  by a database query.

    {
        SQLiteDatabase db=this.getWritableDatabase();
        Cursor res=db.rawQuery(" select * from "+TABLE_NAME,null);
        return  res;
    }
}