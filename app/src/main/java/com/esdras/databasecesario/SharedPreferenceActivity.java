package com.esdras.databasecesario;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SharedPreferenceActivity extends AppCompatActivity {

    private final String NAME = "NAME";
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shared_preference);
        //TODO: Criar instancia de SharedPreferences
    }

    public void saveName(View view){
        //TODO: Salvar novos dados em SharedPreferences
    }
}
