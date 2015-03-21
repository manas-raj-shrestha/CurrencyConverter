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
    private String db_table3 = "country_details";
    private databasehelper ourdatabaseHelper;
    private final Context ourdbContext;
    private SQLiteDatabase ourReportsDatabase;
    private String KEY_ROWID = "row_id";
    private String KEY_COUNTRY_NAME = "country_name";
    private String KEY_COUNTRY_CODE = "country_code";
    private String KEY_RATE_VALUE = "rate_value";
    private String KEY_COUNTRY_CODE_A2 = "cc_a2";
    private String KEY_COUNTRY_CODE_A3 = "cc_a3";
    private String KEY_CURRENCY_USED = "currency_used";

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
            db.execSQL("CREATE TABLE " + db_table3 + " (" + KEY_ROWID
                    + " INTEGER PRIMARY KEY AUTOINCREMENT, " + KEY_COUNTRY_NAME
                    + " TEXT NOT NULL, " + KEY_COUNTRY_CODE_A2 + " TEXT NOT NULL, " + KEY_COUNTRY_CODE_A3 + " TEXT NOT NULL, " + KEY_CURRENCY_USED
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


    public String getCurrencyUsed(String countryCode) {
        String[] columns = new String[]{KEY_ROWID, KEY_COUNTRY_CODE, KEY_RATE_VALUE};
        Cursor c = ourReportsDatabase.query(db_table1, columns,
                "country_code like " + "'" + countryCode + "'", null, null, null, null);
        String result = "";
        c.moveToNext();
        int day1 = c.getColumnIndex(KEY_RATE_VALUE);

        result = c.getString(day1);
        return result;
    }

    public long CreateEntryForCountryTable(String countryName, String alphaCode2, String alphaCode3, String currencies) {
        // TODO Auto-generated method stub

        ContentValues cv = new ContentValues();
        cv.put(KEY_COUNTRY_NAME, countryName);
        cv.put(KEY_COUNTRY_CODE_A2, alphaCode2);
        cv.put(KEY_COUNTRY_CODE_A3, alphaCode3);
        cv.put(KEY_CURRENCY_USED, currencies);
        return ourReportsDatabase.insert(db_table3, null, cv);
    }

    public int getProfilesCount() {
        String countQuery = "SELECT  * FROM " + db_table3;

        Cursor cursor = ourReportsDatabase.rawQuery(countQuery, null);
        int cnt = cursor.getCount();
        cursor.close();
        return cnt;
    }

    public void DeleteFromTable(String TableName){
        ourReportsDatabase.execSQL("delete from '"+ TableName + "'" );
    }
    public String getDataOfCountries() {
        // TODO Auto-generated method stub

        String[] columns = new String[]{KEY_ROWID, KEY_COUNTRY_NAME,
                KEY_COUNTRY_CODE_A2,KEY_COUNTRY_CODE_A3,KEY_CURRENCY_USED};

        Cursor c = ourReportsDatabase.query(db_table3, columns, null, null,
                null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);

        int iCountry = c.getColumnIndex(KEY_COUNTRY_NAME);
        int iA2= c.getColumnIndex(KEY_COUNTRY_CODE_A2);
        int iA3 = c.getColumnIndex(KEY_COUNTRY_CODE_A3);
        int iUsed = c.getColumnIndex(KEY_CURRENCY_USED);

        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iCountry) + " "
                    + c.getString(iA2) +" "
                    + c.getString(iA3)+ " "
                    + c.getString(iUsed)+ "\n";

        }
        return result;
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

        String[] columns = new String[]{KEY_ROWID, KEY_COUNTRY_CODE,
                KEY_RATE_VALUE};
        Cursor c = ourReportsDatabase.query(db_table1, columns, null, null,
                null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iCountry = c.getColumnIndex(KEY_COUNTRY_CODE);
        int iValue = c.getColumnIndex(KEY_RATE_VALUE);


        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iCountry) + " "
                    + c.getString(iValue) + "\n";

        }
        return result;
    }

    public String getData2() {
        // TODO Auto-generated method stub

        String[] columns = new String[]{KEY_ROWID, KEY_COUNTRY_CODE,
                KEY_COUNTRY_NAME};
        Cursor c = ourReportsDatabase.query(db_table2, columns, null, null,
                null, null, null);
        String result = "";

        int iRow = c.getColumnIndex(KEY_ROWID);
        int iCountry = c.getColumnIndex(KEY_COUNTRY_CODE);
        int iValue = c.getColumnIndex(KEY_COUNTRY_NAME);


        for (c.moveToFirst(); !c.isAfterLast(); c.moveToNext()) {
            result = result + c.getString(iRow) + " " + c.getString(iCountry) + " "
                    + c.getString(iValue) + "\n";

        }
        return result;
    }

    public String getRate(String countryCode) {
        String[] columns = new String[]{KEY_ROWID, KEY_COUNTRY_CODE, KEY_RATE_VALUE};
        Cursor c = ourReportsDatabase.query(db_table1, columns,
                "country_code like " + "'" + countryCode + "'", null, null, null, null);
        String result = "";
        c.moveToNext();
        int day1 = c.getColumnIndex(KEY_RATE_VALUE);

        result = c.getString(day1);
        return result;
    }

    public String getCountryName(String countryCode) {
        String[] columns = new String[]{KEY_ROWID, KEY_COUNTRY_CODE, KEY_COUNTRY_NAME};
        Cursor c = ourReportsDatabase.query(db_table2, columns,
                null, null, null, null, null);
        String result = "";
        c.moveToNext();
        int day1 = c.getColumnIndex(KEY_COUNTRY_NAME);

        result = c.getString(day1);
        return result;
    }

    public String getCountryNamedb3(int Position) {
        String[] columns = new String[]{KEY_ROWID, KEY_COUNTRY_NAME, KEY_COUNTRY_CODE_A2};
        Cursor c = ourReportsDatabase.query(db_table3, columns, null, null,
                null, null, null);
        String result = "";
        c.moveToPosition(Position);
        int day1 = c.getColumnIndex(KEY_COUNTRY_NAME);

        result = c.getString(day1);
        return result;
    }
    public String getCountryCurrency(int Position) {
        String[] columns = new String[]{KEY_ROWID,  KEY_CURRENCY_USED};
        Cursor c = ourReportsDatabase.query(db_table3, columns, null, null,
                null, null, null);
        String result = "";
        c.moveToPosition(Position);
        int day1 = c.getColumnIndex(KEY_CURRENCY_USED);

        result = c.getString(day1);
        return result;
    }
    public String getAlphacode2(int Position) {
        String[] columns = new String[]{KEY_ROWID,  KEY_COUNTRY_CODE_A2};
        Cursor c = ourReportsDatabase.query(db_table3, columns, null, null,
                null, null, null);
        String result = "";
        c.moveToPosition(Position);
        int day1 = c.getColumnIndex(KEY_COUNTRY_CODE_A2);

        result = c.getString(day1);
        return result;
    }


}
