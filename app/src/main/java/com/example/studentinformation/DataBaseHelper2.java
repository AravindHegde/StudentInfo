package com.example.studentinformation;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.PriorityQueue;

public class DataBaseHelper2 extends SQLiteOpenHelper {
    public static final String dbname="sis2.db";
    public static final String table="student2";

    DataBaseHelper2(Context c){
        super(c,dbname,null,1);

    }
    @Override
    public void onCreate(SQLiteDatabase s) {
        s.execSQL("create table student2(id integer primary key autoincrement,usn text,name text,email text,phone text,address text,branch text,cgpa text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
    public long addUser(String name,String usn,String email,String phone,String address,String branch,String cgpa){
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues cv=new ContentValues();
        cv.put("usn",usn);
        cv.put("name",name);
        cv.put("email",email);
        cv.put("phone",phone);
        cv.put("address",address);
        cv.put("branch",branch);
        cv.put("cgpa",cgpa);
        long res=db.insert(table,null,cv);
        db.close();
        return res;
    }
    public Cursor getRow(String usn){
        SQLiteDatabase db=this.getReadableDatabase();
        String[] columns={"id","name","email","phone","address","branch","cgpa"};
        String selection="usn"+"=?";
        String[] selectArgs={usn};
        Cursor cursor=db.query(table,columns,selection,selectArgs,null,null,null);
        return cursor;
    }
    public ArrayList<Stud> getAll(){
        PriorityQueue<String>pq=new PriorityQueue<String>();
        pq.clear();
        String ans="";
        SQLiteDatabase db=this.getReadableDatabase();
        String[] columns={"usn","name"};
        String selection="";
        String[] selectArgs={};
        Cursor cursor=db.query(table,columns,selection,selectArgs,null,null,null);
        if(cursor!=null) {
            cursor.moveToFirst();
            do {
                ans=cursor.getString(0);
                ans=ans+"("+cursor.getString(1)+")";
                pq.add(ans);
            } while(cursor.moveToNext());
        }
        ArrayList<Stud>a1=new ArrayList<Stud>();
        a1.clear();
        while(!pq.isEmpty()){
            a1.add(new Stud(pq.remove()));
        }
        return a1;
    }
}
