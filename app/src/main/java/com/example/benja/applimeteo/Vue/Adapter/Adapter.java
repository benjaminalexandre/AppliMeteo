package com.example.benja.applimeteo.Vue.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.benja.applimeteo.Modele.City;
import com.example.benja.applimeteo.Modele.MeteoData;
import com.example.benja.applimeteo.Modele.PartOfDay;
import com.example.benja.applimeteo.Modele.PeriodeID;
import com.example.benja.applimeteo.Modele.Prevision;
import com.example.benja.applimeteo.R;
import com.example.benja.applimeteo.Modele.Utils;

import java.util.ArrayList;

/**
 * Created by benja on 28/03/2017.
 */

public class Adapter extends BaseAdapter {
    Prevision pr;
    PartOfDay pe;
    ArrayList<Prevision> listePrevision;
    final int layout_id = R.layout.activity_listview_cell;
    LayoutInflater layoutInflater;
    Context context;
    City ville;



    /*public Adapter(ArrayList<Prevision> listePrevision, Context context) {
        this.listePrevision = listePrevision;
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }*/

    public Adapter(City ville, Context context) {
        this.ville = ville;
        listePrevision = ville.getListePrevision();
        layoutInflater = LayoutInflater.from(context);
        this.context = context;
    }


    @Override
    public int getCount() {
        return listePrevision.size();
    }

    @Override
    public Prevision getItem(int position) {
        return listePrevision.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;

        if (convertView == null) {
            linearLayout = (LinearLayout) layoutInflater.inflate(layout_id, parent, false);
        } else {
            linearLayout = (LinearLayout) convertView;

        }
        pr = getItem(position);
        pe = position == 0 ? pr.getCurrentPeriode() : pr.getPeriodeFromID(PeriodeID.MATIN);

        TextView villeTextView = (TextView) linearLayout.findViewById(R.id.nomVille);
        villeTextView.setText(ville.getNomVille().toUpperCase());

        TextView tempTextView = (TextView) linearLayout.findViewById(R.id.activity_listview_cell_temp_textView);
        tempTextView.setText(pe.getTemp()+"°");

        TextView localisationTextView = (TextView) linearLayout.findViewById(R.id.activity_listview_cell_date_textView);
        localisationTextView.setText(pr.getJour());

        if(pr.getCurrentPeriode().getPictoId() != null) {
            ImageView icone = (ImageView) linearLayout.findViewById(R.id.activity_listview_cell_img);
            icone.setImageDrawable(Utils.getPictoFromPictoId(pe.getPictoId(), context));
        }

        //MeteoData.getInstance().getListeVille();

        linearLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, DetailsMeteo.class);
                intent.putExtra("PREVISION", listePrevision.get(position));
                context.startActivity(intent);
            }
        });



        return linearLayout;
    }
}
