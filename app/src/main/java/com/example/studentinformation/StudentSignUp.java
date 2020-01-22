package com.example.studentinformation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StudentSignUp extends AppCompatActivity {
    EditText name,usn,email,address,psk,phone,rePsk,branch,cgpa;
    DatabaseHelper db;
    DataBaseHelper2 db2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_sign_up);
        db=new DatabaseHelper(this);
        db2=new DataBaseHelper2(this);
    }
    public boolean checkUSN(String str){

        if(str.length()!=10){
            return false;
        }
        String s=str.substring(0,3);
        if(!s.equals("1MS")){
            return false;
        }
        s=str.substring(3,5);
        if(!Character.isDigit(s.charAt(0))&&!Character.isDigit(s.charAt(1))){
            return false;
        }
        s=str.substring(5,7);
        if(!Character.isLetter(s.charAt(0))&&!Character.isLetter(s.charAt(1))){
            return false;
        }
        s=str.substring(7,10);
        if(!Character.isDigit(s.charAt(0))&&!Character.isDigit(s.charAt(1))&&!!Character.isDigit(s.charAt(2))){
            return false;
        }
        return true;
    }

    public void SignUp(View v){
        name=(EditText)findViewById(R.id.editText);
        usn=(EditText)findViewById(R.id.editText5);
        email=(EditText)findViewById(R.id.editText7);
        phone=(EditText)findViewById(R.id.editText12);
        address=(EditText)findViewById(R.id.editText8);
        psk=(EditText)findViewById(R.id.editText2);
        rePsk=(EditText)findViewById(R.id.editText3);
        branch=(EditText)findViewById(R.id.editText10);
        cgpa=(EditText)findViewById(R.id.editText11);
        String Name=name.getText().toString();
        String Usn=usn.getText().toString();
        String Phone=phone.getText().toString();
        String Email=email.getText().toString();
        String Addr=address.getText().toString();
        String Psk=psk.getText().toString();
        String RePsk=rePsk.getText().toString();
        String Branch=branch.getText().toString();
        String CGPA=cgpa.getText().toString();
        boolean b=true;
        if(Name.equals("")){
            b=false;
            name.setError("This field can't be empty");
        }
        if(Usn.equals("")){
            b=false;
            usn.setError("This field can't be empty");
        }
        else if(!checkUSN(Usn)){
            b=false;
            usn.setError("Invalid USN");
        }
        else if(!db.checkUSN(Usn)){
            b=false;
            usn.setError("Usn already exists");
        }
        String regEmail="^(.+)@(.+)$";
        if(Email.equals("")){
            b=false;
            email.setError("This field can't be empty");
        }
        else if(!Pattern.matches(regEmail,Email)){
            b=false;
            email.setError("Invalid Email");
        }
        String regNumber="[6-9][0-9]{9}";
        if(Phone.equals("")){
            b=false;
            phone.setError("This field can't be empty");
        }
        else if(!Pattern.matches(regNumber,Phone)){
            b=false;
            phone.setError("Invalid Number");
        }
        if(Addr.equals("")){
            b=false;
            psk.setError("This field can't be empty");
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
        if(Branch.equals("")){
            b=false;
            branch.setError("This field can't be empty");
        }
        String cgpaReg="[0-9][.]?[0-9]*";
        if(CGPA.equals("")){
            b=false;
            cgpa.setError("This field can't be empty");
        }
        else if(!CGPA.equals("10")&&!Pattern.matches(cgpaReg,CGPA)){
            b=false;
            psk.setError("invalid CGPA");
        }
        if(b) {
            db.addUser(Usn,Psk);
            long res=db2.addUser(Name, Usn, Email,Phone, Addr, Branch, CGPA);
            Intent i=new Intent(this,Student.class);
            i.putExtra("usn",Usn);
            startActivity(i);
            finish();
        }
    }
    public void SignIn(View v){
        Intent i=new Intent(this,StudentLogin.class);
        startActivity(i);
    }
}
