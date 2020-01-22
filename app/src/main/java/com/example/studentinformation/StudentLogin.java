package com.example.studentinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class StudentLogin extends AppCompatActivity {
    EditText e1;
    EditText e2;
    DatabaseHelper db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_login);
        db=new DatabaseHelper(this);
    }

    public void LogIn(View v){
        e1=(EditText)findViewById(R.id.editText2);
        e2=(EditText)findViewById(R.id.editText4);
        String usn=e1.getText().toString().trim();
        String psk=e2.getText().toString().trim();
        if(usn.isEmpty()) {
            e1.setError("USN can't be empty");
        }
        else if(db.checkUSN(usn)){
            e1.setError("Account doesn't exit");
        }
        else if(!db.check(usn,psk)){
            e2.setError("Password didn't match");
        }
        else {
            Intent i = new Intent(this, Student.class);
            i.putExtra("usn",usn);
            startActivity(i);
            e1.setText("");
            e2.setText("");
        }
    }
    public void SignUp(View v){
        Intent i=new Intent(this,StudentSignUp.class);
        startActivity(i);
    }
}
