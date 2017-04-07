package com.example.benja.applimeteo.Modele;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v4.content.ContextCompat;

import com.example.benja.applimeteo.R;

/**
 * Created by benja on 28/03/2017.
 */

public class Utils {
    public static Drawable getPictoFromPictoId(String pictoId, Context context) {
        Drawable icone = null;

        if (pictoId.equals("soleil")) {
            icone = ContextCompat.getDrawable(context, R.drawable.ic_soleil);
        } else if(pictoId.equals("voile")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_voile);
        }else if(pictoId.equals("nuageux")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_nuageux);
        }else if(pictoId.equals("couvert")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_couvert);
        }else if(pictoId.equals("brouillard")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_brouillard);
        }else if(pictoId.equals("brouillardgivrant")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_brouillard);
        }else if(pictoId.equals("neifefaible")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_neigefaible);
        }else if(pictoId.equals("neigemoderer")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_neigefaible);
        }else if(pictoId.equals("neigeforte")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_neigeforte);
        }else if(pictoId.equals("pluiefaible")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_pluiefaible);
        }else if(pictoId.equals("pluiemoderer")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_pluiefaible);
        }else if(pictoId.equals("lune")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_lune);
        }else if(pictoId.equals("lunevoile")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_lunenuageux);
        }else if(pictoId.equals("lunenuageux")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_lunenuageux);
        }else if(pictoId.equals("luneaverse")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_lunenuageux);
        }else if(pictoId.equals("luneaverseneige")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_neigefaible);
        }else if(pictoId.equals("pluieforte")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_rain);
        }else if(pictoId.equals("verglas")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_thermometer);
        }else if(pictoId.equals("averse")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_pluiefaible);
        }else if(pictoId.equals("averseneige")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_neigefaible);
        }else if(pictoId.equals("orageloc")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_storm);
        }else if(pictoId.equals("oragefort")){
            icone = ContextCompat.getDrawable(context, R.drawable.ic_storm_fort);
        }
        return icone;
    }


    public static boolean isConnected(Context context) {
        ConnectivityManager cm = (ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        return isConnected;
    }
}
