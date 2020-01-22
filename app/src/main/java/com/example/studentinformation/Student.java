package com.example.studentinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;

import java.util.zip.Inflater;

public class Student extends AppCompatActivity {
    TextView name,usn,phone,email,addr,branch,cgpa;
    DataBaseHelper2 db2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);
        db2=new DataBaseHelper2(this);
        func();
    }
    public void func(){
        name=(TextView)findViewById(R.id.editText2);
        usn=(TextView)findViewById(R.id.editText3);
        phone=(TextView)findViewById(R.id.editText4);
        email=(TextView)findViewById(R.id.editText5);
        addr=(TextView)findViewById(R.id.editText6);
        branch=(TextView)findViewById(R.id.editText7);
        cgpa=(TextView)findViewById(R.id.editText8);
        String Usn=getIntent().getStringExtra("usn");
        Cursor cr=db2.getRow(Usn);
        cr.moveToFirst();
        if(cr.getCount()==1){
            name.setText("true");
        }
        else {
            name.setText("false");
        }
        name.setText("Name :"+cr.getString(1));
        usn.setText("USN :"+Usn);
        email.setText("Email :"+cr.getString(2));
        phone.setText("Phone Number :"+cr.getString(3));
        addr.setText("Address :"+cr.getString(4));
        branch.setText("Branch :"+cr.getString(5));
        cgpa.setText("CGPA :"+cr.getString(6));
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.newmenu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.item1:finish();
            break;
        }
        return true;
    }
}
