package com.example.studentinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class display extends AppCompatActivity {
    TextView name,usn,phone,email,addr,branch,cgpa;
    DataBaseHelper2 db2;
    final int code=1;
    String ph="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);
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
        ph=cr.getString(3);


    }
    public void Send(View v){
        //String ph=phone.getText().toString().trim();
        EditText msg=(EditText) findViewById(R.id.tv);
        String Msg=msg.getText().toString();
        Log.i("Hello", "Send: " + ph);
        if(Msg.equals("")){
            msg.setError("This field can't be empty");
        }else {
            if (checkPermission(Manifest.permission.SEND_SMS) ) {
            } else {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.SEND_SMS}, code);
            }
            if (checkPermission(Manifest.permission.SEND_SMS)) {
                SmsManager sms = SmsManager.getDefault();
                try {
                    sms.sendTextMessage( ph, null, Msg, null, null);
                }catch(Exception e){

                    Log.i("Hello", "Send: " + e.getMessage() );

            }
                Toast.makeText(this, "Message sent", Toast.LENGTH_LONG).show();

            } else {
                Toast.makeText(this, "Permisiion denied", Toast.LENGTH_LONG).show();

            }
        }
    }
    public boolean checkPermission(String permission){
        int check= ContextCompat.checkSelfPermission(this,permission);
        return (check== PackageManager.PERMISSION_GRANTED);
    }
}
