package com.example.benja.applimeteo.Vue.Adapter;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.benja.applimeteo.AsyncTask.AsyncTaskMeteo;
import com.example.benja.applimeteo.Modele.Utils;
import com.example.benja.applimeteo.R;

/**
 * Created by benja on 03/04/2017.
 */

public class ChangeVille extends AppCompatActivity {

    EditText villeID, villeNom;
    Button okButton;
    ListView listeVillesActuelles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_ville);

        listeVillesActuelles = (ListView) findViewById(R.id.listView_ville_change);
        listeVillesActuelles.setAdapter(new AdapterVille(this));

        villeID = (EditText) findViewById(R.id.editText_ID);
        villeNom = (EditText) findViewById(R.id.editText_Ville);
        okButton = (Button) findViewById(R.id.button);

        okButton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (Utils.isConnected(ChangeVille.this)) {
                            final AsyncTaskMeteo asyncTask = new AsyncTaskMeteo();
                            asyncTask.execute(villeNom.getText().toString(), villeID.getText().toString());
                            final Handler h = new Handler();
                            h.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    if (asyncTask.getStatus() == AsyncTask.Status.FINISHED) {
                                        ((BaseAdapter) listeVillesActuelles.getAdapter()).notifyDataSetChanged();
                                    } else {
                                        h.postDelayed(this, 1000);
                                    }
                                }
                            }, 1000);
                        } else {
                            Toast.makeText(ChangeVille.this, "Pas de connexion à internet. Merci de vous connecter au réseau", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );

    }
}