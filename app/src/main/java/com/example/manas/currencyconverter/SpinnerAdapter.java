package com.example.manas.currencyconverter;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Manas on 3/19/2015.
 */
public class SpinnerAdapter extends BaseAdapter {
   List<String> data = new ArrayList<>();
Context context;
    SpinnerAdapter(List<String> data,Context context){
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return data.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater= (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.items_for_spinner, parent,
                false);
        ImageView iv = (ImageView) convertView.findViewById(R.id.ivforflags);
        TextView tv = (TextView) convertView.findViewById(R.id.nameofflags);
        String Countryname = data.get(position);
        tv.setText(Countryname);
        String upToNCharacters = (Countryname.substring(0, Math.min(Countryname.length(), 2))).toLowerCase();

        Picasso.with(context).load("http://www.geonames.org/flags/x/" + upToNCharacters + ".gif").into(iv);
        return convertView;
    }
}
