package com.example.studentinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TeacherLogin extends AppCompatActivity {
    EditText e1;
    EditText e2;
    DataBaseHelper3 db3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_login);
        db3=new DataBaseHelper3(this);
    }
    public void LogIn(View v){
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText4);
        String email=e1.getText().toString().trim();
        String psk=e2.getText().toString().trim();
        if(email.isEmpty()) {
            e1.setError("email can't be empty");
        }
        else if(!db3.checkEmail(email)){
            e1.setError("email doesn't exists");
        }
        else if(!db3.check(email,psk)){
            e2.setError("Password didn't match");
        }
        else {
            Intent i=new Intent(this,Teacher.class);
            startActivity(i);
            e1.setText("");
            e2.setText("");
        }
    }
    public void SignUp(View v){
        Intent i=new Intent(this,TeacherSignUp.class);
        startActivity(i);
    }
}
