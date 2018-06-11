package com.esdras.databasecesario;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void doOpenActivity(View view){

        int id = view.getId();
        Intent mIntent;

        switch (id){
            case R.id.cardViewSharedPreference:
                startActivity(new Intent(getApplicationContext(), SharedPreferenceActivity.class));
                break;
            case R.id.cardViewSQLite:
                show("SQLite");
                break;
            case R.id.cardViewFirebase:
                show("Google Firebase");
                break;
            default:
                Log.d("Defaul: ","Deu ruim...");
        }
    }

    void show(String msg){
        Toast.makeText(getApplicationContext(),"Você selecionou "+msg,Toast.LENGTH_SHORT).show();
    }
}
