package com.example.contactmanagerapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import java.util.ArrayList;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "students.db";
    public static final int DATABASE_VERSION = 1;

    public DataBaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table student_table (id integer primary key autoincrement,name text , lastName text , email text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists student_table");
        onCreate(db);

    }

    //CURD -> insert data into table :
    public boolean insertData(String name , String lastName , String email)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name" ,  name);
        contentValues.put("lastName" , lastName);
        contentValues.put("email" , email);
        long result = db.insert("student_table" , null , contentValues);
        if(result==-1)
        {
            return false;
        }
        else
        {
            return true;
        }

    }


    // Read data
    public ArrayList getAllRecords()
    {
        ArrayList arrayList = new ArrayList();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor res = db.rawQuery("select * from student_table" , null);
        res.moveToFirst();
        while(res.isAfterLast()==false)
        {
            String t1 = res.getString(0);
            String t2 = res.getString(1);
            String t3 = res.getString(2);
            String t4 = res.getString(3);

            arrayList.add( "ID :"+ t1+" \n"+"NAME :"+ t2 +"\n"+"lastNAME :"+ t3 +" \n"+"EMAIL:"+t4);
            res.moveToNext();

        }
        return arrayList;
    }

    //update data
    public boolean updateData(String id ,String name , String lastName , String email )
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues =  new ContentValues();
        contentValues.put("name" ,  name);
        contentValues.put("lastName" , lastName);
        contentValues.put("email" , email);
        db.update("student_table" , contentValues,"id=?" , new String[]{id});
        return true;
    }

    // delete data
    public Integer delete(String id)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("student_table" , "id=?" , new String[]{id});

    }








}
