package com.example.manas.currencyconverter;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.sql.SQLException;

/**
 * Created by Manas on 3/16/2015.
 */
public class Database_Handler extends Activity {
    private String db_name = "currency";
    private static final int DATABASE_VERSION = 1;
    private String db_table1 = "currency_offline_table";
    private String db_table2 = "code_country_table";
    private databasehelper ourdatabaseHelper;
    private final Context ourdbContext;
    private SQLiteDatabase ourReportsDatabase;
    private String KEY_ROWID = "row_id";
    private String KEY_COUNTRY_NAME = "country_name";
    private String KEY_COUNTRY_CODE = "country_code";
    private String KEY_RATE_VALUE = "rate_value";
    private class databasehelper extends SQLiteOpenHelper {

        public databasehelper(Context context) {
            super(context, db_name, null, DATABASE_VERSION);
            // TODO Auto-generated constructor stub

        }

        public void onCreate(SQLiteDatabase db) {
            // TODO Auto-generated method stub
            db.execSQL("CREATE TABLE " + db_table1 + " (" + KEY_ROWID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_COUNTRY_CODE
                    + " TEXT NOT NULL, " + KEY_RATE_VALUE
                    + " TEXT NOT NULL);");
            db.execSQL("CREATE TABLE " + db_table2 + " (" + KEY_ROWID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_COUNTRY_CODE
                    + " TEXT NOT NULL, " + KEY_COUNTRY_NAME
                    + " TEXT NOT NULL);");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
            // TODO Auto-generated method stub
            db.execSQL("DROP TABLE IF EXISTS " + db_table1);
            onCreate(db);
        }

    }

    public Database_Handler(Context c) {
        ourdbContext = c;
    }


    public Database_Handler() {
        ourdbContext = getApplicationContext();
    }

    public Database_Handler open() throws SQLException {
        ourdatabaseHelper = new databasehelper(ourdbContext);
        ourReportsDatabase = ourdatabaseHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        ourdatabaseHelper.close();

    }


    public long CreateEntry(String countrycode, String rate) {
        // TODO Auto-generated method stub

        ContentValues cv = new ContentValues();
        cv.put(KEY_COUNTRY_CODE, countrycode);
        cv.put(KEY_RATE_VALUE, rate);

        return ourReportsDatabase.insert(db_table1, null, cv);
    }

    public long CreateEntrydb2(String countrycode, String countryname) {
        // TODO Auto-generated method stub

        ContentValues cv = new ContentValues();
        cv.put(KEY_COUNTRY_CODE, countrycode);
        cv.put(KEY_COUNTRY_NAME, countryname);

        return ourReportsDatabase.insert(db_table2, null, cv);
    }

    public String getData() {
        // TODO Auto-generated method stub

        String[] columns = new String[] { KEY_ROWID, KEY_COUNTRY_CODE,
                KEY_RATE_VALUE};
        Cursor c = ourReportsDatabase.query(db_table1, columns, null, null,
                null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iCountry = c.getColumnIndex(KEY_COUNTRY_CODE);
        int iValue = c.getColumnIndex(KEY_RATE_VALUE);


        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iCountry) + " "
                    + c.getString(iValue) +"\n";

        }
        return result;
    }
    public String getData2() {
        // TODO Auto-generated method stub

        String[] columns = new String[] { KEY_ROWID, KEY_COUNTRY_CODE,
                KEY_COUNTRY_NAME};
        Cursor c = ourReportsDatabase.query(db_table2, columns, null, null,
                null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iCountry = c.getColumnIndex(KEY_COUNTRY_CODE);
        int iValue = c.getColumnIndex(KEY_COUNTRY_NAME);


        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iCountry) + " "
                    + c.getString(iValue) +"\n";

        }
        return result;
    }

    public String getRate(String countryCode) {
        String[] columns = new String[] { KEY_ROWID, KEY_COUNTRY_CODE, KEY_RATE_VALUE };
        Cursor c = ourReportsDatabase.query(db_table1, columns,
                "country_code like " + "'"+countryCode+"'", null, null, null, null);
        String result = "";
        c.moveToNext();
        int day1 = c.getColumnIndex(KEY_RATE_VALUE);

        result = c.getString(day1);
        return result;
    }

    public String getCountryName(String countryCode) {
        String[] columns = new String[] { KEY_ROWID, KEY_COUNTRY_CODE, KEY_COUNTRY_NAME };
        Cursor c = ourReportsDatabase.query(db_table2, columns,
                "country_code like " + "'"+countryCode+"'", null, null, null, null);
        String result = "";
        c.moveToNext();
        int day1 = c.getColumnIndex(KEY_COUNTRY_NAME);

        result = c.getString(day1);
        return result;
    }
}
