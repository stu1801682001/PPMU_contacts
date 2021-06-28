package com.example.contacts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
    DBHelper dbHelper;
    RecyclerView recyclerView;
    ContactAdapter contactAdapter;
    ArrayList<String> ids, names, numbers;
    FloatingActionButton createNewContactButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ids = new ArrayList<>();
        names = new ArrayList<>();
        numbers = new ArrayList<>();
        dbHelper = new DBHelper(MainActivity.this);
        recyclerView = findViewById(R.id.recycler_view);
        createNewContactButton = findViewById(R.id.create_new_contact_button);

        createNewContactButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });

        storeDataInArrays();
        Collections.sort(names);
        contactAdapter = new ContactAdapter(MainActivity.this, ids, names, numbers);
        recyclerView.setAdapter(contactAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
    }

    void storeDataInArrays() {
        Cursor cursor = dbHelper.getAllContacts();

        if (cursor.getCount() == 0) {
            Toast.makeText(this, "Add contacts", Toast.LENGTH_SHORT).show();
        }
        else {
            while (cursor.moveToNext()) {
                ids.add(cursor.getString(0));
                names.add(cursor.getString(1));
                numbers.add(cursor.getString(2));
            }
        }
    }
}