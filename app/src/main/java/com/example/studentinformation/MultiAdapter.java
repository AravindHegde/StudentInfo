package com.example.studentinformation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

public class MultiAdapter extends ArrayAdapter<Stud> {


    private Context mContext;
    private int mResource;
    public MultiAdapter(Context context, int resource, ArrayList<Stud> usn) {
        super(context, resource,usn);
        mContext = context;
        mResource = resource;
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        String usn=getItem(position).getUsn();
        Stud stud=new Stud(usn);
        LayoutInflater inflater = LayoutInflater.from(mContext);
        convertView = inflater.inflate(mResource, parent, false);
        TextView tv=(TextView)convertView.findViewById(R.id.textView1);
        tv.setText(usn);
        return convertView;
    }
}
