package com.example.manas.currencyconverter;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.sql.SQLException;

/**
 * Created by Manas on 3/20/2015.
 */
public class NewMainActivity extends ActionBarActivity {
    ListView lvfrom;
    ListView lvto;
    Toolbar toolbar;
    ListViewAdapter adapter;
    String from = "USD", to = "USD";
    TextView fromTv, toTv,Ans;
    Database_Handler db;
    String toRate = "1", fromRate = "1";
    EditText enteredNo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_activity_main);
        toolbar = (Toolbar) findViewById(R.id.app_bar);
        setSupportActionBar(toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF")); //set the action bar title text color to white
        lvfrom = (ListView) findViewById(R.id.lvfrom);
        lvto = (ListView) findViewById(R.id.lvto);
        fromTv = (TextView) findViewById(R.id.fromTV);
        toTv = (TextView) findViewById(R.id.toTv);
        Ans = (TextView) findViewById(R.id.ans);

        enteredNo = (EditText) findViewById(R.id.userentered);
        try {
            adapter = new ListViewAdapter(this);
            lvfrom.setAdapter(adapter);
            lvto.setAdapter(adapter);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db = new Database_Handler(this);
        try {
            db.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        lvfrom.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {


                    if(enteredNo.getText().toString().isEmpty()){
                        Toast.makeText(NewMainActivity.this,"Please Enter A No",Toast.LENGTH_LONG).show();
                    }else {

                        from = db.getCountryCurrency(position);
                        fromRate = db.getRate(from);
                        fromTv.setText(from);
                        Calculations(fromRate, toRate);


                    }




            }
        });


        lvto.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                   if(enteredNo.getText().toString().isEmpty()){
                       Toast.makeText(NewMainActivity.this,"Please Enter A No",Toast.LENGTH_LONG).show();
                   }else {

                       to = db.getCountryCurrency(position);
                       toTv.setText(to);
                       toRate = db.getRate(to);
                       Calculations(fromRate, toRate);

                   }




            }
        });
    }

    public void Calculations(String from, String to) {
        Double num = Double.parseDouble(enteredNo.getText().toString());
        Double c1 = Double.parseDouble(from);
        Double c2 = Double.parseDouble(to);
        Double ratio = c2 / c1;
        Double ans = ratio * num;
        Ans.setText(""+ans);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        db.close();
    }
}
