package com.example.studentinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String dbname="sis.db";
    public static final String table="student1";
    public static final String id="id";
    public static final String username="username";
    public static final String password="password";
    DatabaseHelper(Context context){
        super(context,dbname,null,1);
    }


    @Override
    public void onCreate(SQLiteDatabase s) {
        s.execSQL("create table student1(id integer primary key autoincrement,username text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase s, int i, int i1) {

    }
    public void addUser(String usr,String psk){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("username",usr);
        cv.put("password",psk);
        long res=db.insert(table,null,cv);
        db.close();
    }
    public boolean checkUSN(String usn){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] columns={id,password};
        String selection=username+"=?";
        String[] selectArgs={usn};
        Cursor cursor=db.query(table,columns,selection,selectArgs,null,null,null);
        int count=cursor.getCount();
        if(count>=1){
            return false;
        }
        else{
            return true;
        }
    }
    public boolean check(String usn,String psk){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] columns={id};
        String selection=username+"=?"+" and "+password+"=?";
        String[] selectArgs={usn,psk};
        Cursor cursor=db.query(table,columns,selection,selectArgs,null,null,null);
        int count=cursor.getCount();
        if(count==0){
            return false;
        }
        else{
            return true;
        }

    }
}
