package com.example.benja.applimeteo.Vue.Adapter;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.example.benja.applimeteo.AsyncTask.AsyncTaskMeteo;
import com.example.benja.applimeteo.Modele.MeteoData;
import com.example.benja.applimeteo.Modele.Utils;
import com.example.benja.applimeteo.R;

/**
 * Created by benja on 28/03/2017.
 */

public class SplashScreen extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        if (Utils.isConnected(SplashScreen.this)) {
            AsyncTaskMeteo asyncTask = new AsyncTaskMeteo();
            asyncTask.execute("Bourg-en-Bresse", "49");
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    intent.putExtra("VILLE", MeteoData.getInstance().getListeVille().get(0));
                    startActivity(intent);
                    SplashScreen.this.finish();
                }
            }, 5000);
        }else {
            Toast.makeText(SplashScreen.this, "Pas de connexion à internet. Merci de vous connecter au réseau", Toast.LENGTH_LONG).show();
        }
    }
}
