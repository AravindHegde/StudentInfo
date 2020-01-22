package com.example.studentinformation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Teacher extends AppCompatActivity {
    ListView lv;
    ArrayList<Stud> al;
    DataBaseHelper2 db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_teacher);
        db=new DataBaseHelper2(this);

        lv=(ListView)findViewById(R.id.lview);
        al=new ArrayList(db.getAll());

        MultiAdapter ma=new MultiAdapter(this,R.layout.multi_list,al);
        lv.setAdapter(ma);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Stud st = al.get(i);
                String usn = st.getUsn();
                usn = usn.substring(0, 10);
                Intent intent=new Intent(Teacher.this,display.class);
                intent.putExtra("usn",usn);
                startActivity(intent);
            }
        });
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
