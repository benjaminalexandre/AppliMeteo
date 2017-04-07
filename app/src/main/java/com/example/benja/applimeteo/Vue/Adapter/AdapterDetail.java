package com.example.benja.applimeteo.Vue.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.benja.applimeteo.Modele.PartOfDay;
import com.example.benja.applimeteo.Modele.Prevision;
import com.example.benja.applimeteo.Modele.Utils;
import com.example.benja.applimeteo.R;

/**
 * Created by benja on 03/04/2017.
 */

public class AdapterDetail extends BaseAdapter {
    Prevision pr;
    final int layout_details = R.layout.activity_listview_details;
    LayoutInflater layoutInflater;
    Context context;

    public AdapterDetail(Context context, Prevision pr) {
        this.context = context;
        layoutInflater=LayoutInflater.from(context);
        this.pr = pr;
    }

    @Override
    public int getCount() {
        return pr.getArrayList().size();
    }

    @Override
    public PartOfDay getItem(int position) {
        return pr.getArrayList().get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LinearLayout linearLayout;
        if (convertView == null) {
            linearLayout = (LinearLayout) layoutInflater.inflate(layout_details, parent, false);
        } else {
            linearLayout = (LinearLayout) convertView;
        }
        TextView peTextView = (TextView) linearLayout.findViewById(R.id.activity_listview_details_periode);
        peTextView.setText(getItem(position).getPeriodeId().toString().toUpperCase());

        TextView tempTextView = (TextView) linearLayout.findViewById(R.id.activity_listview_details_temp);
        tempTextView.setText(getItem(position).getTemp()+"°");

        TextView ventTextView = (TextView) linearLayout.findViewById(R.id.activity_listview_details_vent);
        ventTextView.setText(getItem(position).getVent());

        TextView precTextView = (TextView) linearLayout.findViewById(R.id.activity_listview_details_prec);
        precTextView.setText(getItem(position).getPrecipitation());

        if(pr.getCurrentPeriode().getPictoId() != null) {
            ImageView icone = (ImageView) linearLayout.findViewById(R.id.activity_listview_cell_img);
            icone.setImageDrawable(Utils.getPictoFromPictoId(getItem(position).getPictoId(), context));
        }



        return linearLayout;
    }
}
