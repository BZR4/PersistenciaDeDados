package com.esdras.databasecesario;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import java.util.ArrayList;

public class SQLiteActivity extends AppCompatActivity {

    EditText mEditTextWord;
    EditText mEditTextDefinition;
    DictionaryDatabase mDB;
    ListView mListView;
    ArrayList<String> id = new ArrayList<String>(), word = new ArrayList<String>(), definition = new ArrayList<String>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sqlite);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mEditTextWord = findViewById(R.id.textInputPalavra);
        mEditTextDefinition = findViewById(R.id.textInputSignificado);
        mListView = findViewById(R.id.listViewDicionario);

        mDB = new DictionaryDatabase(this);


        mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
         @Override
         public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
             Log.d("Position/ID: ",position + " - "+id);
             Toast.makeText(SQLiteActivity.this, mDB.getDefinition(id).toString(),Toast.LENGTH_SHORT).show();
         } });

        mListView.setOnItemLongClickListener(new
             AdapterView.OnItemLongClickListener() {
                 @Override
                 public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                     Toast.makeText(SQLiteActivity.this,"Records deleted = " + mDB.deleteRecord(id),Toast.LENGTH_SHORT).show();
                     displayData();
                     return true;
                 }
             });

        displayData();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                saveRecord();
            }
        });
    }

    private void saveRecord() {
        mDB.saveRecord(mEditTextWord.getText().toString(), mEditTextDefinition.getText().toString());
        mEditTextWord.setText("");
        mEditTextDefinition.setText("");
        displayData();
    }

    public void displayData(){
        id.clear();
        word.clear();
        definition.clear();

        SQLiteDatabase db = mDB.getWritableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM dictionary",null);

        if (cursor.moveToFirst()){
            do {
                id.add(cursor.getString(cursor.getColumnIndex("_id")));
                word.add(cursor.getString(cursor.getColumnIndex("word")));
                definition.add(cursor.getString(cursor.getColumnIndex("definition")));
            }while (cursor.moveToNext());
        }

        SQLiteAdapter sqLiteAdapter = new SQLiteAdapter(SQLiteActivity.this,id,word,definition);
        sqLiteAdapter.notifyDataSetChanged();
        mListView.setAdapter(sqLiteAdapter);
        cursor.close();
    }
}
