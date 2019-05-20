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

        TextView textView = findViewById(R.id.textViewPiada);

        //  Declaracao e atribuicao de objeto SharedPreferences
        SharedPreferences mSharedPreferences = getPreferences(MODE_PRIVATE);

        String nome = mSharedPreferences.getString(NAME, null);

        if (nome != null){
            textView.setText("Bem-vindo "+nome+"!");
        }else {
            textView.setText("");
        }
        editText = findViewById(R.id.editTextName);
    }

    /**
     * Método responsável por atualizar os dados do SharedPreferences, permanentemente
     * @param view
     */
    public void saveName(View view){
        SharedPreferences.Editor editor = getPreferences(MODE_PRIVATE).edit();
        editor.putString(NAME,editText.getText().toString().trim());
        editor.commit();
    }
}
