package com.example.benja.applimeteo.Vue.Adapter;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListView;

import com.example.benja.applimeteo.Modele.City;
import com.example.benja.applimeteo.Modele.MeteoData;
import com.example.benja.applimeteo.R;

public class MainActivity extends AppCompatActivity {
    ListView liste;
    City city;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        liste = (ListView) findViewById(R.id.main_activity_listview);

        city = (City) getIntent().getExtras().get("VILLE");
        Adapter adapter = new Adapter(city, this);
        //Adapter adapter = new Adapter(city.getListePrevision(), this);
        MeteoData.getInstance();

        liste.setAdapter(adapter);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();


        if(id == R.id.changeVille){
            Intent i = new Intent(this, ChangeVille.class);
            startActivity(i);
            finish();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
