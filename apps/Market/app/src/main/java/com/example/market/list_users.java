package com.example.market;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.market.classes.connectionDB;

import java.util.ArrayList;

public class list_users extends AppCompatActivity {

    //Call Data Base class connection
    connectionDB market;
    //Create a ListView variable
    ListView userlist;
    //Create an array List variable
    ArrayList<String> listItem;
    //Create an adapter variable
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users);
        //Instance DB connection
        market = new connectionDB(this, "data",
                null, 1);
        //Create an empty array
        listItem = new ArrayList<>();
        //Call ListView id
        userlist = findViewById(R.id.idUserList);

        //List users information
        viewData();

        //Events
        userlist.setOnItemClickListener(
                new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String text = userlist.getItemAtPosition(i).toString();
                Toast.makeText(list_users.this,
                        "Info:" +text, Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void viewData() {
        Cursor cursor = market.SelectUsersData();

        if(cursor.getCount() == 0){
            Toast.makeText(this,
                    "Empty Data Base", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){

                listItem.add(cursor.getString(2));
                listItem.add(cursor.getString(3));
            }
            adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, listItem);
            userlist.setAdapter(adapter);
        }

    }
}
