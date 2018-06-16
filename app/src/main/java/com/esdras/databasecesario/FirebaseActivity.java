package com.esdras.databasecesario;

import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseActivity extends AppCompatActivity {

    FirebaseDatabase database;
    DatabaseReference reference;
    TextInputEditText piada, resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("cesario");

        piada = findViewById(R.id.textInputPiada);
        resposta = findViewById(R.id.textInputResposta);

        Button button = findViewById(R.id.buttonSalvarPiada);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                criarPiada(piada.getText().toString(),resposta.getText().toString());
            }
        });
    }

    public void criarPiada(String pergunta, String resposta){
        Piada piada = new Piada(pergunta,resposta);
        reference.push().setValue(piada);
    }
}
