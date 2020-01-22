package com.example.studentinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import java.util.regex.Pattern;

public class TeacherSignUp extends AppCompatActivity {
    EditText email,psk,rePsk,code;
    public static final String myCode="19";
    DataBaseHelper3 db3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher_sign_up);
        db3=new DataBaseHelper3(this);
    }
    public void SignUp(View v){
        email=(EditText)findViewById(R.id.editText);
        psk=(EditText)findViewById(R.id.editText3);
        rePsk=(EditText)findViewById(R.id.editText5);
        code=(EditText)findViewById(R.id.editText6);
        String Email=email.getText().toString();
        String Psk=psk.getText().toString();
        String RePsk=rePsk.getText().toString();
        String Code=code.getText().toString();
        boolean b=true;
        String regEmail="^(.+)@(.+)$";
        if(Email.equals("")){
            b=false;
            email.setError("This field can't be empty");
        }
        else if(!Pattern.matches(regEmail,Email)){
            b=false;
            email.setError("Invalid Email");
        }
        else if(db3.checkEmail(Email)){
            b=false;
            email.setError("Email already exists");
        }
        String regPsk="[0-9a-zA-Z]{6,}";
        if(Psk.equals("")){
            b=false;
            psk.setError("This field can't be empty");
        }
        else if(!Pattern.matches(regPsk,Psk)){
            b=false;
            psk.setError("Passowrd is not Strong enough");
        }
        if(RePsk.equals("")){
            b=false;
            rePsk.setError("This field can't be empty");
        }
        else if(!Psk.equals(RePsk)){
            b=false;
            rePsk.setError("Password didn't match");
        }
        if(!Code.equals(myCode)){
            b=false;
            code.setError("Invalid code");
        }
        if(b) {
            long res=db3.addUser(Email,Psk);
            /*if(res>0){
                email.setText("Success");
            }
            else{
                email.setText("failure");
            }*/
            Intent i = new Intent(this, Teacher.class);
            startActivity(i);
            finish();
        }
    }
    public void SignIn(View v){
        Intent i=new Intent(this,Teacher.class);
        startActivity(i);
    }
}
