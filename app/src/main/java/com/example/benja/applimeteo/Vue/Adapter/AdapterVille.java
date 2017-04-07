package com.example.benja.applimeteo.Vue.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.benja.applimeteo.Modele.City;
import com.example.benja.applimeteo.Modele.MeteoData;
import com.example.benja.applimeteo.R;

/**
 * Created by benja on 03/04/2017.
 */

public class AdapterVille extends BaseAdapter {

    LayoutInflater inflater;
    Context context;

    public AdapterVille(Context context) {
        this.context = context;
        inflater = LayoutInflater.from(context);
    }

    @Override
    public int getCount() {
        return MeteoData.getInstance().getListeVille().size();
    }

    @Override
    public City getItem(int position) {
        return MeteoData.getInstance().getListeVille().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, final View convertView, ViewGroup parent) {
        LinearLayout layout;
        if (convertView != null) {
            layout = (LinearLayout) convertView;
        } else {
            layout = (LinearLayout) inflater.inflate(R.layout.activity_listview_ville, parent, false);
        }

        TextView text = (TextView) layout.findViewById(R.id.textView_Ville);
        text.setText(getItem(position).getNomVille());

        layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, MainActivity.class);
                i.putExtra("VILLE", getItem(position));
                context.startActivity(i);
                if (context instanceof AppCompatActivity) {
                    ((AppCompatActivity) context).finish();
                }
            }
        });
        return layout;
    }
}
