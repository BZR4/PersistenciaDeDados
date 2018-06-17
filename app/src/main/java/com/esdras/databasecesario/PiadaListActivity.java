package com.esdras.databasecesario;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.Map;

public class PiadaListActivity extends AppCompatActivity {

    ListView listView;
    FirebaseDatabase database;
    DatabaseReference reference;
    Query query;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_piada_list);

        database = FirebaseDatabase.getInstance();
        reference = database.getReference("cesario");

        query = FirebaseDatabase.getInstance().getReference().child("cesario");

        FirebaseListOptions listOptions = new FirebaseListOptions.Builder<Piada>()
                .setLayout(R.layout.cell_joke_layout)
                .setQuery(query, Piada.class)
                .build();



        adapter = new FirebaseListAdapter<Piada>(listOptions) {

            @Override
            protected void populateView(View v, Piada model, int position) {
                ((TextView)v.findViewById(R.id.textViewPiada)).setText(model.getPergunta());
                ((TextView)v.findViewById(R.id.textViewResposta)).setText(model.getResposta());
            }
        };

        listView = findViewById(R.id.listViewPiadas);

        listView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                String piada = ((TextView)view.findViewById(R.id.textViewPiada)).getText().toString();
                String resposta = ((TextView)view.findViewById(R.id.textViewResposta)).getText().toString();
                Intent i = new Intent(Intent.ACTION_SEND);
                i.setType("text/plain");
                i.putExtra(Intent.EXTRA_SUBJECT,"Piada do dia");
                i.putExtra(Intent.EXTRA_TEXT,"Piada: "+piada+"\nResposta: "+resposta);
                startActivity(Intent.createChooser(i,"Compartilhe a piada"));
                Log.d("Piada: ",piada);
                Log.d("Resposta: ",resposta);
                return false;
            }
        });
        listView.setAdapter(adapter);

        AlertDialog.Builder alert = new AlertDialog.Builder(this)
                .setTitle("Piadas Nerd")
                .setMessage("Um toque longo sobre a piada abre o compartilhamento, envie para seus amigos!")
                .setPositiveButton("Ok",null);
                alert.create().show();
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}
