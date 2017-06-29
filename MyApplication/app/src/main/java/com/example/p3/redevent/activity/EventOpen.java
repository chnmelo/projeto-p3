package com.example.p3.redevent.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.p3.redevent.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class EventOpen extends AppCompatActivity {
    TextView title;
    TextView autor;
    TextView dt_ini;
    TextView dt_fim;
    TextView descr;
    TextView parti;
    Button btn_pert;
    String participantes;
    Bundle args;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_open);
        title = (TextView) findViewById(R.id.titulo);
        autor = (TextView) findViewById(R.id.autor);
        dt_ini = (TextView) findViewById(R.id.dt_inicio);
        dt_fim = (TextView) findViewById(R.id.dt_final);
        descr = (TextView) findViewById(R.id.descr);
        parti = (TextView) findViewById(R.id.txt_part);
        btn_pert = (Button) findViewById(R.id.btn_part);
        args = getIntent().getBundleExtra("events");
        final DatabaseReference evetnosRef = FirebaseDatabase.getInstance().getReferenceFromUrl("https://redevent-6cfe4.firebaseio.com/eventos"+args.getString("Id")+"/");
        if (args != null){
            title.setText(args.getString("Titulo"));
            autor.setText(args.getString("Autor"));
            dt_ini.setText(args.getString("Data Inicial"));
            dt_fim.setText(args.getString("Data Final"));
            descr.setText(args.getString("Descrição"));
            parti.setText(args.getString("Participantes").split(";").length);

        }
        btn_pert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                participantes = args.getString("Participantes");

                DatabaseReference novoRegistro = evetnosRef.push();
                if(!args.getString("Autor").equals(args.getString("User"))){
                    evetnosRef.setValue(participantes+";"+args.getString("User"));
                    //novoRegistro.child("participantes").setValue(participantes.add(args.getString("User")));
                }
                else{
                    Toast.makeText(EventOpen.this, "Você é autor do evento.",Toast.LENGTH_LONG).show();}
            }
        });
    }
}
