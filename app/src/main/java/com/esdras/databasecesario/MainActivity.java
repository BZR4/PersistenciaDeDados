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

    /** Método responsável por gerenciar a seleção da atividade selecionada, note que temos um menu
     * de opções e iremos capturar o toque na tela e abrir a Activity
     * selecionada.
     *
     * @param view
     */
    public void doOpenActivity(View view){

        //  Obtem o ID(R.id.selecionado)
        int id = view.getId();
        Intent mIntent;

        //  Conforme o ID, uma Activity é inicializada
        switch (id){
            case R.id.cardViewSharedPreference:
                startActivity(new Intent(getApplicationContext(), SharedPreferenceActivity.class));
                break;
            case R.id.cardViewSQLite:
                startActivity(new Intent(getApplicationContext(), SQLiteActivity.class));
                break;
            case R.id.cardViewFirebase:
                startActivity(new Intent(getApplicationContext(), FirebaseActivity.class));
                break;
            case R.id.cardViewCarrapicho:
                startActivity(new Intent(getApplicationContext(), PiadaListActivity.class));
                break;
            default:
                Log.d("Defaul: ","Deu ruim...");
        }
    }

    //  Mensagem para testes iniciais, não utilizada no momento...
    void show(String msg){
        Toast.makeText(getApplicationContext(),"Você selecionou "+msg,Toast.LENGTH_SHORT).show();
    }
}
