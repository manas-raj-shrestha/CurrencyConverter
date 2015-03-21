package com.example.manas.currencyconverter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class ListViewAdapter extends BaseAdapter {
    Context context;
    Database_Handler db;
    List<String> data;
    public ListViewAdapter(Context c) throws SQLException {
        this.context =c;
//        this.data =data;
        db = new Database_Handler(context);
        db.open();
    }


    @Override
    public int getCount() {

        return db.getProfilesCount();
    }

    @Override
    public Object getItem(int position) {
        return db.getCountryCurrency(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.items_of_listview, parent,
                false);
        ImageView iv = (ImageView) convertView.findViewById(R.id.ivforflags);
        TextView tv1 = (TextView) convertView.findViewById(R.id.countryname);

        tv1.setText(db.getCountryNamedb3(position));


        TextView tv2 = (TextView) convertView.findViewById(R.id.countrycurrency);
        tv2.setText(db.getCountryCurrency(position));

        String imgdata = (db.getAlphacode2(position)).toLowerCase();

        Picasso.with(context).load("http://www.geonames.org/flags/x/" + imgdata + ".gif").into(iv);


        return convertView;
    }
}
