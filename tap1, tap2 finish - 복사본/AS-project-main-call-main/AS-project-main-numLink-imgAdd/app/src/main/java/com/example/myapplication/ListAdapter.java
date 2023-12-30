package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.myapplication.Class.PhoneBook;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<PhoneBook> mItmes;

    public ListAdapter(Context mContext, ArrayList<PhoneBook> mItmes) {
        this.mContext = mContext;
        this.mItmes = mItmes;
    }

    @Override
    public int getCount() {
        return mItmes.size();
    }

    @Override
    public Object getItem(int position) {
        return mItmes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.activity_item, parent, false);
        }

        TextView txt_name = (TextView) convertView.findViewById(R.id.txt_name);
        TextView txt_number = (TextView) convertView.findViewById(R.id.txt_number);


        PhoneBook phoneBook = mItmes.get(position);
        txt_name.setText(phoneBook.getName());
        txt_number.setText(phoneBook.getNumber());

        return convertView;
    }
}