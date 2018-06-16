package com.esdras.databasecesario;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import android.widget.ListView;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

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

        query = FirebaseDatabase.getInstance().getReference().child("cesario").limitToLast(20);

        FirebaseListOptions listOptions = new FirebaseListOptions.Builder<Piada>()
                .setLayout(R.layout.cell_layout)
                .setQuery(query, Piada.class)
                .build();



        adapter = new FirebaseListAdapter<Piada>(listOptions) {

            @Override
            protected void populateView(View v, Piada model, int position) {
                ((TextView)v.findViewById(R.id.textViewWord)).setText(model.getPergunta());
                ((TextView)v.findViewById(R.id.textViewDefinition)).setText(model.getResposta());
                ((TextView)v.findViewById(R.id.textViewId)).setText(model.getId());
            }
        };


        listView = findViewById(R.id.listViewPiadas);
        adapter.notify();
        listView.setAdapter(adapter);
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

    class PiadaHolder extends RecyclerView.ViewHolder{

        public TextView textViewPGT, textViewRSP, textViewID;

        public Context context;

        public PiadaHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            textViewPGT = (TextView) itemView.findViewById(R.id.textInputPiada);
            textViewRSP = (TextView) itemView.findViewById(R.id.textInputResposta);
            textViewID = (TextView) itemView.findViewById(R.id.textViewId);

        }
    }
}
