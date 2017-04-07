package com.example.benja.applimeteo.Vue.Adapter;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.benja.applimeteo.Modele.City;
import com.example.benja.applimeteo.Modele.MeteoData;
import com.example.benja.applimeteo.Modele.Prevision;
import com.example.benja.applimeteo.R;

import java.util.ArrayList;

/**
 * Created by benja on 28/03/2017.
 */

public class DetailsMeteo extends AppCompatActivity {
    ListView liste;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);


        liste = (ListView) findViewById(R.id.activity_details_listview);

        Prevision pr = (Prevision) getIntent().getSerializableExtra("PREVISION");

        AdapterDetail adapterDetail = new AdapterDetail(this, pr);

        liste.setAdapter(adapterDetail);
    }

}
