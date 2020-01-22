package com.example.studentinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBaseHelper3 extends SQLiteOpenHelper {
    public static final String dbname="sis3.db";
    public static final String table="teacher1";
    public static final String id="id";
    public static final String email="email";
    public static final String password="password";
    DataBaseHelper3(Context c){
        super(c,dbname,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase s) {
        s.execSQL("create table teacher1(id integer primary key autoincrement,email text,password text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long addUser(String email,String psk){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("email",email);
        cv.put("password",psk);
        long res=db.insert(table,null,cv);
        db.close();
        return res;
    }
    public boolean checkEmail(String emaill){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] columns={id,password};
        String selection=email+"=?";
        String[] selectArgs={emaill};
        Cursor cursor=db.query(table,columns,selection,selectArgs,null,null,null);
        int count=cursor.getCount();
        if(count>=1){
            return true;
        }
        else{
            return false;
        }
    }
    public boolean check(String emaill,String psk){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] columns={id};
        String selection=email+"=?"+" and "+password+"=?";
        String[] selectArgs={emaill,psk};
        Cursor cursor=db.query(table,columns,selection,selectArgs,null,null,null);
        int count=cursor.getCount();
        if(count>=1){
            return true;
        }
        else{
            return false;
        }

    }
}
