package com.example.myapplication.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplication.ListAdapter;
import com.example.myapplication.Class.PhoneBook;
import com.example.myapplication.R;

import java.util.ArrayList;

public class NumActivity extends AppCompatActivity {

    private ListView list_items;
    private ListAdapter mListItemsAdapter;

    private ArrayList<PhoneBook> mItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num);

        firstInit();    // 객체 초기화 및 생성

        addItem();  // 아이템 리스트 추가

        mListItemsAdapter = new ListAdapter(getApplicationContext(), mItems);  // 어뎁터 객체 생성
        list_items.setAdapter(mListItemsAdapter);   // 리스트뷰에 어뎁터 적용

        // 아이템을 클릭 했을 때 동작하는 클릭 리스너
        list_items.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), "position = " + position + ", name = " + mItems.get(position), Toast.LENGTH_SHORT).show();
            }
        });

        Button mainButton=findViewById(R.id.mainbut);
        mainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
    public void firstInit(){
        list_items = (ListView) findViewById(R.id.list_items);
        mItems = new ArrayList<>();
    }

    public void addItem(){
        mItems.add(new PhoneBook("item1", "111111111111111"));
        mItems.add(new PhoneBook("item2", "222222222222222"));
        mItems.add(new PhoneBook("item3", "333333333333333"));
        mItems.add(new PhoneBook("item4", "444444444444444"));
    }
}