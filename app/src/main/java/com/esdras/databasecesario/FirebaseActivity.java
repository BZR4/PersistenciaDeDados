package com.esdras.databasecesario;

import android.content.Intent;
import android.support.design.widget.TextInputEditText;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class FirebaseActivity extends AppCompatActivity {

    //  Declaracao de objetos necessários ao Firebase
    FirebaseDatabase database;
    DatabaseReference reference;
    TextInputEditText piada, resposta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        //  Criação de instancia
        database = FirebaseDatabase.getInstance();

        //  Definicao do [nó] principal
        reference = database.getReference("cesario");

        piada = findViewById(R.id.textInputPiada);
        resposta = findViewById(R.id.textInputResposta);

        Button button = findViewById(R.id.buttonSalvarPiada);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!validateForm(piada,resposta)){
                    return;
                }
                criarPiada(piada.getText().toString(),resposta.getText().toString());
                piada.setText("");
                resposta.setText("");
            }
        });

        Button buttonListarPiadas = findViewById(R.id.buttonListarPiadas);
        buttonListarPiadas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(), PiadaListActivity.class));
            }
        });
    }

    /**
     * Método responsável por persistir uma piada no Firebase
     * @param pergunta - String
     * @param resposta - String
     */
    public void criarPiada(String pergunta, String resposta){
        Piada piada = new Piada(pergunta,resposta);
        reference.push().setValue(piada);
        Toast.makeText(getApplicationContext(),"Piada Salva",Toast.LENGTH_SHORT).show();
    }

    /**
     * Método responsável por validar a entrada de dados no formulário.
     * @param piada - String
     * @param resposta - String
     * @return
     */
    public boolean validateForm(TextInputEditText piada, TextInputEditText resposta){
        boolean result = true;
        if (TextUtils.isEmpty(piada.getText().toString())){
            piada.setError("OOoops faltou a piada...");
            result = false;

        }else{
            piada.setError(null);
        }

        if (TextUtils.isEmpty(resposta.getText().toString())){
            resposta.setError("OOoops faltou a resposta...");
            result = false;

        }else{
            resposta.setError(null);
        }
        return result;
    }
}
