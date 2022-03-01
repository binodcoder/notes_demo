package com.binodcoder.notes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import java.util.HashSet;

public class EditYourNote extends AppCompatActivity implements TextWatcher {
    EditText editNote;
    int noteId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_your_note);
        editNote=(EditText)findViewById(R.id.editTextTextMultiLine);

        Intent i=getIntent();
        noteId=i.getIntExtra("noteId", -1);

        if(noteId != -1){
           editNote.setText(MainActivity.notes.get(noteId));
        }
        editNote.addTextChangedListener(this);

    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        MainActivity.notes.set(noteId, String.valueOf(s));
        MainActivity.arrayAdapter.notifyDataSetChanged();

        SharedPreferences sharedPreferences=this.getSharedPreferences("com.binodcoder.notes", Context.MODE_PRIVATE);
        if(MainActivity.set==null){
            MainActivity.set=new HashSet<String>();
        }else{
            MainActivity.set.clear();
        }

        MainActivity.set.clear();
        MainActivity.set.addAll(MainActivity.notes);
        sharedPreferences.edit().putStringSet("notes", MainActivity.set).apply();

    }

    @Override
    public void afterTextChanged(Editable s) {

    }
}