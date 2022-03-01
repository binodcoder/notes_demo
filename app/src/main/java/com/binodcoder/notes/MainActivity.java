package com.binodcoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class MainActivity extends AppCompatActivity {
    ListView listView;
    static ArrayList<String> notes=new ArrayList<>();
    static ArrayAdapter arrayAdapter;
    static Set<String> set;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listView=(ListView)findViewById(R.id.list_view);
        SharedPreferences sharedPreferences=this.getSharedPreferences("com.binodcoder.notes", Context.MODE_PRIVATE);
        set=sharedPreferences.getStringSet("notes", null);
        notes.clear();
        if(set!=null){

            notes.addAll(set);
        }else{
            notes.add("Example note");
            set=new HashSet<String>();
            set.addAll(notes);
            sharedPreferences.edit().putStringSet("notes", set).apply();
        }

        notes.add("Example note");
        arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, notes);
        listView.setAdapter(arrayAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent i=new Intent(getApplicationContext(), EditYourNote.class);
                i.putExtra("noteId", position);
                startActivity(i);
            }
        });
    }
}